/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.Validacao;
import Object.Coluna;
import Object.Info;
import Object.Dados;
import Object.Dados;
import Object.Estacao;
import Object.Info;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author jacka
 */
public class Funcionalidades {
    
//    public static String relatorio(Info obj){
//        Info novo;
//        novo = obj;
//        int qtDados = novo.getLista().size();
//        int qtNull = contNull(novo);
//        String relat = mesNull(novo);
//        return "Quantidade de dados: " + qtDados + 
//               "\nQuantidade de lacunas: " + qtNull +
//                relat;
//    }
//
//    public static int contNull(Info obj){
//        Info novo = obj;
//        int cont = 0;
//        for(int i = 0; i < novo.getLista().size(); i++){
//            if(novo.getLista().get(i).getValor().equalsIgnoreCase("null")){
//                cont++;
//            }
//        }
//        return cont;
//    }
//    
//    public static String contNull(Info obj, int mes, int ano){
//        Info novo = obj;
//        int cont = 0;
//        for(int i = 0; i < novo.getLista().size(); i++){
//            if(novo.getLista().get(i).getValor().equalsIgnoreCase("null") && 
//               novo.getLista().get(i).getMonth() == mes && 
//               novo.getLista().get(i).getYear() == ano){
//                cont++;
//            }
//        }
//        return "Mês: "+ mes + 
//               "/" + ano + 
//               "....... Quantidade: " + cont;
//    }
//    
//    public static String mesNull(Info obj){
//        Info novo = obj;
//        String relat;
//        int ano = 0;
//        int mes = 0;
//        for(int i = 0; i < novo.getLista().size(); i++){
//            
//            if(novo.getLista().get(i).getValor().equalsIgnoreCase("null")){
//                mes = novo.getLista().get(i).getData().getMonth()+1;
//                ano = novo.getLista().get(i).getData().getYear()+1900;
//                //System.out.println(contNull(novo, mes, ano));
//            }    
//            while((novo.getLista().get(i+1).getData().getMonth()+1 == mes) && ((i + 3) < novo.getLista().size())){
//                i = i+1;        
//            }
//        }
//        return contNull(novo, mes, ano);
//    }

    

    public static Info lerArquivo(String path) throws FileNotFoundException, ParseException, IOException{
        try(BufferedReader arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(path),"Cp1252"))){
            String linhaUm[];
            
            linhaUm = arquivo.readLine().split(":");
            
            if (linhaUm[0].contains("Nome")) {
                
                return lerArquivoEstacoesConvencionais(arquivo,linhaUm[1].trim());
            }else if (linhaUm[0].contains("REGIAO")){
                return lerArquivoEstacoesAutomaticas(arquivo);
            }
            

            return null;
        }catch(IOException e){
            System.out.println("Funcoes.Funcionalidades.lerArquivo()");
            System.err.println("Error!?!?: " + e.getMessage());
            return null;
        }
        
    }
//    
    public static void addDados (String linha, Info cidade) throws ParseException{
        String[] campos = linha.trim().split(";");
        cidade.addElementos(campos);
    }

    private static void addDadosEstacoesAutomaticas (String linha, Info cidade) throws ParseException{
        String[] campos = linha.trim().split(";");
        cidade.addElementosEstacoesAutomaticas(campos);
    }
    
    // Funes para ler aquivos dow arquivos de estaoes convencionais do site do INMET (Link: https://bdmep.inmet.gov.br/)
    private static Info lerArquivoEstacoesConvencionais(BufferedReader arquivo, String nomeEstacao) throws ParseException, IOException{
        Info medicao = null;
        
        
        medicao = lerCabecalhoEstacoesConvencionais(arquivo, medicao, nomeEstacao);
        
        medicao = addDadosEstacoesConvencionais(arquivo, medicao);
        
        System.out.println("Estacao: " + medicao.getEstacao().getNome());
        
        return medicao;
    }
    
    private static Info lerCabecalhoEstacoesConvencionais(BufferedReader arquivo, Info medicao, String nomeEstacao) throws ParseException, IOException{
        
        //Ler cabeçalho da estacao
        
       
        String[] line2 = arquivo.readLine().split(":");
        String[] line3 = arquivo.readLine().split(":");
        String[] line4 = arquivo.readLine().split(":");
        String[] line5 = arquivo.readLine().split(":");
        String[] line6 = arquivo.readLine().split(":");
        String[] line7 = arquivo.readLine().split(":");
        String[] line8 = arquivo.readLine().split(":");
        String[] line9 = arquivo.readLine().split(":");

        medicao = new Info(
                                nomeEstacao,
                          line2[1].trim(),
                         line3[1].trim(),
                        line4[1].trim(),
                         line5[1].trim(),
                         line6[1].trim(),
                       line7[1].trim(),
                        line8[1].trim(),
                     line9[1].trim());

        return medicao;

    }
    
    private static Info addDadosEstacoesConvencionais(BufferedReader arquivo, Info medicao) throws IOException, ParseException{
        //Ler  colunas
        
        String line = arquivo.readLine();
        line = arquivo.readLine();
        String[] coluna = line.split(";");
        medicao.setColuna(coluna);
        line = arquivo.readLine();
        
        while(line != null){
                addDados(line, medicao);
                line = arquivo.readLine();    
            }
        return medicao;
    }
    
    private static Info lerArquivoEstacoesAutomaticas(BufferedReader arquivo){
        Info medicao;
        medicao = null;
        
        medicao = lerCabecalhoEstacoesAutomaticas(arquivo, medicao);
        
        medicao = addDadosEstacoesAutomaticas(arquivo, medicao);
        
        return medicao;
    }
    
    private static Info lerCabecalhoEstacoesAutomaticas(BufferedReader arquivo, Info medicao) {
        try {Estacao nova = new Estacao();
            medicao =  new Info();

            String linha, campos[];

    //        // Ler Regiao
    //        String[] campos = linha.split(";");
    //        nova.setRegiao(campos[1]);
    //      Pula Linha UF        
            linha = arquivo.readLine();


            linha = arquivo.readLine();

            //Ler Nome
            campos = linha.split(";");
            nova.setNome(campos[1]);

            linha = arquivo.readLine();

            //Ler Codigo
            campos = linha.split(";");
            nova.setCodigo(campos[1]);

            linha = arquivo.readLine();

            //Ler Latitude
            campos = linha.split(";");

            nova.setLatitude(Float.valueOf(campos[1].replace(",",".")));

            linha = arquivo.readLine();

            //Ler Longitude
            campos = linha.split(";");
            nova.setLongitude(Float.valueOf(campos[1].replace(",", ".")));

            linha = arquivo.readLine();

            //Ler Altitude
            campos = linha.split(";");
            nova.setAltitude(Float.valueOf(campos[1].replace(",",".")));


            //pula linha data de de fudacao
            arquivo.readLine();

            //Ler data Fundação
    //        campos = linha.split(";");
    //        nova.setDataFundacao(campos[1]);
    //        
            nova.setID();
            //Definir estacao
            medicao.setEstacao(nova);

             
               

            return medicao;
        
        } catch (IOException e ) {
            return null;
        }
    }
    
    private static Info addDadosEstacoesAutomaticas(BufferedReader arquivo, Info medicao){
        try{
            String campos[], linha;
           // ler colunas
            linha = arquivo.readLine();      

            campos = linha.split(";");

            medicao.setColuna(campos);
            
            System.out.println("Linha: " + linha);
            
            // Ler primeira linha
            linha = arquivo.readLine();

            medicao.setPeriodicidade("Horaria");
            
            
            medicao.atualizaDataInicial(linha.split(";")[0]);
            
            System.out.println(linha);
            while(linha != null){ 
                
                addDadosEstacoesAutomaticas(linha, medicao);
                medicao.atualizaDataFinal(linha.split(";")[0]);
                
                linha = arquivo.readLine();    
            } 
        
            return medicao;
        }catch (IOException ioe) {
            return null;
        } catch (NullPointerException npe){
            return null;
        }  catch (ParseException ex) {
            return null;
        } 
    }
    
public static void gerarCsv(Info info, String pathFile){
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile))){
        bw.write("Nome: " + info.getEstacao().getNome() + "\n");
        bw.write("Codigo Estacao: " + info.getEstacao().getCodigo() + "\n");
        bw.write("Latitude: " + info.getEstacao().getLatitude() + "\n");
        bw.write("Longitude: " + info.getEstacao().getLongitude() + "\n");
        bw.write("Altitude: " + info.getEstacao().getAltitude() + "\n");
        bw.write("Situacao: " + info.getEstacao().getSituacao() + "\n");
        bw.write("Data Inicial: " + info.getDataInicialBR() + "\n");
        bw.write("Data Final: " + info.getDataFinalBR() + "\n");
        bw.write("Periodicidade da Medicao: " + info.getPeriodicidade() + "\n");
        bw.newLine();
        
        for (Coluna coluna : info.getLista()){
            bw.write(coluna.getTitulo() + ";");
        }
        bw.write("\n");
        int linhaCount = info.getLinhaCount();
        for(int index = 0; index < linhaCount; index++){
            List<String> linha = info.getLinha(index);
            for(String valor : linha){
                bw.write(valor + ";");
            }
            bw.write("\n");
        }
        
    }catch (IOException e){
        e.printStackTrace();
    }
    //Escrevendo cabeçalho
    
}    
//    
////Dados-------------------------------------------------------------------------
//    public static List<Float> geraLista(List<Object> lista){
//        List<Float> valores = new ArrayList<Float>();
//        for(int index = 0; index < lista.size(); index++){
//            if(((Dados)lista.get(index)).getValor().equals("null")){
//                valores.add(null);
//            }else{
//                valores.add(Float.parseFloat(((Dados)lista.get(index)).getValor()));
//            }
//        }
//        return valores;
//    }    
//    
//    public static void atualizaDados(List<Object> lista, List<Float> valores){
//        for(int index = 0; index < lista.size(); index++){
//            if(!((Dados)lista.get(index)).getValor().equals(""+valores.get(index))){
//                ((Dados)lista.get(index)).setValor(valores.get(index));
//            }
//        }
//    }
//
//    public static List<String> getObject(List<Object> lista, int index){
//        
//        List<String> obj = new ArrayList<String>();
//        obj.add(((Dados)lista.get(index)).getDataBr());
//        obj.add(""+((Dados)lista.get(index)).getPeriodo());
//        obj.add(((Dados)lista.get(index)).getValor().replace('.', ','));        
//        return obj;
//    }
//    
////INFO--------------------------------------------------------------------------    
//    public static List<Object> geraListaD(Object objeto){
//        List<Object> valores = new ArrayList<Object>();
//        valores.addAll(((Info)(objeto)).getLista());
//        return valores;
//    }
//    
//    public static String[] geraColuna(Object objeto){
//        String[] coluna;
//        coluna = ((Info)(objeto)).getColuna();
//        return coluna;
//    }
    
}
