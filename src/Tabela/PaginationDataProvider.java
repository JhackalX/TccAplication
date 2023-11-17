/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabela;

import java.util.List;

/**
 *
 * @author jacka
 */
public interface PaginationDataProvider<T> {
    

    int getTotalRowCount();
    List<T> getRows(int StartIndex, int endIndex);
    void clearData();
}
