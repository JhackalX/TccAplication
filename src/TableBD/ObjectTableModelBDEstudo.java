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
public class ObjectTableModelBDEstudo extends AbstractTableModel {
    private ArrayList<Info> info;
    private final String[] colunas = {"Nome:", "Data de Criação:", "Periodo:", "Selecionar:", "Excluir:"};

    public ObjectTableModelBDEstudo(ArrayList<Info> info) {
        this.info = info;
    }
    
    public void updateInfoList(ArrayList<Info> newInfo){
        this.info = newInfo;
        fireTableDataChanged();
    }
    
    public void addInfo(Info info){
        this.info.add(info);
        fireTableRowsInserted(this.info.size() - 1, this.info.size() - 1);
        fireTableDataChanged();
    }
    
    public void removeInfo(int rowIndex){
        if (rowIndex >= 0 && rowIndex < this.info.size()){
            this.info.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);   
        }
    }
    
    @Override
    public int getRowCount() {
        if (info == null || info.isEmpty()){
            return 1; // Retorna 1 para mostrar uma linha com mensagem padrão
        }
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
        if(info == null || info.isEmpty()){
            if (columnIndex == 0){
                return "Nenhuma informação disponivel";
            }
            return "";
        }else{
            Info info = this.info.get(rowIndex);
            switch (columnIndex){
                case 0:
                    return info.getNome();
                case 1:
                    return info.getDataCriacaoBR();
                case 2:
                    return info.getPeriodo();
                case 3:
                    return "Selecionar";
                case 4:
                    return "Excluir";
                default:
                    return null;
            }
        }
    }
    
}
