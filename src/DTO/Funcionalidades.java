/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.Validacao;
import Object.Info;
import Object.Dados;
import Object.Dados;
import Object.Info;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        try(BufferedReader ler = new BufferedReader(new FileReader(path))){
           
            String[] line1 = ler.readLine().split(":");
            String[] line2 = ler.readLine().split(":");
            String[] line3 = ler.readLine().split(":");
            String[] line4 = ler.readLine().split(":");
            String[] line5 = ler.readLine().split(":");
            String[] line6 = ler.readLine().split(":");
            String[] line7 = ler.readLine().split(":");
            String[] line8 = ler.readLine().split(":");
            String[] line9 = ler.readLine().split(":");

            Info medicao = new Info(line1[1],
                                   line2[1],
                                   line3[1],
                                   line4[1],
                                   line5[1],
                                   line6[1],
                                   line7[1],
                                   line8[1],
                                   line9[1]);
            

            String line = ler.readLine();
            line = ler.readLine();
            String[] coluna = line.split(";");
            medicao.setColuna(coluna);
            line = ler.readLine();

            while(line != null){
//                System.out.println(line);
                addDados(line, medicao);
                line = ler.readLine();    
            }
//            Guia nova = new Guia();
//            nova.gerarGuia(medicao.getLista(2).getDados());
//            nova.imprimir();
//            for(int i = 3; i < medicao.getColunaCount(); i++){
//                medicao.getLista(i).imprimirColuna();
//                for(int x = 0; x < medicao.getLista(i).getDados().size(); x++){
//                    System.out.println(medicao.getLista(i).getDado(x).toString());
//                }
//            }            
//            Validacao o = new Validacao(cabeca.dadosPValidar());

            JOptionPane.showMessageDialog(null, "Leitura Completa!");

            return medicao;
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
