/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabela;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacka
 */
public class DataProvider<T> implements PaginationDataProvider<T>{
    
    private List<T> data;
    
    public DataProvider(List<T> dataList){
        this.data = dataList;
    }
    
    public void updateData(List<T> newData){
        this.data = newData;
    }
    
    @Override
    public int getTotalRowCount() {
        return data.size();
    }

    @Override
    public List<T> getRows(int StartIndex, int endIndex) {
        List<T> rows = new ArrayList<>();
        for(int index = StartIndex; index < endIndex; index++){
            if(index < data.size()){
                rows.add(data.get(index));
            }
        }
        return rows;
    }

    @Override
    public void clearData() {
        data.clear();
    }
    
}
