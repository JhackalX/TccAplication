/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabela;

import java.util.ArrayList;

/**
 *
 * @author jacka
 */
public class Employee {
    
    private ArrayList<String> line = new ArrayList<String>();
    
    public ArrayList<String> getLine() {
        return line;
    }
    
    
    
    public String getLine(int index) {
        return line.get(index);
    }

    public void setLine(ArrayList<String> line) {
        this.line = line;
    }

    public void addLine(String line) {
        this.line.add(line);
    }

    
}
