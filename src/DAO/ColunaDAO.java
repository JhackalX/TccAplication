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
import Object.Info;
import Object.Sensor;
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
            if (colunas.get(i).getDados().size() > 0) {
                //System.out.println("Gravando coluna: " + colunas.get(i).getSensor().getNome());
                this.ctrlDao.gravarDados(colunas.get(i).getDados(), codigoEstacao);
            }
        }
    }
    
    public void gravarListaColunasProcessadas(Connection conexao, List<Coluna> colunas, String codigoEstacao, String codigoEstudo){
        for (int i = 0; i < colunas.size(); i++){
            if (colunas.get(i).getDados().size() > 0) {
               // System.out.println("Gravando coluna: " + colunas.get(i).getSensor().getNome());
                this.ctrlDao.gravarDadosProcessados(colunas.get(i).getDados(), codigoEstacao, codigoEstudo);
            }
        }
    }
    
    public List<Coluna> getColunasEstacaoPeriodo(String codigoEstacao, String periodo, Info medicao){
        ArrayList<Coluna> lista = null;
       
        ArrayList<Sensor> listasensores;
        
        Coluna nova = null;
                
        listasensores = (ArrayList<Sensor>) ctrlDao.listarSensores();
        
        lista =  new ArrayList<Coluna>();
        
        
        
        for (int i =0; i < listasensores.size(); i++){
            //System.out.println("Recuperando sensor: " + listasensores.get(i).getNome());
            
            nova = new Coluna();
            
            nova.setTitulo(listasensores.get(i).getNome());
            
            nova.setSensor(listasensores.get(i));
            
            nova.setDados(this.ctrlDao.listarDadosEstacaoMes(codigoEstacao, periodo, listasensores.get(i)));
            
            nova.setMedicaoPai(medicao);
            
            if (nova.getDados() != null) {
                 lista.add(nova);
            }
            
        }
        
        
        return lista;
    }
    
    
}
