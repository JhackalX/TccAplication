/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author jacka
 */
public class Marcador {
    //vide classe Guia, acumula idices do inicio do mes e indice do fim do mes
    //OBS:poderia ser mais rapido se fosse implementado como arvore binaria.
    private int inicio;
    private int fim;

    public Marcador() {
        this.inicio = -1;
        this.inicio = -1;
    }

    public Marcador(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public Marcador(int inicio) {
        this.inicio = inicio;
        this.fim = -1;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.inicio;
        hash = 17 * hash + this.fim;
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
        final Marcador other = (Marcador) obj;
        if (this.inicio != other.inicio) {
            return false;
        }
        return this.fim == other.fim;
    }

    @Override
    public String toString() {
        return "\n inicio = " + inicio + 
               "\n fim = " + fim;
    }
    
    
}
