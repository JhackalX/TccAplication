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
import java.util.UUID;
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
            String idAnaliseMensal,idSensor, idEstudo, tendencia, coefSperman, mes, qtdNulls, qtdSubs, qtdElem, desvAbsmed, errAbsMed, errAbsMedper, insert;
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
            
            if (desvAbsmed.compareToIgnoreCase("NAN") ==  0|| desvAbsmed.compareToIgnoreCase("Infinity") == 0) {
                desvAbsmed = "null";
            }
            
            errAbsMedper = analise.getMape();
            
                        
            if (errAbsMedper.compareToIgnoreCase("NAN") ==  0|| errAbsMedper.compareToIgnoreCase("Infinity") == 0) {
                errAbsMedper = "null";
            }
            errAbsMed =analise.getMae();
            if (errAbsMed.compareToIgnoreCase("NAN") ==  0|| errAbsMed.compareToIgnoreCase("Infinity") == 0) {
                errAbsMed = "null";
            }
            
            idAnaliseMensal = UUID.nameUUIDFromBytes((idSensor + idEstudo+ mes).getBytes()).toString();
            
            insert = ""
                    + "INSERT INTO tb_relatorio_mensal(id,id_estudo,id_sensor,desv_abs_medio,erro_abs_medio,erro_abs_medio_per,mes) VALUES('"+idAnaliseMensal+"','"+idEstudo+"','"+idSensor+"',"+desvAbsmed+","+errAbsMed+","+errAbsMedper+","+mes+");\n" 
                    + "INSERT INTO tb_relatorio_coeficientes(id,id_estudo,id_sensor,mes,coef_sperman,tendencia) VALUES('"+idAnaliseMensal+"','"+idEstudo+"','"+idSensor+"','"+mes+"',"+coefSperman+","+tendencia+");\n"
                    + "INSERT INTO tb_relatorio_erro(id,id_estudo,id_sensor,mes,qtd_nulls_mes,qtd_subs_mes,qtd_elem_mes) VALUES('"+idAnaliseMensal+"','"+idEstudo+"','"+idSensor+"',"+mes+","+qtdNulls+","+qtdSubs+","+qtdElem+");";
            
            
            sttm.executeUpdate(insert);
        
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir analise mensal. Mensagem: " + ex.getMessage());
        }
    }
    
    
    public List<AnaliseMensal> getAnalisemensalEstudoSensor(Connection conexao, String idEstudo, String idSensor){
        try {
            List<AnaliseMensal> lista = null;
            AnaliseMensal nova;
            Statement sttm = conexao.createStatement();
            ResultSet result = null;
            
            result = sttm.executeQuery( "SELECT AM.*, AE.*, AC.*\n" +
                                        "FROM tb_relatorio_mensal AS AM\n" +
                                        "LEFT JOIN tb_relatorio_erro AS AE\n" +
                                        "ON AM.id = AE.id\n" +
                                        "LEFT JOIN tb_relatorio_coeficientes as AC\n" +
                                        "ON AM.id = AC.id\n" +
                                        "WHERE AM.id_estudo LIKE '"+idEstudo+"'\n" +
                                        "AND AM.id_sensor LIKE '"+idSensor+"';");
            
            
            if (!result.isBeforeFirst()){
                return null;
            }
            
            
            lista = new ArrayList<AnaliseMensal> ();
            
            
            while (result.next()){
                nova = new AnaliseMensal();
                nova.setMes(Integer.valueOf(result.getString("mes").split("-")[0]));
                nova.setAno(Integer.valueOf(result.getString("mes").split("-")[1]));
                //nova.set
            }
            
            return lista;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnaliseMensalDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
