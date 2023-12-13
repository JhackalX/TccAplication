/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Object.Estacao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aires
 */
public class EstacaoDAO {

    private CtrlDao ctrlDao;
    
    public EstacaoDAO(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }
    
        
    public String gravarEstacaoConvencional (Connection conexaoBase, String idEstacao,String nomeEstacao, String codigoEstacao, String latitude, String Longitude, String altitude, String periodicidade)   {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataCadastro = dateFormat.format(new Date());
                   
            if (existeEstacao(conexaoBase, codigoEstacao)){
                System.out.println("Estacao ja cadastrada.");
                return "0";
            }
            
            Statement sttm = conexaoBase.createStatement();
            
            sttm.executeUpdate("INSERT INTO tb_estacao(id,nome,codigo,latitude,longitude,altitude,data_cadastro,periodicidade) VALUES('"+ idEstacao + "','" +nomeEstacao + "','" + codigoEstacao + "'," + latitude + "," + Longitude + "," + altitude+ ",'" + dataCadastro + "','" + periodicidade +"');");
            System.out.println("INSERT INTO tb_estacao(id,nome,codigo,latitude,longitude,altitude,data_cadastro,periodicidade) VALUES('"+ idEstacao + "','" +nomeEstacao + "','" + codigoEstacao + "'," + latitude + "," + Longitude + "," + altitude+ ",'" + dataCadastro + "','" + periodicidade +"');");
            return getIdEstacao(conexaoBase, codigoEstacao);
            
            
        } catch (SQLException e) {
            System.out.println("Erro ao realizar cadastro. Mensagem: " + e.getMessage() );
            return "-1";
        }
        
    }
    
    public String gravarEstacaoAutomatica (Connection conexaoBase, String idEstacao,String nomeEstacao, String codigoEstacao, String dataFundacao, String latitude, String Longitude, String altitude,String periodicidade )   {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormatBR = new SimpleDateFormat("dd/MM/yyyy");
            String dataCadastro = dateFormat.format(new Date());
            dataFundacao = dateFormat.format(dateFormatBR.parse(dataFundacao));
            if (existeEstacao(conexaoBase, codigoEstacao)){
                System.out.println("Estacao ja cadastrada;");
                return "0";
            }
            
            Statement sttm = conexaoBase.createStatement();
            
            sttm.executeUpdate("INSERT INTO tb_estacao(id, nome,codigo,data_fundacao,latitude,longitude,altitude, data_cadastro, periodicidade) VALUES('"+ idEstacao+ "','"+ nomeEstacao + "','" + codigoEstacao+ "','" + dataFundacao + "'," + latitude + "," + Longitude + "," + altitude+ ",'" + dataCadastro + "','" +periodicidade+"');");

            return getIdEstacao(conexaoBase, codigoEstacao);
            
            
        } catch (SQLException e) {
            System.out.println("Erro ao realizar cadastro. Menssagem: " + e.getMessage() );
            return "-1";
        } catch (ParseException pe) {
            System.out.println("Erro ao converter data. Menssagem:" + pe.getMessage());
            return "-2";
        }
        
    }
    
    public boolean existeEstacao (Connection conexaoBase, String codigoEstacao) throws SQLException{
        Statement sttm = conexaoBase.createStatement();
        ResultSet resultado = sttm.executeQuery("SELECT * FROM tb_estacao WHERE codigo LIKE '" + codigoEstacao + "'");
        return resultado.next();
    }
    
    public String getIdEstacao (Connection conexaoBase, String codigoEstacao) {
        
        
        try {
            Statement sttm = conexaoBase.createStatement();
        
            ResultSet resultado;
            resultado  = sttm.executeQuery("SELECT id FROM tb_estacao WHERE codigo LIKE '%" + codigoEstacao + "%';");
            System.out.println("SELECT id FROM tb_estacao WHERE codigo LIKE '%" + codigoEstacao + "%';");
            if (!resultado.next()) {
                System.out.println("Estacao nao existe na base de dados.");
                return "0";
            }
            
            return (resultado.getString("id"));
                
        } catch (SQLException e) {
            System.out.println("Erro ao realizar consulta. Mensagem: " + e.getMessage() );
            return "-1";
        }
        
    }
    
    public List<Estacao> listarEstacoes (Connection conexaoBase){
        try{
            Estacao nova;
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        
            List<Estacao> lista = new ArrayList<Estacao>();
            
            Statement sttm = conexaoBase.createStatement();
            
            ResultSet resultado = sttm.executeQuery("SELECT * FROM tb_estacao");
            
            if (!resultado.next()){
                return lista;
            }
            
            //resultado.first();
            
            do{
                nova = new Estacao();
                
                nova.setIDExistente((resultado.getString("id")));
                System.out.println("id "+resultado.getString("id"));
                nova.setNome(resultado.getString("nome"));
                nova.setCodigo(resultado.getString("codigo"));
                nova.setLatitude(Float.valueOf(resultado.getString("latitude")));
                nova.setLongitude(Float.valueOf(resultado.getString("longitude")));
                nova.setAltitude(Float.valueOf(resultado.getString("altitude")));
                nova.setPeriodicidade(resultado.getString("periodicidade"));
                
                if(resultado.getString("data_fundacao") != null){
                    nova.setDataFundacao(formato.parse(resultado.getString("data_fundacao").trim()));
                } 
                
                if(resultado.getString("data_cadastro") != null){
                    nova.setDataCadastro(formato.parse(resultado.getString("data_cadastro")));
                } 
                
                
                
                
                lista.add(nova);
            }while(resultado.next());
            
            return lista;
        } catch (SQLException e) {
            System.out.println("Erro ao lista estacoes. Mensagem: " + e.getMessage());
            return null;
        } catch (ParseException pe){
            System.out.println("Erro ao formatar data. Mensagem: " + pe.getMessage());
            return null;
        }
       
       
    }
        
    public void setDataInicial (Connection conexao, String dataInicial, String codigoEstacao) {
        try {
            Statement sttm = conexao.createStatement();
            
            sttm.executeUpdate("UPDATE tb_estacao SET data_inicial=´" + dataInicial.trim()+ "' WHERE codigo LIKE '%" + codigoEstacao + "';" );
            
        } catch (SQLException ex) {
            System.out.println("Erro ao defini data inicial na estacao " + codigoEstacao+ ". Mensagem: " + ex.getMessage());;
        }
    }
    
    public void setDataFinal (Connection conexao, String dataFinal, String codigoEstacao) {
        try {
            Statement sttm = conexao.createStatement();
            
            sttm.executeUpdate("UPDATE tb_estacao SET data_final=´" + dataFinal.trim()+ "' WHERE codigo LIKE '%" + codigoEstacao + "';" );
            
        } catch (SQLException ex) {
            System.out.println("Erro ao defini data Final na estacao " + codigoEstacao+ ". Mensagem: " + ex.getMessage());;
        }
    }
    
    public void setDataFundacao (Connection conexao, String dataFundacao, String codigoEstacao) {
        try {
            Statement sttm = conexao.createStatement();
            
            sttm.executeUpdate("UPDATE tb_estacao SET data_fundacao=´" + dataFundacao.trim()+ "' WHERE codigo LIKE '%" + codigoEstacao + "';" );
            
        } catch (SQLException ex) {
            System.out.println("Erro ao defini data de fundacao na estacao " + codigoEstacao+ ". Mensagem: " + ex.getMessage());;
        }
    }
    
    public Estacao getEstacao(Connection conexao, String codigo){
        try {
            Estacao nova =  null;
            ResultSet resultado = null;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            Statement sttm = conexao.createStatement();
            
            resultado = sttm.executeQuery("SELECT * FROM tb_estacao WHERE codigo LIKE '%" + codigo + "%';");
            
            if (resultado.next()){
                nova = new Estacao();
                
                nova.setIDExistente((resultado.getString("id")));
                System.out.println("iD: " +resultado.getString("id"));
                nova.setNome(resultado.getString("nome"));
                nova.setCodigo(resultado.getString("codigo"));
                nova.setLatitude(Float.valueOf(resultado.getString("latitude")));
                nova.setLongitude(Float.valueOf(resultado.getString("longitude")));
                nova.setAltitude(Float.valueOf(resultado.getString("altitude")));
                nova.setPeriodicidade(resultado.getString("periodicidade"));
                
                if(resultado.getString("data_fundacao") != null){
                    nova.setDataFundacao(formato.parse(resultado.getString("data_fundacao").trim()));
                } 
                
                if(resultado.getString("data_cadastro") != null){
                    nova.setDataCadastro(formato.parse(resultado.getString("data_cadastro")));
                } 
                return nova;
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar estacao do banco. Codigo da estacao: "+ codigo + ". Mensagem do erro: " + ex.getMessage());
            return null;
        } catch (ParseException ex) {
            System.out.println("Erro ao conbverter data da estacao. Codigo da estacao: "+ codigo + ". Mensagem do erro: " + ex.getMessage());
            return null;
        }
    }
    
}
