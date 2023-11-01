/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jacka
 */
class ResultadosDecorator {

    private JPanel fundo;
    private JPanel jPanelOpcao;
    private JPanel jPanelRelatorio;
       
    private JButton btSair;
    private JTextArea jTextAreaRelatorio;        

    private JCheckBox jCheckBoxOpcao1;
    private JCheckBox jCheckBoxOpcao2;
    private JCheckBox jCheckBoxOpcao3;
    
    private JScrollPane jScrollPaneRelatorio;
    
    public ResultadosDecorator() {
        this.initComponets();
        this.configureOpcaoCheckBox();
        this.configureTextArea();
        this.configureBtn();
    }

    public JPanel ResultadoReady() {

        this.panelOpcoes();
        
        this.panelRelatorio();
        
        this.configureFundo();
        
        return this.fundo; }

    private void initComponets() {
        
        this.fundo = new JPanel();
        this.jPanelOpcao = new JPanel();
        this.jPanelRelatorio = new JPanel();

        this.btSair = new JButton();
        this.jTextAreaRelatorio = new JTextArea();        

        this.jCheckBoxOpcao1 = new JCheckBox();
        this.jCheckBoxOpcao2 = new JCheckBox();
        this.jCheckBoxOpcao3 = new JCheckBox();

        this.jScrollPaneRelatorio = new JScrollPane();
        
    }

    private void configureOpcaoCheckBox() {
        
        this.jCheckBoxOpcao1.setText("Salvar dados gerados em Banco de Dados");

        this.jCheckBoxOpcao2.setText("Exportar tabela em CSV");

        this.jCheckBoxOpcao3.setText("Salvar relatorio em Read-me.txt");
        
    }

    private void configureTextArea() {
        
        this.jTextAreaRelatorio.setEditable(false);
        this.jTextAreaRelatorio.setBackground(new java.awt.Color(153, 153, 153));
        this.jTextAreaRelatorio.setColumns(20);
        this.jTextAreaRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        this.jTextAreaRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        this.jTextAreaRelatorio.setRows(5);

        this.jScrollPaneRelatorio.setViewportView(this.jTextAreaRelatorio);
        
    }

    private void panelOpcoes() {        
        
        this.jPanelOpcao.setBackground(new java.awt.Color(255, 255, 255));
        this.jPanelOpcao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções Finais:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        
        javax.swing.GroupLayout opcaoLayout = new javax.swing.GroupLayout(this.jPanelOpcao);
        this.jPanelOpcao.setLayout(opcaoLayout);
        opcaoLayout.setHorizontalGroup(
            opcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(opcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.jCheckBoxOpcao2)
                    .addComponent(this.jCheckBoxOpcao1)
                    .addComponent(this.jCheckBoxOpcao3))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        opcaoLayout.setVerticalGroup(
            opcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opcaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.jCheckBoxOpcao1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.jCheckBoxOpcao2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.jCheckBoxOpcao3)
                .addGap(126, 126, 126))
        );        
    }

    private void panelRelatorio() {

        this.jPanelRelatorio.setBackground(new java.awt.Color(54, 63, 73));
        this.jPanelRelatorio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relatorio Final:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        javax.swing.GroupLayout relatorioLayout = new javax.swing.GroupLayout(this.jPanelRelatorio);        
        this.jPanelRelatorio.setLayout(relatorioLayout);
        
        relatorioLayout.setHorizontalGroup(
            relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneRelatorio)
                .addContainerGap())
        );
        relatorioLayout.setVerticalGroup(
            relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );        
    }

    private void configureBtn() {
        this.btSair.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.btSair.setText("Sair");
    }

    private void configureFundo() {
        
        this.fundo.setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout fundoLayout = new javax.swing.GroupLayout(this.fundo);
        this.fundo.setLayout(fundoLayout);
        
        fundoLayout.setHorizontalGroup(
            fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addContainerGap(1182, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.btSair)
                .addContainerGap())
            .addGroup(fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jPanelOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(954, Short.MAX_VALUE))
            .addComponent(this.jPanelRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        fundoLayout.setVerticalGroup(
            fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jPanelOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(this.jPanelRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(this.btSair))
                .addContainerGap())
        );
    }
    
}
