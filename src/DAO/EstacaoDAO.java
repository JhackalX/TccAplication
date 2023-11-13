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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Aires
 */
public class EstacaoDAO {

    private CtrlDao ctrlDao;
    
    public EstacaoDAO(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }
    
    public String gravarEstacao (Connection conexaoBase, String nomeEstacao, String codigoEstacao, String ufEstacao, String regiaoEstacao, String dataFundacao, String latitude, String Longitude, String altitude)   {
        try {
            if (existeEstacao(conexaoBase, codigoEstacao)){
                System.out.println("Estacao ja cadastrada;");
                return "0";
            }
            
            Statement sttm = conexaoBase.createStatement();
            
            //System.out.println("INSERT INTO tb_estacao(nome,codigo,uf,regiao,data_fundacao,latitude,longitude,altitude) VALUES('"+ nomeEstacao + "','" + codigoEstacao+ "','" + ufEstacao+ "','" + regiaoEstacao + "','" + dataFundacao + "'," + latitude + "," + Longitude + "," + altitude+ ")");
            sttm.executeUpdate("INSERT INTO tb_estacao(nome,codigo,uf,regiao,data_fundacao,latitude,longitude,altitude) VALUES('"+ nomeEstacao + "','" + codigoEstacao+ "','" + ufEstacao+ "','" + regiaoEstacao + "','" + dataFundacao + "'," + latitude + "," + Longitude + "," + altitude+ ")");
            
            return getIdEstacao(conexaoBase, codigoEstacao);
            
            
        } catch (SQLException e) {
            System.out.println("Erro ao realizar cadastro. Mensagem: " + e.getMessage() );
            return "-1";
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
            resultado  = sttm.executeQuery("SELECT id FROM tb_estacao WHERE codigo LIKE '" + codigoEstacao + "'");
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
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            
            List<Estacao> lista = new ArrayList<Estacao>();
            
            Statement sttm = conexaoBase.createStatement();
            
            ResultSet resultado = sttm.executeQuery("SELECT * FROM tb_estacao");
            
            if (!resultado.next()){
                return null;
            }
            
            resultado.first();
            
            do{
                nova = new Estacao();
                
               // nova.setId(Integer.valueOf(resultado.getShort("id")));
                nova.setNome(resultado.getString("nome"));
                nova.setCodigo(resultado.getString("codigo"));
                nova.setLatitude(Float.valueOf(resultado.getString("latitude")));
                nova.setLongitude(Float.valueOf(resultado.getShort("longitude")));
                nova.setAltitude(Float.valueOf(resultado.getShort("altitude")));
                                
                lista.add(nova);
            }while(resultado.next());
            
            return lista;
        } catch (SQLException e) {
            System.out.println("Erro ao lista estacoes. Mensagem: " + e.getMessage());
            return null;
        }
       
        
    }
}
