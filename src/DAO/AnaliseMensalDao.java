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
import Object.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.*;
/**
 *
 * @author aires
 */
public class AnaliseMensalDao {
    private CtrlDao ctrlDao;

    public AnaliseMensalDao(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }

    public CtrlDao getCtrlDao() {
        return ctrlDao;
    }

    public void setCtrlDao(CtrlDao ctrlDao) {
        this.ctrlDao = ctrlDao;
    }
    
    public void gravarAnaliseMensal (Connection conexao, AnaliseMensal analise, Sensor sensor, Info estudo){
        try {
            String idSensor, idEstudo, tendencia, coefSperman, mes, qtdNulls, qtdSubs, qtdElem, desvAbsmed, errAbsMed, errAbsMedper, insert;
            Statement sttm = conexao.createStatement();
            
            idSensor = sensor.getId();
            idEstudo = estudo.getId();
            
            tendencia = analise.getTendencia();
            coefSperman = analise.getCoefSperman();
            mes = analise.getMes()+"-"+analise.getAno();
            
            qtdNulls = String.valueOf(analise.getQtdNull());
            qtdSubs = String.valueOf(analise.getQtdSubs());
            qtdElem = String.valueOf(analise.getQtdElementos());
            
            desvAbsmed = analise.getMad();
            errAbsMedper = analise.getMape();
            errAbsMed =analise.getMae();
            
            insert = ""
                    + "INSERT INTO tb_relatorio_mensal(id,id_estudo,id_sensor,desv_abs_medio,erro_abs_medio,erro_abs_medio_per) VALUES("+")";
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir analise mensal. Mensagem: " + ex.getMessage());
        }
    }
    
}
