/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.List;
import static DTO.CalculoErro.calculaMAPE;
import static DTO.CalculoErro.calculaMAD;
import static DTO.CalculoErro.calculaMAE;
import static DTO.CalculoErro.somaErroABS;
import static DTO.CalculoErro.somaErroABSNormalizada;
import static DTO.CalculoErro.somaListaErroABS;
import static DTO.CalculoErro.somaListaErroABSNormalizada;
/**
 *
 * @author jacka
 */
public class AuxEs {
    //pesos e coeficiente
    private double coefSp;
    //lista de dados
    private List<Float> dados;
    private List<Float> copiaDados;

    //listas de dados auto-regressivo
    private List<Float> autoSmoot;
    //lista de erros
    private List<Float> erroEs;
    //vetor que acumula as posições que houve a substituição
    //seu erro será nulo, e isso tendencia o mape
    private List<Integer> subsEs;
    //vetor que contem intervalos de mês
    private Guia guia;
    //soma
    private int somaNulls;
    
//    private Float madES;
//    private Float maeES;
//    private Float mapeES;
    
    private List<Integer> nullMensal;
    private List<Integer> subsMensal;
    private List<Integer> nElementMensal;
    
    private List<Float> madListaES;
    private List<Float> maeListaES;
    private List<Float> mapeListaES;
    
    //construtor
    public AuxEs(List<Float> dados, double coef, Guia guia) {
        
        this.coefSp =coef;
                
        this.dados = new ArrayList<>(dados);
        this.copiaDados = new ArrayList<>(dados);
        //listas auxiliares
        this.autoSmoot = new ArrayList<Float>();
        this.erroEs = new ArrayList<Float>();
        this.subsEs = new ArrayList<Integer>();
        //valores acumulados
        
//        this.madES = Float.parseFloat("0");               
//        this.maeES = Float.parseFloat("0");                
//        this.mapeES = Float.parseFloat("0");
        this.madListaES = new ArrayList<Float>();
        this.maeListaES = new ArrayList<Float>();
        this.mapeListaES = new ArrayList<Float>();
        this.somaNulls = this.contNull(this.copiaDados);
        this.guia = guia;
        
        this.preencherAutoSmooth();
        this.nullMensal = this.contListaNull(this.autoSmoot);
        this.subsMensal = this.contListaQtdSub();
 
        this.calculaErro();

        this.nElementMensal = this.contListaQtdErro();
//        this.esMAD();
        //System.out.println(this.madES);
//        this.esMAE();
        //System.out.println(this.maeES);
//        this.esMAPE();
        //System.out.println(this.esMAPE());

        this.madListaES = this.esListaMAD();
        this.maeListaES = this.esListaMAE();
        this.mapeListaES = this.esListaMAPE();
        
//        this.relatorio();
    }
    
    //Getter e Setter
    public List<Float> getDados() {
        return dados;
    }

    public List<Float> getDadosProcessados() {
        return copiaDados;
    }

    public void setDados(List<Float> dados) {
        this.dados = dados;
    }

    public List<Float> getAutoSmoot() {
        return autoSmoot;
    }

    public void setAutoSmoot(List<Float> autoSmoot) {
        this.autoSmoot = autoSmoot;
    }

    public List<Float> getErroEs() {
        return erroEs;
    }

    public void setErroEs(List<Float> erroEs) {
        this.erroEs = erroEs;
    }

//    public String getMadES() {
//        return ""+madES;
//    }
//
//    public void setMadES(String madES) {
//        this.madES = Float.parseFloat(madES);
//    }
//
//    public String getMaeES() {
//        return ""+maeES;
//    }
//
//    public void setMaeES(String maeES) {
//        this.maeES = Float.parseFloat(maeES);
//    }
//
//    public String getMapeES() {
//        return ""+mapeES;
//    }
//
//    public void setMapeES(String mapeES) {
//        this.mapeES = Float.parseFloat(mapeES);
//    }

    public int getNullMensal(int index) {
        return nullMensal.get(index);
    }

    public int getSubsMensal(int index) {
        return subsMensal.get(index);
    }

    public int getElementosMensal(int index) {
        return nElementMensal.get(index);
    }

    //------Funções Administrativas entre vetores--------
    public void relatorio(){
        System.out.println("***********************************************");
        System.out.println("QTD lacunas totais Antes: " + this.somaNulls);
        System.out.println("QTD lacunas totais Depois: " + this.contNull(this.autoSmoot));
        System.out.println("QTD substituida: " + this.subsEs.size());
        System.out.println("Numero de elementos (n): " + (this.autoSmoot.size()-
                                                          this.subsEs.size()-
                                                          this.contNull(this.autoSmoot)));
        
        System.out.println("Tamanho Lista de ErrosES: " + this.contListaQtdErro().size());
        System.out.println("Tamanho Lista de Nulls: " + this.contListaNull(this.autoSmoot).size());
        System.out.println("Tamanho Lista de QtdSub: " + this.contListaQtdSub().size());
        System.out.println("Quantidade de meses: " + this.guia.getApendice().size());

        System.out.println("Tamanho Mad Lista: "+ this.madListaES.size() + " valores: " + this.madListaES);
        System.out.println("Tamanho Mae Lista: "+ this.maeListaES.size() + " valores: " + this.maeListaES);
        System.out.println("Tamanho Mape Lista: "+ this.mapeListaES.size() + " valores: " + this.mapeListaES);
        System.out.println("***********************************************");
    }
    
    //função de texte a remover
    public void relatorioMensal(){


        int mes = 3;
        int ano = 2017; 
        
        List<Integer> cont = this.contListaNull(this.autoSmoot);
        this.nElementMensal = this.contListaQtdErro();
        List<Integer> erroEs = this.contListaQtdErro();
        List<Integer> subsEs = this.contListaQtdSub();

        
        for(int i = 0; i < this.guia.getApendice().size(); i++){

            System.out.println("***********************************************");
            System.out.println("Mes: " + mes);
            System.out.println("Ano: " + ano);
            System.out.println("QTD erros mensal: " + erroEs.get(i));
            System.out.println("QTD substituições: " + subsEs.get(i));
            System.out.println("QTD null mensal: " + cont.get(i));

            System.out.println("Numero de elementos (n): " + (erroEs.get(i)-
                                                              subsEs.get(i)-
                                                              cont.get(i)));
            System.out.println("***********************************************");            
            mes++;
            if(mes == 13){
                mes = 1;
                ano++;
            }            
        }
    }
    
    //preenche meu vetor de predicao
    public void preencherAutoSmooth(){
        for(int index = 0; index < this.dados.size(); index++){
            if(this.autoSmoot.isEmpty()){
                this.autoSmoot.add(this.dados.get(index));
            }else{
                if(this.autoSmoot.get(this.autoSmoot.size()-1) == null){
                    this.autoSmoot.add(this.dados.get(index));
                }else{//erro, sem checagem da quantidade de preenchimento antes da formula 
                    ////maquina de estados
                    if(this.dados.get(index) == null){
                        if(this.trocaValorNull(index) == true){
                            this.formulaES(index);
                            this.troca(index);
                        }else{
                            this.autoSmoot.add(this.dados.get(index));
                        }
                    }else{
                        this.formulaES(index);
                    }
                }
            }   
        }
    }
    
    //Predição-troca o valor null pelo valor predito
    public void troca(int index){
        this.copiaDados.remove(index);
        this.copiaDados.add(index, this.autoSmoot.get(index));
        this.subsEs.add(index);
    }
    
    //verifica se eh possivel trocar valor null
    //obedece as regras das 3 substituições
    public boolean trocaValorNull(int index){
        return !(this.subsEs.contains(index-1) && 
                this.subsEs.contains(index-2) &&
                this.subsEs.contains(index-3));
    }
    
    //------Funções para calculo-------
    //calcula o vetor erroES
    public void calculaErro(){
        for(int index = 0; index < this.copiaDados.size(); index++){        
            if(this.autoSmoot.isEmpty() || this.copiaDados.isEmpty()){
                //mensagem para erro de listagem
                System.out.println("(AuxEs-void calculoErro):lista de dados ou lista de predicao vazias...");
            }else{
                if(this.autoSmoot.get(index) == null && this.copiaDados.get(index) == null){
                    this.erroEs.add(null);
                }else{
                    this.erroEs.add((this.copiaDados.get(index)-this.autoSmoot.get(index)));
                }
            }
        }
    }
    
    //Equação Exponetial Smooth obs: add null no erro para a logica fazer sentido
    public void formulaES(int index){
        this.autoSmoot.add(this.autoSmoot.get(index-1) + Float.parseFloat
                          ("" + this.coefSp * (this.copiaDados.get(index-1)-
                                               this.autoSmoot.get(index-1))));
    }
    
    //contador de valores nulls
    public int contNull(List<Float> lista){
        int qtd = 0;
        for(int index = 0; index < lista.size(); index++){
            if(lista.get(index) == null){
                qtd++;
            }
        }
        return qtd;
    }

    //contador de valores nulls mensal
    public List<Integer> contListaNull(List<Float> lista){
        
        List<Integer> listaCont = new ArrayList<Integer>();
        
        for(int index = 0; index < this.guia.getApendice().size(); index++){
        int qtd = 0;
            for(int i = this.guia.getMarcador(index).getInicio(); 
                    i <= this.guia.getMarcador(index).getFim(); 
                    i++){
                if(lista.get(i) == null){
                    qtd++;
                }
            }
            listaCont.add(qtd);
        }
        return listaCont;
    }
    
    //contador de elementos na lista de erro mensal
    public List<Integer> contListaQtdErro(){
        
        List<Integer> listaCont = new ArrayList<Integer>();
        
        int qtd = 0;
        
        for(int index = 0; index < this.guia.getApendice().size(); index++){

            qtd = this.erroEs.subList(this.guia.getMarcador(index).getInicio(), 
                                       (this.guia.getMarcador(index).getFim() + 1)).size();
            listaCont.add(qtd);
        }
        return listaCont;
    }
    
    //contador de valores nulls mensal
    public List<Integer> contListaQtdSub(){
        
        List<Integer> listaCont = new ArrayList<Integer>();
        
        int qtd = 0;
        
        for(int index = 0; index < this.guia.getApendice().size(); index++){
            qtd = 0;
            for(Integer valor : this.subsEs){
                if(valor >= this.guia.getMarcador(index).getInicio() &&
                   valor <= this.guia.getMarcador(index).getFim()){
                   qtd++;
                }
            }
            listaCont.add(qtd);
        }
        return listaCont;
    }

    //claculo de erro
    
    //Desvio Absoluto Médio
//    public void esMAD(){
//        float abs = somaErroABS(this.erroEs, this.subsEs);
//        int cont = this.contNull(this.autoSmoot);
//        float mad = calculaMAD(abs, 
//                            this.erroEs.size(), 
//                            this.subsEs.size(), 
//                            cont);
//        this.setMadES(""+mad);
//    }

    //Erro Médio Absoluto
//    public void esMAE(){
//        float absn = somaErroABSNormalizada(this.dados, this.erroEs, this.subsEs);
//        int cont = this.contNull(this.autoSmoot);
//        float mae = calculaMAE(absn, 
//                            this.erroEs.size(), 
//                            this.subsEs.size(), 
//                            cont);
//        this.setMaeES(""+mae);
//    }
    
    //Erro Absoluto Médio Percentual
//    public void esMAPE(){
//        this.setMapeES(calculaMAPE(this.getMaeES()).toString());        
//    }
    
    //Lista Mensal de Desvio Absoluto Médio
    public List<Float> esListaMAD(){
        
        List<Float> lista = new ArrayList<Float>();

//        int mes = 3;
//        int ano = 2017; 
        
        List<Float> abs = somaListaErroABS(this.erroEs,
                                            this.subsEs,
                                           this.guia);
//        this.nElementMensal = this.contListaQtdErro();
//        List<Integer> erroEs = this.contListaQtdErro();

        float mad = 0;
        
        for(int i = 0; i < abs.size(); i++){
            mad = 0;
            mad = calculaMAD(abs.get(i), 
                                this.nElementMensal.get(i), 
                                 this.subsMensal.get(i), 
                                this.nullMensal.get(i));
            lista.add(mad);
//            System.out.println("***********************************************");
//            System.out.println("Mes: " + mes);
//            System.out.println("Ano: " + ano);
//            System.out.println("QTD erros mensal: " + erroEs.get(i));
//            System.out.println("QTD substituições: " + subsEs.get(i));
//            System.out.println("QTD null mensal: " + cont.get(i));
//            System.out.println("QTD MAD: " + mad);
//
//            System.out.println("Numero de elementos (n): " + (erroEs.get(i)-
//                                                              subsEs.get(i)-
//                                                              cont.get(i)));
//            System.out.println("***********************************************");            
//            mes++;
//            if(mes == 13){
//                mes = 1;
//                ano++;
//            }            
        }
        return lista;
    }
    
    //Lista Mensal de Erro Médio Absoluto
    public List<Float> esListaMAE(){
        
        List<Float> lista = new ArrayList<Float>();
        
//        int mes = 3;
//        int ano = 2017; 
        
        List<Float> absn = somaListaErroABSNormalizada(this.copiaDados,
                                                       this.erroEs,
                                                        this.subsEs,
                                                       this.guia);
//        List<Integer> erroEs = this.contListaQtdErro();

        float mae = 0;
        for(int i = 0; i < absn.size(); i++){
            mae = 0;        
            mae = calculaMAE(absn.get(i), 
                                    this.nElementMensal.get(i), 
                                     this.subsMensal.get(i), 
                                    this.nullMensal.get(i));
            lista.add(mae);
//            System.out.println("***********************************************");
//            System.out.println("Mes: " + mes);
//            System.out.println("Ano: " + ano);
//            System.out.println("QTD erros mensal: " + erroEs.get(i));
//            System.out.println("QTD substituições: " + subsEs.get(i));
//            System.out.println("QTD null mensal: " + cont.get(i));
//            System.out.println("QTD MAD: " + mae);
//
//            System.out.println("Numero de elementos (n): " + (erroEs.get(i)-
//                                                              subsEs.get(i)-
//                                                              cont.get(i)));
//            System.out.println("***********************************************");            
//            mes++;
//            if(mes == 13){
//                mes = 1;
//                ano++;
//            }            
        }
        return lista;
    }
   
    //Lista Mensal de Erro Absoluto Médio Percentual
    public List<Float> esListaMAPE(){
        List<Float> lista = new ArrayList<Float>();        
        
        for(Float valor : this.esListaMAE()){
            lista.add(calculaMAPE(valor.toString()));
        }
        return lista;       
    }
    
}
