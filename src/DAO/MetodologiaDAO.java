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
    
    public Metodologia getMeodologia(Connection conexao, Integer codigo) {        
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
                
            }
            
            return met;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar metodologia. Codigo: " + codigo + ". Mensagem de erro: " + ex.getMessage());
            return null;
        }
        
       
        
    }
}
