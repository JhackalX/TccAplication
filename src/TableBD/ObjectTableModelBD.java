/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableBD;

import Object.Info;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jacka
 */
public class ObjectTableModelBD extends AbstractTableModel {
    private ArrayList<Info> info;
    private final String[] colunas = {"Estação:", "Data de Criação:", "Codigo:", "Selecionar:", "Excluir:"};

    public ObjectTableModelBD(ArrayList<Info> info) {
        this.info = info;
    }
    
    public void addInfo(Info info){
        this.info.add(info);
        fireTableRowsInserted(this.info.size() - 1, this.info.size() - 1);
    }
    
    public void removeInfo(int rowIndex){
        this.info.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    @Override
    public int getRowCount() {
        return info.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Info info = this.info.get(rowIndex);
        switch (columnIndex){
            case 0:
                return info.getEstacao().getNome();
            case 1:
                return info.getDataCriacaoBR();
            case 2:
                return info.getEstacao().getCodigo();
            case 3:
                return "Selecionar";
            case 4:
                return "Excluir";
            default:
                return null;
        }
    }
    
}
