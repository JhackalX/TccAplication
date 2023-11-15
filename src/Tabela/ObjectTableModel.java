/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jacka
 */
public abstract class ObjectTableModel<T> extends AbstractTableModel {

    private List<String> columnNames = new ArrayList<>();
    private List<T> objectRows = new ArrayList<>();

    public void addColumn(String columnName){
        columnNames.add(columnName);
        this.refreshTab();
    }

    public void addColumn(String[] columnName){
        if(columnName.length > 0){
            for(int i = 0; i < columnName.length; i++){
                this.addColumn(columnName[i]);            
            }            
        }
        this.refreshTab();
    }

    public void addColumn(ArrayList<String> columnName){
        if(columnName.size() > 0){
            for(int i = 0; i < columnName.size(); i++){
                this.addColumn(columnName.get(i));            
            }            
        }
        this.refreshTab();
    }
    
    public void addRow(T row){
        objectRows.add(row);
        fireTableRowsInserted(objectRows.size() - 1, objectRows.size() - 1);
    }


    public void removeColumn(int index){
        if(!this.columnNames.isEmpty()){
            columnNames.remove(index);            
        }
        this.refreshTab();
    }    

    public void removeRow(int index){
        if(!this.objectRows.isEmpty()){
            objectRows.remove(index);            
        }
        fireTableRowsDeleted(index, index);
    }    

    public void removeAllColumn(){
        columnNames.clear();
        this.refreshTab();
    }    

    public void removeAllRow(){
        if(!this.objectRows.isEmpty()){
            while(!this.objectRows.isEmpty()){
                this.removeRow(0);            
            }            
        }
        this.refreshTab();
    }    

//    public void replaceAllColumns(List<T> newRows){
//        this.objectRows = newRows;
//        fireTableDataChanged();
//    }    

    public void refreshTab(){
        fireTableStructureChanged();
        fireTableDataChanged();
    }    

//verificar a linha comentada poruque está dando erro    
    public void clearAll(){
        this.clearData();//passivel de se comentada
        this.clearColumn();
        this.refreshTab();
    }

//limpa dados da coluna    
    public void clearColumn(){
        this.columnNames.clear();
//        this.refreshTab();
    }

//limpa dados da tabela    
    public void clearData(){
        this.objectRows.clear();//linha passivel de ser comentada
//        fireTableDataChanged();
    }
    
    public void updateColumnNames(List<String> newColumns){
        this.columnNames = newColumns;
        fireTableStructureChanged();
    }
    
    public void updateData(List<T> newRows){
        this.objectRows = newRows;
        fireTableDataChanged();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }   
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }
    
    public List<T> getObjectRows() {
        if(objectRows == null){
            return null;           
        }else{
            return objectRows;
        }
    }

    public void setObjectRows(List<T> objectRows) {
        this.objectRows = objectRows;
    }
    
    @Override
    public int getRowCount() {
        if(objectRows == null){
            return 0;           
        }else{
            return objectRows.size();
        }        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(objectRows == null){
            return null;           
        }else{
            T t = objectRows.get(rowIndex);
            return getValueAt(t, columnIndex);
        } 
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (objectRows.isEmpty()) {
            return Object.class;
        }
        Object valueAt = getValueAt(0, columnIndex);
        return valueAt != null ? valueAt.getClass() : Object.class;
    }
    
//    @Override
//    public void fireTableDataChanged() {
//        super.fireTableDataChanged();
//        adjustColumnWidths();
//    }
    
    public abstract Object getValueAt(T t, int columnIndex);


    
}
