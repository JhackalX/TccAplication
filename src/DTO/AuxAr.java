/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import static DTO.CalculoErro.calculaMAD;
import static DTO.CalculoErro.calculaMAE;
import static DTO.CalculoErro.calculaMAPE;
import static DTO.CalculoErro.somaErroABS;
import static DTO.CalculoErro.somaErroABSNormalizada;
import static DTO.CalculoErro.somaListaErroABS;
import static DTO.CalculoErro.somaListaErroABSNormalizada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacka
 */
public class AuxAr {
    //pesos e coeficiente
    private List<Float> pesos;
    //lista de dados, apenas valores numéricos para contabilidades e formulas
    private List<Float> dados;
    private List<Float> copiaDados;
    //listas de dados auto-Medias moveis ponderadas
    private List<Float> autoAr;
    //lista de erros
    private List<Float> erroAr;
    //vetor que acumula as posições que houve a substituição
    //seu erro será nulo, e isso tendencia o mape
    private List<Integer> subsAr; 
    private List<Integer> copiaAr;
    //vetor que contem apendices de intervalos de mês
    private Guia guia;    
    //soma de valores nulos geral
    private int somaNulls;
    //variaveis para acumular erros e valores para o documento texto
//    private Float madAR;
//    private Float maeAR;
//    private Float mapeAR;

    private List<Integer> nullMensal;
    private List<Integer> subsMensal;
    private List<Integer> nElementMensal;
    
    private List<Float> madListaAR;
    private List<Float> maeListaAR;
    private List<Float> mapeListaAR;
    
    public AuxAr(List<Float> pesos, List<Float> dados, Guia guia) {
        this.pesos = pesos;
        this.dados = new ArrayList<>(dados);
        this.copiaDados = new ArrayList<>(dados);
        //listas auxiliares
        this.autoAr =  new ArrayList<Float>();
        this.erroAr =  new ArrayList<Float>();
        this.subsAr = new ArrayList<Integer>();
        this.copiaAr = new ArrayList<Integer>();
        //valores acumulados  
//        this.madAR = Float.parseFloat("0");
//        this.maeAR = Float.parseFloat("0");
//        this.mapeAR = Float.parseFloat("0");
        this.madListaAR = new ArrayList<Float>();
        this.maeListaAR = new ArrayList<Float>();
        this.mapeListaAR = new ArrayList<Float>();
        
        this.somaNulls = this.contNull(this.copiaDados);
        
        this.guia = guia; 
        
        this.preencherAutoAR();
        this.nullMensal = this.contListaNull(this.autoAr);
        this.subsMensal = this.contListaQtdSub();
        
        this.calculaErro();
        this.nElementMensal = this.contListaQtdErro();        
//        this.arMAD();
        //System.out.println(this.madAR);
//        this.arMAE();
        //System.out.println(this.maeAR);
//        this.arMAPE();
        //System.out.println(this.arMAPE());
        this.relatorio();
        
        this.madListaAR = this.arListaMAD();
        this.maeListaAR = this.arListaMAE();
        this.mapeListaAR = this.arListaMAPE();        

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

    public List<Float> getAutoAr() {
        return autoAr;
    }

    public void setAutoAr(List<Float> autoAr) {
        this.autoAr = autoAr;
    }

    public List<Float> getErroAr() {
        return erroAr;
    }

    public void setErroAr(List<Float> erroAr) {
        this.erroAr = erroAr;
    }

    public List<Integer> getSubsAr() {
        return subsAr;
    }

    public void setSubsAr(List<Integer> subsAr) {
        this.subsAr = subsAr;
    }

//    public String getMadAR() {
//        return ""+madAR;
//    }
//
//    public void setMadAR(String madAR) {
//        this.madAR = Float.parseFloat(madAR);
//    }
//
//    public String getMaeAR() {
//        return ""+maeAR;
//    }
//
//    public void setMaeAR(String maeAR) {
//        this.maeAR = Float.parseFloat(maeAR);
//    }
//
//    public String getMapeAR() {
//        return ""+mapeAR;
//    }
//
//    public void setMapeAR(String mapeAR) {
//        this.mapeAR = Float.parseFloat(mapeAR);
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
    private void relatorio() {
        System.out.println("***********************************************");
        System.out.println("QTD lacunas totais Antes: " + this.somaNulls);
        System.out.println("QTD lacunas totais Depois: " + this.contNull(this.autoAr));
        System.out.println("QTD substituida: " + this.subsAr.size());
        System.out.println("Numero de elementos (n): " + (this.autoAr.size()-
                                                          this.subsAr.size()-
                                                          this.contNull(this.autoAr)));
        
        System.out.println("Tamanho Lista de ErrosES: " + this.contListaQtdErro().size());
        System.out.println("Tamanho Lista de Nulls: " + this.contListaNull(this.autoAr).size());
        System.out.println("Tamanho Lista de QtdSub: " + this.contListaQtdSub().size());
        System.out.println("Quantidade de meses: " + this.guia.getApendice().size());

        System.out.println("Tamanho Mad Lista: "+ this.madListaAR.size() + " valores: " + this.madListaAR);
        System.out.println("Tamanho Mae Lista: "+ this.maeListaAR.size() + " valores: " + this.maeListaAR);
        System.out.println("Tamanho Mape Lista: "+ this.mapeListaAR.size() + " valores: " + this.mapeListaAR);
        System.out.println("***********************************************");
    }
    
    //função para teste
    public void relatorioMensal(){


        int mes = 3;
        int ano = 2017; 
        
        List<Integer> cont = this.contListaNull(this.autoAr);
        this.nElementMensal = this.contListaQtdErro();
        List<Integer> erroEs = this.contListaQtdErro();
        List<Integer> subsEs = this.contListaQtdSub();

        
        for(int i = 0; i < this.guia.getApendice().size(); i++){

            System.out.println("***********************************************");
            System.out.println("Mes: " + mes);
            System.out.println("Ano: " + ano);
            System.out.println("QTD erros mensal: " + erroAr.get(i));
            System.out.println("QTD substituições: " + subsAr.get(i));
            System.out.println("QTD null mensal: " + cont.get(i));

            System.out.println("Numero de elementos (n): " + (erroAr.get(i)-
                                                              subsAr.get(i)-
                                                              cont.get(i)));
            System.out.println("***********************************************");            
            mes++;
            if(mes == 13){
                mes = 1;
                ano++;
            }            
        }
    }
    
    private void preencherAutoAR() {
        for(int index = 0; index < this.dados.size(); index++){
            if(this.autoAr.isEmpty() || 
               !this.checkCadeia(index-1)){
                this.autoAr.add(this.dados.get(index));
                this.copiaAr.add(index);
            }else{
                if(this.checkCadeia(index-1) &&
         //          this.trocaValorNull(index) &&
                   (this.dados.get(index) != null)){
                    this.formulaAR(index-1);                    
                }else{
                    if((this.dados.get(index) == null) &&
                        this.checkCadeia(index-1) &&
                        this.trocaValorNull(index)){
                        this.formulaAR(index-1);
                        this.troca(index);                        
                    }else{
                        this.autoAr.add(this.dados.get(index));
                        this.copiaAr.add(index);
                    }
                }
            }  
        }
    }

    //Predição-troca o valor null pelo valor predito    
    private void troca(int index) {
        this.copiaDados.remove(index);
        this.copiaDados.add(index, this.autoAr.get(index));
        this.subsAr.add(index);
    }
    
    //verifica se eh possivel trocar valor null
    //obedece as regras das 3 substituições    
    private boolean trocaValorNull(int index) {
        //---------------------------------------------------//
        return !(this.subsAr.contains(index-1) && 
                this.subsAr.contains(index-2) &&
                this.subsAr.contains(index-3));
    }

    //verifica se existe valor null na cadeia de valores 
    //na formula arima de pesos
    private boolean checkCadeia(int inicial) {
        if((this.autoAr.size() - this.pesos.size()) < 0){
            return false;
        }
        for (int index = 0; index < this.pesos.size(); index++ ){
            if(this.dados.get(inicial - index) == null){
                return false;
            }
        }
        return true;
    }
    
    //------Funções para calculo-------
    //calcula o vetor erroAR   
    private void calculaErro() {
        for(int index = 0; index < this.copiaDados.size(); index++){        
            if(this.autoAr.isEmpty() || this.copiaDados.isEmpty()){
                //mensagem para erro de listagem
                System.out.println("(AuxAr-void calculoErro):lista de dados ou lista de predicao vazias...");
            }else{
                if((this.autoAr.get(index) == null || 
                    this.copiaDados.get(index) == null) || 
                    (this.copiaAr.contains(index) ||
                     this.subsAr.contains(index))){
                    this.erroAr.add(null);
                }else{
                    this.erroAr.add((this.copiaDados.get(index)-
                                     this.autoAr.get(index)));
                }
            }
        }    
    }

    //Equação arima de pesos obs: add null no erro para a logica fazer sentido
    private void formulaAR(int index) {
        float soma = 0;
        for(int pos = 0; pos < this.pesos.size(); pos++){
            soma += this.copiaDados.get(index - pos)*this.pesos.get(pos);
        }
        this.autoAr.add(soma);
    }
    
    //contador de valores nulls
    private int contNull(List<Float> lista) {
        int qtd = 0;
        for(int index = 0; index < lista.size(); index++){
            if(lista.get(index) == null){
                qtd++;
            }
        }
        return qtd;
    }
    
    //Desvio Absoluto Médio
//    private void arMAD() {
//        float abs = somaErroABS(this.erroAr, this.subsAr);
//        //int cont = this.contNull(this.autoAr);
//        float mad = calculaMAD(abs, 
//                            this.erroAr.size(), 
//                             this.subsAr.size(), 
//                            this.copiaAr.size());
//        this.setMadAR(""+mad);    
//    }

    //Erro Médio Absoluto
//    private void arMAE() {
//       float absn = somaErroABSNormalizada(this.dados, 
//                                            this.erroAr,
//                                            this.subsAr);
//        //int cont = this.contNull(this.autoAr);
//        float mae = calculaMAE(absn, 
//                            this.erroAr.size(), 
//                             this.subsAr.size(), 
//                            this.copiaAr.size());
//        this.setMaeAR(""+mae);
//    }

    //Erro Absoluto Médio Percentual
//    private void arMAPE() {
//       this.setMapeAR(calculaMAPE(this.getMaeAR()).toString());        
//    }
    
    //contador de elementos na lista de erro mensal
    private List<Integer> contListaNull(List<Float> lista) {
        
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
    
    //contador de valores nulls mensal
    private List<Integer> contListaQtdSub() {
        
        List<Integer> listaCont = new ArrayList<Integer>();
        
        int qtd = 0;
        
        for(int index = 0; index < this.guia.getApendice().size(); index++){
            qtd = 0;
            for(Integer valor : this.subsAr){
                if(valor >= this.guia.getMarcador(index).getInicio() &&
                   valor <= this.guia.getMarcador(index).getFim()){
                   qtd++;
                }
            }
            listaCont.add(qtd);
        }
        return listaCont;    
    }
    
    //contador de elementos na lista de erro mensal
    private List<Integer> contListaQtdErro() {
        
        List<Integer> listaCont = new ArrayList<Integer>();
        
        int qtd = 0;
        
        for(int index = 0; index < this.guia.getApendice().size(); index++){

            qtd = this.erroAr.subList(this.guia.getMarcador(index).getInicio(), 
                                       (this.guia.getMarcador(index).getFim() + 1)).size();
            listaCont.add(qtd);
        }
        return listaCont;    
    }
    
    //Lista Mensal de Desvio Absoluto Médio
    public List<Float> arListaMAD() {
        
        List<Float> lista = new ArrayList<Float>();

//        int mes = 3;
//        int ano = 2017; 
        
        List<Float> abs = somaListaErroABS(this.erroAr,
                                            this.subsAr,
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
    public List<Float> arListaMAE() {
        
        List<Float> lista = new ArrayList<Float>();
        
//        int mes = 3;
//        int ano = 2017; 
        
        List<Float> absn = somaListaErroABSNormalizada(this.copiaDados,
                                                       this.erroAr,
                                                        this.subsAr,
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
    public List<Float> arListaMAPE() {
        List<Float> lista = new ArrayList<Float>();        
        
        for(Float valor : this.arListaMAE()){
            lista.add(calculaMAPE(valor.toString()));
        }
        return lista;  
    }
}
