/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.util.ArrayList;
import java.util.List;

//Classe a ser extinta, apenas para testes
public class Funcionalidades {
    public static List gerarListaAnaliseMensais(){
        
        List<AnaliseMensal> lista = new ArrayList();
        
        String valor1;
        String valor2;
        String valor3;
        String colunas;
        
        for(int coluna = 1; coluna < 10; coluna++ ){
            for(int ano = 2015; ano < 2025; ano++ ){
                for(int mes = 1; mes < 13; mes++ ){
                    valor1 = "" + (coluna + ano);
                    valor2 = "" + (mes + ano);
                    valor3 = "" + (coluna + mes);
                    colunas = "coluna " + coluna;
                    
//                    lista.add(new AnaliseMensal(colunas,
//                                                  ano,
//                                                  mes,
//                                           valor1,
//                                            valor2,
//                                       valor3));
                }
            }
        }
        return lista;
    }
}
