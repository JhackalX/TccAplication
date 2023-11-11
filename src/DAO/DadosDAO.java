/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import Object.Dados;
import java.util.ArrayList;
/**
 *
 * @author Aires
 */
public class DadosDAO {
    
    private CtrlDao ctrlDao;

    
    public DadosDAO(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }
        
    public void gravarDado(Connection conexaoBase,String idEstacao, String dataMedicao, String peridoMedicao, String valorMedicao, String nomeSensor)  {
        try {
            Statement sttm =  conexaoBase.createStatement();
            if (existeDado(conexaoBase, idEstacao, dataMedicao, peridoMedicao, nomeSensor)){
                System.out.println("Dado ja existe!");
            } else {
                //System.out.println("INSERT INTO tb_dados_medidos (id_estacao, data_medicao, periodo_medicao, valor, sensor) VALUES (" + idEstacao + ",'" + dataMedicao + "'," + peridoMedicao + "," + valorMedicao + ",'" + nomeSensor + "')");
                sttm.executeUpdate("INSERT INTO tb_dados_medidos (id_estacao, data_medicao, periodo_medicao, valor, sensor) VALUES (" + idEstacao + ",'" + dataMedicao + "'," + peridoMedicao + "," + valorMedicao + ",'" + nomeSensor + "')");
            }
        }
        catch (SQLException e ){
            System.out.println("Erro ao gravar dado. Mensagem: " + e.getMessage());
        }
    }
    
    public boolean existeDado(Connection conexaoBase, String idEstacao, String dataMedicao, String periodoMedicao, String nomeSensor) throws SQLException {
        Statement sttm = conexaoBase.createStatement();
        ResultSet resultado;
        resultado = sttm.executeQuery("BEGIN TRANSACTION;"
                + "SELECT id FROM tb_dados_medidos WHERE id_estacao = " + idEstacao + " AND periodo_medicao = " + periodoMedicao + " AND data_medicao LIKE '" + dataMedicao + "' AND sensor LIKE '" + nomeSensor + "'"
                        + "END TRANSACTION;");
        
        return resultado.next();
    }
    
    public int getIdDado (Connection conexaoBase, String idEstacao, String dataMedicao, String periodoMedicao, String nomeSensor) throws SQLException {
        Statement sttm = conexaoBase.createStatement();
        ResultSet resultado;
        
        try {
            resultado = sttm.executeQuery("SELECT id FROM tb_dados_medidos WHERE id_estacao = " + idEstacao + " AND periodo_medicao = " + periodoMedicao + " AND data_medicao LIKE '" + dataMedicao + "' AND sensor LIKE '" + nomeSensor + "'");
            
            if (!resultado.next()) {
                System.out.println("Dado nao cadastrado no sistema");
                return 0;
            }
            
            return Integer.parseInt(resultado.getString("id"));
            
        } catch (SQLException e) {
            System.out.println("Erro ao consultado dado. Mensagem: " + e.getMessage());
            return -1;
        }
        
        
    }
    
    public List<Dados> listaDadosEstacaoAno (Connection conexaoBase, String codigoEstacao, String ano){
        try {
            List<Dados> lista = new ArrayList<Dados>();
            Dados novo;
            Statement sttm =  conexaoBase.createStatement();
            ResultSet resultado =  sttm.executeQuery("SELECT d.id, d.data_medicao, d.periodo_medicao, d.valor FROM tb_dados_medidos as d LEFT JOIN tb_estacao AS e ON d.id_estacao = e.id WHERE data_medicao LIKE '%/%/" + ano + "' AND e.codigo LIKE '" + codigoEstacao + "'");
            
            if (!resultado.next()){
                System.out.println("Dados nao encontrados.");
                return null;
            }
            
            resultado.first();
            
            do{
                novo = new Dados();
                novo.setId(resultado.getString("id"));
                novo.setDataBR(resultado.getString("data_medicao"));
                novo.setPeriodo(Integer.parseInt(resultado.getString("periodo_medicao")));
                novo.setValor(Float.parseFloat(resultado.getString("valor")));
                novo.setSensor(resultado.getString("sensor"));
                lista.add(novo);
            }while(resultado.next());
            
            return lista;
        }catch (SQLException e ){
            System.out.println("Erro ao listar dados (Estacao/ano). Mensagem: " + e.getMessage());
            return null;
        }
    }
    
    public void gravarListDados(Connection conexaoBase, List<Dados> lista, String idEstacao){
        try {
            
            
            String dataMedicao, nomeSensor,valorMedicao,peridoMedicao, idDado;
            
            String sttmInsert =  new String("begin transaction;");
            String sttmInsertArray[];
            Statement sttm =  conexaoBase.createStatement();
            
            
            System.out.println("Tamanho Array: " + String.valueOf(lista.size()));
            
            
            for (int i =0; i < lista.size(); i ++) {
                
                //sttm.executeUpdate("PRAGMA main.wal_checkpoint(FULL)");
                dataMedicao = lista.get(i).getDataBr();
                peridoMedicao = String.valueOf(lista.get(i).getPeriodo());
                nomeSensor = lista.get(i).getSensor();
                valorMedicao = lista.get(i).getValor();
                idDado = lista.get(i).getId();

//                if (existeDado(conexaoBase, idEstacao, dataMedicao, peridoMedicao, nomeSensor)){
//                    System.out.println("Dado ja existe!");
//                } else {
//                    sttmInsert = sttmInsert + ("INSERT INTO tb_dados_medidos (id_estacao, data_medicao, periodo_medicao, valor, sensor) VALUES (" + idEstacao + ",'" + dataMedicao + "'," + peridoMedicao + "," + valorMedicao + ",'" + nomeSensor + "');\n");
//                }
                
                sttmInsert = sttmInsert + ("INSERT INTO tb_dados_medidos (id_estacao, data_medicao, periodo_medicao, valor, sensor, id) VALUES (" + idEstacao + ",'" + dataMedicao + "'," + peridoMedicao + "," + valorMedicao + ",'" + nomeSensor + "' ,'"+ idDado + " ');\n");
                
                
                if (((i % 1000 == 0) || (i == lista.size() - 1) ) && (!sttmInsert.isEmpty())) { 
                    sttmInsert = sttmInsert + ("END TRANSACTION;");
                    
                    try {
                        
                        sttm.executeLargeUpdate(sttmInsert);
                        
                        
                        
                    } catch (SQLException e){
                        System.out.println("Erro ao inserir grupo de dados. Tentando inserts individais.");
                        sttm.executeUpdate("END TRANSACTION;");
                        
                        sttmInsertArray = sttmInsert.split(";");
                        
                        for (int j = 0; j < sttmInsertArray.length ; j++){
                            try {
                                sttm.executeUpdate(sttmInsertArray[j]);
                            } catch (SQLException ex) {
                                System.out.println("Erro ao gravar dado individual. Mensagem: "+ ex.getMessage());
                            }
                            
                            
                        }
                        
                        
                    }
                        
                    
                    sttmInsert = "begin transaction;";
                    System.out.println("Iteracao: " + String.valueOf(i));
                    
                }
                
                
                
            }
            
            
        }
        catch (SQLException e ){
            System.out.println("Erro ao gravar dado. Mensagem: " + e.getMessage());
        }
    }
    
}
