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
import Object.Coluna;
/**
 *
 * @author aires
 */
public class ColunaDAO {
    private CtrlDao ctrlDao;
    
    
    public ColunaDAO(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }
    
    public void gravarListaColunas(Connection conexao, List<Coluna> colunas, String codigoEstacao){
        for (int i = 0; i < colunas.size(); i++){
            
            this.ctrlDao.gravarDados(colunas.get(i).getDados(), codigoEstacao);
        }
    }
    
    
}
