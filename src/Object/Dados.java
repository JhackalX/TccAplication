/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacka
 */


public class Dados implements Comparable<Dados> {
    private Date data;
    private int periodo;
    private Float valor;
    

    public Dados() {
    }

    public Dados(Date data, int periodo, float valor){
        DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.data = dateFormate.parse(data.toString());
        } catch (ParseException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
            this.data = null;
        }
        this.periodo = periodo;
        this.valor = valor;
    }
    
    public Dados(String data, String periodo, String valor){
        DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            this.data = dateFormate.parse(data.toString());
        } catch (ParseException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
            this.data = null;
        }
        
        if(valor.equalsIgnoreCase("null")){
            this.valor = null;
        }else{
            this.valor = Float.parseFloat(valor.replace(',', '.'));
        }
        
        this.periodo = Integer.parseInt(periodo);
    }

    public Date getData(){
        //DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        return data;
        //return dateFormate.parse(this.data.toString());
    }
    
    public String getDataBr(){
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormate.format(data).toString();
    }

    public void setData(Date data){
        //DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        this.data = data;
        //this.data = dateFormate.parse(data.toString());
    }
    
    public void setData(String data){
        DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            setData(dateFormate.parse(data));
        } catch (ParseException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = Integer.parseInt(periodo);
    }

    public String getValor(){
        if(this.valor == null){
            return "null";
        }else{
            return valor.toString();
        }
    }

    public Float getValorF(){
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setValor(String valor) {
        if(valor.equalsIgnoreCase("null")){
            this.valor = null;
        }else{
            this.valor = Float.parseFloat(valor.replace(',', '.'));
        }     
    }
    
    public int getDay (){
        return (data.getDay()+1);
    }
    
    public int getMonth (){
        return (data.getMonth()+1);
    }
    
    public int getYear (){
        return (data.getYear()+1900);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.data);
        hash = 19 * hash + this.periodo;
        hash = 19 * hash + Objects.hashCode(this.valor);
        return hash;
    }

    //equals para comparação de objeto

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
        final Dados other = (Dados) obj;
        if (this.periodo != other.periodo) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return true;
    }

    //to string
    @Override
    public String toString() {

        return "Medicao{" + "data: " + getDataBr() + 
                            "\n hora: " + periodo + 
                            "\n valor: " + this.getValor() + '}';
    }
      
    //funções práticas de objeto
    
    public void limpar(){
        limparData();
        limparPeriodo();
        limparValor();
    }
    public void limparData(){
        this.data = null;
    }    
    public void limparPeriodo(){
        this.periodo = 0;
    }    
    public void limparValor(){
        this.valor = null;
    }

    @Override
    public int compareTo(Dados outroObj) {
        if (this.data == null){
            System.out.println("Data sem valor gravado!!!");
            return -2;
        }else {
            if (this.data.compareTo(outroObj.getData())<0) { 
                return -1;
                //se this.data < outroObj.data
            }else {
                if (this.data.compareTo(outroObj.getData())>0) { 
                    return 1;
                    //se this.data > outroObj.data
                } else {
                    if(this.periodo < outroObj.getPeriodo()){
                        return -1;
                        // se this.periodo < outroObj.periodo
                    }else{
                        if(this.periodo > outroObj.getPeriodo()){
                            return 1;
                            // se this.periodo > outroObj.periodo
                        }else{
                            if(this.valor.compareTo(outroObj.valor) == 0){
                                return 0;//se todas os atributos dos objetos são iguais
                            }else{
                                return -3;//neste caso o dado pode ser editado
                            }   
                        }                         
                    }
                }
            }
        }           
    } 
    
}
