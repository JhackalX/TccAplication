/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlInterface;
import Object.AnaliseMensal;
import Object.ListaClassificacao;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 *
 * @author jacka
 */
public class TableShowDecorator {
    
    private JPanel fundo;
    private JPanel jPanelStatus;
    private JPanel panelBTNs;
    
    private JFrame janela;
    private JScrollPane jScrollPaneStatus;
    private JTabbedPane jTabbedPaneColunas;
    private JButton okBtn;
    
    private CtrlInterface ctrlInterface;
    
    private List<ListaClassificacao> listaClassificacao;

    
    private final String meses[] = {"Janeiro", "Fervereiro", "Março", "Abril", 
                                    "Maio", "Junho", "Julho", "Agosto", 
                                    "Setembro", "Outubro", "Novembro", "Dezembro"};

    public TableShowDecorator() {
        this.fundo = new JPanel();
        this.jPanelStatus = new JPanel();
        this.panelBTNs = new JPanel();
        this.janela = new JFrame();
        this.jScrollPaneStatus = new JScrollPane();
        this.jTabbedPaneColunas = new JTabbedPane();
        this.okBtn = new JButton();
        this.listaClassificacao = null;
    }

    public JPanel TableShowReady(JFrame janela, List<ListaClassificacao> lista, int op){
        
        this.janela = janela;
        this.StatusTab(lista, op);
        this.configureBtn();

        if(op == 1){
            this.panelFundoA();
        }else{
            this.panelFundoB();            
        }
        
        return this.fundo;
    }
    
    private void configureBtn() {
               
        this.okBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.okBtn.setText("OK");

        this.okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });        
        
        this.panelBTNs.setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout BTNsLayout = new javax.swing.GroupLayout(this.panelBTNs);
        this.panelBTNs.setLayout(BTNsLayout);
        
        BTNsLayout.setHorizontalGroup(
            BTNsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(BTNsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addContainerGap())
        );
        BTNsLayout.setVerticalGroup(
            BTNsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(BTNsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BTNsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(this.okBtn))
                .addContainerGap())
        );        
    }

    private void StatusTab(List<ListaClassificacao> list, int op){
        
       this.listaClassificacao = list;
       this.jTabbedPaneColunas = this.AddColunasTab(listaClassificacao, op);
       this.jScrollPaneStatus.setViewportView(this.jTabbedPaneColunas);
//       this.jPanelStatus.add(this.jScrollPaneStatus);       
//       this.atualizarPanel();
    }
    
    private int calcPivorAno(List<AnaliseMensal> lista, int inicio){
        
        int pivor = inicio;
        
        while((pivor + 1 < lista.size()) && 
            (lista.get(pivor).getIntAno() == lista.get(pivor + 1).getIntAno())){
            pivor++;
        }
        return pivor;
    }
    
    private JTabbedPane AddMesesTab(List<AnaliseMensal> list, int op){
        
        JTabbedPane mesTabs = new JTabbedPane();
        List<AnaliseMensal> lista = list;
        if(op == 1){
            for(int index = 0; index < lista.size(); index++){
                mesTabs.addTab(this.meses[(lista.get(index).getIntMes() - 1)],
                            new JScrollPane(this.panelStatusA(lista.get(index).getTendencia(),
                                                                lista.get(index).getCoefSperman(),
                                                               lista.get(index).getEstacionariedade())));
            }            
        }else{
            for(int index = 0; index < lista.size(); index++){
                if(lista.get(index).getQtdSubs() != 0){
                    mesTabs.addTab(this.meses[(lista.get(index).getIntMes() - 1)],
                                new JScrollPane(this.panelStatusB(lista.get(index).getMad(),
                                                                      lista.get(index).getMae(),
                                                                      lista.get(index).getMape())));                   
                }
            }            
        }
        
        return mesTabs;
    }
    
    private JTabbedPane AddAnosTab(List<AnaliseMensal> list, int op){
        
        JTabbedPane anoTabs = new JTabbedPane();
        List<AnaliseMensal> lista = list;
        int pivor = 0;
   
        for(int index = 0; index < lista.size();){
            pivor = this.calcPivorAno(lista, index);
            anoTabs.addTab( lista.get(index).getAno(), 
                         this.AddMesesTab(lista.subList(index, pivor + 1), op));
            index = pivor + 1;               
        }
       
        return anoTabs;
    }

    private JTabbedPane AddColunasTab(List<ListaClassificacao> list, int op){
        
        JTabbedPane colunaTabs = new JTabbedPane();
        List<ListaClassificacao> lista = list;
        
        for(int coluna = 0; coluna < lista.size(); coluna++){            
            colunaTabs.addTab(lista.get(coluna).getTitulo(),
                           this.AddAnosTab(lista.get(coluna).getLista(), op));
        }
       
        return colunaTabs;
    }
        
    private JPanel panelStatusA(String tendencia, 
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

    private JPanel panelStatusB(String mad, 
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

        Status.setBorder(BorderFactory.createTitledBorder(
                                                  null,
                                                   "Status:",
                                          javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                              javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                                        new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Status.setForeground(new java.awt.Color(204, 204, 204));
        //jPanelStatus.setBackground(Color.red);
        GroupLayout statusLayout = new GroupLayout(Status);
        Status.setLayout(statusLayout);
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

    public void atualizarPanel(int op) {
        this.jPanelStatus.removeAll();
        if (this.listaClassificacao != null){
//            System.out.println("ouve mudança!");
            this.jTabbedPaneColunas = this.AddColunasTab(listaClassificacao, op);
            this.jScrollPaneStatus.setViewportView(this.jTabbedPaneColunas);
            this.jPanelStatus.add(this.jScrollPaneStatus);
            
            
        }else{
//            System.out.println("ouve nenhuma mudança!");
        }
        
        this.jPanelStatus.revalidate();
        this.jPanelStatus.repaint();
        
    }
    
    private void okBtnActionPerformed(ActionEvent evt) {
        this.janela.dispose();
    }    

    private void panelFundoA() {
        this.fundo.setBackground(new java.awt.Color(255, 255, 255));
        this.fundo.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                   new javax.swing.border.LineBorder(
                                        new java.awt.Color(0, 0, 0), 
                                5, 
                             true),
                              "Tabela de Classificação:", 
                     javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                  new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        this.fundo.setLayout(new BorderLayout());
        this.fundo.add(this.jScrollPaneStatus, BorderLayout.NORTH);
        this.fundo.add(this.panelBTNs, BorderLayout.SOUTH);
    }

    private void panelFundoB() {
        this.fundo.setBackground(new java.awt.Color(255, 255, 255));
        this.fundo.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                   new javax.swing.border.LineBorder(
                                        new java.awt.Color(0, 0, 0), 
                                5, 
                             true),
                              "Tabela de Erro:", 
                     javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                  new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        this.fundo.setLayout(new BorderLayout());
        this.fundo.add(this.jScrollPaneStatus, BorderLayout.NORTH);
        this.fundo.add(this.panelBTNs, BorderLayout.SOUTH);
    }

}
