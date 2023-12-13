/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
    
}
