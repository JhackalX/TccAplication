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
import Object.Info;
import java.util.ArrayList;
import Object.Sensor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aires
 */
public class EstudoDAO {
    private CtrlDao ctrldao;

    public EstudoDAO(CtrlDao ctrldao) {
        this.ctrldao = ctrldao;
    }

    public CtrlDao getCtrldao() {
        return ctrldao;
    }

    public void setCtrldao(CtrlDao ctrldao) {
        this.ctrldao = ctrldao;
    }
    
    public void gravarEstudo(Connection conexao, Info estudo){
        try {
            String idEstudo, idEstacao, idMetodologia, nome, periodo, dataCadatro;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            
            nome = estudo.getNome();
            idEstudo = estudo.getId();
            idEstacao = estudo.getEstacao().getId();
            idMetodologia = estudo.getMetodologiaAplicada().getId();
            periodo = estudo.getPeriodo();
            
            dataCadatro =  format.format(new Date() );
                    
            Statement sttm = conexao.createStatement();
            //System.out.println("INSERT INTO tb_estudo (id, id_estacao, id_metodologia, nome, periodo, data_estudo) VALUES('" + idEstudo+ "','" + idEstacao + "','" + idMetodologia + "','" + nome + "','" + periodo + "','" + dataCadatro + "');");
            sttm.executeUpdate("INSERT INTO tb_estudo (id, id_estacao, id_metodologia, nome, periodo, data_estudo) VALUES('" + idEstudo+ "','" + idEstacao + "','" + idMetodologia + "','" + nome + "','" + periodo + "','" + dataCadatro + "');");
        } catch (SQLException ex) {
            Logger.getLogger(EstudoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Info> listarEstudos(Connection conexao){
        try {
            ArrayList<Info> lista = null;
            
            Info nova;
            Statement sttm = conexao.createStatement();
            
            ResultSet resultEstudo, resultEstacao;
            
            resultEstudo = sttm.executeQuery(   "SELECT TB.*, TE.codigo as TECodigo,TM.codigo as TMCodigo\n" +
                                                "FROM tb_estudo as TB\n" +
                                                "LEFT JOIN tb_estacao as TE\n" +
                                                "ON TB.id_estacao LIKE TE.id\n" +
                                                "LEFT JOIN tb_metodologia as TM\n" +
                                                "ON TB.id_metodologia LIKE tm.id;");
            if (!resultEstudo.isBeforeFirst()) {
                return null;
            }
            
            lista = new ArrayList<Info> ();
            
            while(resultEstudo.next()) {
                nova = new Info();
                
                nova.setId(resultEstudo.getString("id"));
                nova.setEstacao(this.ctrldao.getEstacao(resultEstudo.getString("TECodigo")));
                nova.setMetodologiaAplicada(this.ctrldao.getMetodologia(resultEstudo.getInt("TMCodigo")));
                nova.setNome(resultEstudo.getString("nome"));
                nova.setPeriodo(resultEstudo.getString("periodo"));
                
                if (nova.getMetodologiaAplicada().getOpcao() == 0){
                    nova.getMetodologiaAplicada().setLista(this.ctrldao.listarPesosEstudo(nova.getId()));
                    
                } else {
                    nova.getMetodologiaAplicada().setCoef(this.ctrldao.listarCoeficienteES(nova.getId()));
                }
                
                lista.add(nova);
                
            }
            
            
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar estudos. Mensagem" + ex.getMessage());
            return null;
        }
    }
}
