/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import static DTO.Funcionalidades.lerArquivo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jacka
 */
public class ImportarDecorator {
    
    private JPanel fundo;
    private JPanel jPanelBtn;
    private JPanel jPanelAjuda;
    private JPanel jPanelInfo;
    
    private JLabel jLabelDataCriacao;
    private JLabel jLabelArquivo;
    private JLabel jLabelExplicacao;
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
    private JButton btImportar;
    private JButton btAbrir;
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
      
    private JTextField camField;
    private JFormattedTextField dataCriacaoFTF;
    private JScrollPane jScrollPaneAjuda;
    private JTextArea jTextAreaAjuda;
    
    public ImportarDecorator() {
               
        this.initComponets();
        this.configureInfoTextFields();
        
    }
    
    public void initComponets(){
        
        this.fundo = new JPanel();
        this.jPanelBtn = new JPanel();
        this.jPanelAjuda = new JPanel();
        this.jPanelInfo = new JPanel();
        this.jScrollPaneAjuda = new JScrollPane();
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
        this.jLabelExplicacao = new JLabel();
        this.jLabelNome = new JLabel();
        this.jLabelCodEstacao = new JLabel();
        this.jLabelLatitude = new JLabel();
        this.jLabelSituacao = new JLabel();
        this.jLabelAltitude = new JLabel();
        this.jLabelLongitude = new JLabel();
        this.jLabelDataFinal = new JLabel();
        this.jLabelDataInicial = new JLabel();
        this.jLabelPeriodMedicao = new JLabel();
        
        this.camField = new JTextField();
        this.dataCriacaoFTF = new JFormattedTextField();
        
        this.btAvancar = new JButton();
        this.btVoltar = new JButton();
        this.btImportar = new JButton();
        this.btAbrir = new JButton();
    }
    
    public JPanel ImportarReady(){
        
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
        
        this.jLabelExplicacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelExplicacao.setText("Entre com o arquivo CSV para que o programa submeta os dados a analise.");
        this.jLabelExplicacao.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        this.jLabelDataCriacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelDataCriacao.setText("Data de criação:");        
    }
    
    private void configureBtns(){
        this.btAbrir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.btAbrir.setText("Abrir");
        this.btAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirActionPerformed(evt);
            }
        });

        this.btImportar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.btImportar.setText("Importar");
        this.btImportar.setToolTipText("");
        this.btImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btImportarActionPerformed(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(ImportarDecorator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ImportarDecorator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.btAvancar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.btAvancar.setText("Avançar");

        this.btVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.btVoltar.setText("Voltar");
    }
    
    private void ConfigureFundoCamp(){
        
        this.dataCriacaoFTF.setEditable(false);
        this.dataCriacaoFTF.setFormatterFactory(
                new javax.swing.text.DefaultFormatterFactory(
                new javax.swing.text.DateFormatter(
              java.text.DateFormat.getDateInstance(
               java.text.DateFormat.SHORT))));
       
    }
    
    private void ConfigurePanelBtn(){
        
        this.configureBtns();
        
        this.jPanelBtn.setBackground(new java.awt.Color(255, 255, 255));        
        javax.swing.GroupLayout PanelBtnLayout = new javax.swing.GroupLayout(this.jPanelBtn);
        this.jPanelBtn.setLayout(PanelBtnLayout);
        
        PanelBtnLayout.setHorizontalGroup(
            PanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.btVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, GroupLayout.PREFERRED_SIZE)
                .addComponent(this.btAvancar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelBtnLayout.setVerticalGroup(
            PanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(this.btAvancar)
                    .addComponent(this.btVoltar))
                .addContainerGap())
        );
       
    }

    //configura as textFields do painel info
    private void configureInfoTextFields(){
        
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
    private void configureFundo(){
        
        this.configureFundoLabels();
        this.ConfigureFundoCamp();
        this.ConfigurePanelBtn();
        
        this.fundo.setBackground(new Color(255, 255, 255));

        GroupLayout fundoLayout = new GroupLayout(this.fundo);       
        this.fundo.setLayout(fundoLayout);
       
        fundoLayout.setHorizontalGroup(
            fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, 
                                 fundoLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 
                                         GroupLayout.DEFAULT_SIZE, 
                                          Short.MAX_VALUE)
                        .addComponent(this.jPanelBtn))
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addComponent(this.jLabelArquivo)
                        .addGap(18, 18, 18)
                        .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(fundoLayout.createSequentialGroup()
                                .addComponent(this.camField)
                                .addGap(18, 18, 18)
                                .addComponent(this.btImportar))
                            .addGroup(fundoLayout.createSequentialGroup()
                                .addComponent(this.btAbrir, 
                                                  GroupLayout.PREFERRED_SIZE, 
                                                 69, 
                                                  GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(this.jLabelExplicacao)
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addComponent(this.jPanelInfo, 
                                          GroupLayout.PREFERRED_SIZE, 
                                         GroupLayout.DEFAULT_SIZE, 
                                          GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(fundoLayout.createSequentialGroup()
                                .addComponent(this.jLabelDataCriacao)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(this.dataCriacaoFTF, 
                                                  GroupLayout.PREFERRED_SIZE, 
                                                 98, 
                                                  GroupLayout.PREFERRED_SIZE))
                            .addComponent(this.jPanelAjuda, 
                                              GroupLayout.PREFERRED_SIZE, 
                                             GroupLayout.DEFAULT_SIZE, 
                                              GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );        
        fundoLayout.setVerticalGroup(
            fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jLabelExplicacao)
                .addGap(42, 42, 42)
                .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.camField, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jLabelArquivo)
                    .addComponent(this.btImportar))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.btAbrir, 
                                  GroupLayout.PREFERRED_SIZE, 
                                 30, 
                                  GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING, 
                                                               false)
                    .addGroup(fundoLayout.createSequentialGroup()
                        .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.jLabelDataCriacao)
                            .addComponent(this.dataCriacaoFTF, 
                                              GroupLayout.PREFERRED_SIZE, 
                                             GroupLayout.DEFAULT_SIZE, 
                                              GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(this.jPanelAjuda, 
                                          GroupLayout.DEFAULT_SIZE, 
                                         GroupLayout.DEFAULT_SIZE, 
                                          Short.MAX_VALUE))
                    .addComponent(this.jPanelInfo, 
                                      GroupLayout.PREFERRED_SIZE, 
                                     GroupLayout.DEFAULT_SIZE, 
                                      GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 
                                 245, 
                                 Short.MAX_VALUE)
                .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.jPanelBtn))
                .addContainerGap())
        );
    }
    
    private void btAbrirActionPerformed(java.awt.event.ActionEvent evt) {                                        
       
       JFileChooser arquivo = new JFileChooser();
       FileNameExtensionFilter filtro = new FileNameExtensionFilter("csv","CSV");
       arquivo.setFileFilter(filtro);
       arquivo.showOpenDialog(null);
       File arq = arquivo.getSelectedFile();
       String arqNome = arq.getAbsolutePath();
       this.camField.setText(arqNome);
       this.btImportar.setVisible(true);
       this.btAvancar.setVisible(true);
    }
//    private void btAvancarActionPerformed(ActionEvent evt) {
//        this.focusPainel(3);
//        this.habilitarPainel(3, true);
//        this.habilitarPainel(1, false);
//        this.habilitarPainel(2, false);
//    }
//
//    private void btVoltarActionPerformed(ActionEvent evt) {
//        this.focusPainel(0);
//        this.habilitarPainel(0, true);
//        this.habilitarPainel(1, false);
//        this.habilitarPainel(2, false);
//    }    
    private void btImportarActionPerformed(java.awt.event.ActionEvent evt) throws ParseException, IOException {                                           
            CtrlGeral.setMedicao(lerArquivo(camField.getText()));
//            CtrlGeral
            this.insertInfoTextFields(CtrlGeral.getMedicao().getCidade(), 
                                  CtrlGeral.getMedicao().getCodigo(), 
                                   CtrlGeral.getMedicao().getLatitude(),
                                   CtrlGeral.getMedicao().getSituacao(), 
                                   CtrlGeral.getMedicao().getAltitude(), 
                                  CtrlGeral.getMedicao().getLongitude(),
                                 CtrlGeral.getMedicao().getDataInicialBR(), 
                                  CtrlGeral.getMedicao().getDataFinalBR(), 
                               CtrlGeral.getMedicao().getPeriodicidade(), 
                                 CtrlGeral.getMedicao().getDataCriacaoBR());
            //lerArquivo(camField.getText());
            //tabelaTM.setModel(modelo);
//            System.out.println(modelo.getLista());
            //pop = new pesosPopup(4);
            //pop.setVisible(true);
        
       
    }
}
