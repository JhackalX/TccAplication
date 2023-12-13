package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import Object.Dados;
import Object.Metodologia;
import java.util.ArrayList;
import Object.Sensor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetodologiaDAO {
    private CtrlDao ctrlDao;

    public MetodologiaDAO(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }

    public CtrlDao getCtrlDao() {
        return ctrlDao;
    }

    public void setCtrlDao(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }
    
    public Metodologia getMetodologia(Connection conexao, Integer codigo) {        
        try { 
            Metodologia met = null;
            ResultSet result;
            
            Statement sttm = conexao.createStatement();
            
            result = sttm.executeQuery("SELECT * FROM tb_metodologia WHERE codigo = " + codigo.toString() + ";");
            
            if(result.isBeforeFirst()){
                met = new Metodologia();
                met.setId(result.getString("id"));
                met.setNome(result.getString("nome"));
                met.setSigla(result.getString("sigla"));
                met.setOpcao(result.getInt("codigo"));
            }
            
            return met;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar metodologia. Codigo: " + codigo + ". Mensagem de erro: " + ex.getMessage());
            return null;
        }       
    }
    
    public void gravarCoef(Connection conexao, String idEstudo, Float value){
        try {
            String idCoef;
            
            idCoef = UUID.nameUUIDFromBytes((idEstudo + value.toString()).getBytes()).toString();
            
            Statement sttm = conexao.createStatement();
            
                        sttm.executeUpdate("INSERT INTO tb_parametros(id,id_estudo,valor) VALUES('"+idCoef + "','" + idEstudo + "'," + value +");");
                        System.out.println("INSERT INTO tb_parametros(id,id_estudo,valor) VALUES('"+idCoef + "','" + idEstudo + "'," + value +");");

            
        } catch (SQLException ex) { 
            System.out.println("Erro ao inserir coeficiente de alizamento exponencial. Mensagem: " + ex.getMessage());
        }
    }
    
    
    public void gravarListaPesos(Connection conexao, String idEstudo, List<Float> pesos) {
        try{
            String idPeso, insert;
            
            insert = "BEGIN TRANSACTION;\n";
            
            Statement sttm = conexao.createStatement();
            
            for (int i =0; i< pesos.size(); i++) {
                idPeso =  UUID.nameUUIDFromBytes((idEstudo + pesos.get(i).toString() + String.valueOf(i)).getBytes()).toString();
                
                insert = insert + "INSERT INTO tb_parametros(id,id_estudo,valor, num_ordem) VALUES ('" + idPeso + "','" + idEstudo+ "'," + pesos.get(i).toString() + "," + String.valueOf(i) + ");\n";
            }
            
            insert = insert + "END TRANSACTION;";
            
            System.out.println(insert);
            
            sttm.executeUpdate(insert);
            
        }catch (SQLException ex) {
            System.out.println("Erro ao gravar pesos. Mensagem: " + ex.getMessage());
        }
    }
    
    public List<Float> listarPesosEstudo (Connection conexao, String idEstudo){
        try {
            List<Float> pesos = new ArrayList();
            ResultSet result;
            Statement sttm = conexao.createStatement();
            
            result = sttm.executeQuery(  "SELECT * \n" +
                                "FROM tb_parametros \n" +
                                "WHERE id_estudo LIKE '"+ idEstudo+ "'\n" +
                                "ORDER BY num_ordem ASC;");
            
            if (!result.isBeforeFirst()){
                return null;
            }
            
            while(result.next()){
                pesos.add(result.getFloat("valor"));
            }
            
            return pesos;
            
        } catch (SQLException ex) {
            Logger.getLogger(MetodologiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
        public Float listarCoeficienteES (Connection conexao, String idEstudo){
        try {
            Float coef = null;
            ResultSet result;
            Statement sttm = conexao.createStatement();
            
            result = sttm.executeQuery(  "SELECT * \n" +
                                "FROM tb_parametros \n" +
                                "WHERE id_estudo LIKE '"+ idEstudo+ "'\n" +
                                "ORDER BY num_ordem ASC;");
            
            if (!result.isBeforeFirst()){
                return null;
            }
            
            
            coef = result.getFloat("valor");
            
            
            return coef;
            
        } catch (SQLException ex) {
            Logger.getLogger(MetodologiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
