/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tccaplication;

import DTO.CtrlGeral;
import DTO.CtrlInterface;
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
        CtrlGeral ctrlGeral = new CtrlGeral();
        
        CtrlInterface ctrlInterface= new CtrlInterface(ctrlGeral);
    }
    
}
