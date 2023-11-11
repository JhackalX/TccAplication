/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jacka
 */
public class Info {
    
    //informações lidas no CSV
    private String cidade;//nome da cidade
    private String codigo;//codigo da estação
    private String latitude;//latitude
    private String longitude;//longitude
    private String altitude;//altitude
    private String situacao;//situação
    private Date dataInicial;//data inicial da medição
    private Date dataFinal;//data final da medição
    private String periodicidade;//

    private List<Coluna> lista;
    
    //informção criada pelo sistema
    private Metodologia metodologiaAplicada;//formula aplicada (indice 1 ou 2)    
    private Date dataCriacao;//data gravada da dos dados atual
    
    //inicializar o basico
    public Info() {
        Date data = new Date();
        this.dataCriacao = data;
        this.lista = new ArrayList<Coluna>();
        this.metodologiaAplicada = new Metodologia();
    }

    //pegar do BD
    public Info(String cidade, 
                String codigo, 
                String latitude, 
                String longitude, 
                String altitude, 
                String situacao, 
                Date dataInicial, 
                Date dataFinal, 
                String periodicidade, 
                List<Coluna> lista,
                Metodologia metodologiaAplicada, 
                Date dataCriacao) {
        this.cidade = cidade;
        this.codigo = codigo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.situacao = situacao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.periodicidade = periodicidade;
        this.lista = lista;
        this.metodologiaAplicada = metodologiaAplicada;
        this.dataCriacao = dataCriacao;
    }

    
    //leitura de inicial
    public Info(String cidade, 
                String codigo, 
                String latitude, 
                String longitude, 
                String altitude, 
                String situacao, 
                String dataInicial, 
                String dataFinal, 
                String periodicidade                                   
                ) throws ParseException {
        
        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        
        this.cidade = cidade.trim();
        this.codigo = codigo.trim();
        this.latitude = latitude.trim();
        this.longitude = longitude.trim();
        this.altitude = altitude.trim();
        this.situacao = situacao.trim();
        this.dataInicial = dateFormate.parse(dataInicial.trim());
        this.dataFinal = dateFormate.parse(dataFinal.trim());
        this.periodicidade = periodicidade.trim();

        Date data = new Date();
        this.dataCriacao = data;
        this.lista = new ArrayList<Coluna>();
        this.metodologiaAplicada = new Metodologia();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }
   
    //add colunas
    public void addColuna(String titulo) {
        this.lista.add(new Coluna(titulo));
    }
    
    //add elementos
    public void addElementos(String[] dados) {
        for(int i = 2; i < dados.length; i++){
            Dados dado = new Dados(dados[0], dados[1], dados[i]);
            this.lista.get(i).addDado(dado);            
        }
    }
    
    //adiciona uma sequencia de dados(é criada para entrada de linha por linha no CSV)
    public void addColuna(String titulo, List<Dados> lista) {
        this.lista.add(new Coluna(titulo, lista));         
    }

    public void excluirColuna(int index) {
        this.lista.remove(index);         
    }

    public void excluirColuna(Coluna coluna) {
        this.lista.remove(coluna);         
    }

    public void excluirColuna(String titulo) {
        for(int i = 0; i < this.lista.size(); i++){
            if(this.lista.get(i).getTitulo().equalsIgnoreCase(titulo)){
                this.lista.remove(i);
            }
        }         
    }

    public List<Coluna> getLista() {
        return lista;
    }

    public Coluna getLista(int index) {
        if(this.lista == null){
            return null;
        }else{
            return lista.get(index);
        }
    }

    public void setLista(List<Coluna> lista) {
        this.lista = lista;
    }
    
        
    public int getColunaCount() {
        if (this.lista == null){
            return 1;
        }else{
            return this.lista.size();
        }
    }
    public int getLinhaCount() {
        if (this.lista == null){
            return 0;
        }else{
            return this.getLista(3).getLinhas();
        }
    }
    
    public String[] getColuna() {
        System.out.println("-------------------------------");
        if (!this.lista.isEmpty()){
            String[] colunas = new String[this.lista.size()];
            for(int i = 0; i < this.lista.size(); i++){
                colunas[i] = this.lista.get(i).getTitulo();
            } 
            return colunas;
        }else{
            String[] colunas = {"vazio"};
            return colunas;       
        }        
    }

    public ArrayList<String> getLinha(int index) {
        ArrayList<String> linha = new ArrayList<String>();
        if(this.getColunaCount() == 1){
            linha.add("vazio");
            return linha;
        }else{       
            for(int i = 0; i < this.getColunaCount(); i++){
                if(i == 0){
                    //linha[i] = this.getLista(3).getDado(index).getDataBr();
                }else{
                    if(i == 1){
                        //linha[i] = "" + this.getLista(3).getDado(index).getPeriodo();
                    }else{
                        linha.add(this.getLista(i).getDado(index).getValor());
                    }
                }
            } 
            return linha;
        }
    }
    
    public String getColuna(int index) {
        return this.lista.get(index).getTitulo();
    }

    public void setColuna(String coluna, int index) {
        this.lista.get(index).setTitulo(coluna);
    }
    
    public void setColuna(String[] coluna) {
        if(this.isEmpty()){
            for(int i = 0; i < coluna.length; i++){
                this.lista.add(new Coluna(coluna[i]));
            }           
        }else{
            for(int c = 0; c < coluna.length; c++){
                this.lista.get(c).setTitulo(coluna[c]);
            }             
        }
    }

    public Metodologia getMetodologiaAplicada() {
        return metodologiaAplicada;
    }

    public void setMetodologiaAplicada(Metodologia metodologiaAplicada) {
        this.metodologiaAplicada = metodologiaAplicada;
    }

    public Date getDataInicial() {
        return dataInicial;
    }
    
    public String getDataInicialBR() {
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormate.format(dataInicial).toString();
    }

//    public void setDataInicial(){
//        if((!lista.isEmpty()) || (lista != null)){
//            this.dataInicial = lista.get(0).getData();
//        }else{
//            this.dataInicial = null;
//        }
//    }

    public Date getDataFinal() {
        return dataFinal;
    }
    
    public String getDataFinalBR() {
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormate.format(dataFinal).toString();
    }
    
//    public void setDataFinal() {
//        if((!lista.isEmpty()) || (lista != null)){
//            this.dataFinal = lista.get(lista.size()-1).getData();
//        }else{
//            this.dataFinal = null;
//        }
//    }

    public List<Float> subLista(int coluna, int mes, int ano){
        
        List<Dados> subDados = this.getLista(coluna).getSubDados(mes, ano);
        List<Float> subLista = new ArrayList<Float>();
//                System.out.println("-------------------------------------");
        for(int i = 0; i < subDados.size(); i++){
            if(!subDados.get(i).getValor().equalsIgnoreCase("null")){
//                subLista.add(null);
                subLista.add(Float.parseFloat(subDados.get(i).getValor()));
//                System.out.println(Float.parseFloat(subDados.get(i).getValor()));
            }
        }              
                System.out.println(subLista);
        return subLista;
    }

    public List<Float> subListaIndex(int coluna, int inicio, int fim){
        
        List<Dados> subDados = this.getLista(coluna).getSubDadosIndex(inicio, fim);
        List<Float> subLista = new ArrayList<Float>();
        
//                System.out.println("-------------------------------------");
        for(int i = 0; i < subDados.size(); i++){
            if(!subDados.get(i).getValor().equalsIgnoreCase("null")){
//                subLista.add(null);
                subLista.add(Float.parseFloat(subDados.get(i).getValor()));
//                System.out.println(Float.parseFloat(subDados.get(i).getValor()));
            }
        }              
        System.out.println(subLista);
        return subLista;
    }

    public List<Float> subListaFloatIndex(int coluna, int inicio, int fim){
        
        List<Dados> subDados = this.getLista(coluna).getSubDadosIndex(inicio, fim);
        List<Float> subLista = new ArrayList<Float>();
        
//                System.out.println("-------------------------------------");
        for(int i = 0; i < subDados.size(); i++){
            subLista.add(subDados.get(i).getValorF());
        }
        
        System.out.println(subLista);
        return subLista;
    }
    
    public Date getDataCriacao() {
        return dataCriacao;
    }
    
    public String getDataCriacaoBR(){
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormate.format(dataCriacao).toString();
    }

    public void setDataCriacao() {
        //DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        this.dataCriacao = data;
    }

//    public List<Dados> getLista() {
//        return lista;
//    }
//    
//    public Dados getDado(int index) {
//        return lista.get(index);
//    }
//
//    public void setLista(List<Dados> lista) {
//        this.lista = lista;
// 
//        this.atualizarIntervaloData();
//    }
    
//    public void atualizarIntervaloData(){
//        this.setDataInicial();
//        this.setDataFinal();   
//    }

    public int getInicDay (){
        return (dataInicial.getDay()+1);
    }
    
    public int getInicMonth (){
        return (dataInicial.getMonth()+1);
    }
    
    public int getInicYear (){
        return (dataInicial.getYear()+1900);
    }    

    public int getFinDay (){
        return (dataFinal.getDay()+1);
    }
    
    public int getFinMonth (){
        return (dataFinal.getMonth()+1);
    }
    
    public int getFinYear (){
        return (dataFinal.getYear()+1900);
    }  
    
    @Override
    public String toString() {
        return "Info{" + "\n Nome = " + this.cidade
                       + "\n Codigo Estacao = " + this.codigo 
                       + "\n Latitude = " + this.latitude 
                       + "\n Longitude = " + this.longitude 
                       + "\n Altitude = " + this.altitude 
                       + "\n Situacao = " + this.situacao 
                       + "\n Data Inicial = " + this.getDataInicialBR()
                       + "\n Data Final = " + this.getDataFinalBR()
                       + "\n Periodicidade da Medicao = " + this.periodicidade//obs 
                       + '}'; 
    }//obs: ajeitar o toString da lista

    public void imprimirColuna(){
        for(int cont = 0; cont < this.lista.size(); cont++){
            System.out.println(this.lista.get(cont).getTitulo());
        }
    }
//    public void adicionarListaMedicao(List<Dados> objList){
//        List<Dados> lista = objList;
//        for(int i=0; i < lista.size(); i++){
//            if(!this.lista.contains(lista.get(i))){
//                this.lista.add(lista.get(i));
//                //this.ordenarLista();
//                
//                //this.atualizarIntervaloData();            
//            }else{
//                System.out.println("elemento ja existe na lista...");
//            }
//        }
//        this.atualizarIntervaloData();
//    }
    
    


    //gera uma lista sem nulls de dados
//    public List<Float> dadosPValidar(){
//        List<Float> dados = new ArrayList<Float>();
//        for(int i = 0; i < this.lista.size(); i++){
//            if(!this.getDado(i).getValor().equals("null")){
//                dados.add(Float.parseFloat(lista.get(i).getValor()));
//            }
//        }       
//        return dados;
//    }
    
    //validada pelo controlador
    //exclui todas as medicoes no dia especifico
//    public void excluirDia(int dia, int mes,  int ano){
//        for(int i = 0; i < this.lista.size(); i++){
//            if((this.getDado(i).getDay() == dia) && 
//               (this.getDado(i).getYear() == ano) &&
//               (this.getDado(i).getMonth() == mes)){
//                this.lista.remove(i);
//            }
//        }  
//        this.atualizarIntervaloData();
//    }
    
    //validada pelo controlador
    //exclui todas as medicoes no mes especifico
//    public void excluirMes(int mes, int ano){
//        for(int i = 0; i < this.lista.size(); i++){
//            if((this.getDado(i).getMonth() == mes) && 
//               (this.getDado(i).getYear() == ano)){
//                this.lista.remove(i);
//            }
//        }
//        this.atualizarIntervaloData();
//    }
//
//    public void excluirAno(int ano){
//        for(int i = 0; i < this.lista.size(); i++){
//            if((this.getDado(i).getYear()) == ano){
//                this.lista.remove(i);
//            }
//        }
//        this.atualizarIntervaloData();
//    }
    
    public void limparGeral(){
        this.lista.removeAll(this.lista);
        
        this.dataInicial = null;
        this.dataFinal = null;
    }
    
    public boolean isEmpty(){
        return lista.isEmpty();
    }
    

    
//    public void listarMedicao(){
//        System.out.println(this.toString());
//        for (int i = 0; i<lista.size(); i++){
//            System.out.println(this.getDado(i).toString());
//        }       
//    }
    
}
