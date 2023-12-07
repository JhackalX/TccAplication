/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Interface.WindowDecorator;
import Object.Info;
import Object.ListaClassificacao;
import Tabela.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author aires
 */
public class CtrlInterface {
    private WindowDecorator windowDecorator;
    private CtrlGeral ctrlGeral;

    public CtrlInterface(CtrlGeral ctrlGeral) {
        this.ctrlGeral = ctrlGeral;
        
        JFrame janela = new JFrame();     
        
        this.windowDecorator = Interface.WindowDecorator.decorator(janela, this);
        
        janela.show();
    }
    
    public Info getMedicao(){
        return ctrlGeral.getMedicao();
    }
    
    public ArrayList<String> getColuna(){
        return ctrlGeral.getColuna();
    }
    
    public List<ListaClassificacao> setListaClassificacao(){
        return ctrlGeral.setListaClassificacao();
    }
    
    public void gerarMetEs(){
        this.ctrlGeral.gerarMetEs();
    }
    
    public void gerarMetAR(){
        this.ctrlGeral.gerarMetAR();
    }
    
    public String gerarRelatorio(){
        return this.ctrlGeral.gerarRelatorio();
    }
    
    public void imputarValoresAr(){
        this.ctrlGeral.imputarValoresAr();
    }
    
    public void imputarValoresEs(){
        this.ctrlGeral.imputarValoresAr();
    }
    
    public void setMedicao(Info medicao){
        this.ctrlGeral.setMedicao(medicao);
    }
    
    public List<ListaClassificacao> getListaClassificacoes() {
        return this.ctrlGeral.getListaClassificacoes();
    }
    
    
    public ListaClassificacao getListaClassificacao(int index) {
        return this.ctrlGeral.getListaClassificacao(index);
    }
    
    public void setListaClassificacao(List<ListaClassificacao> listaClassificacao){
        this.ctrlGeral.setListaClassificacao(listaClassificacao);
    }
    
    public void addListaClassificacao(ListaClassificacao listaClassificacao) {
        this.ctrlGeral.addListaClassificacao(listaClassificacao);
    }
    
    public  List<Marcador> getApendice(){
        return this.ctrlGeral.getApendice();
    }
    
    public Marcador getMarcador(int index){
        return this.getMarcador(index);
    }
    
    public void reSetMedicao(){
        this.ctrlGeral.reSetMedicao();
    }
    
    public void setApendice(Guia apendice) {
        this.ctrlGeral.setApendice(apendice);
    }
    
    public List<Employee> gerarDadosTabelaOriginal(){
        return this.ctrlGeral.gerarDadosTabelaOriginal();
    }
    
    public List<Employee> gerarDadosTabelaImputados(){
        return this.ctrlGeral.gerarDadosTabelaImputados();
    }
    
    public Info lerArquivo(String caminho){
        return this.ctrlGeral.lerArquivo(caminho);
    }
    
    public void gravarEstacao(Info medicao){
        this.ctrlGeral.gravarEstacao(medicao);
    }
    
    public void gravarListaColunas(Info medicao){
        this.ctrlGeral.gravarListaColunas(medicao);
    }
    
    public List<Info> listarEstacoes(){
        return this.ctrlGeral.listarEstacoes();
    }
    
    public List<String>listarAnosDadosMedidosEstacoes( String codigoEstacao){
        return this.ctrlGeral.listarAnosDadosMedidosEstacoes(codigoEstacao);
    }
    
    public void recarregarRecuperarDecorator(){
        this.windowDecorator.montarRecuperar();
    }
}
