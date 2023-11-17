/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Object.Dados;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jacka
 */
public class Guia {
    //criada para acumular idices de inicio e final de mês
    //para facilitar a obtenção de dados para constar no documento texto
    // e formulas geradas para analises.
    private List<Marcador> apendice;

    public Guia(List<Marcador> apendice) {
        this.apendice = apendice;
    }

    public Guia() {
        this.apendice = new ArrayList<Marcador>();
    }

    public List<Marcador> getApendice() {
        return apendice;
    }

    public Marcador getMarcador(int index) {
        return apendice.get(index);
    }

    public void setApendice(List<Marcador> apendice) {
        this.apendice = apendice;
    }
    public void addApendice(Marcador obj) {
        this.apendice.add(obj);
    }
    
    public void addApendice(int inicio, int fim) {
        this.apendice.add(new Marcador(inicio, fim));
    }
    
    public void editApendice(int index, Marcador obj) {
        this.apendice.get(index).setInicio(obj.getInicio());
        this.apendice.get(index).setFim(obj.getFim());
    }

    public void editApendice(int index, int inicio, int fim) {
        this.apendice.get(index).setInicio(inicio);
        this.apendice.get(index).setFim(fim);
    }
    
    public void removeApendice(int index) {
        this.apendice.remove(index);
    }

    public void removeApendice(Marcador obj) {
        this.apendice.remove(obj);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.apendice);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Guia other = (Guia) obj;
        return Objects.equals(this.apendice, other.apendice);
    }

    public void gerarGuia(List<Dados> lista){
        for(int i = 0; i < lista.size(); i++){
            if(this.apendice.isEmpty()){
                this.apendice.add(new Marcador(i));
            }else{
                if((this.getMarcador((this.apendice.size()-1)).getFim() != -1)){
                    this.apendice.add(new Marcador(i));                   
                }else{
                    if( i == (lista.size()-1)){
                        this.getMarcador((this.apendice.size()-1)).setFim(i);
                    }else{
                        if(lista.get(i).getMonth() != lista.get((i + 1)).getMonth()){
                            this.getMarcador((this.apendice.size()-1)).setFim(i); 
                        }
                    }                    
                }
            }
        }
    }

    public void imprimir(){
        System.out.println("Marcadores:");
        for(int i = 0; i < this.apendice.size(); i++){
            System.out.println("Posicao " + i +" :");
            System.out.println(this.getMarcador(i).toString());
        }
    }
    
}
