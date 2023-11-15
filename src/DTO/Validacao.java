/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 * @author jacka
 */
public class Validacao {
    
    //listas base de calculos
    private List<Float> valor;
    private List<Integer> rank;
    
    //auxiliares de classificação
    private List<Integer> classificacao;
    private List<Float> ordenada;
    
    //valores dos coeficientes
    private double tendencia;
    private double coefSp;
    
    //construtor
    public Validacao() {
        this.valor = new ArrayList<Float>();
        this.rank = new ArrayList<Integer>();
        this.ordenada = new ArrayList<Float>();
        this.classificacao = new ArrayList<Integer>();
        this.tendencia = 0;
        this.coefSp = 0;
    }

    public Validacao(List<Float> valor) {
        if(valor.isEmpty()){
            this.tendencia = 0;
            this.coefSp = 0;            
        }else{           
            this.valor = valor;
            this.rank = new ArrayList<Integer>();
            this.ordenada = new ArrayList<Float>();
            this.classificacao = new ArrayList<Integer>();
            this.validarDados();       
        }
    }
    
    //getter e setter
    public List<Float> getValor() {
        return valor;
    }

    public void setValor(List<Float> valor) {
        this.valor = valor;
    }

    public List<Integer> getRank() {
        return rank;
    }

    public void setRank(List<Integer> rank) {
        this.rank = rank;
    }

    public List<Integer> getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(List<Integer> classificacao) {
        this.classificacao = classificacao;
    }

    public List<Float> getOrdenada() {
        return ordenada;
    }

    public void setOrdenada(List<Float> ordenada) {
        this.ordenada = ordenada;
    }

    public double getTendencia() {
        return tendencia;
    }
    public String getStrTendencia() {
        return ""+tendencia;
    }

    public void setTendencia(double tendencia) {
        this.tendencia = tendencia;
    }

    public double getCoefSp() {
        return coefSp;
    }
    
    public String getStrCoefSp() {
        if(this.coefSp >= 0){
            return ""+ Math.abs(this.coefSp);

        }else{
            return "-" + Math.abs(this.coefSp);            
        }
    }

    public void setCoefSp(double coefSp) {
        this.coefSp = coefSp;
    }
    
    public String getEstacionariedade(){
        if(this.coefSp >= 0){
            return "não é estacionaria";
        }else{
            return "é estacionaria";
        }
    }
    
    //remove valores nulos
    public void removerNull(){
        for(int i = 0; i < this.valor.size(); i++){
            if(this.valor.get(i) == null){
                this.valor.remove(i);
            }
        }
    }
    
    //calcula o coeficiente de spearman
    public void calcularSP(){
        int n = this.valor.size();
        this.coefSp = (1-((6*this.tendencia)/(n*(Math.pow(n, 2)-1))));
    }
    
    //busca um valor dentro da lista ordenada
    public int buscarValor(Float valor){
        //System.out.println("entrou aqui");
        for(int i = 0; i < this.ordenada.size(); i++){
            if(this.ordenada.get(i).equals(valor)){
                return i;
            }
        }
        return -1;
    }
    
    //calcula a tendencia
    public void calcularTendencia(){
        double result = 0;
        for(int i = 0; i < this.rank.size(); i++){
            result += Math.pow(Math.abs(rank.get(i) - (i+1)), 2);            
        }
        this.tendencia = (int) result;      
    }
    
    //gera um vetor de classificação
    public void classificarRank(){
        for(int i = 0; i < this.ordenada.size(); i++){
            this.classificacao.add(i+1);
        }
    }
    
    //atribui a classificação a lista Rank
    public void classificaGeral(){
        int index;
        for(int i = 0; i < this.valor.size(); i++ ){
            index = this.buscarValor(this.valor.get(i));
            if(index != -1){
                this.rank.add(classificacao.get(index));
            }           
        }       
    }
    
    //Função que cria lista organizada e ordenada
    //Sobre hashset:
    //*Quando você adiciona um objeto ao HashSet, 
    // o hashcode desse objeto é calculado e usado 
    // para determinar onde o objeto deve ser armazenado 
    // internamente.
    //*Se dois objetos são considerados iguais pelo método 
    // equals, eles terão o mesmo hashcode e não poderão 
    // ambos ser armazenados no HashSet.
    //*Quando você verifica a presença de um objeto ou o remove, 
    // o HashSet calcula o hashcode do objeto para rapidamente 
    // encontrar a localização dele e fazer a operação necessária.
    public void organizarEordenar(){

        Set<Float> aux = new HashSet<>();
        aux.addAll(this.valor);

        this.ordenada.addAll(aux);

        this.ordenarLista();              
    }

    //ordenação de lista
    public void ordenarLista(){
       boolean troca = true;
       Float aux;
       while (troca){
           troca = false;
           for(int i = 0; i < this.ordenada.size()-1; i++){
               if(this.ordenada.get(i) > this.ordenada.get(i+1)){
                   //auxiliar recebe o valor maior
                   aux = ordenada.get(i);
                   
                   //troca
                   this.ordenada.set(i, ordenada.get(i+1));
                   this.ordenada.set(i+1, aux);
                   
                   troca = true;                   
               }          
           }
       }      
    }

    //Função faz tudo!(preciso seriamente mudar isso)
    public void validarDados(){
        
        this.organizarEordenar();
        this.classificarRank();
        
        this.classificaGeral();
        this.calcularTendencia();
        this.calcularSP();
//        this.imprirRelatorio();
    }
    
    //checa se a serie é estacionaria
    public boolean isEstacionaria(){
        return !(this.coefSp >= 0);  
    }
    
    //Relata o resultado do estado do coeficiente de Spearman   
    public String Resultado(){

        if(this.coefSp >= 0){
            return "\n n: " + this.valor.size() + 
                   "\n Tendencia: " + this.tendencia +
                   "\n Coef. de Spearman: " + Math.abs(this.coefSp) +
                   "\n A Série temporal não é estacionaria.";
        }else{
            return "\n n: " + this.valor.size() + 
                   "\n Tendencia: " + this.tendencia +
                   "\n Coef. de Spearman: -" + Math.abs(this.coefSp) +
                   "\n A Série temporal é estacionaria.";            
        }
    } 
    
    //para testes
    public void imprirRelatorio(){

        System.out.println("n: " + this.valor.size());
        System.out.println("Tendencia: " + this.tendencia);
        if(this.coefSp >= 0){
            System.out.println("Coef. de Spearman: " + Math.abs(this.coefSp));
            System.out.println("A Série temporal não é estacionaria.");
        }else{
            System.out.println("Coef. de Spearman: -" + Math.abs(this.coefSp));
            System.out.println("A Série temporal é estacionaria.");            
        }
    } 
}