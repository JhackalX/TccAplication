/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import DTO.CtrlInterface;
import DTO.EstacoesListaDeAnos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jacka
 */
public class PopupSelectDecorator {

    //Panels
    private JPanel jPanelInfo;
    private JPanel jPaneButtons;
    //Buttons
    private JButton jButtonOk;
    private JButton jButtonCancel;
    //Labels
    private JLabel jLabelCodEst;
    private JLabel jLabelCidade;
    private JLabel jLabelLatitude;
    private JLabel jLabelLongitude;
    private JLabel jLabelPeriodMed;
    private JLabel jLabelAltitude;
    private JLabel jLabelSituacao;
    private JLabel jLabelDataInicial;
    private JLabel jLabelDataFinal;
    private JLabel jLabelDataCria;
    private JLabel jLabelAnoEst;
    private JLabel jLabelUf;
    private JLabel jLabelRegiao;
    //TextFields
    private JTextField jTextFieldCidade;
    private JTextField jTextFieldLatitude;
    private JTextField jTextFieldLongitude;
    private JTextField jTextFieldDataInic;
    private JTextField jTextFieldAltitude;
    private JTextField jTextFieldSituacao;
    private JTextField jTextFieldDataFinal;
    private JTextField jTextFieldDataCria;
    private JTextField jTextFieldPeriodMed;
    private JTextField jTextFieldUf;
    private JTextField jTextFieldRegiao;
    //ComboBoxs
    private JComboBox<EstacoesListaDeAnos> jComboBoxCodEst;
    private JComboBox<String> jComboBoxAnoEst;
    //propriedades de auxilio
    private ArrayList<EstacoesListaDeAnos> listEstacoes;
//    private ArrayList<String> listAnos;
//    private ArrayList<String> listEstacoes;
    private JFrame janela;
    
    private CtrlInterface ctrlInterface;
    
    public PopupSelectDecorator(CtrlInterface ctrlInterface) {
        this.ctrlInterface = ctrlInterface;
        this.initGeralComponets();
//        this.configureComboBox();
        this.configureButtonPane();
    }
    
    public void PopupSelectReady(int op, JFrame janela){
        switch (op){
            case 1:
                this.configureLambelsAndTextFilds1();
                this.configureComboBox();
                this.configureInfo1Panel();
                this.configureInfoButtons();
     
                javax.swing.GroupLayout layout1 = new javax.swing.GroupLayout(janela.getContentPane());
                janela.getContentPane().setLayout(layout1);
                layout1.setHorizontalGroup(
                    layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout1.setVerticalGroup(
                    layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                
                this.janela = janela;

                break;
                
//            case 2:               
//                this.configureLambelsAndTextFilds2();
//                this.configureComboBox();
//                this.configureInfo2Panel();
//                this.configureInfoButtons();
//     
//                javax.swing.GroupLayout layout2 = new javax.swing.GroupLayout(janela.getContentPane());
//                janela.getContentPane().setLayout(layout2);
//                layout2.setHorizontalGroup(
//                    layout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(jPanelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                );
//                layout2.setVerticalGroup(
//                    layout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                );
//                
//                this.janela = janela;                               
//                
//                break;
                
            default:                               
                this.configureLambelsAndTextFilds3();
                this.configureInfo3Panel();
                this.configureInfoButtons();
                
                javax.swing.GroupLayout layout3 = new javax.swing.GroupLayout(janela.getContentPane());
                janela.getContentPane().setLayout(layout3);
                layout3.setHorizontalGroup(
                    layout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout3.setVerticalGroup(
                    layout3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                this.janela = janela;
                
                break;
        }
                
        
    }
    //função para teste

    private void popularTeste(){
        this.listEstacoes = new ArrayList<EstacoesListaDeAnos>();
        EstacoesListaDeAnos novo = new EstacoesListaDeAnos();
        this.listEstacoes.add(novo);
        this.listEstacoes.get(0).setNomeEstacao("estacao 1");
        this.listEstacoes.get(0).addAnos("2010");
        this.listEstacoes.get(0).addAnos("2011");
        this.listEstacoes.get(0).addAnos("2012");
        this.listEstacoes.get(0).addAnos("2013");
        this.listEstacoes.get(0).addAnos("2014");
        this.listEstacoes.add(new EstacoesListaDeAnos());
        this.listEstacoes.get(1).setNomeEstacao("estacao 2");
        this.listEstacoes.get(1).addAnos("2010");
        this.listEstacoes.add(new EstacoesListaDeAnos());
        this.listEstacoes.get(2).setNomeEstacao("estacao 3");
        this.listEstacoes.get(2).addAnos("2010");
        this.listEstacoes.get(2).addAnos("2011");
        this.listEstacoes.get(2).addAnos("2014");
        this.listEstacoes.add(new EstacoesListaDeAnos());
        this.listEstacoes.get(3).setNomeEstacao("estacao 4");
        this.listEstacoes.get(3).addAnos("2010");
        this.listEstacoes.get(3).addAnos("2012");
        this.listEstacoes.get(3).addAnos("2013");
        this.listEstacoes.get(3).addAnos("2014");
    }
    
    //inicia componetes constantes na janela 
    private void initGeralComponets() {
        this.jPanelInfo = new JPanel();
        this.jPaneButtons = new JPanel();

        this.listEstacoes = new ArrayList<EstacoesListaDeAnos>();
        this.popularTeste();
        this.jButtonCancel = new JButton();
        this.jButtonOk = new JButton();
        this.jButtonOk.setText("OK");
        this.jButtonCancel.setText("Cancelar");
        
        this.jComboBoxCodEst = new JComboBox<EstacoesListaDeAnos>();    
        this.jComboBoxAnoEst = new JComboBox<String>();
       
    }
    //atualiza anos de estudo conforme muda o nome da estação
    private void atualizarComboBoxAnos(EstacoesListaDeAnos estacao) {
        jComboBoxAnoEst.removeAllItems();
        if (estacao != null) {
            for (String ano : estacao.getListAnos()) {
                this.jComboBoxAnoEst.addItem(ano);
            }
        }
    }
    
// seta as listas para combox
    public void setListasEstacoes(ArrayList<EstacoesListaDeAnos> listEstacoes){
        this.listEstacoes = listEstacoes;
    }
//    public void setListasAnos(ArrayList<String> listAnos){
//        this.listAnos = listAnos;
//    }
    
    //configura os comboBox
    private void configureComboBox() {

        this.jComboBoxCodEst.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                EstacoesListaDeAnos estacaoSelecionada = (EstacoesListaDeAnos) jComboBoxCodEst.getSelectedItem();
                atualizarComboBoxAnos(estacaoSelecionada);
            }
        });

        for (EstacoesListaDeAnos estacao : this.listEstacoes) {
            this.jComboBoxCodEst.addItem(estacao);
        } 
    }

    //retorna os itens selecionados
    public String getSelectedItemEstacao() {
        return jComboBoxCodEst.getSelectedItem().toString();
    }

    public String getSelectedItemAno() {
        return jComboBoxAnoEst.getSelectedItem().toString();
    }
//------------------------------------------------------------------------------   
    //componetes do layout da Info1
    private void configureLambelsAndTextFilds1() {

//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //Labels
        this.jLabelCodEst = new JLabel();
        this.jLabelCidade = new JLabel();
        this.jLabelLatitude = new JLabel();
        this.jLabelLongitude = new JLabel();
        this.jLabelPeriodMed = new JLabel();
        this.jLabelAltitude = new JLabel();
        this.jLabelSituacao = new JLabel();
        this.jLabelDataInicial = new JLabel();
        this.jLabelDataFinal = new JLabel();
        this.jLabelDataCria = new JLabel();
        this.jLabelAnoEst = new JLabel();
        //TextFields
        this.jTextFieldCidade = new JTextField();
        this.jTextFieldLatitude = new JTextField();
        this.jTextFieldLongitude = new JTextField();
        this.jTextFieldDataInic = new JTextField();
        this.jTextFieldAltitude = new JTextField();
        this.jTextFieldSituacao = new JTextField();
        this.jTextFieldDataFinal = new JTextField();
        this.jTextFieldDataCria = new JTextField();
        this.jTextFieldPeriodMed = new JTextField();

        jLabelCodEst.setText("Codigo Estação:");
        jLabelCidade.setText("Cidade:");
        jLabelLatitude.setText("Latitude:");
        jLabelLongitude.setText("Longitude:");
        jLabelPeriodMed.setText("Periodicidade da Medição:");
        jLabelAltitude.setText("Altitude:");
        jLabelSituacao.setText("Situação:");
        jLabelDataInicial.setText("Data Inicial:");
        jLabelDataFinal.setText("Data Final:");
        jLabelDataCria.setText("Data de Criação:");
        jLabelAnoEst.setText("Ano de Estudo:");

        jTextFieldCidade.setEditable(false);
        jTextFieldLatitude.setEditable(false);
        jTextFieldLongitude.setEditable(false);
        jTextFieldDataInic.setEditable(false);
        jTextFieldAltitude.setEditable(false);
        jTextFieldSituacao.setEditable(false);
        jTextFieldDataCria.setEditable(false);
        jTextFieldPeriodMed.setEditable(false);
        jTextFieldDataFinal.setEditable(false);
       
    }

    //componetes do layout da Info2
    private void configureLambelsAndTextFilds2() {

//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //Labels
        this.jLabelCodEst = new JLabel();
        this.jLabelCidade = new JLabel();
        this.jLabelLatitude = new JLabel();
        this.jLabelLongitude = new JLabel();
        this.jLabelAltitude = new JLabel();
        this.jLabelDataInicial = new JLabel();
        this.jLabelDataCria = new JLabel();
        this.jLabelAnoEst = new JLabel();
        this.jLabelUf = new JLabel();
        this.jLabelRegiao = new JLabel();
        //TextFields
        this.jTextFieldCidade = new JTextField();
        this.jTextFieldLatitude = new JTextField();
        this.jTextFieldLongitude = new JTextField();
        this.jTextFieldDataInic = new JTextField();
        this.jTextFieldAltitude = new JTextField();
        this.jTextFieldSituacao = new JTextField();
        this.jTextFieldDataFinal = new JTextField();
        this.jTextFieldDataCria = new JTextField();
        this.jTextFieldUf = new JTextField();
        this.jTextFieldRegiao = new JTextField();

        jLabelCodEst.setText("Codigo (WMO):");
        jLabelAnoEst.setText("Ano de Estudo:");
        jLabelCidade.setText("Cidade:");
        jLabelLatitude.setText("Latitude");
        jLabelLongitude.setText("Longitude:");
        jLabelAltitude.setText("Altitude:");
        jLabelDataInicial.setText("Data Fundação:");
        jLabelUf.setText("UF:");
        jLabelRegiao.setText("Região:");
        jLabelDataCria.setText("Data de Criação:");

        jTextFieldCidade.setEditable(false);
        jTextFieldLatitude.setEditable(false);
        jTextFieldLongitude.setEditable(false);
        jTextFieldDataInic.setEditable(false);
        jTextFieldAltitude.setEditable(false);
        jTextFieldDataCria.setEditable(false);
        jTextFieldRegiao.setEditable(false);
        jTextFieldUf.setEditable(false);

    }
    
    //componetes do layout da Info3
    private void configureLambelsAndTextFilds3() {

//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //Labels
        this.jLabelCodEst = new JLabel();

        jLabelCodEst.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabelCodEst.setText("Erro Objeto Vaizo!");

    }

    //Configurações dos actionpeformed dos botões para cada layout
    private void configureInfoButtons() {
        this.jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
    }

//------------------------------------------------------------------------------
    //configuração do painel de botões da janela
    private void configureButtonPane() {
        jPaneButtons.setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout jPaneButtonsLayout = new javax.swing.GroupLayout(jPaneButtons);
        jPaneButtons.setLayout(jPaneButtonsLayout);
        jPaneButtonsLayout.setHorizontalGroup(
                jPaneButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPaneButtonsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonOk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCancel)
                                .addContainerGap())
        );
        jPaneButtonsLayout.setVerticalGroup(
                jPaneButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneButtonsLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPaneButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonOk)
                                        .addComponent(jButtonCancel))
                                .addContainerGap())
        );
    }

    //Configuração do painel Info1 da janela    
    private void configureInfo1Panel() {

        jPanelInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        javax.swing.BorderFactory.createTitledBorder(
                                "Info"), "",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Segoe UI", 1, 14)),
                "Info",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addComponent(jPaneButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDataInicial)
                            .addComponent(jLabelPeriodMed))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPeriodMed, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDataCria)
                                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                                        .addComponent(jTextFieldDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelDataFinal)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDataCria, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLongitude)
                            .addComponent(jLabelCidade)
                            .addComponent(jLabelCodEst)
                            .addComponent(jLabelAnoEst)
                            .addComponent(jLabelLatitude))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAltitude, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelSituacao, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldSituacao, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(jTextFieldAltitude)))
                            .addComponent(jTextFieldCidade)
                            .addComponent(jComboBoxCodEst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxAnoEst, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodEst)
                    .addComponent(jComboBoxCodEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAnoEst)
                    .addComponent(jComboBoxAnoEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCidade)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelLatitude)
                            .addComponent(jTextFieldLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelLongitude)
                            .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAltitude)
                            .addComponent(jTextFieldAltitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSituacao))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPeriodMed)
                    .addComponent(jTextFieldPeriodMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataInicial)
                    .addComponent(jTextFieldDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataFinal)
                    .addComponent(jTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataCria)
                    .addComponent(jTextFieldDataCria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPaneButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

    //Configuração do painel Info2 da janela
    private void configureInfo2Panel() {

        jPanelInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        javax.swing.BorderFactory.createTitledBorder(
                                "Info"), "",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Segoe UI", 1, 14)),
                "Info",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addComponent(jLabelDataCria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataCria, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPaneButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelCidade)
                                .addComponent(jLabelCodEst)
                                .addComponent(jLabelAnoEst))
                            .addComponent(jLabelLongitude, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelAltitude, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDataInicial, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLatitude, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelInfoLayout.createSequentialGroup()
                                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextFieldDataInic)
                                        .addComponent(jTextFieldAltitude, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)
                                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                                        .addComponent(jLabelUf)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldUf, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelInfoLayout.createSequentialGroup()
                                        .addComponent(jLabelRegiao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBoxCodEst, 0, 353, Short.MAX_VALUE)
                                .addComponent(jComboBoxAnoEst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldCidade)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCodEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCodEst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAnoEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAnoEst))
                .addGap(14, 14, 14)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCidade))
                .addGap(6, 6, 6)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLatitude)
                    .addComponent(jTextFieldUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLongitude)
                    .addComponent(jTextFieldRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRegiao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAltitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAltitude))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDataInic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataCria)
                    .addComponent(jTextFieldDataCria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPaneButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }
    
    //Configuração do painel Info2 da janela
    private void configureInfo3Panel() {
        jPanelInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Info"), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)), "Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPaneButtons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCodEst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelCodEst)
                .addGap(18, 18, 18)
                .addComponent(jPaneButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

    //Set dos campos de texto do info1
    public void setTextFieldsInfo1(String cidade,
            String latitude,
            String longitude,
            String altitude,
            String dataInicial,
            String dataFinal,
            String situacao,
            String dataCriacao,
            String periodMed) {

        this.jTextFieldCidade.setText(cidade);
        this.jTextFieldAltitude.setText(altitude);
        this.jTextFieldLatitude.setText(latitude);
        this.jTextFieldLongitude.setText(longitude);
        this.jTextFieldDataInic.setText(dataInicial);
        this.jTextFieldDataFinal.setText(dataFinal);
        this.jTextFieldDataCria.setText(dataCriacao);
        this.jTextFieldPeriodMed.setText(periodMed);
        this.jTextFieldSituacao.setText(situacao);
    }

    //Set dos campos de texto do info2
    public void setTextFieldsInfo2(String regiao,
            String uf,
            String estacao,
            String latitude,
            String longitude,
            String altitude,
            String dataFundacao,
            String dataCriacao) {

        jTextFieldCidade.setText(estacao);
        jTextFieldLatitude.setText(latitude);
        jTextFieldLongitude.setText(longitude);
        jTextFieldDataInic.setText(dataFundacao);
        jTextFieldAltitude.setText(altitude);
        jTextFieldDataCria.setText(dataCriacao);
        jTextFieldRegiao.setText(regiao);
        jTextFieldUf.setText(uf);
    }
    
    private void jButtonCancelActionPerformed(ActionEvent evt) {
        this.janela.dispose();
    }
}
