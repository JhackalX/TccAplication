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
public class ListaClassificacaoDAO {
    private CtrlDao ctrldao;

    public ListaClassificacaoDAO(CtrlDao ctrldao) {
        this.ctrldao = ctrldao;
    }

    public CtrlDao getCtrldao() {
        return ctrldao;
    }

    public void setCtrldao(CtrlDao ctrldao) {
        this.ctrldao = ctrldao;
    }
    
    public void gravarListaClassificação(Connection conexao, ArrayList<ListaClassificacao> lista, Info Estudo){
        for (int l =0; l < lista.size(); l++) {
            for(int a = 0; a < lista.get(l).getAnaliseMensal().size(); a++){
                //System.out.println("Gravando relatorio mes: " + lista.get(l).getAnaliseMensal().get(a).getMes() + ", sensor: " + lista.get(l).getSensor().getNome());
                this.ctrldao.gravarAnaliseMensal(lista.get(l).getAnaliseMensal().get(a), lista.get(l).getSensor(), Estudo);
            }   
        }
    }
    
}
