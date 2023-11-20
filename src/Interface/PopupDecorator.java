/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author jacka
 */
public class PopupDecorator {
    private JPanel fundo;
    private JPanel panelBTNs;
    
    private JButton avancarBtn;
    private JButton limparBtn;
    private JButton voltarBtn;
    
    private List<Component> listaPesos;
    
    private Boolean somaPesos;
    private JFrame janela;
    private CtrlGeral ctrlGeral;

    public PopupDecorator(CtrlGeral ctrlGeral) {
        this.ctrlGeral = ctrlGeral;
        this.fundo = new JPanel(new GridLayout());
        this.panelBTNs = new JPanel();
        this.avancarBtn = new JButton();
        this.limparBtn = new JButton();
        this.voltarBtn = new JButton();
    }
        
    public JPanel PopupReady(int Qtd, JFrame janela) {
        this.janela = janela;
        this.configureComponets(Qtd);
        this.configureBtn();
        this.configureFundo();
        
        return this.fundo;        
    }

    public List<Float> getListaPesos() {
        
        List<Float> lista = new ArrayList(); 
        float valor = 0;

        for(int i = 0; i < this.listaPesos.size(); i++){
            Component[] components = ((JPanel)this.listaPesos.get(i)).getComponents();
            for (Component c : components) {
                if (c instanceof JTextField) {
                    JTextField textFieldPesos = (JTextField) c;
                    valor = Float.parseFloat(textFieldPesos.getText().isEmpty() ? "0" : textFieldPesos.getText());
                    lista.add(valor);
                }
            }            
        }        
        //remove valores nulos
        lista.removeIf(n -> (Float.parseFloat(n + "") == (0.0)));
        
        return lista;
    }

    private void configureFundo() {
        
        this.fundo.setBackground(new java.awt.Color(255, 255, 255));
        this.fundo.setBorder(javax.swing.BorderFactory.createTitledBorder(
                                   new javax.swing.border.LineBorder(
                                        new java.awt.Color(0, 0, 0), 
                                5, 
                             true),
                              "Pesos:", 
                     javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                                  new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        this.fundo.setLayout(new GridLayout((this.listaPesos.size()+1),1,0,1));
        this.fundo.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        for(int i = 0; i < this.listaPesos.size(); i++){
            this.fundo.add(this.listaPesos.get(i));
        }
       
        this.fundo.add(this.panelBTNs);
        
    }

    private void configureBtn() {
        
        this.limparBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.limparBtn.setText("Limpar");       
        this.limparBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparBtnActionPerformed(evt);
            }
        });
        
        this.voltarBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.voltarBtn.setText("Voltar");
        this.voltarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarBtnActionPerformed(evt);
            }
        });        
        
        this.avancarBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        this.avancarBtn.setText("Avançar");        
        this.avancarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avancarBtnActionPerformed(evt);
            }
        });

        this.panelBTNs.setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout BTNsLayout = new javax.swing.GroupLayout(this.panelBTNs);
        this.panelBTNs.setLayout(BTNsLayout);
        
        BTNsLayout.setHorizontalGroup(
            BTNsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(BTNsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.voltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(this.limparBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(this.avancarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        BTNsLayout.setVerticalGroup(
            BTNsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(BTNsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BTNsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(this.avancarBtn)
                    .addComponent(this.limparBtn)
                    .addComponent(this.voltarBtn))
                .addContainerGap())
        );        
    }

    private void configureComponets(int Qtd) {

        this.listaPesos = new ArrayList();
        
        for(int i = 0; i < Qtd; i++){            
            this.listaPesos.add(this.configurePanelComponets(i));            
        }
    
    }

    private JPanel configurePanelComponets(int valor) {

        JLabel jLabelPeso = new JLabel();
        jLabelPeso.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelPeso.setText("Peso " + (valor+1) + ":");

        JTextField jTextFieldPeso = new JTextField();
        JPanel panelPeso = new JPanel();
        
        // cria o DocumentFilter para permitir apenas números float entre 0 e 1
        ((AbstractDocument) jTextFieldPeso.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
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
        
        // cria um DocumentListener para atualizar os valores dos JTextField
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                atualizarValores();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                atualizarValores();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                atualizarValores();
            }

        };

        jTextFieldPeso.getDocument().addDocumentListener(documentListener);
        
        panelPeso.setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout panelPesoLayout = new javax.swing.GroupLayout(panelPeso);
        panelPeso.setLayout(panelPesoLayout);
        
        panelPesoLayout.setHorizontalGroup(
            panelPesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(panelPesoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelPesoLayout.setVerticalGroup(
            panelPesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPeso)
                    .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        return panelPeso;
    }
    
    private void atualizarValores() {

        float valor = 0;
        float soma = 0;
   
        for(int i = 0; i < this.listaPesos.size(); i++){
            Component[] components = ((JPanel)this.listaPesos.get(i)).getComponents();
            for (Component c : components) {
                if (c instanceof JTextField) {
                    JTextField textFieldPesos = (JTextField) c;
                    valor = Float.parseFloat(textFieldPesos.getText().isEmpty() ? "0" : textFieldPesos.getText());
                    soma += valor;
                }
            }            
        }

        if (soma != 1) {
            this.somaPesos = false;
        }else{
            this.somaPesos = true;
        }

    }
    
    private void avancarBtnActionPerformed(ActionEvent evt) {
        if(!this.somaPesos){
            JOptionPane.showMessageDialog(null,"Soma de pesos diferente de 1.");
        }else{
            //aqui deve exportar a lista de pesos
            this.ctrlGeral.getMedicao().getMetodologiaAplicada().setLista(this.getListaPesos());
            System.out.println(this.ctrlGeral.getMedicao().getMetodologiaAplicada().toString());
            this.ctrlGeral.gerarMetAR();
            this.janela.dispose();
        }
    }
    
    private void limparBtnActionPerformed(ActionEvent evt) {
        //deve ser assim que funfa...
        for(int i = 0; i < this.listaPesos.size(); i++){
            Component[] components = ((JPanel)this.listaPesos.get(i)).getComponents();
            for (Component c : components) {
                if (c instanceof JTextField) {
                    ((JTextField) c).setText("");    
                }
            }            
        }         
    }    

    private void voltarBtnActionPerformed(ActionEvent evt) {
        this.janela.dispose();
    }
}
