/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import DTO.CtrlInterface;
import Object.Info;
import TableBD.ButtonEditor;
import TableBD.ButtonRenderer;
import TableBD.ObjectTableModelBD;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.TableModel;

/**
 *
 * @author jacka
 */
public class RecuperarDecorator {

    private JPanel fundo;
    private JPanel jPanelSuperio;
    private JPanel jPanelInferior;
    private JPanel jPanelBtn;
    private JPanel jPanelAjuda;
    private JPanel jPanelInfo;
    
    private JLabel jLabelDataCriacao;
    private JLabel jLabelArquivo;
    private JLabel jLabelNome;
    private JLabel jLabelCodEstacao;
    private JLabel jLabelLatitude;
    private JLabel jLabelSituacao;
    private JLabel jLabelAltitude;
    private JLabel jLabelLongitude;
    private JLabel jLabelAnoEst;
//    private JLabel jLabelDataFinal;
//    private JLabel jLabelDataInicial;
    private JLabel jLabelPeriodMedicao;
       
    private JButton btAvancar;
    private JButton btVoltar;
    
    private JTextField jTextFieldNome;
    private JTextField jTextFieldCodEstacao;
    private JTextField jTextFieldLatitude;
    private JTextField jTextFieldSituacao;
    private JTextField jTextFieldAltitude;
    private JTextField jTextFieldLongitude;
//    private JTextField jTextFieldDataInicial;
//    private JTextField jTextFieldDataFinal;
    private JTextField jTextFieldPeriodMedicao;
    
    private JComboBox jComboBoxAnoEst;
    private JFormattedTextField dataCriacaoFTF;
    private JScrollPane jScrollPaneAjuda;
    private JScrollPane jScrollPaneTabela;
    private JTextArea jTextAreaAjuda;    
    private JTable jTableBanco;
    private PopupSelectDecorator popup;
    private CtrlInterface ctrlInterface;
    private ObjectTableModelBD model;
    //para teste
    private ArrayList<Info> listInfos;
    
    private Info infoSelecionada;
    
    public RecuperarDecorator(CtrlInterface ctrlInterface) {
        this.ctrlInterface = ctrlInterface;
        this.initComponets();
        this.configureInfoFields();
        configureTable();
    }
    
    public void initComponets(){
        
        this.fundo = new JPanel();
        this.jPanelSuperio = new JPanel();
        this.jPanelInferior = new JPanel();
        this.jPanelBtn = new JPanel();
        this.jPanelAjuda = new JPanel();
        this.jPanelInfo = new JPanel();
        this.jScrollPaneAjuda = new JScrollPane();
        this.jScrollPaneTabela = new JScrollPane();
        this.jTextAreaAjuda = new JTextArea();
        this.jComboBoxAnoEst = new JComboBox();
        
        this.jTextFieldNome = new JTextField();
        this.jTextFieldCodEstacao = new JTextField();
        this.jTextFieldLatitude = new JTextField();
        this.jTextFieldSituacao = new JTextField();
        this.jTextFieldAltitude = new JTextField();
        this.jTextFieldLongitude = new JTextField();
//        this.jTextFieldDataInicial = new JTextField();
//        this.jTextFieldDataFinal = new JTextField();
        this.jTextFieldPeriodMedicao = new JTextField();
        
        this.jLabelDataCriacao = new JLabel();
        this.jLabelArquivo = new JLabel();
        this.jLabelNome = new JLabel();
        this.jLabelCodEstacao = new JLabel();
        this.jLabelLatitude = new JLabel();
        this.jLabelSituacao = new JLabel();
        this.jLabelAltitude = new JLabel();
        this.jLabelLongitude = new JLabel();
//        this.jLabelDataFinal = new JLabel();
//        this.jLabelDataInicial = new JLabel();
        this.jLabelPeriodMedicao = new JLabel();
        this.jLabelAnoEst = new JLabel();
        
        this.listInfos = null;
        this.dataCriacaoFTF = new JFormattedTextField();
//        this.jTableBanco = new JTable();
        this.popup = new PopupSelectDecorator(this.ctrlInterface);
        
        this.btAvancar = new JButton();
        this.btVoltar = new JButton();
        
        btAvancar.setEnabled(false);
    }
    
    public JPanel RecuperarReady(){

        this.panelAjuda();
        this.panelInfo();
        
        this.configureFundo();

        return this.fundo;    
    }
    
    
    public void populateInfo() throws ParseException{
        this.listInfos =  (ArrayList<Info>) this.ctrlInterface.listarEstacoes();
//        this.listInfos = new ArrayList<Info>();
//        listInfos.add(new Info("estacao1", "0001", "0.1", "0.1", "0.1", "doido", "1998-10-01", "1998-10-02", "hora"));
//        listInfos.add(new Info("estacao2", "0001", "0.1", "0.1", "0.1", "doido", "1999-10-01", "1999-10-02", "hora"));
//        listInfos.add(new Info("estacao3", "0001", "0.1", "0.1", "0.1", "doido", "2000-10-01", "2000-10-02", "hora"));
//        listInfos.get(0).setListaAnos(new ArrayList<String>(Arrays.asList("2017", "2018", "2019")));
//        listInfos.get(1).setListaAnos(new ArrayList<String>(Arrays.asList("2017", "2018", "2019", "2020")));
//        listInfos.get(2).setListaAnos(new ArrayList<String>(Arrays.asList("2017", "2018")));
    }
    
    private void ConfigureButtonTable(int column){
        this.jTableBanco.getColumnModel().getColumn(column).setCellRenderer(new ButtonRenderer());
        this.jTableBanco.getColumnModel().getColumn(column).setCellEditor(new ButtonEditor(new JCheckBox(), this.model, this.jTableBanco));
    }
    
    private void configureTable(){
        try {
            this.populateInfo();
//            System.out.println(this.listInfos.get(0).toString());
        } catch (ParseException ex) {
            Logger.getLogger(RecuperarDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.model = new ObjectTableModelBD(listInfos);
        this.jTableBanco = new JTable(model);
        
        this.jTableBanco.setSurrendersFocusOnKeystroke(true);
        this.jTableBanco.setRowSelectionAllowed(true);
        this.jTableBanco.setColumnSelectionAllowed(false);
        
        this.ConfigureButtonTable(3);
        this.ConfigureButtonTable(4);
        
        if(!(this.listInfos == null || this.listInfos.isEmpty())){            
            this.jTableBanco.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int row = jTableBanco.rowAtPoint(e.getPoint());
                    int column = jTableBanco.columnAtPoint(e.getPoint());

                    if(row >= 0 && column >= 0){
                        if(column == 3){
//                            System.out.println("botão selecionar clicado: " + row);//para teste
                            
                            insertInfoTextFields(listInfos.get(row).getEstacao().getNome(),
                                             listInfos.get(row).getEstacao().getCodigo(),
                                              listInfos.get(row).getEstacao().getLatitude().toString(),
                                              listInfos.get(row).getEstacao().getSituacao(),
                                              listInfos.get(row).getEstacao().getAltitude().toString(), 
                                             listInfos.get(row).getEstacao().getLongitude().toString(), 
                                          listInfos.get(row).getPeriodicidade(),
                                           listInfos.get(row).getDataCriacaoBR(),
                                                listInfos.get(row).getListaAnos());
                            
                            atualizarComboBoxAnos(ctrlInterface.listarAnosDadosMedidosEstacoes(listInfos.get(row).getEstacao().getCodigo()));
                            
                            infoSelecionada = listInfos.get(row);
                            
                            btAvancar.setEnabled(true);
                            
                        }else if(column == 4){
                            int confirm = JOptionPane.showConfirmDialog(jTableBanco, "Tem certeza que deseja excluir?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if(confirm == JOptionPane.YES_NO_OPTION){
                                model.removeInfo(row);
                                jTableBanco.repaint();
                            }
                        }
                    }
                }
            });
        }
        this.jScrollPaneTabela.setViewportView(this.jTableBanco);
    }
    
    //Iniciliza e escreve o painel ajuda da janela
    private void panelAjuda(){
        
        this.jPanelAjuda.setBackground(new Color(255, 255, 255));
        this.jPanelAjuda.setBorder(BorderFactory.createTitledBorder(
                null, 
                "Ajuda:",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new Font("Tahoma", 1, 12)
        ));
        
        this.jPanelAjuda.setForeground(new Color(255, 255, 255));        
        GroupLayout jPanelAjudaLayout = new GroupLayout(this.jPanelAjuda);
        this.jPanelAjuda.setLayout(jPanelAjudaLayout);
        this.jScrollPaneAjuda.setViewportView(this.jTextAreaAjuda);
        
        this.jTextAreaAjuda.setEditable(false);
        this.jTextAreaAjuda.setColumns(20);
        this.jTextAreaAjuda.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        this.jTextAreaAjuda.setRows(5);
        this.jTextAreaAjuda.setText(" Escolha os dados que queira trabalhar na tabela abaixo"
                + "\n vinda do banco de dados.\n"
                + "\n A data de criação será preenchida e alterada automaticamente"
                + "\n toda vez que um dados for selecionado, o painel info atualizará.\n"
                + "\n O campo ano do quadro \"Info\" é obrigatório para começar o"
                + "\n estudo.\n");

        
        jPanelAjudaLayout.setHorizontalGroup(
            jPanelAjudaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjudaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneAjuda, 
                                  GroupLayout.DEFAULT_SIZE, 
                                 349, 
                                 Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelAjudaLayout.setVerticalGroup(
            jPanelAjudaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAjudaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneAjuda, 
                                  GroupLayout.PREFERRED_SIZE, 
                                  161, 
                                  GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, 
                                  Short.MAX_VALUE))
        );
    }
    
    private void atualizarListaAnos(){
        String codigo;
        
       // codigo = jTableBanco.get
    }
    
    private void atualizarComboBoxAnos(List<String> listAnos) {
        jComboBoxAnoEst.removeAllItems();
        if (listAnos != null) {
            for (String ano : listAnos) {
                this.jComboBoxAnoEst.addItem(ano);
            }
        }
    }
    
    //Iniciliza e escreve o painel info da janela
    private void panelInfo(){
        
        this.setInfoLabels();
        
        this.jPanelInfo.setBackground(new Color(255, 255, 255));
        this.jPanelInfo.setBorder(
                  BorderFactory.createTitledBorder(null, 
                                                         "Info", 
                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                                                    javax.swing.border.TitledBorder.DEFAULT_POSITION, new 
                                                              java.awt.Font("Tahoma", 1, 11))
        );
        
        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabelNome)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNome))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addComponent(jLabelCodEstacao)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldCodEstacao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addComponent(jLabelLongitude)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addComponent(jLabelLatitude)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAltitude, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelSituacao, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAltitude, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jTextFieldSituacao)))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPeriodMedicao)
                            .addComponent(jLabelAnoEst))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAnoEst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPeriodMedicao))))
                .addContainerGap())
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodEstacao)
                    .addComponent(jTextFieldCodEstacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAltitude)
                    .addComponent(jTextFieldAltitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLatitude)
                    .addComponent(jTextFieldSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSituacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLongitude, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPeriodMedicao)
                    .addComponent(jTextFieldPeriodMedicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAnoEst)
                    .addComponent(jComboBoxAnoEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
    }
    
    //inicializa as labels do painel info
    private void setInfoLabels(){
        
        this.jLabelNome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelNome.setText("Nome:");
        
        this.jLabelCodEstacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelCodEstacao.setText("Codigo Estação:");
        
        this.jLabelLatitude.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelLatitude.setText("Latitude:");
        
        this.jLabelSituacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelSituacao.setText("Situação:");

        this.jLabelAltitude.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelAltitude.setText("Altitude:");
        
        this.jLabelLongitude.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelLongitude.setText("Longitude:");

        this.jLabelAnoEst.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelAnoEst.setText("Selecione o ano de estudo:");

//        this.jLabelDataFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
//        this.jLabelDataFinal.setText("Data Final:");
//
//        this.jLabelDataInicial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
//        this.jLabelDataInicial.setText("Data Inicial:");
        
        this.jLabelPeriodMedicao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelPeriodMedicao.setText("Periodicidade da Medição:");
    }
    
    private void configureFundoLabels(){
        this.jLabelArquivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelArquivo.setText("Arquivo:");

        this.jLabelDataCriacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelDataCriacao.setText("Data de criação:");        
    }
    
    private void configureBtns(){
        
        this.jPanelBtn.setBackground(new java.awt.Color(255, 255, 255));

        this.btAvancar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.btAvancar.setText("Avançar");
        this.btAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvancarActionPerformed(evt);
            }
        });
                
        this.btVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.btVoltar.setText("Voltar");         

        javax.swing.GroupLayout BtnLayout = new javax.swing.GroupLayout(this.jPanelBtn);
        this.jPanelBtn.setLayout(BtnLayout);
        BtnLayout.setHorizontalGroup(
            BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.btVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(this.btAvancar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        BtnLayout.setVerticalGroup(
            BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(this.btAvancar)
                    .addComponent(this.btVoltar))
                .addContainerGap())
        );
    }
    
    private void ConfigureFundoCamp(){
        
        this.dataCriacaoFTF.setEditable(false);
        this.dataCriacaoFTF.setFormatterFactory(
                new javax.swing.text.DefaultFormatterFactory(
                new javax.swing.text.DateFormatter(
              java.text.DateFormat.getDateInstance(
               java.text.DateFormat.SHORT))));
       
    }

    //configura as textFields do painel info
    private void configureInfoFields(){
        
        this.jTextFieldNome.setEditable(false);
        this.jTextFieldCodEstacao.setEditable(false);
        this.jTextFieldLatitude.setEditable(false);
        this.jTextFieldSituacao.setEditable(false);
        this.jTextFieldAltitude.setEditable(false);
        this.jTextFieldLongitude.setEditable(false);
//        this.jTextFieldDataInicial.setEditable(false);
//        this.jTextFieldDataFinal.setEditable(false);
        this.jTextFieldPeriodMedicao.setEditable(false);

    }

    //insere nas textFields do painel info
    private void insertInfoTextFields(String Nome, 
                                      String CodEstacao, 
                                      String Latitude, 
                                      String Situacao,
                                      String Altitude,
                                      String Longitude,
                                      String PeriodMedicao,
                                      String DataCriacao,
                                      List<String> anos){
        
        this.jTextFieldNome.setText(Nome);
        this.jTextFieldCodEstacao.setText(CodEstacao);
        this.jTextFieldLatitude.setText(Latitude);
        this.jTextFieldSituacao.setText(Situacao);
        this.jTextFieldAltitude.setText(Altitude);
        this.jTextFieldLongitude.setText(Longitude);
//        this.jTextFieldDataInicial.setText(DataInicial);
//        this.jTextFieldDataFinal.setText(DataFinal);
        this.jTextFieldPeriodMedicao.setText(PeriodMedicao);
        this.dataCriacaoFTF.setText(DataCriacao);
        this.atualizarComboBoxAnos(anos);
    }
    
    //Configura e inicializa o painel de fundo geral
    private void configurePanelSuperior(){
        
        this.configureFundoLabels();
        this.ConfigureFundoCamp();
        
        this.jPanelSuperio.setBackground(new Color(255, 255, 255));
        this.jPanelSuperio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Escolha um arquivo do Banco de Dados na Tabela abaixo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        GroupLayout PanelSuperioLayout = new GroupLayout(this.jPanelSuperio);       
        this.jPanelSuperio.setLayout(PanelSuperioLayout);

        PanelSuperioLayout.setHorizontalGroup(
            PanelSuperioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
            .addGroup(PanelSuperioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSuperioLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(this.jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(PanelSuperioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelSuperioLayout.createSequentialGroup()
                            .addComponent(this.jLabelDataCriacao)
                            .addGap(18, 18, 18)
                            .addComponent(this.dataCriacaoFTF, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(this.jPanelAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PanelSuperioLayout.setVerticalGroup(
            PanelSuperioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
            .addGroup(PanelSuperioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSuperioLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(PanelSuperioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(this.jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelSuperioLayout.createSequentialGroup()
                            .addGroup(PanelSuperioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(this.jLabelDataCriacao)
                                .addComponent(this.dataCriacaoFTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(this.jPanelAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );    

    }

    private void configurePanelInferior(){
        
        this.jPanelInferior.setBackground(new java.awt.Color(54, 63, 73));
        this.jPanelInferior.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                                                                                         "Arquivos em Banco:",
                                                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                                                   javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                                                             new java.awt.Font("Segoe UI", 1, 18),
                                                                                             new java.awt.Color(255, 255, 255))); // NOI18N
                                                                                              
        GroupLayout PanelInferiorLayout = new GroupLayout(this.jPanelInferior);       
        this.jPanelInferior.setLayout(PanelInferiorLayout);

        PanelInferiorLayout.setHorizontalGroup(
            PanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneTabela)
                .addContainerGap())
        );
        PanelInferiorLayout.setVerticalGroup(
            PanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );    

    }
    
    private void configureFundo(){
        
        this.configurePanelSuperior();
        this.configurePanelInferior();
        this.configureBtns();
        
        this.fundo.setBackground(new Color(255, 255, 255));

        GroupLayout fundoLayout = new GroupLayout(this.fundo);       
        this.fundo.setLayout(fundoLayout);
       
        fundoLayout.setHorizontalGroup(
            fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addComponent(this.jPanelSuperio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(this.jPanelInferior, javax.swing.GroupLayout.Alignment.TRAILING,javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fundoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(this.jPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        fundoLayout.setVerticalGroup(
            fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jPanelSuperio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(this.jPanelInferior, javax.swing.GroupLayout.PREFERRED_SIZE,275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.jPanelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

    }
    
    public void btnAvancarActionPerformed(java.awt.event.ActionEvent evt){
        System.out.println("Ano Selecionado: " + this.jComboBoxAnoEst.getSelectedItem().toString());
        System.out.println("Estacao Selecionada: " + this.infoSelecionada.getEstacao().getNome());
        this.ctrlInterface.setMedicao(infoSelecionada.getEstacao().getCodigo(), jComboBoxAnoEst.getSelectedItem().toString());
        this.ctrlInterface.atualizarTabelaVisaoGeral();
//       JFrame janela = new JFrame();                
//       popup.PopupSelectReady(1, janela);
//       janela.setVisible(true);
//       janela.repaint();
//       janela.pack();
//       janela.show();
    }

}
