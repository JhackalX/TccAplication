/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import Object.AnaliseMensal;
import Object.ListaClassificacao;
import Tabela.Employee;
import static Tabela.Funcionalidades.createDataProvider;
import static Tabela.Funcionalidades.createObjectDataModel;
import Tabela.ObjectTableModel;
import Tabela.PaginatedTableDecorator;
import Tabela.PaginationDataProvider;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author jacka
 */
public class VisaoAuxiliarDecorator {
    
    private JPanel fundo;
    private JPanel jPanelTable;
    private JPanel jPanelDetalhes;
    private JPanel jPanelBtn;
    private JPanel jPanelInfo;
    private JPanel jPanelStatus;
    
    private JScrollPane jScrollPaneInfo1;
    private JScrollPane jScrollPaneInfo2;
    private JScrollPane jScrollPaneInfo3;
    private JScrollPane jScrollPaneSaibaMais;
    private JScrollPane jScrollPaneStatus;
    
    private JTextArea jTextAreaInfo1;
    private JTextArea jTextAreaInfo2;
    private JTextArea jTextAreaInfo3;
    private JTextArea jTextAreaSaibaMais;
    
    private JTabbedPane jTabbedPaneColunas;
    
    private JButton avancarBtn;
    private JButton salvarBtn;
    private JButton voltarBtn;
    
    private PaginationDataProvider<Employee> dataProvider;
    private PaginatedTableDecorator<Employee> paginatedDecorator;
    private ArrayList<Employee> list;
    private TableModel tabDataModel;
    private JTable tabela;
    private List<ListaClassificacao> lista;
    private final String meses[] = {"Janeiro", "Fervereiro", "Março", "Abril", 
                                    "Maio", "Junho", "Julho", "Agosto", 
                                    "Setembro", "Outubro", "Novembro", "Dezembro"};
    
    public VisaoAuxiliarDecorator(){
        this.initComponents();
    }
    
    public void initComponents(){

        this.fundo = new JPanel(new BorderLayout());
        this.jPanelTable = new JPanel();
        this.jPanelDetalhes = new JPanel(new BorderLayout());
        this.jPanelInfo = new JPanel();
        this.jPanelBtn = new JPanel();
        this.jPanelStatus = new JPanel();

        this.jScrollPaneInfo1 = new JScrollPane();
        this.jScrollPaneInfo2 = new JScrollPane();
        this.jScrollPaneInfo3 = new JScrollPane();
        this.jScrollPaneSaibaMais = new JScrollPane();
        this.jScrollPaneStatus = new JScrollPane();

        this.jTextAreaInfo1 = new JTextArea();
        this.jTextAreaInfo2 = new JTextArea();
        this.jTextAreaInfo3 = new JTextArea();
        this.jTextAreaSaibaMais = new JTextArea();
        
        this.jTabbedPaneColunas = new JTabbedPane();
        
        this.avancarBtn = new JButton();
        this.salvarBtn = new JButton();
        this.voltarBtn = new JButton();
        String[] coluna = {"id" , "Posição", "Temperatura", "Pressão"};
//        this.tabela = new JTable(createObjectDataModel(DTO.CtrlGeral.getColuna()));
//        Funcionalidades.adjustColumnWidths(tabela);
//        this.tabela = new JTable();
//        this.list = new ArrayList<Employee>();
        this.lista = null;
        this.list = new ArrayList<Employee>();
        
        for (int i = 1; i <= 500; i++) {
            Employee e = new Employee();
            e.addLine("" + i);
            e.addLine("" + i);
            e.addLine(i + "ºC");
            e.addLine(i + "PA");
            list.add(e);
        }
        this.initTable(createObjectDataModel(coluna), 
                           this.list, 
                                new int[]{29, 30, 31},
                   30);        
    }
    
    private void initTable(TableModel novoModelo, ArrayList<Employee> lista, int[] pSizes, int defaultPageSize){
        this.tabela = new JTable(novoModelo);
        this.dataProvider = createDataProvider(lista);
        this.paginatedDecorator = PaginatedTableDecorator.decorate(tabela,
                this.dataProvider,pSizes , 30);
    }

    public JPanel visaoAuxiliarReady(){
        //this.panelStatus();
        this.panelTable();
        this.panelDetalhes();
        
        this.panelFundo();
        
        return this.fundo;
    }

    private void configureBtns(){
        this.avancarBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.avancarBtn.setText("Avançar");

        this.salvarBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.salvarBtn.setText("Salvar");        

        this.voltarBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.voltarBtn.setText("Voltar");     
        
        this.salvarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBActionPerformed(evt);
            }
        });   

        this.voltarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTableBActionPerformed(evt);
            }
        });   
        
        
    }

    private void configureAjuda(){
        
        this.jTextAreaInfo1.setEditable(false);
        this.jTextAreaInfo1.setColumns(20);
        this.jTextAreaInfo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaInfo1.setRows(5);
        this.jTextAreaInfo1.setText("\n Fórmula:"
                         + "\n MAD = ( 1 / n ) x ( ∑| Y - Ȳ | )"
                         + "\n Ȳ = Valor predito"
                         + "\n Y = Valor original"
                         + "\n ∑ = Somatória"
                         + "\n n = Numero de elementos"
                         + "\n | | = Módulo (valor absoluto)");
        this.jTextAreaInfo1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Desvio Absoluto Médio:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        this.jScrollPaneInfo1.setViewportView(this.jTextAreaInfo1);

        this.jTextAreaInfo2.setEditable(false);
        this.jTextAreaInfo2.setColumns(20);
        this.jTextAreaInfo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaInfo2.setRows(5);
        this.jTextAreaInfo2.setText("\n Fórmula:"
                         + "\n MAE = ( 1 / n ) x ( ∑( | Y - Ȳ | / Y ) )"
                         + "\n Ȳ = Valor predito"
                         + "\n Y = Valor original"
                         + "\n ∑ = Somatoria"
                         + "\n n = Numero de elementos"
                         + "\n | | = Módulo (valor absoluto)");
        this.jTextAreaInfo2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Erro Médio Absoluto:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        this.jScrollPaneInfo2.setViewportView(this.jTextAreaInfo2);

        this.jTextAreaInfo3.setEditable(false);
        this.jTextAreaInfo3.setColumns(20);
        this.jTextAreaInfo3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaInfo3.setRows(5);
        this.jTextAreaInfo3.setText("\n Fórmula:"
                         + "\n MAPE = [ MAE ] x 100"
                         + "\n MAE = Erro Médio Absoluto");
        this.jTextAreaInfo3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Erro Absoluto Médio Percentual: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        this.jScrollPaneInfo3.setViewportView(this.jTextAreaInfo3);

        this.jTextAreaSaibaMais.setColumns(20);
        this.jTextAreaSaibaMais.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaSaibaMais.setRows(5);
        this.jTextAreaSaibaMais.setText("\n Os resultados apresentados na  área \"STATUS\" foram"
                          + "\n desconsiderados os valores preditos onde ouve a"
                          + "\n substituição das lacunas, pois seus valores se anulariam"
                          + "\n recorrente a formula do cálculo do erro e tendênciariam"
                          + "\n os  valores finais.");
        this.jTextAreaSaibaMais.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saiba mais:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        this.jScrollPaneSaibaMais.setViewportView(this.jTextAreaSaibaMais);        
    }
    
    private void StatusTab(List<ListaClassificacao> list){
        this.lista = list;
        this.atualizarPanel();

    }
    
    private int calcPivorAno(List<AnaliseMensal> lista, int inicio){
        
        int pivor = inicio;
        
        while((pivor + 1 < lista.size()) && 
            (lista.get(pivor).getIntAno() == lista.get(pivor + 1).getIntAno())){
            //System.out.println(lista.get(pivor).imprimir());
            pivor++;
        }
        return pivor;
    }
    

    
    private JTabbedPane AddMesesTab(List<AnaliseMensal> list){
        
        JTabbedPane mesTabs = new JTabbedPane();
        List<AnaliseMensal> lista = list;
        
        for(int index = 0; index < lista.size(); index++){
            mesTabs.addTab(this.meses[lista.get(index).getIntMes()-1],
                        new JScrollPane(this.panelStatus(lista.get(index).getMad(),
                                                            lista.get(index).getMae(),
                                                           lista.get(index).getMape())));
        }    
        return mesTabs;
    }    

    private JTabbedPane AddAnosTab(List<AnaliseMensal> list){
        
        JTabbedPane anoTabs = new JTabbedPane();
        List<AnaliseMensal> lista = list;
        int pivor = 0;
   
        for(int index = 0; index < lista.size();){
            pivor = this.calcPivorAno(lista, index);
            anoTabs.addTab(lista.get(index).getAno(),
                        this.AddMesesTab(lista.subList(index, pivor)));
            index = pivor + 1;               
        }
       
        return anoTabs;
    }    
    
    private JTabbedPane AddColunasTab(List<ListaClassificacao> list){
        
        JTabbedPane colunaTabs = new JTabbedPane();
        List<ListaClassificacao> lista = list;
        
        for(int coluna = 0; coluna < lista.size(); coluna++){            
            colunaTabs.addTab(lista.get(coluna).getTitulo(),
                           this.AddAnosTab(lista.get(coluna).getLista()));
//            System.out.println("Pivor: " + pivor);
        }
        return colunaTabs;
    }    
    
    private JPanel panelStatus(String mad, 
                             String mae, 
                             String mape){
        
        JPanel Status = new JPanel();
        
        JLabel jlabelMAPE = new JLabel();
        JLabel jlabelMAE = new JLabel();
        JLabel jlabelMAD = new JLabel();
        
        JTextField jTextFieldMAD = new JTextField();
        JTextField jTextFieldMAE = new JTextField();
        JTextField jTextFieldMAPE = new JTextField();        
        
        jlabelMAPE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabelMAPE.setText("Erro Absoluto Médio Percentual (MAPE):");

        jlabelMAE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabelMAE.setText("Erro Médio Absoluto (MAE):");

        jlabelMAD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabelMAD.setText("Desvio Absoluto Médio (MAD):");

        jTextFieldMAD.setEditable(false);
        jTextFieldMAD.setText(mad);
        jTextFieldMAE.setEditable(false);
        jTextFieldMAE.setText(mae);
        jTextFieldMAPE.setEditable(false);
        jTextFieldMAPE.setText(mape);

        jPanelStatus.setBorder(BorderFactory.createTitledBorder(
                                                  null,
                                                   "Status:",
                                          javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                              javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                                        new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanelStatus.setForeground(new java.awt.Color(204, 204, 204));
        //jPanelStatus.setBackground(Color.red);
        GroupLayout statusLayout = new GroupLayout(jPanelStatus);
        jPanelStatus.setLayout(statusLayout);
        statusLayout.setHorizontalGroup(
            statusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jlabelMAPE)
                        .addComponent(jlabelMAE,
                                      GroupLayout.Alignment.TRAILING))
                    .addComponent(jlabelMAD))
                .addGap(10, 10, 10)
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMAE,
                                      GroupLayout.PREFERRED_SIZE,
                                     200,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMAD,
                                      GroupLayout.PREFERRED_SIZE,
                                     200,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMAPE,
                                      GroupLayout.PREFERRED_SIZE,
                                     200,
                                     GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                 Short.MAX_VALUE))
        );
        statusLayout.setVerticalGroup(
            statusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMAD,
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelMAD))
                .addGap(10, 10, 10)
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelMAE,
                                      GroupLayout.PREFERRED_SIZE,
                                     15,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMAE,
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMAPE,
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelMAPE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                 Short.MAX_VALUE))
        );
        return Status;
    }
    
    private void panelTable(){
        //introduz o layout a tabela a variavel
        this.tabela.setAutoCreateRowSorter(false);
        
        

        
//        PaginationDataProvider<Employee> dataProvider = createDataProvider(this.list);
//        PaginatedTableDecorator<Employee> paginatedDecorator = PaginatedTableDecorator.decorate(this.tabela,
//              dataProvider, new int[]{29, 30, 31}, 30);
               
        //configura o painel onde a tabela é inserida
        this.jPanelTable.setBackground(new java.awt.Color(54, 63, 73));
        GroupLayout jPanelTableLayout = new GroupLayout(this.jPanelTable);
        this.jPanelTable.setLayout(jPanelTableLayout);

        //organiza o layout com a tabela
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.paginatedDecorator.getContentPanel(),
//                .addComponent(this.tabela,
                              GroupLayout.PREFERRED_SIZE,
                              1200,
                              (GroupLayout.PREFERRED_SIZE))
                .addContainerGap()
                .addGap(1))
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.paginatedDecorator.getContentPanel(),
//                .addComponent(this.tabela,
                              GroupLayout.PREFERRED_SIZE,
                              300,
                              GroupLayout.PREFERRED_SIZE)
                .addContainerGap()
                .addGap(1))
        );        
    }
 
    private void panelBtn(){
        this.configureBtns();
        this.jPanelBtn.setBackground(new java.awt.Color(255, 255, 255));
        this.jPanelBtn.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
 
        GroupLayout BtnLayout = new GroupLayout(this.jPanelBtn);
        this.jPanelBtn.setLayout(BtnLayout);
 
        BtnLayout.setHorizontalGroup(
            BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.voltarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BtnLayout.createSequentialGroup()
                        .addGroup(BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(this.avancarBtn)
                            .addComponent(this.salvarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BtnLayout.setVerticalGroup(
            BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.salvarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.avancarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.voltarBtn)
                .addContainerGap())
        );        
    } 

    private void panelInfo(){
        this.configureAjuda();

        this.jPanelInfo.setBackground(new java.awt.Color(255, 255, 255));
        this.jPanelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));        
        GroupLayout infoLayout = new GroupLayout(this.jPanelInfo);
        this.jPanelInfo.setLayout(infoLayout);
        
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(this.jScrollPaneInfo1,
                                          GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.DEFAULT_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jScrollPaneSaibaMais,
                                          GroupLayout.PREFERRED_SIZE,
                                         336,
                                         GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(this.jScrollPaneInfo2,
                                          GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.DEFAULT_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jScrollPaneInfo3,
                                          GroupLayout.PREFERRED_SIZE,
                                         GroupLayout.DEFAULT_SIZE,
                                         GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infoLayout.setVerticalGroup(
            infoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.jScrollPaneInfo1,
                                      GroupLayout.PREFERRED_SIZE,
                                     146,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jScrollPaneSaibaMais,
                                      GroupLayout.PREFERRED_SIZE,
                                     136, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.jScrollPaneInfo2,
                                      GroupLayout.PREFERRED_SIZE,
                                     146,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jScrollPaneInfo3,
                                      GroupLayout.PREFERRED_SIZE,
                                     146,
                                     GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    private void panelDetalhes(){
        this.panelBtn();
        this.panelInfo();
        this.StatusTab(CtrlGeral.getListaClassificacoes());
        
        this.jPanelDetalhes.setBackground(new java.awt.Color(255, 255, 255));
        
        GroupLayout detalhesLayout = new GroupLayout(this.jPanelDetalhes);
        this.jPanelDetalhes.setLayout(detalhesLayout);
        detalhesLayout.setHorizontalGroup(
            detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jTabbedPaneColunas, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.jPanelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        detalhesLayout.setVerticalGroup(
            detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detalhesLayout.createSequentialGroup()
                .addGap(0, 0, 1)
                .addComponent(this.jTabbedPaneColunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(0, Short.MAX_VALUE))
            .addGroup(detalhesLayout.createSequentialGroup()
                .addComponent(this.jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.jPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        ); 
    }

    private void panelFundo(){
        this.fundo.add(this.jPanelTable, BorderLayout.NORTH);
        this.fundo.add(this.jPanelDetalhes, BorderLayout.CENTER);        
    }
    
    private void setTableBActionPerformed(ActionEvent evt) {

        System.out.println("Numero de colunas:" + DTO.CtrlGeral.getColuna().size());
        this.list = null;
        this.list = (ArrayList<Employee>) DTO.CtrlGeral.gerarDadosTabela();
        
        this.paginatedDecorator.setNewDataModel(createObjectDataModel(DTO.CtrlGeral.getColuna()),
                                                createDataProvider(this.list),
                                                new int[]{720, 744});

    }
    
    private void salvarBActionPerformed(ActionEvent evt) {
        JFrame janela = new JFrame();
        TableShowDecorator popup = new TableShowDecorator();
        JScrollPane jScrollPanePopup = new JScrollPane();
        
        janela.setSize(550,700);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
        janela.setLayout(new BorderLayout());
        
        jScrollPanePopup.setViewportView(popup.TableShowReady(janela, CtrlGeral.getListaClassificacoes(), 2));
        
        janela.add(jScrollPanePopup);
        janela.repaint();
        janela.show();
    }
    
    public void atualizarPanel() {
        this.jPanelStatus.removeAll();
        if (this.lista != null){
            System.out.println("ouve mudança!");
            this.jTabbedPaneColunas = this.AddColunasTab(lista);
            this.jScrollPaneStatus.setViewportView(this.jTabbedPaneColunas);
            this.jPanelStatus.add(this.jScrollPaneStatus);
                     
        }else{
            System.out.println("ouve nenhuma mudança!");
        }
        
        this.jPanelStatus.revalidate();
        this.jPanelStatus.repaint();
        
    }    
    
    
}
