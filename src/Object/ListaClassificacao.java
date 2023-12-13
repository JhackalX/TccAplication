/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacka
 */
public class ListaClassificacao {
        
    private String titulo;
    private List<AnaliseMensal> analiseMensal;
    
    private Sensor sensor;


    public ListaClassificacao(String titulo) {
        this.titulo = titulo;
        this.analiseMensal = new ArrayList<AnaliseMensal>();
    }

    public ListaClassificacao() {
        this.analiseMensal = new ArrayList<AnaliseMensal>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AnaliseMensal> getLista() {
        return analiseMensal;
    }

    public void setLista(List<AnaliseMensal> analiseMensal) {
        this.analiseMensal = analiseMensal;
    }
    
    public boolean isEmpty(){
        return this.analiseMensal.isEmpty();
    }
    
    public AnaliseMensal getClassificador(int index) {
        return this.analiseMensal.get(index);
    }
    
    public void removeClassificador(int index) {
        this.analiseMensal.remove(index);
    }
    
    public void addClassificador(AnaliseMensal Obj) {
        AnaliseMensal dado = new AnaliseMensal();
        
        dado.setAno(Obj.getIntAno());
        dado.setMes(Obj.getIntMes());
        dado.setCoefSperman(Obj.getCoefSperman());
        dado.setEstacionariedade(Obj.getEstacionariedade());
        dado.setTendencia(Obj.getTendencia());
        
        this.analiseMensal.add(dado);
    }
    
    public void editarClassificador(AnaliseMensal Obj, int index){
        this.getClassificador(index).setAno(Obj.getIntAno());
        this.getClassificador(index).setMes(Obj.getIntMes());
        
        this.getClassificador(index).setCoefSperman(Obj.getCoefSperman());
        this.getClassificador(index).setEstacionariedade(Obj.getEstacionariedade());
        this.getClassificador(index).setTendencia(Obj.getTendencia());
        
        this.getClassificador(index).setMad(Obj.getMad());
        this.getClassificador(index).setMae(Obj.getMae());
        this.getClassificador(index).setMape(Obj.getMape());
        
        this.getClassificador(index).setQtdNull(Obj.getQtdNull());
        this.getClassificador(index).setQtdSubs(Obj.getQtdSubs());
        this.getClassificador(index).setQtdElementos(Obj.getQtdElementos());
    }
    
    public void removeDado(AnaliseMensal Obj){
        this.analiseMensal.remove(Obj);
    }

    public void imprimirDadosMensais(){
        System.out.println("Info{\n coluna = " + this.titulo);
        for(int i = 0; i < this.analiseMensal.size(); i++ ){
            System.out.println(this.getClassificador(i).imprimir());
        }
        System.out.println("\n }");        
    }

    public List<AnaliseMensal> getAnaliseMensal() {
        return analiseMensal;
    }

    public void setAnaliseMensal(List<AnaliseMensal> analiseMensal) {
        this.analiseMensal = analiseMensal;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
   
    
}
