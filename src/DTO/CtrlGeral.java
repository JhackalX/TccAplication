/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DAO.CtrlDao;
import Object.AnaliseMensal;
import Object.Estacao;
import Object.Info;
import Object.ListaClassificacao;
import Object.Sensor;
import Tabela.Employee;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacka
 */
public class CtrlGeral{
    
    private Info medicao = null;
//    private Info copiaMedicao = null;
    //lista de gerencias
    private List<ListaClassificacao> listaClassificacao = null;
//    private List<ListaClassificacao> listaClassificacaoDuplicada = null;
    private List<AuxAr> listaAR = null;
    private List<AuxEs> listaEs = null;
    
    private Guia guia = null;
    private CtrlDao ctrlDao;
    private Configuracoes config;
    
    public CtrlGeral() {
        this.config = new Configuracoes();
        this.ctrlDao =  new CtrlDao(config.getCaminhoBasesEstacoes());
        this.medicao = new Info();
        this.listaClassificacao = new ArrayList<ListaClassificacao>();
    }

    public Info getMedicao() {
        if(medicao == null){
            return null;           
        }else{
//            medicao.imprimirColuna();
            return medicao;
        } 
    }

    public ArrayList<String> getColuna() {
        ArrayList<String> colunas = new ArrayList<String>();
        colunas.add("Data");
        colunas.add("Hora");
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
    
    public List<ListaClassificacao> setListaClassificacao(){

        List<ListaClassificacao> listaClassificacao = new ArrayList<ListaClassificacao>();
        
        for(int coluna = 2; coluna < medicao.getColunaCount(); coluna++){
           
            int mes = medicao.getInicMonth();
            int ano = medicao.getInicYear();            
            ListaClassificacao listCla = new ListaClassificacao(medicao.getColuna(coluna));            
            
            for(int i = 0; i < getApendice().size(); i++){
                                
                Validacao valMes = new Validacao(medicao.subListaIndex(coluna,
                                                                      getMarcador(i).getInicio(),
                                                                        (getMarcador(i).getFim())));
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
    
    public void gerarMetEs(){

        this.listaEs = new ArrayList<AuxEs>();

        for(int a = 2; a < medicao.getColunaCount(); a++){
            this.listaEs.add(new AuxEs(medicao.getLista(a).getAllDados(), 
                                 medicao.getMetodologiaAplicada().getCoef(),
                                     guia));   
        }

        for(int coluna = 0; coluna < listaClassificacao.size(); coluna++){
            for(int index = 0; index < guia.getApendice().size(); index++){

                listaClassificacao.get(coluna).getClassificador(index).setErros(
                                                this.listaEs.get(coluna).esListaMAD().get(index).toString(), 
                                                this.listaEs.get(coluna).esListaMAE().get(index).toString(), 
                                                this.listaEs.get(coluna).esListaMAPE().get(index).toString());
                listaClassificacao.get(coluna).getClassificador(index).setEstatisca(
                                                                   this.listaEs.get(coluna).getQtdNullMensal(index), 
                                                                   this.listaEs.get(coluna).getQtdSubsMensal(index), 
                                                                this.listaEs.get(coluna).getQtdElementosMensal(index));
            }
        }
        System.out.println(Funcionalidades.relatorioEs(listaEs));
//        this.imputarValoresEs();
       
        for(int i = 2; i < listaClassificacao.size(); i++){
            System.out.println("=======================================");
            System.out.println(listaClassificacao.get(i).getTitulo());
            System.out.println("=======================================");
            
            System.out.println("Erro Min::==============================");
            System.out.println(listaEs.get(i-2).getMimMape());
            System.out.println("Erro Min::==============================");
            System.out.println(listaEs.get(i-2).getMaxMape());
        }
    }
    
    public void gerarMetAR(){

        this.listaAR = new ArrayList<AuxAr>();

        for(int a = 2; a < medicao.getColunaCount(); a++){
            this.listaAR.add(new AuxAr(medicao.getMetodologiaAplicada().getPesos(),
                                medicao.getLista(a).getAllDados(),  
                                     guia));
        }

        for(int coluna = 0; coluna < listaClassificacao.size(); coluna++){
            for(int index = 0; index < guia.getApendice().size(); index++){

                listaClassificacao.get(coluna).getClassificador(index).setErros(
                                                this.listaAR.get(coluna).arListaMAD().get(index).toString(), 
                                                this.listaAR.get(coluna).arListaMAE().get(index).toString(), 
                                                this.listaAR.get(coluna).arListaMAPE().get(index).toString());
                listaClassificacao.get(coluna).getClassificador(index).setEstatisca(
                                                                   this.listaAR.get(coluna).getQtdNullMensal(index), 
                                                                   this.listaAR.get(coluna).getQtdSubsMensal(index), 
                                                                this.listaAR.get(coluna).getQtdElementosMensal(index));
            }
        }
        System.out.println(Funcionalidades.relatorioAr(listaAR));
        
//        this.imputarValoresAr();
       
        for(int i = 2; i < listaClassificacao.size(); i++){
            System.out.println("=======================================");
            System.out.println(listaClassificacao.get(i).getTitulo());
            System.out.println("=======================================");

            System.out.println("Erro Min::==============================");
            System.out.println(listaAR.get(i-2).getMimMape());             
            System.out.println("Erro Max::==============================");
            System.out.println(listaAR.get(i-2).getMaxMape());             
            
        }
    }
    
    public String gerarRelatorio(){
        StringBuilder relatorio = new StringBuilder();
        int opcao = medicao.getMetodologiaAplicada().getOpcao();
        relatorio.append("=======================================\n");
        relatorio.append(" Metodologia: " + medicao.getMetodologiaAplicada().getMetodologia("" + opcao)).append("\n");
        relatorio.append(medicao.getMetodologiaAplicada().getValoresReferentes(opcao)).append("\n");
        relatorio.append("=======================================\n");        
        for (int coluna = 2; coluna < listaClassificacao.size(); coluna++) {
            relatorio.append("=======================================\n");
            relatorio.append(listaClassificacao.get(coluna).getTitulo()).append("\n");
            relatorio.append("=======================================\n");

            for (int mes = 0; mes < guia.getApendice().size(); mes++) {
                relatorio.append("=======================================\n");
                relatorio.append(listaClassificacao.get(coluna).getClassificador(mes).imprimir()).append("\n");
            }
        }
        return relatorio.toString();        
    }
    
    //imputa valores calculados a serie original
    public void imputarValoresAr(){
       for(int coluna = 2; coluna < medicao.getColunaCount(); coluna++){
            this.medicao.getLista(coluna).atualizaValor(this.listaAR.get(coluna-2).getDadosProcessados());            
        }        
    }
    
    public void imputarValoresEs(){
       for(int coluna = 2; coluna < medicao.getColunaCount(); coluna++){
            this.medicao.getLista(coluna).atualizaValor(this.listaEs.get(coluna-2).getDadosProcessados());            
        }       
    }
    
    public void setMedicao(Info medicao) {
//        medicao.imprimirColuna();
        this.medicao = medicao;
//        Tabela.Funcionalidades.setColumn(CtrlGeral.meses);
//        System.out.println("setMEdicao!!!!!");
        Guia nova = new Guia();
        nova.gerarGuia(this.medicao.getLista(2).getDados());
        this.setApendice(nova);
//        apendice.imprimir();
        this.listaClassificacao = setListaClassificacao();
    }

    public List<ListaClassificacao> getListaClassificacoes() {
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

    public  List<Marcador> getApendice() {
        return guia.getApendice();
    }

    public Marcador getMarcador(int index) {
        return guia.getMarcador(index);
    }
    
    public void reSetMedicao(){
        this.medicao.reSetLista();
    }
    
    public void setApendice(Guia apendice) {
        this.guia = apendice;
    }
    //Analizar aqui com mais calma
    public List<Employee> gerarDadosTabelaOriginal(){

        if(medicao == null || medicao.getLinhaCount() == 0){
            return null;
        }else{
            List<Employee> lista = new ArrayList<Employee>();
            for(int i = 0; i < medicao.getLinhaCount(); i++){
                Employee e = new Employee();
                e.setLine(medicao.getLinhaOriginal(i));
                lista.add(e);
            }
            return lista;  
        }
    }
    
    public List<Employee> gerarDadosTabelaImputados(){

        if(medicao == null || medicao.getLinhaCount() == 0){
            return null;
        }else{
            List<Employee> lista = new ArrayList<Employee>();
            for(int i = 0; i < medicao.getLinhaCount(); i++){
                Employee e = new Employee();
                e.setLine(medicao.getLinha(i));
                lista.add(e);
            }
            return lista;  
        }
    } 
    
    public Info lerArquivo(String caminho){
        try {
            ArrayList<Sensor> sensores = null;

            sensores = (ArrayList<Sensor>) ctrlDao.listarSensores();
        
            return Funcionalidades.lerArquivo(caminho, sensores);
        } catch (ParseException ex) {
            Logger.getLogger(CtrlGeral.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(CtrlGeral.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    
    public void gravarEstacao(Info medicao){
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFundacao =  null;
        
        if (medicao.getEstacao().getDataFundacao() != null){
            dataFundacao = dateformat.format(medicao.getEstacao().getDataFundacao());
        }
        
        this.ctrlDao.gravarEstacao(medicao.getEstacao().getId(), medicao.getEstacao().getNome(), medicao.getEstacao().getCodigo(), dataFundacao, medicao.getEstacao().getLatitude().toString(), medicao.getEstacao().getLongitude().toString(), medicao.getEstacao().getAltitude().toString(), medicao.getEstacao().getPeriodicidade());
    }
    
    public void gravarListaColunas(Info medicao){
        
        this.ctrlDao.gravarListaColunas(medicao.getLista(), medicao.getEstacao().getCodigo());
    }
    
    public List<Info> listarEstacoes(){
        ArrayList<Info> lista = new ArrayList<>();
        ArrayList<Estacao> estacoes;
        Info nova;
        
        estacoes = (ArrayList<Estacao>) this.ctrlDao.listarEstacoes();
        
        for (int i = 0; i < estacoes.size(); i++){
            nova = new Info();
            nova.setEstacao(estacoes.get(i));
            lista.add(nova);
        }
        
        return lista;
    }
    
    public List<String>listarAnosDadosMedidosEstacoes( String codigoEstacao) {
        return this.ctrlDao.listarAnosDadosMedidosEstacoes(codigoEstacao);
    }
    
    public Info getMedicaoPeriodo(String codigo, String periodo){
        return this.ctrlDao.getMedicaoPeriodo(codigo,periodo);
    }
}
