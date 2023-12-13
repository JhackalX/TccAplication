/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author jacka
 */
public class Info {
    
    //informações lidas no CSV
    private Estacao estacao;
    
    private String id;
    
    private String nome;
    
    private String periodo;
    
    //private String cidade;//nome da cidade
   // private String codigo;//codigo da estação
   // private String latitude;//latitude
    //private String longitude;//longitude
   // private String altitude;//altitude
    //private String situacao;//situação
    //private Date dataInicial;//data inicial da medição
    //private Date dataFinal;//data final da medição
    //private String periodicidade;//

    private List<Coluna> lista;
    private List<Coluna> copiaLista;
    private List<String> listaAnos;
    //informção criada pelo sistema
    private Metodologia metodologiaAplicada;//formula aplicada (indice 1 ou 2)    
    private Date dataCriacao;//data gravada da dos dados atual
    
    //inicializar o basico
    public Info() {
        Date data = new Date();
        this.dataCriacao = data;
        this.lista = new ArrayList<Coluna>();
        this.copiaLista = new ArrayList<Coluna>();
        this.listaAnos = new ArrayList<String>();
        this.metodologiaAplicada = new Metodologia();
    }

    //pegar do BD
    public Info(String nomeEstacao, 
                String codigo, 
                String latitude, 
                String longitude, 
                String altitude, 
//                Date dataInicial, 
//                Date dataFinal,  
                List<Coluna> lista,
                Metodologia metodologiaAplicada, 
                Date dataCriacao) {
        this.estacao.setNome(nomeEstacao);
        this.estacao.setCodigo(codigo);
        this.estacao.setLatitude(Float.parseFloat(latitude));
        this.estacao.setLongitude(Float.parseFloat(longitude));
        this.estacao.setAltitude(Float.parseFloat(altitude));
//        this.dataInicial = dataInicial;
//        this.dataFinal = dataFinal;
        this.lista = lista;
        this.copiaLista = new ArrayList<Coluna>(lista);
        this.metodologiaAplicada = metodologiaAplicada;
        this.dataCriacao = Date.from(Instant.now());
    }

    
    //leitura de inicial
    public Info(String nomeEstacao, 
                String codigo, 
                String latitude, 
                String longitude, 
                String altitude, 
                String situacao, 
//                String dataInicial, 
//                String dataFinal, 
                String periodicidade                                   
                ) throws ParseException {
        
        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        this.estacao = new Estacao();
        this.estacao.setNome(nomeEstacao);
        this.estacao.setCodigo(codigo);
        this.estacao.setLatitude(Float.parseFloat(latitude));
        this.estacao.setLongitude(Float.parseFloat(longitude));
        this.estacao.setAltitude(Float.parseFloat(altitude));
//        this.dataInicial = dateFormate.parse(dataInicial.trim());
//        this.dataFinal = dateFormate.parse(dataFinal.trim());
        this.estacao.setPeriodicidade(periodicidade.trim());
        
        this.estacao.setID(this.estacao.getCodigo());

        Date data = new Date();
        this.dataCriacao = data;
        this.lista = new ArrayList<Coluna>();
        this.copiaLista = new ArrayList<Coluna>();
        this.metodologiaAplicada = new Metodologia();
    }
    
    public Estacao getEstacao (){
        return this.estacao;
    }
    
    public void setEstacao(Estacao estacao){
        this.estacao = estacao;
    }

    public List<String> getListaAnos (){
        return this.listaAnos;
    }
    
    public void setListaAnos(List<String> listaAnos){
        this.listaAnos = listaAnos;
    }

//    public String getCidade() {
//        return cidade;
//    }
//
//    public void setCidade(String cidade) {
//        this.cidade = cidade;
//    }
//
//    public String getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(String codigo) {
//        this.codigo = codigo;
//    }
//
//    public String getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//
//    public String getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//
//    public String getAltitude() {
//        return altitude;
//    }
//
//    public void setAltitude(String altitude) {
//        this.altitude = altitude;
//    }
//
//    public String getSituacao() {
//        return situacao;
//    }
//
//    public void setSituacao(String situacao) {
//        this.situacao = situacao;
//    }

    public String getPeriodicidade() {
        return this.estacao.getPeriodicidade();
    }

    public void setPeriodicidade(String periodicidade) {
        this.estacao.setPeriodicidade(periodicidade);
    }
   
    //add colunas
    public void addColuna(String titulo) {
        this.lista.add(new Coluna(titulo));
        this.copiaLista.add(new Coluna(titulo));
    }
    
    //add elementos
    public void addElementos(String[] dados, Boolean gabarito[]) {
        for(int i = 2; i < dados.length; i++){
            if (gabarito[i]) {
                Dados dado = new Dados(dados[0], dados[1], dados[i]);
                this.lista.get(i).addDado(dado);            
                this.copiaLista.get(i).addDado(dado);
            }            
        }
    }
    
    public void addElementosEstacoesAutomaticas(String[] dados, Boolean gabarito[]) throws ParseException, NumberFormatException{
        
        for(int i = 2; i < dados.length; i++){
            if (gabarito[i]) {
                Dados dado = new Dados(dados[0], (dados[1].split(" ")[0]), dados[i]);
                this.lista.get(i).addDado(dado);            
                this.copiaLista.get(i).addDado(dado); 
            }
        }
    }
    
    //adiciona uma sequencia de dados(é criada para entrada de linha por linha no CSV)
    public void addColuna(String titulo, List<Dados> lista) {
        this.lista.add(new Coluna(titulo, lista));         
        this.copiaLista.add(new Coluna(titulo, lista));         
    }

    public void excluirColuna(int index) {
        this.lista.remove(index);         
        this.copiaLista.remove(index);         
    }

    public void excluirColuna(Coluna coluna) {
        this.lista.remove(coluna);         
        this.copiaLista.remove(coluna);         
    }

    public void excluirColuna(String titulo) {
        for(int i = 0; i < this.lista.size(); i++){
            if(this.lista.get(i).getTitulo().equalsIgnoreCase(titulo)){
                this.lista.remove(i);
                this.copiaLista.remove(i);
            }
        }         
    }

    public List<Coluna> getLista() {
        return lista;
    }

    public List<Coluna> getCopiaLista() {
        return copiaLista;
    }

    public Coluna getLista(int index) {
        if(this.lista == null){
            return null;
        }else{
            return lista.get(index);
        }
    }

    public Coluna getCopiaLista(int index) {
        if(this.copiaLista == null){
            return null;
        }else{
            return copiaLista.get(index);
        }
    }

    public void setLista(List<Coluna> lista) {
        this.lista = lista;
        this.copiaLista = new ArrayList<Coluna>(lista);
    }
    
    //retorna a lista ao seus dados originais
    public void reSetLista() {
        this.lista = new ArrayList<Coluna>(this.copiaLista);
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
    
    public void setSensor(int index, Sensor sensor){
        this.lista.get(index).setSensor(sensor);
        this.copiaLista.get(index).setSensor(sensor);
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
            for(int i = -2; i < this.getColunaCount(); i++){
                if(i == -2){
                    linha.add(this.getLista(3).getDado(index).getDataBr());
                }else{
                    if(i == -1){
                        linha.add(""+ this.getLista(3).getDado(index).getPeriodo());
                    }else{
                        linha.add(this.getLista(i).getDado(index).getValor());
                    }
                }
            } 
            return linha;
        }
    }

    public ArrayList<String> getLinhaCsv(int index) {
        ArrayList<String> linha = new ArrayList<String>();
        if(this.getColunaCount() == 1){
            linha.add("vazio");
            return linha;
        }else{
            for(int i = -2; i < this.getColunaCount(); i++){
                if(i == -2){
                    linha.add(this.getLista(3).getDado(index).getDataBr());
                }else{
                    if(i == -1){
                        linha.add(""+ this.getLista(3).getDado(index).getPeriodo());
                    }else{
                        linha.add(this.getLista(i).getDado(index).getValorCsv());
                    }
                }
            } 
            return linha;
        }
    }

    public ArrayList<String> getLinhaOriginal(int index) {
        ArrayList<String> linha = new ArrayList<String>();
        if(this.getColunaCount() == 1){
            linha.add("vazio");
            return linha;
        }else{
            for(int i = -2; i < this.getColunaCount(); i++){
                if(i == -2){
                    linha.add(this.getCopiaLista(3).getDado(index).getDataBr());
                }else{
                    if(i == -1){
                        linha.add(""+ this.getCopiaLista(3).getDado(index).getPeriodo());
                    }else{
                        linha.add(this.getCopiaLista(i).getDado(index).getValor());
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
        this.copiaLista.get(index).setTitulo(coluna);
    }
    
    public void setColuna(String[] coluna) {
        if(this.isEmpty()){
            for(int i = 0; i < coluna.length; i++){
                this.lista.add(new Coluna(coluna[i], this));
                this.copiaLista.add(new Coluna(coluna[i], this));
            }           
        }else{
            for(int c = 0; c < coluna.length; c++){
                this.lista.get(c).setTitulo(coluna[c]);
                this.copiaLista.get(c).setTitulo(coluna[c]);
                this.lista.get(c).setMedicaoPai(this);
                this.copiaLista.get(c).setMedicaoPai(this);
            }             
        }
    }

    public Metodologia getMetodologiaAplicada() {
        return metodologiaAplicada;
    }

    public void setMetodologiaAplicada(Metodologia metodologiaAplicada) {
        this.metodologiaAplicada = metodologiaAplicada;
    }

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
//                System.out.println(subLista);
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
//        System.out.println(subLista);
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
        return (lista.get(3).getDado(0).getDay());
    }
    
    public int getInicMonth (){
        return (lista.get(3).getDado(0).getMonth());
    }
    
    public int getInicYear (){
        return (lista.get(3).getDado(0).getYear());
    }    

//    public int getFinDay (){
//        return (dataFinal.getDay()+1);
//    }
//    
//    public int getFinMonth (){
//        return (dataFinal.getMonth()+1);
//    }
//    
//    public int getFinYear (){
//        return (dataFinal.getYear()+1900);
//    }  
    
    @Override
    public String toString() {
        return "Info{" + "\n Nome = " + this.estacao.getNome()
                       + "\n Codigo Estacao = " + this.estacao.getCodigo()
                       + "\n Latitude = " + this.estacao.getLatitude()
                       + "\n Longitude = " + this.estacao.getLongitude()
                       + "\n Altitude = " + this.estacao.getAltitude()
//                       + "\n Data Inicial = " + this.getDataInicialBR()
//                       + "\n Data Final = " + this.getDataFinalBR()
                       + "\n Periodicidade da Medicao = " + this.estacao.getPeriodicidade()//obs 
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
        
//        this.dataInicial = null;
//        this.dataFinal = null;
    }
    
    public boolean isEmpty(){
        return lista.isEmpty();
    }
    
    private String converterDataBRUTC(String data) throws ParseException{
               
        DateFormat dataUTC = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dataBR = new SimpleDateFormat("yyyy/MM/dd");
        
        return dataUTC.format(dataBR.parse(data));
    }
    
//    public void atualizaDataInicial (String data){
//        DateFormat dateFormate;
//        
//        if (data.contains("-")) {
//            dateFormate = new SimpleDateFormat("yyyy-MM-dd");
//        } else {
//            dateFormate = new SimpleDateFormat("yyyy/MM/dd");
//        }
//        try {
//            this.dataInicial = dateFormate.parse(data.toString());
//        } catch (ParseException ex) {
//            System.out.println("Erro ao definir data inicial. Mensagem: " + ex.getMessage());
//        }
//    }
//    
//        public void atualizaDataFinal (String data){
//        DateFormat dateFormate;
//        
//        if (data.contains("-")) {
//            dateFormate = new SimpleDateFormat("yyyy-MM-dd");
//        } else {
//            dateFormate = new SimpleDateFormat("yyyy/MM/dd");
//        }
//        try {
//            this.dataFinal = dateFormate.parse(data.toString());
//        } catch (ParseException ex) {
//            System.out.println("Erro ao definir data inicial. Mensagem: " + ex.getMessage());
//        }
//    }
    
//    public void listarMedicao(){
//        System.out.println(this.toString());
//        for (int i = 0; i<lista.size(); i++){
//            System.out.println(this.getDado(i).toString());
//        }       
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setId (){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd_HH:mm");
        String entradaHash = ("Estacao:" + this.getEstacao().getId() + "\n" +
                              "Metodologia: " + this.getMetodologiaAplicada().getId() + "\n" + 
                              "Pesos:" + this.getMetodologiaAplicada().getStringPesos() + "\n" + 
                              "TimeStamp: " + format.format(new Date()));
        //System.out.println("Definindo hash para: "  + entradaHash);
        this.id = UUID.nameUUIDFromBytes(entradaHash.getBytes()).toString();
        //System.out.println("Hash gerada: " + this.id);
    }
    
    public void atualizarIDsDadosProcessados(){
        for (int c = 0; c < this.lista.size(); c++){
            for (int d = 0; d < this.lista.get(c).getDados().size(); d++){
                this.lista.get(c).getDados().get(d).setIdDadoProcessado(this.id, this.estacao.getId());
            }
        }
        
        for (int c = 0; c < this.copiaLista.size(); c++){
            for (int d = 0; d < this.copiaLista.get(c).getDados().size(); d++){
                this.copiaLista.get(c).getDados().get(d).setIdDadoProcessado(this.id, this.estacao.getId());
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    public void setNome(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        this.nome = this.getEstacao().getNome() +  "_" + this.getEstacao().getCodigo() + "_" + this.getMetodologiaAplicada().getSigla()+ "_"+ this.getPeriodo() + "_" + format.format(new Date());
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
