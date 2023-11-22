/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import DTO.CtrlGeral;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author jacka
 */
public class WindowDecorator {
    
    private JPanel face;
    private JTabbedPane painelTab;
    
    private JPanel inicio;
    private JPanel recuperar;
    private JPanel importar;
    private JPanel visaoGeral;
    private JPanel metodologias;
    private JPanel visaoAuxiliar;
    private JPanel resultados;

    private JScrollPane jScrollPaneInicio;
    private JScrollPane jScrollPaneRecuperar;
    private JScrollPane jScrollPaneImportar;
    private JScrollPane jScrollPaneVisaoGeral;
    private JScrollPane jScrollPaneMetodologias;
    private JScrollPane jScrollPaneVisaoAuxiliar;
    private JScrollPane jScrollPaneResultados;
    
    private CtrlGeral ctrlGeral;
    
    private WindowDecorator(JFrame janela, CtrlGeral ctrlGeral) {
        this.ctrlGeral = ctrlGeral;
        
        this.initComponets();

        janela.setSize(1200, 600);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
        janela.setLayout(new BorderLayout());
        //this.face.setBackground(Color.red);
        
        this.painelTab.setBounds(50, 50, 250, 200);
        this.painelTab.setSize(janela.getSize());
        this.painelTab.setVisible(true);
        
        this.montarTabPanel();
        this.montarFace(janela);

        janela.add(this.face);
        janela.repaint();
        
    }
    
    public static WindowDecorator decorator (JFrame janela, CtrlGeral ctrlGeral) {
     
        WindowDecorator decorate = new WindowDecorator(janela, ctrlGeral);
        
        return decorate;
    }
    
    private void initAbas(){

//        this.montarInicio();
        this.montarImportar();//Agora importar csv vem primeiro, vai pro banco de dados
        this.montarRecuperar();//Se recupera do banco de dados
        this.montarVisaoGeral();
        this.montarMetodologias();
        this.montarVisaoAuxiliar();
        this.montarResultados();
               
    }
    
    private void initComponets(){

        this.face = new JPanel(new BorderLayout());       
        this.painelTab = new JTabbedPane();
       
        this.inicio = new JPanel(new BorderLayout());
        this.recuperar = new JPanel(new BorderLayout());
        this.importar = new JPanel(new BorderLayout());
        this.visaoGeral = new JPanel(new BorderLayout());
        this.metodologias = new JPanel(new BorderLayout());
        this.visaoAuxiliar = new JPanel(new BorderLayout());
        this.resultados = new JPanel(new BorderLayout());

        this.jScrollPaneInicio = new JScrollPane();
        this.jScrollPaneRecuperar = new JScrollPane();
        this.jScrollPaneImportar = new JScrollPane();
        this.jScrollPaneVisaoGeral = new JScrollPane();
        this.jScrollPaneMetodologias = new JScrollPane();
        this.jScrollPaneVisaoAuxiliar = new JScrollPane();
        this.jScrollPaneResultados = new JScrollPane();       
    }

    //configuração do componete de abas
    private void montarTabPanel(){
        this.painelTab.setTabPlacement(JTabbedPane.LEFT);
        this.painelTab.setFont(new Font("Tahoma", 1, 12)); // NOI18N 
        
//        this.painelTab.add("Inicio", this.jScrollPaneInicio);
//        this.painelTab.add("Importar", this.jScrollPaneImportar);
//        this.painelTab.add("Recuperar", this.jScrollPaneRecuperar);
//        this.painelTab.add("Visão Geral", this.jScrollPaneVisaoGeral);
//        this.painelTab.add("Metodologias", this.jScrollPaneMetodologias);
//        this.painelTab.add("Visão Auxiliar", this.jScrollPaneVisaoAuxiliar);
//        this.painelTab.add("Resultados", this.jScrollPaneResultados);

        this.painelTab.add("Importar", this.importar);
        this.painelTab.add("Recuperar", this.recuperar);
        this.painelTab.add("Visão Geral", this.jScrollPaneVisaoGeral);
        this.painelTab.add("Metodologias", this.metodologias);
        this.painelTab.add("Visão Auxiliar", this.jScrollPaneVisaoAuxiliar);
        this.painelTab.add("Resultados", this.jScrollPaneResultados);
        // set original
        // 0 --> true
        // 1 a 6 --> false
        this.painelTab.setEnabledAt(0, true);       
        this.painelTab.setEnabledAt(1, true);       
        this.painelTab.setEnabledAt(2, true);       
        this.painelTab.setEnabledAt(3, true);       
        this.painelTab.setEnabledAt(4, true);       
        this.painelTab.setEnabledAt(5, true);       
//        this.painelTab.setEnabledAt(6, true);
        
        this.initAbas();
    }

    //configuração do painele sub aba    

    private void montarFace(JFrame janela){
        
        this.face.setBackground(new java.awt.Color(34, 41, 50));
        this.face.setLayout(new BorderLayout());
        this.face.add(this.painelTab, BorderLayout.CENTER);

        janela.add(this.face, BorderLayout.CENTER);
        
    }

    //construção de cada aba----------------------------------------------------    
    private void montarInicio() {
        InicioDecorator inicio = new InicioDecorator();
        JPanel jPanelInicio = inicio.InicioReady();

        GroupLayout inicioLayout = new GroupLayout(this.inicio);
        this.inicio.setLayout(inicioLayout);
        
        
        inicioLayout.setHorizontalGroup(
            inicioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicio, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
        inicioLayout.setVerticalGroup(
            inicioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicio, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );        
//        this.painelTab.add("Inicio", this.inicio);
    }

    private void montarRecuperar() {
        RecuperarDecorator recuperar = new RecuperarDecorator(this.ctrlGeral);
        JPanel jPanelRecuperar = recuperar.RecuperarReady();

        GroupLayout recuperarLayout = new GroupLayout(this.recuperar);
        this.recuperar.setLayout(recuperarLayout);
        this.jScrollPaneRecuperar.setViewportView(jPanelRecuperar);
        
        recuperarLayout.setHorizontalGroup(
            recuperarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.jScrollPaneRecuperar, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
        recuperarLayout.setVerticalGroup(
            recuperarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.jScrollPaneRecuperar, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );        
//        this.painelTab.add("Recuperar", this.recuperar);        
    }
    
    private void montarImportar(){
        ImportarDecorator importar = new ImportarDecorator(this.ctrlGeral, this.painelTab);
        JPanel jPanelImportar = importar.ImportarReady();

        GroupLayout importarLayout = new GroupLayout(this.importar);
        this.importar.setLayout(importarLayout);
        
        
        importarLayout.setHorizontalGroup(
            importarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImportar, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
        importarLayout.setVerticalGroup(
            importarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImportar, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );        
//        this.painelTab.add("Importar", this.importar);
    }
    
    private void montarVisaoGeral(){
        VisaoGeralDecorator visaoGeral = new VisaoGeralDecorator(this.ctrlGeral);
        JPanel jPanelVisaoGeral = visaoGeral.visaoGeralReady();        

        GroupLayout visaoGeralLayout = new GroupLayout(this.visaoGeral);
        this.visaoGeral.setLayout(visaoGeralLayout);
        this.jScrollPaneVisaoGeral.setViewportView(this.visaoGeral);
        
        visaoGeralLayout.setHorizontalGroup(
            visaoGeralLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelVisaoGeral, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
        visaoGeralLayout.setVerticalGroup(
            visaoGeralLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelVisaoGeral, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );   

//        this.painelTab.add("Visão Geral", this.jScrollPaneVisaoGeral);
    }
    
    private void montarMetodologias(){ 
        MetodologiaDecorator metodologia = new MetodologiaDecorator(this.ctrlGeral);
        JPanel jPanelMetodologia = metodologia.metodologiaReady();
        
        GroupLayout metodologialLayout = new GroupLayout(this.metodologias);
        this.metodologias.setLayout(metodologialLayout);
        
        metodologialLayout.setHorizontalGroup(
            metodologialLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMetodologia, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
        metodologialLayout.setVerticalGroup(
            metodologialLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMetodologia, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        ); 

//        this.painelTab.add("Metodologias", this.metodologias);
    }
    
    private void montarVisaoAuxiliar(){
        
        VisaoAuxiliarDecorator visaoAuxiliar = new VisaoAuxiliarDecorator(this.ctrlGeral);
        JPanel jPanelVisaoAuxiliar = visaoAuxiliar.visaoAuxiliarReady();
        
        GroupLayout visaoAuxiliarLayout = new GroupLayout(this.visaoAuxiliar);
        this.visaoAuxiliar.setLayout(visaoAuxiliarLayout);
        this.jScrollPaneVisaoAuxiliar.setViewportView(this.visaoAuxiliar);
        
        visaoAuxiliarLayout.setHorizontalGroup(
            visaoAuxiliarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelVisaoAuxiliar, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
        visaoAuxiliarLayout.setVerticalGroup(
            visaoAuxiliarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelVisaoAuxiliar, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
//        this.painelTab.add("Visão Auxiliar", this.jScrollPaneVisaoAuxiliar);
    }
    
    private void montarResultados(){
        
        ResultadosDecorator resultados = new ResultadosDecorator(this.ctrlGeral);
        JPanel jPanelResultados = resultados.ResultadoReady();
        
        GroupLayout resultadoLayout = new GroupLayout(this.resultados);
        this.resultados.setLayout(resultadoLayout);
        this.jScrollPaneResultados.setViewportView(this.resultados);
        
        resultadoLayout.setHorizontalGroup(
            resultadoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelResultados, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
        resultadoLayout.setVerticalGroup(
            resultadoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelResultados, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              Short.MAX_VALUE)
        );
//        this.painelTab.add("Resultados", this.resultados);
    }

}
