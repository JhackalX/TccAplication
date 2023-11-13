/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.io.File;

/**
 *
 * @author Aires
 */
public final class Configuracoes {
    private Float versaoSistema;
    private String caminhoSistema;
    private String caminhoBaseEstacoes;
    private String camihoUltimoArquivoCarga;
    private boolean IS_WINDOWS;
    private boolean IS_LINUX;

    public Configuracoes() {        
        this.versaoSistema = Float.valueOf("0.01");
        this.IS_WINDOWS = false;
        this.IS_LINUX = false;
        this.loadOS();
        this.inicializaAmbiente();
        this.inicializaSistema();
    }
    
    public void verificarDiretorio(String Diretorio) {
        File dir;
        
        dir = new File(Diretorio);
        
        if (dir.exists() & dir.isDirectory()) {
            System.out.println("o diretorio " + Diretorio + " ja existe.");
        } else {
            System.out.println("O diretorio " + Diretorio + " não existe, criando agora...");
            dir.mkdir();
        }
    }
    
    public void verificarArquivoBase(String baseDados) {
        File base;
        
        base = new File(baseDados);
        
        if (base.exists() & !base.isDirectory()) {
            System.out.println("o arquivo " + baseDados + " ja existe.");
        } else {
            System.out.println("O arquivo " + baseDados + " nao existe, criando agora...");
            DAO.CtrlDao.inicializarBaseDados(baseDados);
        }
    }
    
    public void inicializaSistema() {
        System.out.println("Verificando pasta raiz do sistema..." + this.getCaminhoSistema());
        
        this.verificarDiretorio(this.getCaminhoSistema());
        
        System.out.println("Verificando arquivo de estacoes do sistema..." + this.getCaminhoBasesEstacoes());
        
        this.verificarArquivoBase(this.getCaminhoBasesEstacoes());
        
//        System.out.println("Verificando pasta de estudos do sistema...");
//        this.verificarDiretorio(this.getCaminhoBasesEstudos());
    }

    public String getCaminhoSistema() {
        return caminhoSistema;
    }

    public void setCaminhoSistema(String caminhoSistema) {
        this.caminhoSistema = caminhoSistema;
    }

    public String getCaminhoBasesEstacoes() {
        return caminhoBaseEstacoes;
    }

    public void setCaminhoBasesEstacoes(String caminhoBasesEstacoes) {
        this.caminhoBaseEstacoes = caminhoBasesEstacoes;
    }

    public String getCamihoUltimoArquivoCarga() {
        return camihoUltimoArquivoCarga;
    }

    public void setCamihoUltimoArquivoCarga(String camihoUltimoArquivoCarga) {
        this.camihoUltimoArquivoCarga = camihoUltimoArquivoCarga;
    }
    
    private void loadOS(){
        String os;
        
        os = System.getProperty("os.name");
        System.out.println("OS: " + os);
        if (os.indexOf("Win") >= 0) {
            this.IS_WINDOWS = true;
        } else if (os.indexOf("Linux") >= 0){
           this.IS_LINUX = true;
        }
    }
    
    private void inicializaAmbiente(){
        if (this.IS_WINDOWS) {
            String raiz = System.getenv("APPDATA");    
            this.caminhoSistema = new String(raiz + "\\TCC");
            this.caminhoBaseEstacoes = new String(raiz + "\\TCC\\Estacoes.db");
        } else if (this.IS_LINUX){
            String raiz = System.getenv("HOME");
            System.out.println(raiz);
            this.caminhoSistema = new String(raiz + "/TCC");
            this.caminhoBaseEstacoes = new String(raiz + "/TCC/Estacoes.db");          
        } 
    }
    
}
