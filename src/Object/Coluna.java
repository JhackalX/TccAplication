/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jacka
 */
public class Coluna {
    
    private String titulo;
    private List<Dados> dados;

    public Coluna() {
    }

    public Coluna(String titulo, List<Dados> dados) {
        this.titulo = titulo;
        this.dados = dados;
    }

    public Coluna(String titulo) {
        this.titulo = titulo;
        this.dados = new ArrayList<Dados>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getLinhas() {
        return dados.size();
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Dados> getDados() {
        return dados;
    }

    public List<Dados> getSubDados(int mes, int ano) {

        List<Dados> subLista = new ArrayList<Dados>();
        
        for(int i = 0; i < this.dados.size(); i++){
            if((this.getDado(i).getYear() == ano) && 
               (this.getDado(i).getMonth() == mes)){ 
                
                subLista.add(this.getDado(i));
            }
        }

        return subLista;
    }

    public List<Float> getAllDados() {

        List<Float> allLista = new ArrayList<Float>();
        
        for(int i = 0; i < this.dados.size(); i++){                
            allLista.add(this.getDado(i).getValorF());
        }
        
        return allLista;
    }

    public List<Dados> getSubDadosIndex(int inicio, int fim) {
        return this.getDados().subList(inicio, fim);
    }

    public void setDados(List<Dados> dados) {
        this.dados = dados;
    }
    
    public boolean isEmpty(){
        return this.dados.isEmpty();
    }
    
    public Dados getDado(int index) {
        return this.dados.get(index);
    }
    
    public void removeDado(int index) {
        this.dados.remove(index);
    }
    
    public void addDado(Dados Obj) {
        //System.out.println(Obj.getData()+","+Obj.getPeriodo()+","+Obj.getValor());
        Dados dado = new Dados();
        dado.setData(Obj.getData());
        dado.setPeriodo(Obj.getPeriodo());
        dado.setValor(Obj.getValor());
        
        //System.out.println(dado.getData()+","+dado.getPeriodo()+","+dado.getValor());
        if(this.isEmpty()){
                this.dados.add(dado);

//                this.atualizarIntervaloData();
        }else{
            //System.out.println("entrou aqui");
            if(!dados.contains(dado)){
                this.dados.add(dado);
                //this.ordenarLista();

//                this.atualizarIntervaloData();
            }else{
                System.out.println("elemento ja existe na lista...");
            }    
        }
        //this.listarMedicao();
    }
    
    public void editarDado(Dados Obj, int index){
        this.getDado(index).setData(Obj.getData());
        this.getDado(index).setPeriodo(Obj.getPeriodo());
        this.getDado(index).setValor(Obj.getValor());

//        this.atualizarIntervaloData();
    }
    
    public void removeDado(Dados Obj){
        this.dados.remove(Obj);

//        this.atualizarIntervaloData();
    }

    //exclui todas as medicoes de valor null
    public void excluirMedicaoInvalida(){
        for(int i = 0; i < this.dados.size(); i++){
            if(this.getDado(i).getValor().equals("null")){
                this.dados.remove(i);
            }
        } 
//        this.atualizarIntervaloData();
    }
    
    //ordenação de lista
    public void ordenarLista(){
       boolean troca = true;
       Dados aux = new Dados();
       while (troca){
           troca = false;
           for(int i = 0; i < this.dados.size()-1; i++){
               
               
               if(this.getDado(i).compareTo(this.getDado(i+1)) > 0){
                   //auxiliar recebe o valor maior
                   aux.setData(this.getDado(i).getData());
                   aux.setPeriodo(this.getDado(i).getPeriodo());
                   aux.setValor(this.getDado(i).getValor());
                   //troca
                   this.editarDado(this.getDado(i+1), i);
                   this.editarDado(aux, i+1);
                   
                   troca = true;                   
               }          
           }
       }      
    }

    public void imprimirColuna(){
        System.out.println("Info{\n coluna = " + this.titulo);
        for(int i = 0; i < this.dados.size(); i++ ){
            System.out.println(this.getDado(i).toString());
        }
        System.out.println("}");        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.titulo);
        hash = 37 * hash + Objects.hashCode(this.dados);
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
        final Coluna other = (Coluna) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return Objects.equals(this.dados, other.dados);
    }
       
}
