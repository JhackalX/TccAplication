/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tccaplication;

import Interface.WindowDecorator;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author jacka
 */
public class TccAplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Window teste = new Window();
        JFrame janela = new JFrame();     
        WindowDecorator teste = Interface.WindowDecorator.decorator(janela);
        janela.setDefaultCloseOperation(janela.EXIT_ON_CLOSE);
        janela.show();
    }
    
}
