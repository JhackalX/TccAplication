/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabela;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author jacka
 */
public class PaginatedTableDecorator<T> {
    private JTable table;
    private PaginationDataProvider<T> dataProvider;
    private int[] pageSizes;
    private JPanel contentPanel;
    private int currentPageSize;
    private int currentPage = 1;
    private JPanel pageLinkPanel;
    private ObjectTableModel objectTableModel;
    private static final int maxPageComponentsToShow = 9;
    
    private JButton btnPrevious;
    private JButton btnNext;
    private JLabel lblCurrentPage;
    
    private PaginatedTableDecorator(JTable table, PaginationDataProvider<T> dataProvider,
                                    int[] pageSizes, int defaultPageSize) {
        this.table = table;
        this.dataProvider = dataProvider;
        this.pageSizes = pageSizes;
        this.currentPageSize = defaultPageSize;
    }

    public static <T> PaginatedTableDecorator<T> decorate(JTable table,
                                                          PaginationDataProvider<T> dataProvider,
                                                          int[] pageSizes, int defaultPageSize) {
        PaginatedTableDecorator<T> decorator = new PaginatedTableDecorator<>(table, dataProvider,
                pageSizes, defaultPageSize);
        decorator.init();
        return decorator;
    }
    
    private void init() {
        initDataModel();
        initPaginationComponents();
        paginate();
    }

    private void initDataModel() {
        TableModel model = table.getModel();
        if (!(model instanceof ObjectTableModel)) {
            throw new IllegalArgumentException("TableModel must be a subclass of ObjectTableModel");
        }
        objectTableModel = (ObjectTableModel) model;
    }
    
    //adicionando uma table em um jscroll e ensirindo em um panel logo em seguida
    private void initPaginationComponents() {        
        contentPanel = new JPanel(new BorderLayout());
        btnNext = new JButton("Next");
        btnPrevious = new JButton("Previous");
        lblCurrentPage = new JLabel();
        
        JPanel paginationPanel = createPaginationPanel();
        contentPanel.add(paginationPanel, BorderLayout.NORTH);    
        // Cria e adiciona o pageLinkPanel
        pageLinkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));        
        pageLinkPanel.add(btnPrevious);
        pageLinkPanel.add(lblCurrentPage);
        pageLinkPanel.add(btnNext);
        contentPanel.add(pageLinkPanel, BorderLayout.SOUTH);
        
        contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        // configura a ação do botão previous
        btnPrevious.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                currentPage--;
                paginate();
                updatePaginationControls();
            }
        });
        // configura a ação do botão next
        btnNext.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                currentPage++;
                paginate();
                updatePaginationControls();
            }
        });
    }
    
    private void paginate() {
        int startIndex = (currentPage - 1) * currentPageSize;
        int endIndex = startIndex + currentPageSize;
        if (endIndex > dataProvider.getTotalRowCount()) {
            endIndex = dataProvider.getTotalRowCount();
        }
        List<T> rows = dataProvider.getRowsBetweenIdices(startIndex, endIndex);
        objectTableModel.setObjectRows(rows);
        objectTableModel.fireTableDataChanged();
        updatePaginationControls();
    }
    
    public JPanel getContentPanel() {
        return contentPanel;
    }
    
    private void updatePaginationControls(){
        
        int totalRows = dataProvider.getTotalRowCount();
        int totalPageCount = (int) Math.ceil((double) totalRows / currentPageSize);
        lblCurrentPage.setText("Page " + currentPage + " of " + totalPageCount);
        
        btnPrevious.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage < totalPageCount);    
    }
    
    private JPanel createPaginationPanel() {
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        if(pageSizes != null){
            JComboBox<Integer> pageComboBox = new JComboBox<>(
                    Arrays.stream(pageSizes)
                               .boxed()
                               .toArray(Integer[]::new));
            pageComboBox.addActionListener((ActionEvent e) -> {
                int currentPageStartRow = ((currentPage -1) * currentPageSize) + 1;
                currentPageSize = (Integer) pageComboBox.getSelectedItem();
                currentPage = ((currentPageStartRow - 1) / currentPageSize) + 1;
                paginate();
            });
            paginationPanel.add(new JLabel("Page Size:"));
            paginationPanel.add(pageComboBox);
            pageComboBox.setSelectedItem(currentPageSize);
        }
        return paginationPanel;
    }
    
    public void setNewDataModel(ObjectTableModel newModel, 
                                PaginationDataProvider<T> newDataProvider, 
                                int[] newPageSizes){
        this.dataProvider = newDataProvider;
        this.pageSizes = newPageSizes;
        
        this.currentPage = 1;
        if(newPageSizes != null && newPageSizes.length > 0){
            this.currentPageSize = newPageSizes[0];
        }
        
        this.objectTableModel = newModel;
        this.table.setModel(newModel);
        //função de ajuste de tamanho de titulo da coluna
        //this.adjustColumnWidths();
        paginate();
    }
    //função de ajuste de espaçamento de colunas
    public void adjustColumnWidths(){
        TableModel model = table.getModel();
        
        for(int column = 0; column < model.getColumnCount(); column++){
            int width = 50;
            for(int row = 0; row < model.getRowCount(); row++){
                TableCellRenderer render = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(render, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            
            TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
            Component headerComp = headerRenderer.getTableCellRendererComponent(table,
                                                                           model.getColumnName(column),
                                                                        false,
                                                                         false,
                                                                             0,
                                                                                column);
            width = Math.max(width, headerComp.getPreferredSize().width + 1);
            
            table.getColumnModel().getColumn(column).setPreferredWidth(width);
        }
        table.revalidate();
    }
    
    
    
}
