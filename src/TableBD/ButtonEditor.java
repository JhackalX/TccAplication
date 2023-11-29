/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableBD;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author jacka
 */
public class ButtonEditor extends DefaultCellEditor {
    private JTable table;
    private ObjectTableModelBD model;
    private JButton button;
    private String label;
    private boolean isPushed;
    
    public ButtonEditor(JCheckBox checkBox,
                        ObjectTableModelBD modelo,
                        JTable table){
        super(checkBox);
        this.model = modelo;
        this.table = table;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fireEditingStopped();
                System.out.println("clicou em algo");
            }
        });
    }
    
    public Component getTableCellEditorComponent(JTable table, 
                                                 Object value, 
                                                 boolean isSelected, 
                                                 int row, int column){
        if(isSelected){
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        }else{
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "": value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }
    
    public Object getCellEditorValue(){
        if(isPushed){
            int selectedRow = table.convertRowIndexToModel(table.getEditingRow());
            System.out.println("selecionou: " + selectedRow + " selecionada.");
            
            if(label.equals("Excluir:")){
                int confirm = JOptionPane.showConfirmDialog(table, "Tem certeza que deseja excluir?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_NO_OPTION){
                    model.removeInfo(selectedRow);
                    table.repaint();                    
                }
            } else if (label.equals("Selecionar:")){
                table.setRowSelectionInterval(selectedRow, selectedRow);
//                System.out.println("selecionou: " + selectedRow + " selecionada.");
            }
            //aqui coloca o codigo para tratar o click no botão
            JOptionPane.showMessageDialog(button, label+ " : Ouch!");
        }
        isPushed = false;
        return label;
    }
}
