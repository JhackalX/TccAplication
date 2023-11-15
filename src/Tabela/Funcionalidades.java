/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabela;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.TableModel;
/**
 *
 * @author jacka
 */
public class Funcionalidades {
    
    //aqui passa as colunas
    public static TableModel createObjectDataModel(String[] coluna) {
        
        ObjectTableModel<Employee> objTabModel = new ObjectTableModel<Employee>() {
            @Override
            public Object getValueAt(Employee employee, int columnIndex) {
                if(columnIndex >= 0){
                    return employee.getLine(columnIndex);
                }
                return null;
            }            
        };
        objTabModel.addColumn(coluna);
        return objTabModel;
    }
    //aqui passa as colunas
    public static ObjectTableModel createObjectDataModel(ArrayList<String> coluna) {
        
        ObjectTableModel<Employee> objTabModel = new ObjectTableModel<Employee>() {
            @Override
            public Object getValueAt(Employee employee, int columnIndex) {
                if(columnIndex >= 0){
                    return employee.getLine(columnIndex);
                }
                return null;
            }            
        };
        objTabModel.addColumn(coluna);
        return objTabModel;
    }
    
    //aqui passa a lista com o objeto
    public static PaginationDataProvider<Employee> createDataProvider(List<Employee> lista) {
        
        final List<Employee> list = lista;

        return new PaginationDataProvider<Employee>() {
            @Override
            public int getTotalRowCount() {
                if(list == null){
                    return 0;
                }else{
                    return list.size();
                }                

            }

            @Override
            public List<Employee> getRowsBetweenIdices(int startIndex, int endIndex) {
                if(list == null){
                    return null;
                }else{
                    return list.subList(startIndex, endIndex);
                } 
            }
            
            @Override
            public void clearData(){
                list.clear();
            }
        };
    }

    public static JFrame createFrame() {
        JFrame frame = new JFrame("JTable Pagination example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(new Dimension(600, 300));
        frame.setSize(new Dimension(300, 200));
        return frame;
    }

}
