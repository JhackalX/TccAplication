/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.util.Objects;

/**
 *
 * @author jacka
 */
public class AnaliseMensal {   
    
    private int mes;
    private int ano;
    //coeficientes
    private String coefSperman;
    private String tendencia;
    private String estacionariedade;
    //Erros
    private String mad;
    private String mae;
    private String mape;
    //Valores para pesquisa
    private int qtdNull;
    private int qtdSubs;
    private int qtdElementos;
    
    public AnaliseMensal() {
        this.mes = 0;
        this.ano = 0;
        
        this.coefSperman = "";
        this.tendencia = "";
        this.estacionariedade = "";
        
        this.mad = "";
        this.mae = "";
        this.mape = "";
        
        this.qtdNull = 0;
        this.qtdSubs = 0;
        this.qtdElementos = 0;        
    }

    public AnaliseMensal(int ano,
                         int mes,  
                         String coefSperman, 
                         String tendencia, 
                         String estacionariedade) {
        this.mes = mes;
        this.ano = ano;
        
        this.coefSperman = coefSperman;
        this.tendencia = tendencia;
        this.estacionariedade = estacionariedade;
        
        this.mad = "";
        this.mae = "";
        this.mape = "";
        
        this.qtdNull = 0;
        this.qtdSubs = 0;
        this.qtdElementos = 0;
    }

    public void setErros(String mad, 
                         String mae, 
                         String mape){
        this.mad = mad;
        this.mae = mae;
        this.mape = mape;
    }

    public void setEstatisca(int qtdNull, 
                         int qtdSubs, 
                         int qtdElementos){
        
        this.qtdNull = qtdNull;
        this.qtdSubs = qtdSubs;
        this.qtdElementos = qtdElementos;
    }
     
    public String getMes() {
        return "" + mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getAno() {
        return "" + ano;
    }

    public int getIntAno() {
        return ano;
    }

    public int getIntMes() {
        return mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCoefSperman() {
        return coefSperman;
    }

    public void setCoefSperman(String coefSperman) {
        this.coefSperman = coefSperman;
    }

    public String getTendencia() {
        return tendencia;
    }

    public void setTendencia(String tendencia) {
        this.tendencia = tendencia;
    }

    public String getEstacionariedade() {
        return estacionariedade;
    }

    public void setEstacionariedade(String estacionariedade) {
        this.estacionariedade = estacionariedade;
    }

    public String getMad() {
        return mad;
    }

    public void setMad(String mad) {
        this.mad = mad;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getMape() {
        return mape;
    }

    public void setMape(String mape) {
        this.mape = mape;
    }

    public int getQtdNull() {
        return qtdNull;
    }

    public void setQtdNull(int qtdNull) {
        this.qtdNull = qtdNull;
    }

    public int getQtdSubs() {
        return qtdSubs;
    }

    public void setQtdSubs(int qtdSubs) {
        this.qtdSubs = qtdSubs;
    }

    public int getQtdElementos() {
        return qtdElementos;
    }

    public void setQtdElementos(int qtdElementos) {
        this.qtdElementos = qtdElementos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.mes;
        hash = 37 * hash + this.ano;
        hash = 37 * hash + Objects.hashCode(this.coefSperman);
        hash = 37 * hash + Objects.hashCode(this.tendencia);
        hash = 37 * hash + Objects.hashCode(this.estacionariedade);
        hash = 37 * hash + Objects.hashCode(this.mad);
        hash = 37 * hash + Objects.hashCode(this.mae);
        hash = 37 * hash + Objects.hashCode(this.mape);
        hash = 37 * hash + this.qtdNull;
        hash = 37 * hash + this.qtdSubs;
        hash = 37 * hash + this.qtdElementos;
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
        final AnaliseMensal other = (AnaliseMensal) obj;
        if (this.mes != other.mes) {
            return false;
        }
        if (this.ano != other.ano) {
            return false;
        }
        if (this.qtdNull != other.qtdNull) {
            return false;
        }
        if (this.qtdSubs != other.qtdSubs) {
            return false;
        }
        if (this.qtdElementos != other.qtdElementos) {
            return false;
        }
        if (!Objects.equals(this.coefSperman, other.coefSperman)) {
            return false;
        }
        if (!Objects.equals(this.tendencia, other.tendencia)) {
            return false;
        }
        if (!Objects.equals(this.estacionariedade, other.estacionariedade)) {
            return false;
        }
        if (!Objects.equals(this.mad, other.mad)) {
            return false;
        }
        if (!Objects.equals(this.mae, other.mae)) {
            return false;
        }
        return Objects.equals(this.mape, other.mape);
    }


    
    public String imprimir(){
        return"" 
             + "\n Ano: "+ this.getAno()
             + "\n Mes: "+ this.getMes()
             + "\n Coeficiente-------------------------------------------------"
             + "\n Coeficiente de Sperman: "+ this.getCoefSperman()
             + "\n Tendencia: "+ this.getTendencia()
             + "\n Estacionariedade: "+ this.getEstacionariedade()
             + "\n Erro::------------------------------------------------------"
             + "\n Mad: "+ this.getMad() //Desvio absoluto
             + "\n Mae: "+ this.getMae() // Erro merdio abs
             + "\n Mape: "+ this.getMape() // percentual erro
             + "\n Info::------------------------------------------------------"
             + "\n Quantidade de Null's restante: "+ this.getQtdNull()
             + "\n Quantidade de Substituições: "+ this.getQtdSubs()
             + "\n Quantidade de Elementos(n): "+ this.getQtdElementos()
             + "\n ----------------------------------------------------";
    }
    
}
