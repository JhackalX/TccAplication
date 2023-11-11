/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Object.AnaliseMensal;
import Object.Info;
import Object.ListaClassificacao;
import Tabela.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacka
 */
public class CtrlGeral{
    
    private static Info medicao = null;
    private static List<ListaClassificacao> listaClassificacao = null;
    private static Guia guia = null;
    
    public CtrlGeral() {
        
        this.medicao = new Info();
        this.listaClassificacao = new ArrayList<ListaClassificacao>();
     
    }

    public static Info getMedicao() {
        if(medicao == null){
            return null;           
        }else{
//            medicao.imprimirColuna();
            return medicao;
        } 
    }

    public static ArrayList<String> getColuna() {
        ArrayList<String> colunas = new ArrayList<String>();
        if(medicao == null){
            colunas.add("vazio");
            return colunas;           
        }else{
            for(int i = 0; i < medicao.getColuna().length; i++){
                colunas.add(medicao.getColuna(i));
            }
//            medicao.imprimirColuna();
            return colunas;
        } 
    }
    
    public static List<ListaClassificacao> setListaClassificacao(){

        List<ListaClassificacao> listaClassificacao = new ArrayList<ListaClassificacao>();
        
        for(int coluna = 2; coluna < medicao.getColunaCount(); coluna++){
           
            int mes = medicao.getInicMonth();
            int ano = medicao.getInicYear();            
            ListaClassificacao listCla = new ListaClassificacao(medicao.getColuna(coluna));            
            
            for(int i = 0; i < getApendice().size(); i++){
                                
                Validacao valMes = new Validacao(medicao.subListaIndex(coluna,
                                                                      getMarcador(i).getInicio(),
                                                                        (getMarcador(i).getFim()+1)));
                AnaliseMensal nova = new AnaliseMensal(ano, 
                                                       mes, 
                                             valMes.getStrCoefSp(), 
                                              valMes.getStrTendencia(), 
                                         valMes.getEstacionariedade());

                listCla.addClassificador(nova);
                
                mes++;
                if(mes == 13){
                    mes = 1;
                    ano++;
                }
            }
            listaClassificacao.add(listCla);
        }

        return listaClassificacao;
    }
    
    public static void gerarMetEs(){

        List<AuxEs> lista = new ArrayList<AuxEs>();

        for(int a = 2; a < medicao.getColunaCount(); a++){
            lista.add(new AuxEs(medicao.getLista(a).getAllDados(), 
                                 medicao.getMetodologiaAplicada().getCoef(),
                                     guia));
            
        }

        for(int coluna = 0; coluna < listaClassificacao.size(); coluna++){

            for(int index = 0; index < guia.getApendice().size(); index++){

                listaClassificacao.get(coluna).getClassificador(index).setErros(
                                                lista.get(coluna).esListaMAD().get(index).toString(), 
                                                lista.get(coluna).esListaMAE().get(index).toString(), 
                                                lista.get(coluna).esListaMAPE().get(index).toString());
                listaClassificacao.get(coluna).getClassificador(index).setEstatisca(
                                                                   lista.get(coluna).getNullMensal(index), 
                                                                   lista.get(coluna).getSubsMensal(index), 
                                                                lista.get(coluna).getElementosMensal(index));

            }
        }

        for(int i = 2; i < listaClassificacao.size(); i++){
            System.out.println("=======================================");
            System.out.println(listaClassificacao.get(i).getTitulo());
            System.out.println("=======================================");
            for(int o = 0; o < guia.getApendice().size(); o++){
                System.out.println("Erro::==============================");
                System.out.println(listaClassificacao.get(i).getClassificador(o).imprimir());
                
            }
        }
    }
    
    public static void gerarMetAR(){

        List<AuxAr> lista = new ArrayList<AuxAr>();

        for(int a = 2; a < medicao.getColunaCount(); a++){
            lista.add(new AuxAr(medicao.getMetodologiaAplicada().getPesos(),
                                medicao.getLista(a).getAllDados(),  
                                     guia));
            
        }

        for(int coluna = 0; coluna < listaClassificacao.size(); coluna++){

            for(int index = 0; index < guia.getApendice().size(); index++){

                listaClassificacao.get(coluna).getClassificador(index).setErros(
                                                lista.get(coluna).arListaMAD().get(index).toString(), 
                                                lista.get(coluna).arListaMAE().get(index).toString(), 
                                                lista.get(coluna).arListaMAPE().get(index).toString());
                listaClassificacao.get(coluna).getClassificador(index).setEstatisca(
                                                                   lista.get(coluna).getNullMensal(index), 
                                                                   lista.get(coluna).getSubsMensal(index), 
                                                                lista.get(coluna).getElementosMensal(index));

            }
        }

        for(int i = 2; i < listaClassificacao.size(); i++){
            System.out.println("=======================================");
            System.out.println(listaClassificacao.get(i).getTitulo());
            System.out.println("=======================================");
            for(int o = 0; o < guia.getApendice().size(); o++){
                System.out.println("Erro::==============================");
                System.out.println(listaClassificacao.get(i).getClassificador(o).imprimir());
                
            }
        }
    }
    
    public static void setMedicao(Info medicao) {
//        medicao.imprimirColuna();
        CtrlGeral.medicao = medicao;
//        Tabela.Funcionalidades.setColumn(CtrlGeral.meses);
//        System.out.println("setMEdicao!!!!!");
        Guia nova = new Guia();
        nova.gerarGuia(medicao.getLista(2).getDados());
        CtrlGeral.setApendice(nova);
//        apendice.imprimir();
        CtrlGeral.listaClassificacao = setListaClassificacao();
       
    }

    public static List<ListaClassificacao> getListaClassificacoes() {
        return listaClassificacao;
    }

    public ListaClassificacao getListaClassificacao(int index) {
        return listaClassificacao.get(index);
    }

    public void setListaClassificacao(List<ListaClassificacao> listaClassificacao) {
        this.listaClassificacao = listaClassificacao;
    }

    public void addListaClassificacao(ListaClassificacao listaClassificacao) {
        this.listaClassificacao.add(listaClassificacao);
    }

    public static List<Marcador> getApendice() {
        return guia.getApendice();
    }

    public static Marcador getMarcador(int index) {
        return guia.getMarcador(index);
    }

    public static void setApendice(Guia apendice) {
        CtrlGeral.guia = apendice;
    }
    //Analizar aqui com mais calma
    public static List<Employee> gerarDadosTabela(){

        if(medicao == null || medicao.getLinhaCount() == 0){
            return null;
        }else{
            List<Employee> lista = new ArrayList<Employee>();
            for(int i = 0; i < medicao.getLinhaCount(); i++){
                Employee e = new Employee();
//                System.out.println(medicao.getLinha(i));
//                e.setRow(meses);
//                System.out.println("Quantidade de elementos da linha "+ i +" eh: " + medicao.getLinha(i).size());
                e.setLine(medicao.getLinha(i));
//                System.out.println(e.getLine());
                lista.add(e);
            }
            return lista;  
        }
    } 
}
