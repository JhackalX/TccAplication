/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.Validacao;
import Object.Coluna;
import Object.Sensor;
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
import java.text.Normalizer;
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
    
    public static String relatorioAr(List<AuxAr> auxAr){
        int nullAcumuladoAntes = 0;
        int nullAcumuladoDepois = 0;
        int QtdSubsAcumulado = 0;
        int nElementosAcumulado = 0;
        for(int index = 0; index < auxAr.size(); index++ ){
            nullAcumuladoAntes =+ auxAr.get(index).getSomaNullsAntes();
            nullAcumuladoDepois =+ auxAr.get(index).getSomaNullsDpois();
            QtdSubsAcumulado =+ auxAr.get(index).getQtdSubstituido();
            nElementosAcumulado =+ auxAr.get(index).getQtdElementosProcessados();
        }
               
        return "***********************************************" +
               "\n QTD lacunas totais Antes: " + nullAcumuladoAntes +
               "\n QTD lacunas totais Depois: " + nullAcumuladoDepois +
               "\n QTD substituida: " + QtdSubsAcumulado +
               "\n Numero de elementos (n): " + nElementosAcumulado +
               "\n***********************************************";
    }

    public static String relatorioEs(List<AuxEs> auxEs){
        int nullAcumuladoAntes = 0;
        int nullAcumuladoDepois = 0;
        int QtdSubsAcumulado = 0;
        int nElementosAcumulado = 0;
        for(int index = 0; index < auxEs.size(); index++ ){
            nullAcumuladoAntes =+ auxEs.get(index).getSomaNullsAntes();
            nullAcumuladoDepois =+ auxEs.get(index).getSomaNullsDpois();
            QtdSubsAcumulado =+ auxEs.get(index).getQtdSubstituido();
            nElementosAcumulado =+ auxEs.get(index).getQtdElementosProcessados();
        }
               
        return "***********************************************" +
               "\n QTD lacunas totais Antes: " + nullAcumuladoAntes +
               "\n QTD lacunas totais Depois: " + nullAcumuladoDepois +
               "\n QTD substituida: " + QtdSubsAcumulado +
               "\n Numero de elementos (n): " + nElementosAcumulado +
               "\n***********************************************";
    }
     

    public static Info lerArquivo(String path, ArrayList<Sensor> sensores) throws FileNotFoundException, ParseException, IOException{
        try(BufferedReader arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(path),"Cp1252"))){
            String linhaUm[];
            
            linhaUm = arquivo.readLine().split(":");
            
            if (linhaUm[0].contains("Nome")) {
                
                return lerArquivoEstacoesConvencionais(arquivo,linhaUm[1].trim(), sensores);
            }else if (linhaUm[0].contains("REGIAO")){
                return lerArquivoEstacoesAutomaticas(arquivo, sensores);
            }
            

            return null;
        }catch(IOException e){
            System.out.println("Funcoes.Funcionalidades.lerArquivo()");
            System.err.println("Error!?!?: " + e.getMessage());
            return null;
        }
        
    }
//    
    public static void addDados (String linha, Info cidade, Boolean gabarito[]) throws ParseException{
        String[] campos = linha.trim().split(";");
        cidade.addElementos(campos, gabarito);
    }

    private static void addDadosEstacoesAutomaticas (String linha, Info cidade, Boolean gabarito[]) throws ParseException{
        String[] campos = linha.trim().split(";");
        cidade.addElementosEstacoesAutomaticas(campos,gabarito);
    }
    
    // Funes para ler aquivos dow arquivos de estaoes convencionais do site do INMET (Link: https://bdmep.inmet.gov.br/)
    private static Info lerArquivoEstacoesConvencionais(BufferedReader arquivo, String nomeEstacao, ArrayList<Sensor> sensores) throws ParseException, IOException{
        Info medicao = null;
        
        
        medicao = lerCabecalhoEstacoesConvencionais(arquivo, medicao, nomeEstacao);
        
        medicao = addDadosEstacoesConvencionais(arquivo, medicao, sensores);
        
        System.out.println("Estacao: " + medicao.getEstacao().getNome());
        
        return medicao;
    }
    
    private static Info lerCabecalhoEstacoesConvencionais(BufferedReader arquivo, Info medicao, String nomeEstacao) throws ParseException, IOException{
        
        //Ler cabeçalho da estacao
        
        //codigo
        String[] line2 = arquivo.readLine().split(":");
        //latitude
        String[] line3 = arquivo.readLine().split(":");
        //longitude
        String[] line4 = arquivo.readLine().split(":");
        //altitude
        String[] line5 = arquivo.readLine().split(":");
        //situação
        String[] line6 = arquivo.readLine().split(":");
        //data inicial
        String[] line7 = arquivo.readLine().split(":");
        // datafinal
        String[] line8 = arquivo.readLine().split(":");
        //periodicidade
        String[] line9 = arquivo.readLine().split(":");

        medicao = new Info(
                                nomeEstacao,
                          line2[1].trim(),
                         line3[1].trim(),
                        line4[1].trim(),
//                         line5[1].trim(),
//                         line6[1].trim(),
                       line5[1].trim(),
                        line6[1].trim(),
                     line9[1].trim());

        return medicao;

    }
    
    private static Info addDadosEstacoesConvencionais(BufferedReader arquivo, Info medicao, ArrayList<Sensor> sensores) throws IOException, ParseException{
       
        Boolean gabarito[];
                
        String line = arquivo.readLine();
        line = arquivo.readLine();
        String[] coluna = line.split(";");
        medicao.setColuna(coluna);
        
        gabarito = preencherGabarito(coluna, sensores);
        
        medicao = vincularSensoresColunas(medicao, sensores);
                
        line = arquivo.readLine();
        
        while(line != null){
                addDados(line, medicao,gabarito);
                line = arquivo.readLine();    
            }
        return medicao;
    }
    
    private static Info lerArquivoEstacoesAutomaticas(BufferedReader arquivo,ArrayList<Sensor> sensores){
        Info medicao;
        medicao = null;
        
        medicao = lerCabecalhoEstacoesAutomaticas(arquivo, medicao);
        
        medicao = addDadosEstacoesAutomaticas(arquivo, medicao, sensores);
        
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
            linha = arquivo.readLine();

            //Ler data Fundação
            campos = linha.split(";");
            nova.setDataFundacaoBR(campos[1]);
            
            nova.setID();
            //Definir estacao
            medicao.setEstacao(nova);

             
               

            return medicao;
        
        } catch (IOException e ) {
            return null;
        }
    }
    
    private static Info addDadosEstacoesAutomaticas(BufferedReader arquivo, Info medicao, ArrayList<Sensor> sensores) {
        try{
            
            Boolean gabarito[];
            String campos[], linha;
           // ler colunas
            linha = arquivo.readLine();      

            campos = linha.split(";");

            medicao.setColuna(campos);
            
            System.out.println("Linha: " + linha);
            

            gabarito = preencherGabarito(campos, sensores);

            medicao = vincularSensoresColunas(medicao, sensores);  
            
            medicao.getEstacao().setPeriodicidade("Horaria");
            
                      

            // Ler primeira linha
            linha = arquivo.readLine();
            
            //medicao.atualizaDataInicial(linha.split(";")[0]); 
            //System.out.println(linha);
            while(linha != null){ 
                
                addDadosEstacoesAutomaticas(linha, medicao, gabarito);
                //medicao.atualizaDataFinal(linha.split(";")[0]);
                
                linha = arquivo.readLine();    
            } 
        
            return medicao;
        }catch (IOException ioe) {
            System.out.println("Erro de entrada e saida. Mensagem: " + ioe.getMessage());
            return null;
//        } catch (NullPointerException npe){
//            System.out.println("Erro de ponteiro nulo. Mensagem: " + npe.getMessage() );
//            return null;
        }  catch (ParseException ex) {
            System.out.println("Erro de conversao de valores. Mensagem: " + ex.getMessage());
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
            
            
            //medicao.atualizaDataInicial(linha.split(";")[0]);
            
            System.out.println(linha);
            while(linha != null){ 
                
                addDadosEstacoesAutomaticas(arquivo, medicao);
                //medicao.atualizaDataFinal(linha.split(";")[0]);
                
                linha = arquivo.readLine();    
            } 
        
            return medicao;
        }catch (IOException ioe) {
            return null;
        } catch (NullPointerException npe){
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
//            bw.write("Data Inicial: " + info.getDataInicialBR() + "\n");
//            bw.write("Data Final: " + info.getDataFinalBR() + "\n");
            bw.write("Periodicidade da Medicao: " + info.getPeriodicidade() + "\n");
            bw.newLine();

            for (Coluna coluna : info.getLista()){
                bw.write(coluna.getTitulo() + ";");
            }
            bw.write("\n");
            int linhaCount = info.getLinhaCount();
            for(int index = 0; index < linhaCount; index++){
                List<String> linha = info.getLinhaCsv(index);
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

    public static void gerarTxt(String relatorio, String pathFile){
        try(FileWriter fileWriter = new FileWriter(pathFile)){
            fileWriter.write(relatorio);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    private static Boolean[] preencherGabarito(String[] coluna, ArrayList<Sensor> sensores){
        String stra, strb;
        
        Boolean gabarito[];
        
        gabarito = new Boolean[coluna.length];
        
        for (int i = 0; i < gabarito.length; i++){
            gabarito[i] = false;
        }
        
        gabarito[0] = true;
        gabarito[1] = true;
        
        for (int i = 2; i < coluna.length; i++){
            for(int j = 0; j < sensores.size(); j++){
                stra = removerAcentos(coluna[i]);
                strb = removerAcentos(sensores.get(j).getTextoCarga().split("\\(")[0]);
                if (stra.contains(strb)){
                    gabarito[i] = true;
                    break;
                }
            }
        }
        return  gabarito;
    }
    
    private static Info vincularSensoresColunas(Info medicao, ArrayList<Sensor> sensores ){
        String stra, strb;
        for (int c = 0; c < medicao.getColuna().length; c++){
            for (int s = 0; s < sensores.size(); s++) {
                stra = removerAcentos(medicao.getColuna(c));
                strb = removerAcentos(sensores.get(s).getTextoCarga().split("\\(")[0]);
                if (stra.contains(strb)){
                    medicao.setSensor(c, sensores.get(s));
                    break;
                }
            }
            
        }
        return medicao;
    }
    
    public static String removerAcentos(String str) {
    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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
