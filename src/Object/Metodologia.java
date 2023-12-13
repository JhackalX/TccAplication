/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacka
 */

public class Metodologia {
    
    private String id;
    private String nome;
    private String sigla;
    //opcao de escolha de metodologia
    private int opcao;
    //coeficiente exponencial
    private Float coef;
    //lista de pesos
    private List<Float> pesos;
    
    private final String Opcoes[] = {"Media Movel Ponderada",
                                     "Alisamento Exponencial"};    
    //construtor
    public Metodologia() {
        this.opcao = -1;
        this.coef = Float.parseFloat("0");
        this.pesos = new ArrayList<Float>();        
    }

    public Metodologia(String opcao, String coef, List<Float> pesos) {
        this.opcao = Integer.parseInt(opcao);
        this.coef = Float.parseFloat(coef.replace(',', '.'));
        this.pesos = new ArrayList<Float>();
        this.pesos.addAll(pesos);
    }

    public Metodologia(String opcao, List<Float> pesos) {
        this.opcao = Integer.parseInt(opcao);
        this.pesos = new ArrayList<Float>();
        this.pesos.addAll(pesos);
    }

    public Metodologia(String opcao, String coef) {
        this.opcao = Integer.parseInt(opcao);
        this.coef = Float.parseFloat(coef.replace(',', '.'));
    }

    public String getMetodologia(String opcao) {
        if(Integer.parseInt(opcao) == -1){
            return "null";
        }
        return this.Opcoes[Integer.parseInt(opcao)];
    }

    public int getOpcao() {
        return opcao;
    }
    
    //reorna string com os coeficientes ou os pesos
    public String getValoresReferentes(int opcao) {
        if(opcao == 0){
            return " Pesos: " + getPesosToString();
        }
        if(opcao == 1){
            return " Coeficiente: " + getCoefBR();
        }
        return "null";
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    public Float getCoef() {
        return coef;
    }
    
    public String getCoefBR() {
        return coef.toString().replace('.', ',');
    }

    public void setCoef(String coef) {
        this.coef = Float.parseFloat(coef.replace(',', '.'));
    }

    public void setCoef(Float coef) {
        this.coef = coef;
    }

    public List<Float> getPesos() {
        return pesos;
    }

    public String getPesosToString() {
//        System.out.println("entrou aqui getPesosToString()");
            if (!(pesos == null || pesos.isEmpty())) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            // Itera sobre todos os elementos da lista, exceto o último
            for (int i = 0; i < pesos.size() - 1; i++) {
                sb.append(pesos.get(i)).append(", ");
            }

            // Adiciona o último elemento sem a vírgula
            sb.append(pesos.get(pesos.size() - 1));

            sb.append("]");
            return sb.toString();            
        }
        
        return "[]";
    }

    public Float getValor(int index) {
        return pesos.get(index);
    }
    
    public String getValorBr(int index) {
        return pesos.get(index).toString().replace('.', ',');
    }
    
    public int getQtdPesos(){
        return pesos.size();
    }
    
    //soma a lista de peso
    public Float getSoma(){
        if(!this.pesos.isEmpty() && 
            this.pesos != null){
            Float cont = Float.parseFloat("0");
            for (int i = 0; i < this.pesos.size(); i++){
                cont += this.getValor(i);
            }
            return cont;
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        if(this.opcao == -1){
            return "{" + "\n opcao = " + "não foi escolhida" + 
                         "\n coef = " + this.coef + 
                         "\n lista = " + this.pesos + '}';           
        }else{
            return "{" + "\n opcao = " + this.Opcoes[this.opcao] + 
                         "\n coef = " + this.coef + 
                         "\n lista = " + this.pesos + '}';    
        }
    }

    public void setLista(List<Float> lista) {
        this.pesos = lista;
    }

    public void addPeso(String peso) {
        this.pesos.add(Float.parseFloat(peso.replace(',', '.')));
    }
    
    public void editPeso(String peso, int index) {
        this.removePeso(index);
        this.pesos.add(index, Float.parseFloat(peso.replace(',', '.')));
    }
    
    public void removePeso(int index) {
        this.pesos.remove(index);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
       
    public String getStringPesos(){
        String pesos = new String ("");
        
        for (int i = 0; i < this.pesos.size(); i++){
            pesos = pesos+ ", "+this.pesos.get(i);
        }
        
        return pesos;
    }
}
