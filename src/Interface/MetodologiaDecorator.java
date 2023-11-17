/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author jacka
 */
public class MetodologiaDecorator {

    private JPanel fundo;
    private JPanel jPanelChoosen;
    private JPanel jPanelDetalhes;
    private JPanel jPanelStatus;
    
    private JButton avancarBtn;
    private JButton retornarBtn;
    
    private JLabel jLabelOpcao1;
    private JLabel jLabelOpcao2;
    
    private JRadioButton jRadioButtonOpcao1;
    private JRadioButton jRadioButtonOpcao2;
    
    private JTextArea jTextAreaDetalhes;
    private JTextArea jTextAreaDescricao1;
    private JTextArea jTextAreaDescricao2;
    private JTextField jTextFieldnPesos;
    private JTextField jTextFieldcoef;
    
    private JScrollPane jScrollPaneDetalhes;
    private JScrollPane jScrollPaneDescricao1;
    private JScrollPane jScrollPaneDescricao2;
    
    
    private ButtonGroup buttonGroupOpcoes;

    
    public MetodologiaDecorator() {
        this.initComponents();
    }
    
    public void initComponents(){
        
        this.fundo = new JPanel();
        this.jPanelChoosen = new JPanel();
        this.jPanelDetalhes = new JPanel();
        this.jPanelStatus = new JPanel();
        
        this.avancarBtn = new JButton();
        this.retornarBtn = new JButton();
        
        this.jLabelOpcao1 = new JLabel();
        this.jLabelOpcao2 = new JLabel();
        
        this.jRadioButtonOpcao1 = new JRadioButton();
        this.jRadioButtonOpcao2 = new JRadioButton();
        
        this.jTextFieldnPesos = new JTextField();
        this.jTextFieldcoef = new JTextField();
        
        this.jTextAreaDetalhes = new JTextArea();
        this.jTextAreaDescricao1 = new JTextArea();
        this.jTextAreaDescricao2 = new JTextArea();
        
        this.jScrollPaneDetalhes = new JScrollPane();
        this.jScrollPaneDescricao2 = new JScrollPane();
        this.jScrollPaneDescricao1 = new JScrollPane();

        this.buttonGroupOpcoes = new ButtonGroup();
        
        this.mostrarDescricaoAr(false);                
        this.mostrarDescricaoEs(false);
    
    }
    
    public JPanel metodologiaReady(){
        
        this.panelDetalhes();
        this.panelChoosen();
        this.panelFundo();
        
        return this.fundo;
    }
    
    private void configureChoosen(){

        this.jPanelChoosen.setBackground(new java.awt.Color(255, 255, 255));
        this.jPanelChoosen.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                                                                                        "Metodologia",
                                                                               javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                                                  javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                                                            new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        this.jPanelChoosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                choosenMouseEntered(evt);
            }
        });

        this.jTextAreaDescricao1.setEditable(false);
        this.jTextAreaDescricao1.setColumns(20);
        this.jTextAreaDescricao1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaDescricao1.setRows(5);
        this.jTextAreaDescricao1.setText("\n Ȳ(t)=∑ ( P(t-1)  x Y(t-1) )"
                                       + "\n Ȳ = Valor predito"
                                       + "\n Y = Valor original"
                                       + "\n (t) = periodo atual"
                                       + "\n (t-1) = periodo anterior"
                                       + "\n P = Peso atribuidol"
                                       + "\n ∑ = Somatória\n Obs: 0 < ∑ (P) < 1. ");
        this.jTextAreaDescricao1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, 
                                                                                              "Formula:",
                                                                                     javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                                                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                                                                  new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        this.jTextAreaDescricao1.setOpaque(false);
        this.jScrollPaneDescricao1.setViewportView(this.jTextAreaDescricao1);

        this.jTextAreaDescricao2.setEditable(false);
        this.jTextAreaDescricao2.setColumns(20);
        this.jTextAreaDescricao2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        this.jTextAreaDescricao2.setRows(5);
        this.jTextAreaDescricao2.setText("\n Ȳ(t) = Ȳ(t-1) + (α x (Y(t-1) - Ȳ(t-1)))"
                                       + "\n Ȳ = Valor predito"
                                       + "\n Y = Valor original"
                                       + "\n (t) = periodo atual"
                                       + "\n (t-1) = periodo anterior"
                                       + "\n α = coeficiente Exponencial"
                                       + "\n Obs: 0 < α < 1.");
        this.jTextAreaDescricao2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                                                                                              "Formula:",
                                                                                     javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                                                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                                                                  new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        this.jTextAreaDescricao2.setMinimumSize(new java.awt.Dimension(147, 116));
        this.jTextAreaDescricao2.setName(""); // NOI18N
        this.jTextAreaDescricao2.setOpaque(false);
        this.jScrollPaneDescricao2.setViewportView(this.jTextAreaDescricao2);

        this.jLabelOpcao2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelOpcao2.setText("Coeficiente (α):");        
        
        this.jLabelOpcao1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jLabelOpcao1.setText("Nº de Pesos (P):");

        this.jRadioButtonOpcao1.setBackground(new java.awt.Color(255, 255, 255));
        this.buttonGroupOpcoes.add(this.jRadioButtonOpcao1);
        this.jRadioButtonOpcao1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jRadioButtonOpcao1.setText("Media Movel Ponderada");
        this.jRadioButtonOpcao1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                opcao1FocusGained(evt);
                CtrlGeral.getMedicao().getMetodologiaAplicada().setOpcao(0);
//                System.out.println(CtrlGeral.getMedicao().getMetodologiaAplicada().toString());
            }
        });        
        
        this.jRadioButtonOpcao2.setBackground(new java.awt.Color(255, 255, 255));
        this.buttonGroupOpcoes.add(this.jRadioButtonOpcao2);
        this.jRadioButtonOpcao2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.jRadioButtonOpcao2.setText("Alisamento Exponencial");
        this.jRadioButtonOpcao2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                opcao2FocusGained(evt);
                CtrlGeral.getMedicao().getMetodologiaAplicada().setOpcao(1);
//                System.out.println(CtrlGeral.getMedicao().getMetodologiaAplicada().toString());
            }
        });
       
    }


    private void choosenMouseEntered(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void configureDetalhes(){

        this.jTextAreaDetalhes.setEditable(false);
        this.jTextAreaDetalhes.setColumns(20);
        this.jTextAreaDetalhes.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        this.jTextAreaDetalhes.setRows(5);
        this.jTextAreaDetalhes.setText("\n Autoregressive Integrated Moving Average (ARIMA ):\n"
                                     + "\n É um algoritmo de estatísticas locais comumente "
                                     + "\n usado para previsões de série temporais. "
                                     + "\n Ela captura várias estruturas temporais padrão "
                                     + "\n (organizações com padrão de tempo) no conjunto "
                                     + "\n de dados de entrada.\n\n Média Movel Ponderada:\n"
                                     + "\n A média móvel ponderada é utilizada para "
                                     + "\n \"solucionar\" o problema de igualdade de pesos."
                                     + "\n Este indicador é calculado através da soma de todos "
                                     + "\n os valores multiplicado por seus respectivos pesos e "
                                     + "\n dividido pela soma de todos os pesos de cada dia."
                                     + "\n Os valores dos pesos indica o grau de importancia "
                                     + "\n que aquele dado tem em relação a outros dados "
                                     + "\n mais antigos.\n\n Alisamento Exponencial\n"
                                     + "\n O alisamento exponencial é uma das técnicas mais "
                                     + "\n utilizadas na previsão, em parte devido aos seus "
                                     + "\n requisitos mínimos de armazenamento de dados e "
                                     + "\n facilidade de cálculo e, também, devido à facilidade "
                                     + "\n com que o sistema de ponderação pode ser alterado "
                                     + "\n através da variação do valor coeficiente de \n alisamento. "
                                     + "\n\t                   (Stevenson, 1996, p. 479)\n"
                                     + "\n OBS: O método de preenchimento recupera até "
                                     + "\n 3 lacunas seguidas de dados faltantes garantindo "
                                     + "\n uma taxa de precisão segura, acima disso não é "
                                     + "\n garantido confiabilidade em suas predições.");
        this.jTextAreaDetalhes.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                                                                                            "Saiba mais:",
                                                                                   javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                                                      javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                                                                                new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        this.jScrollPaneDetalhes.setViewportView(this.jTextAreaDetalhes);        
        
    }
    
    
    private void configureBtns(){
        this.avancarBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.avancarBtn.setText("Avançar");
        this.avancarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(jTextFieldcoef.getText().equalsIgnoreCase("") && 
                   jTextFieldnPesos.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null,"Os campos de valores devem ser preenchidos!"); 
                }else{
                    if(jRadioButtonOpcao1.isSelected() && (!jTextFieldnPesos.getText().equalsIgnoreCase(""))){
                        avancarBtnPesosActionPerformed(evt);                    
                    }else{
                        if(jRadioButtonOpcao2.isSelected() && (!jTextFieldcoef.getText().equalsIgnoreCase(""))){
                            avancarBtnCoefActionPerformed(evt);
                        }else{
                            JOptionPane.showMessageDialog(null,"Os campos de valores devem ser preenchidos!");
                        }
                    }                 
                }
            }
        });
        
        this.retornarBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        this.retornarBtn.setText("Retornar");
    }
    
    
    private void panelDetalhes(){
        
        this.configureDetalhes();
        this.configureBtns();
        
        this.jPanelDetalhes.setBackground(new java.awt.Color(255, 255, 255));

        GroupLayout DetalhesLayout = new GroupLayout(this.jPanelDetalhes);
        this.jPanelDetalhes.setLayout(DetalhesLayout);
        DetalhesLayout.setHorizontalGroup(
            DetalhesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(DetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.retornarBtn)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE,
                                 Short.MAX_VALUE)
                .addComponent(this.avancarBtn))
            .addComponent(this.jScrollPaneDetalhes,
                              GroupLayout.DEFAULT_SIZE,
                             980,
                             Short.MAX_VALUE)
        );
        DetalhesLayout.setVerticalGroup(
            DetalhesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, DetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPaneDetalhes)
                .addGap(60, 60, 60)
                .addGroup(DetalhesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.avancarBtn)
                    .addComponent(this.retornarBtn))
                .addContainerGap())
        );
    }
    
    private void panelChoosen(){
        this.configureChoosen();
        
        GroupLayout choosenLayout = new GroupLayout(this.jPanelChoosen);
        this.jPanelChoosen.setLayout(choosenLayout);
        choosenLayout.setHorizontalGroup(
            choosenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(choosenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(choosenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(choosenLayout.createSequentialGroup()
                        .addComponent(this.jLabelOpcao1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jTextFieldnPesos,
                                          GroupLayout.PREFERRED_SIZE,
                                         42, 
                                         GroupLayout.PREFERRED_SIZE))
                    .addComponent(this.jRadioButtonOpcao1,
                                      GroupLayout.PREFERRED_SIZE,
                                     200,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jScrollPaneDescricao1,
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE,
                                 Short.MAX_VALUE)
                .addGroup(choosenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(choosenLayout.createSequentialGroup()
                        .addComponent(this.jLabelOpcao2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jTextFieldcoef,
                                          GroupLayout.PREFERRED_SIZE,
                                         42,
                                         GroupLayout.PREFERRED_SIZE))
                    .addComponent(this.jRadioButtonOpcao2,
                                      GroupLayout.PREFERRED_SIZE,
                                     219,
                                     GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.jScrollPaneDescricao2,
                                      GroupLayout.PREFERRED_SIZE,
                                     GroupLayout.DEFAULT_SIZE,
                                     GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );
        choosenLayout.setVerticalGroup(
            choosenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(choosenLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(choosenLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(choosenLayout.createSequentialGroup()
                        .addComponent(this.jRadioButtonOpcao2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(choosenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.jLabelOpcao2)
                            .addComponent(this.jTextFieldcoef,
                                              GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.DEFAULT_SIZE,
                                             GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(this.jScrollPaneDescricao2,
                                          GroupLayout.DEFAULT_SIZE,
                                         189,
                                         Short.MAX_VALUE))
                    .addGroup(choosenLayout.createSequentialGroup()
                        .addComponent(this.jRadioButtonOpcao1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(choosenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.jTextFieldnPesos,
                                              GroupLayout.PREFERRED_SIZE,
                                             GroupLayout.DEFAULT_SIZE,
                                             GroupLayout.PREFERRED_SIZE)
                            .addComponent(this.jLabelOpcao1))
                        .addGap(18, 18, 18)
                        .addComponent(this.jScrollPaneDescricao1,
                                          GroupLayout.DEFAULT_SIZE,
                                         189,
                                         Short.MAX_VALUE))))
        );        
    }
    
    private void panelFundo(){
        
        GroupLayout fundoLayout = new GroupLayout(this.fundo);
        this.fundo.setLayout(fundoLayout);
        fundoLayout.setHorizontalGroup(
            fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addGroup(fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING,
                                                               false)
                    .addComponent(this.jPanelStatus,
                                       GroupLayout.DEFAULT_SIZE,
                                       GroupLayout.DEFAULT_SIZE,
                                       Short.MAX_VALUE)
                    .addComponent(this.jPanelChoosen, 
                                  GroupLayout.DEFAULT_SIZE, 
                                  GroupLayout.DEFAULT_SIZE, 
                                  Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.jPanelDetalhes, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE))
        );
        fundoLayout.setVerticalGroup(
            fundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addComponent(this.jPanelChoosen, 
                                   GroupLayout.PREFERRED_SIZE, 
                                  GroupLayout.DEFAULT_SIZE,
                                  GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE,
                                 Short.MAX_VALUE)
                .addComponent(this.jPanelStatus, 
                              GroupLayout.PREFERRED_SIZE,
                              GroupLayout.DEFAULT_SIZE,
                              GroupLayout.PREFERRED_SIZE))
            .addComponent(this.jPanelDetalhes,
                          GroupLayout.DEFAULT_SIZE, 
                          GroupLayout.DEFAULT_SIZE, 
                          Short.MAX_VALUE)
        );        
    }

    private void opcao1FocusGained(FocusEvent evt) {
        this.mostrarDescricaoAr(true);
        this.mostrarDescricaoEs(false);
    }    

    private void opcao2FocusGained(FocusEvent evt) {
        this.mostrarDescricaoAr(false);
        this.mostrarDescricaoEs(true);    
    }
    
    private void mostrarDescricaoAr(boolean entrada) {
        this.jLabelOpcao1.setVisible(entrada);
        this.jTextFieldnPesos.setVisible(entrada);
        this.jScrollPaneDescricao1.setVisible(entrada);       
        this.jTextAreaDescricao1.setVisible(entrada);
        
        ((AbstractDocument) jTextFieldnPesos.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                                throws BadLocationException {
                String newText = text.replaceAll("[^0-9]", "");
                super.replace(fb, offset, length, newText, attrs);
            }
        });
    }

    private void mostrarDescricaoEs(boolean entrada) {
        this.jLabelOpcao2.setVisible(entrada);
        this.jTextFieldcoef.setVisible(entrada);
        this.jScrollPaneDescricao2.setVisible(entrada);
        this.jTextAreaDescricao2.setVisible(entrada);
        
        // cria o DocumentFilter para permitir apenas números float entre 0 e 1
        ((AbstractDocument) jTextFieldcoef.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
                // Remove caracteres não numéricos
                String newText = text.replaceAll("[^0-9\\.]", "");
                String fullText = fb.getDocument().getText(0, fb.getDocument().getLength()) + newText;

                // Converte o valor para float e verifica se está dentro do intervalo 0 a 1
                float value = Float.parseFloat(fullText);
                if (value < 0 || value >= 1) {
                  return;
                }

                // Chama o método replace da classe pai para inserir o novo valor no JTextField
                super.replace(fb, offset, length, newText, attrs);
            }
        });
    }
    private void avancarBtnPesosActionPerformed(ActionEvent evt) {
        JFrame janela = new JFrame();
        PopupDecorator popup = new PopupDecorator();
        JScrollPane jScrollPanePopup = new JScrollPane();
        
        janela.setSize(447,312);
        janela.setDefaultCloseOperation(janela.EXIT_ON_CLOSE);
        janela.setVisible(true);
        janela.setLayout(new BorderLayout());
        
        jScrollPanePopup.setViewportView(popup.PopupReady(Integer.parseInt(this.jTextFieldnPesos.getText()), janela));
        
        janela.add(jScrollPanePopup);
        janela.repaint();
        janela.show();
    }    
    
    private void avancarBtnCoefActionPerformed(ActionEvent evt) {
        CtrlGeral.getMedicao().getMetodologiaAplicada().setCoef(this.jTextFieldcoef.getText());
//        System.out.println(CtrlGeral.getMedicao().getMetodologiaAplicada().toString());
        CtrlGeral.gerarMetEs();

    }    
    
}
