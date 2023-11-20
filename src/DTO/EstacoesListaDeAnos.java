/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author jacka
 */
public class EstacoesListaDeAnos {
    private String nomeEstacao;
    private ArrayList<String> listAnos;

    public EstacoesListaDeAnos(String nomeEstacao, ArrayList<String> listAnos) {
        this.nomeEstacao = nomeEstacao;
        this.listAnos = listAnos;
    }

    public EstacoesListaDeAnos() {
        this.listAnos = new ArrayList<String>();
    }

    public String getNomeEstacao() {
        return nomeEstacao;
    }

    public void setNomeEstacao(String nomeEstacao) {
        this.nomeEstacao = nomeEstacao;
    }

    public ArrayList<String> getListAnos() {
        return listAnos;
    }

    public void setListAnos(ArrayList<String> listAnos) {
        this.listAnos = listAnos;
    }
    
    public void addAnos(String anos){
        this.listAnos.add(anos);
    }

    @Override
    public String toString() {
        return getNomeEstacao();
    }

    

}
