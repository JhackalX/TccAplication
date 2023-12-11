/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import DTO.CtrlInterface;
import Object.AnaliseMensal;
import Object.ListaClassificacao;
import Tabela.Employee;
import static Tabela.Funcionalidades.createDataProvider;
import static Tabela.Funcionalidades.createObjectDataModel;
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
import javax.swing.LayoutStyle;
import javax.swing.table.TableModel;

/**
 *
 * @author jacka
 */
public class VisaoGeralDecorator {
    
    private JPanel fundo;
    private JPanel jPanelBtn;
    private JPanel jPanelTabbedButton;
    private JPanel jPanelTable;
    private JPanel jPanelAjuda;
    private JPanel jPanelDetalhes;
    private JPanel jPanelStatus;
    
    private JScrollPane jScrollPaneAjuda1;
    private JScrollPane jScrollPaneAjuda2;
    private JScrollPane jScrollPaneAjuda3;
    private JScrollPane jScrollPaneDetalhes;
    private JScrollPane jScrollPaneStatus;
    
    private JTextArea jTextAreaAjuda1;
    private JTextArea jTextAreaAjuda2;
    private JTextArea jTextAreaAjuda3;
    
    private JTabbedPane jTabbedPaneColunas;
    
    private JButton btnTabbed;
    private JButton btnAvancar;
    private JButton btnRefresh;
    private JButton btnVoltar;
    
    private List<ListaClassificacao> lista;
    private JTable tabela;

    private CtrlInterface ctrlInterface;
    
    private final String meses[] = {"Janeiro", "Fervereiro", "Março", "Abril", 
                                    "Maio", "Junho", "Julho", "Agosto", 
                                    "Setembro", "Outubro", "Novembro", "Dezembro"};
    private TableModel tabDataModel;
    private ArrayList<Employee> list;
    private PaginatedTableDecorator<Employee> paginatedDecorator;
    private PaginationDataProvider<Employee> dataProvider;
        
    public VisaoGeralDecorator(CtrlInterface ctrlInterface){
        this.ctrlInterface = ctrlInterface;
        this.initComponents();  
    }
    
    public void initComponents() {
        
        this.fundo = new JPanel(new BorderLayout());
        this.jPanelDetalhes = new JPanel(new BorderLayout());        
        this.jPanelAjuda = new JPanel();        
        this.jPanelBtn = new JPanel();        
        this.jPanelTable = new JPanel();
        this.jPanelStatus = new JPanel();
        this.jPanelTabbedButton = new JPanel();
        
        this.jScrollPaneDetalhes = new JScrollPane();
        this.jScrollPaneAjuda1 = new JScrollPane();
        this.jScrollPaneAjuda2 = new JScrollPane();
        this.jScrollPaneAjuda3 = new JScrollPane();
        this.jScrollPaneStatus = new JScrollPane();
        
        this.jTextAreaAjuda1 = new JTextArea();
        this.jTextAreaAjuda2 = new JTextArea();
        this.jTextAreaAjuda3 = new JTextArea();
        
        this.btnTabbed = new JButton();
        this.btnAvancar = new JButton();
        this.btnRefresh = new JButton();
        this.btnVoltar = new JButton();
        
        this.jTabbedPaneColunas = new JTabbedPane();                     
        String[] coluna = {"id" , "Posição", "Temperatura", "Pressão"};
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
                                new int[]{696, 720, 744},
                   720);
        this.tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }
    
    private void initTable(TableModel novoModelo, ArrayList<Employee> lista, int[] pSizes, int defaultPageSize){
        this.tabela = new JTable(novoModelo);
        this.dataProvider = createDataProvider(lista);
        this.paginatedDecorator = PaginatedTableDecorator.decorate(tabela,
                this.dataProvider,pSizes , 30);
    } 
    
    public JPanel visaoGeralReady(){

        this.panelTable();

        this.panelDetalhes();
        
        this.panelTabbedButton();
        
        this.panelFundo();
        
        return this.fundo;
    }
    //obs: tentar tornar a tabela redimensionavel
    //Produz todo painel referente a tabela
    private void panelTable(){

        //introduz o layout a tabela a variavel
        this.tabela.setAutoCreateRowSorter(false);
                
        //configura o painel onde a tabela é inserida
        this.jPanelTable.setBackground(new java.awt.Color(54, 63, 73));
        GroupLayout jPanelTableLayout = new GroupLayout(this.jPanelTable);
        this.jPanelTable.setLayout(jPanelTableLayout);
        
        //organiza o layout com a tabela
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paginatedDecorator.getContentPanel(),
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
                .addComponent(paginatedDecorator.getContentPanel(),
//                .addComponent(this.tabela,
                              GroupLayout.PREFERRED_SIZE,
                              300,
                              GroupLayout.PREFERRED_SIZE)
                .addContainerGap()
                .addGap(1))
        );
    }
    
    private void configureBtns(){
        this.btnTabbed.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        this.btnTabbed.setText("Tabela de valores");
        
        this.btnAvancar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.btnAvancar.setText("Avançar");

        this.btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        this.btnRefresh.setText("Refresh");
        
        this.btnVoltar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.btnVoltar.setText("Voltar"); 
        
        this.btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                salvarBActionPerformedAltered(evt);
                setTableBActionPerformed(evt);
            }
        });        

        this.btnTabbed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBActionPerformed(evt);
                //setTableBActionPerformed(evt);
            }
        });        
    }
    
    private void StatusTab(List<ListaClassificacao> list){
        
       this.lista = list;
       this.atualizarPanel();
    }
    
    private int calcPivorAno(List<AnaliseMensal> lista, int inicio){
        
        int pivor = inicio;
        
        while((pivor + 1 < lista.size()) && 
            (lista.get(pivor).getIntAno() == lista.get(pivor + 1).getIntAno())){
            //System.out.println(lista.get(pivor).impprimir());
            pivor++;
        }
        return pivor;
    }
    
    private JTabbedPane AddMesesTab(List<AnaliseMensal> list){
        
        JTabbedPane mesTabs = new JTabbedPane();
        List<AnaliseMensal> lista = list;
        
        for(int index = 0; index < lista.size(); index++){
//            System.out.println("" + (lista.get(index).getIntMes() - 1));
            mesTabs.addTab(this.meses[(lista.get(index).getIntMes() - 1)],
                        new JScrollPane(this.panelStatus(lista.get(index).getTendencia(),
                                                            lista.get(index).getCoefSperman(),
                                                           lista.get(index).getEstacionariedade())));
        }
        
        return mesTabs;
    }
    
    private JTabbedPane AddAnosTab(List<AnaliseMensal> list){
        
        JTabbedPane anoTabs = new JTabbedPane();
        List<AnaliseMensal> lista = list;
        int pivor = 0;
   
        for(int index = 0; index < lista.size();){
            pivor = this.calcPivorAno(lista, index);
            anoTabs.addTab( lista.get(index).getAno(), 
                         this.AddMesesTab(lista.subList(index, pivor + 1)));
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
    
    
    private JPanel panelStatus(String tendencia, 
                             String coeficiente, 
                             String estacionaria){
        
        JPanel jPanelStatus = new JPanel();
        
        JLabel jLabelTed = new JLabel();
        JLabel jLabelCoef = new JLabel();
        JLabel jLabelEstac = new JLabel();
        
        JTextField jTextFieldTed = new JTextField();
        JTextField jTextFieldCoef = new JTextField();
        JTextField jTextFieldEstac = new JTextField();        
        
        jLabelTed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelTed.setText("Tendência:");
        
        jLabelCoef.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCoef.setText("Coef. de Spearman:");

        jLabelEstac.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEstac.setText("Presença de estacionariedade:");

        jTextFieldTed.setEditable(false);
        jTextFieldTed.setText(tendencia);
        jTextFieldCoef.setEditable(false);
        jTextFieldCoef.setText(coeficiente);
        jTextFieldEstac.setEditable(false);
        jTextFieldEstac.setText(estacionaria);
                
        jPanelStatus.setBorder(BorderFactory.createTitledBorder(
                                                  null,
                                                   "Status:",
                                          javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                              javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                                        new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanelStatus.setForeground(new java.awt.Color(204, 204, 204));
        //jPanelStatus.setBackground(Color.red);
        GroupLayout statusLayout = new GroupLayout(jPanelStatus );
        jPanelStatus.setLayout(statusLayout);
        statusLayout.setHorizontalGroup(
            statusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelEstac)
                    .addComponent(jLabelTed)
                    .addComponent(jLabelCoef))
                .addGap(18, 18, 18)
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.LEADING,
                                                                false)
                    .addComponent(jTextFieldEstac)
                    .addComponent(jTextFieldCoef, GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldTed,
                                  GroupLayout.Alignment.TRAILING,
                                      GroupLayout.DEFAULT_SIZE,
                                     220,
                                     Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                 Short.MAX_VALUE))
        );
        statusLayout.setVerticalGroup(
            statusLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTed)
                    .addComponent(jTextFieldTed, 
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCoef,
                                      GroupLayout.PREFERRED_SIZE,
                                      15,
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCoef,
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEstac)
                    .addComponent(jTextFieldEstac,
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        return jPanelStatus;
    }
    
    private void configureAjuda(){
        this.jTextAreaAjuda1.setEditable(false);
        this.jTextAreaAjuda1.setColumns(20);
        this.jTextAreaAjuda1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaAjuda1.setRows(5);
        this.jTextAreaAjuda1.setText(" p = 1 - (6 x ∑ (d²) ) / (n (n² - 1) )\n "
                                    + "p = coef. de Spearman\n "
                                    + "d = diferença entre dois pontos\n "
                                    + "de cada observação.\n "
                                    + "n = número da quantidade de \n "
                                    + "dados.\n p = 1 =>Correlação perfeita\n "
                                    + "entre variáveis.\n "
                                    + "p = 0 =>Indepêndencia entre\n variáveis.\n "
                                    + "p = -1 =>Correlação negativa\n perfeita entre variáveis.");
        this.jTextAreaAjuda1.setBorder(BorderFactory.createTitledBorder(
                                                            null,
                                                            "Coef. de Spearman:",
                                                   javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                      javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                                                new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        this.jTextAreaAjuda2.setEditable(false);
        this.jTextAreaAjuda2.setColumns(20);
        this.jTextAreaAjuda2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaAjuda2.setRows(5);
        this.jTextAreaAjuda2.setText(" T = ∑ (d²)\n d = (R - P) : diferença entre dois \n "
                                   + "pontos de cada observação.\n\n "
                                   + "(OBS: neste caso foi adotado um \n "
                                   + "sistema de Ranking de valores do \n "
                                   + "menor para o maior e Periodos \n "
                                   + "espaçados igualmente entre os \n "
                                   + "valores)\n R = ranking\n P = periodo");
        this.jTextAreaAjuda2.setBorder(BorderFactory.createTitledBorder(
                                                            null, 
                                                            "Tendência:", 
                                                   javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                      javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                                new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        this.jTextAreaAjuda3.setEditable(false);
        this.jTextAreaAjuda3.setColumns(20);
        this.jTextAreaAjuda3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaAjuda3.setRows(5);
        this.jTextAreaAjuda3.setText("\n Não é recomendável o uso do \n "
                                   + "programa caso o coeficiente de\n "
                                   + "Spearman gere valor maior ou\n "
                                   + "igual a 0 (zero), pois as formulas\n "
                                   + "são precisamente projetadas para \n "
                                   + "séries temporais estacionárias, \n "
                                   + "os valores da tabela \"status\" \n "
                                   + "são valores para a validação dos \n "
                                   + "dados para que os valores \n "
                                   + "preditos tenham confiabilidade e\n precisão.");
        this.jTextAreaAjuda3.setBorder(BorderFactory.createTitledBorder(
                                                            null, 
                                                            "Entenda:", 
                                                   javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                      javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                                                new java.awt.Font("Tahoma", 1, 12))); // NOI18N
               
        this.jScrollPaneAjuda1.setViewportView(this.jTextAreaAjuda1);
        this.jScrollPaneAjuda2.setViewportView(this.jTextAreaAjuda2);
        this.jScrollPaneAjuda3.setViewportView(this.jTextAreaAjuda3);
    }

    private void panelAjuda(){
        
        this.configureAjuda();
        
        this.jPanelAjuda.setBackground(new java.awt.Color(255, 255, 255));
        this.jPanelAjuda.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        GroupLayout AjudaLayout = new GroupLayout(this.jPanelAjuda);
        this.jPanelAjuda.setLayout(AjudaLayout);
        AjudaLayout.setHorizontalGroup(
            AjudaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(AjudaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneAjuda1, 
                                  GroupLayout.PREFERRED_SIZE, 
                                 GroupLayout.DEFAULT_SIZE, 
                                 GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.jScrollPaneAjuda2, 
                                  GroupLayout.PREFERRED_SIZE, 
                                 GroupLayout.DEFAULT_SIZE, 
                                 GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.jScrollPaneAjuda3, 
                                  GroupLayout.PREFERRED_SIZE, 
                                 GroupLayout.DEFAULT_SIZE, 
                                 GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AjudaLayout.setVerticalGroup(
            AjudaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(AjudaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AjudaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.jScrollPaneAjuda3, 
                                      GroupLayout.PREFERRED_SIZE,
                                     200,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jScrollPaneAjuda1,
                                      GroupLayout.PREFERRED_SIZE,
                                     200,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jScrollPaneAjuda2,
                                      GroupLayout.PREFERRED_SIZE,
                                     200,
                                     GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );    
    }
    
    private void panelBtn(){
        
        this.configureBtns();
        
        this.jPanelBtn.setBackground(new java.awt.Color(255, 255, 255));
        this.jPanelBtn.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        
        GroupLayout btnLayout = new GroupLayout(this.jPanelBtn);
        this.jPanelBtn.setLayout(btnLayout);
        btnLayout.setHorizontalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btnLayout.createSequentialGroup()
                        .addGroup(btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(this.btnAvancar)
                            .addComponent(this.btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        btnLayout.setVerticalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.btnAvancar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.btnVoltar)
                .addContainerGap())
        );
        
    }
    
    private void panelTabbedButton(){
        jPanelTabbedButton.setBorder(javax.swing.BorderFactory.createTitledBorder("Painel de Status:"));
        
        this.jPanelTabbedButton.setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout jPanelTabbedButtonLayout = new javax.swing.GroupLayout(jPanelTabbedButton);
        jPanelTabbedButton.setLayout(jPanelTabbedButtonLayout);
        
        jPanelTabbedButtonLayout.setHorizontalGroup(
            jPanelTabbedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTabbedButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTabbed, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTabbedButtonLayout.setVerticalGroup(
            jPanelTabbedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTabbedButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTabbed, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addContainerGap())
        );        
    }
    
    private void panelDetalhes(){
        
        this.panelBtn();
        this.panelAjuda();
        this.StatusTab(ctrlInterface.getListaClassificacoes());

        this.jPanelDetalhes.setBackground(new java.awt.Color(255, 255, 255));
        GroupLayout jPanelDetalhesLayout = new GroupLayout(this.jPanelDetalhes);
        this.jPanelDetalhes.setLayout(jPanelDetalhesLayout);

        jPanelDetalhesLayout.setHorizontalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTabbedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanelAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelDetalhesLayout.setVerticalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTabbedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );  
        
//        this.jPanelDetalhes.setBackground(new java.awt.Color(255, 255, 255));
//        GroupLayout detalhesLayout = new GroupLayout(this.jPanelDetalhes);
//        this.jPanelDetalhes.setLayout(detalhesLayout);
// 
//        detalhesLayout.setHorizontalGroup(
//            detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(detalhesLayout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(this.jTabbedPaneColunas, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addGroup(detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(this.jPanelAjuda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(this.jPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addContainerGap())
//        );
//        detalhesLayout.setVerticalGroup(
//            detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(detalhesLayout.createSequentialGroup()
//                .addGap(0, 0, 1)
//                .addComponent(this.jTabbedPaneColunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(0, Short.MAX_VALUE))
//            .addGroup(detalhesLayout.createSequentialGroup()
//                .addComponent(this.jPanelAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addComponent(this.jPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap())
//        );

        this.jScrollPaneDetalhes.setViewportView(this.jPanelDetalhes);

    }

//    private void panelDetalhes(){
//        
//        this.panelBtn();
//        this.StatusTab(this.ctrlGeral.getListaClassificacoes());
//        
//        this.jPanelDetalhes.setBackground(new java.awt.Color(255, 255, 255));
//        GroupLayout detalhesLayout = new GroupLayout(this.jPanelDetalhes);
//        this.jPanelDetalhes.setLayout(detalhesLayout);
// 
//        detalhesLayout.setHorizontalGroup(
//            detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(detalhesLayout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(this.jPanelAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addGroup(detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(this.jPanelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addContainerGap())
//        );
//        detalhesLayout.setVerticalGroup(
//            detalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(detalhesLayout.createSequentialGroup()
//                .addGap(0, 0, 1)
//                .addComponent(this.jPanelAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(0, Short.MAX_VALUE))
//            .addGroup(detalhesLayout.createSequentialGroup()
//                .addComponent(this.jPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap())
//        );
//
//        this.jScrollPaneDetalhes.setViewportView(this.jPanelDetalhes);
//
//    }
    
    //Configura o painel de fundo da aba
    private void panelFundo(){
       
        this.fundo.add(this.jPanelTable, BorderLayout.NORTH);
        this.fundo.add(this.jPanelDetalhes, BorderLayout.CENTER);
     
    }
    
    private void setTableBActionPerformed(ActionEvent evt) {
        

        System.out.println("Numero de colunas:" + this.ctrlInterface.getColuna().size());
        this.list = null;
        this.list = (ArrayList<Employee>) this.ctrlInterface.gerarDadosTabelaOriginal();
        
        this.paginatedDecorator.setNewDataModel(createObjectDataModel(this.ctrlInterface.getColuna()),
                                                createDataProvider(this.list),
                                                new int[]{720, 744});
        this.paginatedDecorator.adjustColumnWidths();
    }
    
    private void salvarBActionPerformed(ActionEvent evt) {
        JFrame janela = new JFrame();
        TableShowDecorator popup = new TableShowDecorator();
        JScrollPane jScrollPanePopup = new JScrollPane();
        
        janela.setSize(550,700);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
        janela.setLayout(new BorderLayout());
        
        jScrollPanePopup.setViewportView(popup.TableShowReady(janela, this.ctrlInterface.getListaClassificacoes(), 1));
        
        janela.add(jScrollPanePopup);
        janela.repaint();
        janela.show();
    }

    private void salvarBActionPerformedAltered(ActionEvent evt) {
        this.panelTable();
        JFrame janela = new JFrame();
        TableShowDecorator popup = new TableShowDecorator();
        JScrollPane jScrollPanePopup = new JScrollPane();
        
        janela.setSize(550,700);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
        janela.setLayout(new BorderLayout());
        
        jScrollPanePopup.setViewportView(this.jPanelTable);
        
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

