/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Object.Sensor;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author aires
 */
public class SensorDAO {
    private CtrlDao ctrlDao;

    public SensorDAO(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }

    public CtrlDao getCtrlDao() {
        return ctrlDao;
    }

    public void setCtrlDao(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }
    
    public List<Sensor> listarSensores(Connection conexao){
        try{
            Statement sttm = null;
            ResultSet resultado = null;
            Sensor novo = null;
            ArrayList<Sensor> lista = new ArrayList<Sensor>();

            sttm = conexao.createStatement();
            
            resultado = sttm.executeQuery("SELECT * FROM TB_SENSOR");
            
            while(resultado.next()){
                novo = new Sensor();
                novo.setId(resultado.getString("id"));
                novo.setNome(resultado.getString("nome"));
                novo.setTextoCarga(resultado.getString("txt_arquivo_carga"));
                novo.setUnidadeMedida(resultado.getString("unidade_medida"));
                lista.add(novo);
            }
            
            return lista;
        } catch (SQLException se) {
            System.out.println("Erro ao lista sensores. Mensagem: " + se.getMessage());
            return null;
        }
    }
}
