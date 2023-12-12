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
    
//    public Metodologia getMeodologia(Connection conexao, String código) {
//        
//        
//    }
}
