/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

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
    private JLabel jLabelDataFinal;
    private JLabel jLabelDataInicial;
    private JLabel jLabelPeriodMedicao;
       
    private JButton btAvancar;
    private JButton btVoltar;
    
    private JTextField jTextFieldNome;
    private JTextField jTextFieldCodEstacao;
    private JTextField jTextFieldLatitude;
    private JTextField jTextFieldSituacao;
    private JTextField jTextFieldAltitude;
    private JTextField jTextFieldLongitude;
    private JTextField jTextFieldDataInicial;
    private JTextField jTextFieldDataFinal;
    private JTextField jTextFieldPeriodMedicao;
      
    private JFormattedTextField dataCriacaoFTF;
    private JScrollPane jScrollPaneAjuda;
    private JScrollPane jScrollPaneTabela;
    private JTextArea jTextAreaAjuda;    
    private JTable jTableBanco;
    
    public RecuperarDecorator() {

        this.initComponets();
        this.configureInfoFields();        
        
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
        
        this.jTextFieldNome = new JTextField();
        this.jTextFieldCodEstacao = new JTextField();
        this.jTextFieldLatitude = new JTextField();
        this.jTextFieldSituacao = new JTextField();
        this.jTextFieldAltitude = new JTextField();
        this.jTextFieldLongitude = new JTextField();
        this.jTextFieldDataInicial = new JTextField();
        this.jTextFieldDataFinal = new JTextField();
        this.jTextFieldPeriodMedicao = new JTextField();
        
        this.jLabelDataCriacao = new JLabel();
        this.jLabelArquivo = new JLabel();
        this.jLabelNome = new JLabel();
        this.jLabelCodEstacao = new JLabel();
        this.jLabelLatitude = new JLabel();
        this.jLabelSituacao = new JLabel();
        this.jLabelAltitude = new JLabel();
        this.jLabelLongitude = new JLabel();
        this.jLabelDataFinal = new JLabel();
        this.jLabelDataInicial = new JLabel();
        this.jLabelPeriodMedicao = new JLabel();
        
        this.dataCriacaoFTF = new JFormattedTextField();
        this.jTableBanco = new JTable();
        
        this.btAvancar = new JButton();
        this.btVoltar = new JButton();
        
        this.jScrollPaneTabela.setViewportView(this.jTableBanco);
    }
    
    public JPanel RecuperarReady() {

        this.panelAjuda();
        this.panelInfo();
        
        this.configureFundo();
        
        return this.fundo;    
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
        this.jTextAreaAjuda.setText(" Preencha as informações para diferenciar os dados gravados"
                + "\n no banco de dados.\n"
                + "\n A data de criação será preenchida e alterada automaticamente"
                + "\n toda a vez que a base de dados for alterada ou criada.\n"
                + "\n O campo titulo do quadro \"Info\" é obrigatório para manter sua"
                + "\n própria organização.\n");

        
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
        
        GroupLayout jPanelInfoLayout = new GroupLayout(this.jPanelInfo);
        this.jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(this.jLabelNome)
                        .addGap(18, 18, 18)
                        .addComponent(this.jTextFieldNome))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addComponent(this.jLabelCodEstacao)
                                .addGap(18, 18, 18)
                                .addComponent(this.jTextFieldCodEstacao, 
                                                  GroupLayout.PREFERRED_SIZE, 
                                                 125, 
                                                  GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addComponent(this.jLabelLongitude)
                                .addGap(18, 18, 18)
                                .addComponent(this.jTextFieldLongitude, 
                                                  GroupLayout.PREFERRED_SIZE, 
                                                 125, 
                                                 GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addComponent(this.jLabelLatitude)
                                .addGap(18, 18, 18)
                                .addComponent(this.jTextFieldLatitude, 
                                                  GroupLayout.PREFERRED_SIZE, 
                                                 125, 
                                                 GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(this.jLabelAltitude, GroupLayout.Alignment.TRAILING)
                            .addComponent(this.jLabelSituacao, GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(this.jTextFieldAltitude, 
                                              GroupLayout.DEFAULT_SIZE, 
                                             108, 
                                             Short.MAX_VALUE)
                            .addComponent(this.jTextFieldSituacao)))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addComponent(this.jLabelPeriodMedicao)
                        .addGap(18, 18, 18)
                        .addComponent(this.jTextFieldPeriodMedicao))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanelInfoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanelInfoLayout.createSequentialGroup()
                                .addComponent(this.jLabelDataFinal)
                                .addGap(18, 18, 18)
                                .addComponent(this.jTextFieldDataFinal, 
                                                  GroupLayout.PREFERRED_SIZE, 
                                                 100, 
                                                  GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanelInfoLayout.createSequentialGroup()
                                .addComponent(this.jLabelDataInicial)
                                .addGap(18, 18, 18)
                                .addComponent(this.jTextFieldDataInicial, 
                                                  GroupLayout.PREFERRED_SIZE, 
                                                 100, 
                                                  GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.jTextFieldNome, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jLabelNome))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.jLabelCodEstacao)
                    .addComponent(this.jTextFieldCodEstacao, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jLabelAltitude)
                    .addComponent(this.jTextFieldAltitude, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.jTextFieldLatitude, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jLabelLatitude)
                    .addComponent(this.jTextFieldSituacao, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jLabelSituacao))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(this.jLabelLongitude, GroupLayout.Alignment.TRAILING)
                    .addComponent(this.jTextFieldLongitude, 
                                  GroupLayout.Alignment.TRAILING, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.jLabelPeriodMedicao)
                    .addComponent(this.jTextFieldPeriodMedicao, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.jTextFieldDataInicial, 
                                      GroupLayout.PREFERRED_SIZE, 
                                      GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jLabelDataInicial))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.jLabelDataFinal, 
                                      GroupLayout.PREFERRED_SIZE, 
                                      15, 
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jTextFieldDataFinal, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        this.jLabelDataFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelDataFinal.setText("Data Final:");

        this.jLabelDataInicial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelDataInicial.setText("Data Inicial:");
        
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
        this.jTextFieldDataInicial.setEditable(false);
        this.jTextFieldDataFinal.setEditable(false);
        this.jTextFieldPeriodMedicao.setEditable(false);

    }

    //insere nas textFields do painel info
    private void insertInfoTextFields(String Nome, 
                                      String CodEstacao, 
                                      String Latitude, 
                                      String Situacao,
                                      String Altitude,
                                      String Longitude,
                                      String DataInicial,
                                      String DataFinal,
                                      String PeriodMedicao,
                                      String DataCriacao){
        
        this.jTextFieldNome.setText(Nome);
        this.jTextFieldCodEstacao.setText(CodEstacao);
        this.jTextFieldLatitude.setText(Latitude);
        this.jTextFieldSituacao.setText(Situacao);
        this.jTextFieldAltitude.setText(Altitude);
        this.jTextFieldLongitude.setText(Longitude);
        this.jTextFieldDataInicial.setText(DataInicial);
        this.jTextFieldDataFinal.setText(DataFinal);
        this.jTextFieldPeriodMedicao.setText(PeriodMedicao);
        this.dataCriacaoFTF.setText(DataCriacao);
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

}
