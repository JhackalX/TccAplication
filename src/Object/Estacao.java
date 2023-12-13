/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aires
 */
public class Estacao {
    private String id;
    private String nome;
    private String codigo;
    private String situacao;
    private String periodicidade;
    
    private Float longitude;
    private Float latitude;
    private Float altitude;
    
    private Date dataFundacao;
    private Date dataCadastro;
    

    public Estacao() {
        this.altitude =null;
        this.codigo = null;
        this.dataFundacao = null;
        this.id = null;
        this.latitude = null;
        this.longitude = null;
        this.nome = null;
        this.situacao = null;
        this.dataCadastro = null;
        this.periodicidade = null;
    }
    
    public Estacao(String nome, String codigo, Float latitude, Float altitude) {
        this.nome = nome;
        this.codigo = codigo;
        this.latitude = latitude;
        this.altitude = altitude;
        this.id = UUID.nameUUIDFromBytes((codigo).getBytes()).toString();
        System.out.println(this.id);
    }
    
    public void setID (String codigo){
        this.id = UUID.nameUUIDFromBytes((codigo).getBytes()).toString();
        //System.out.println(this.id);
    }
    
    public void setIDExistente (String codigo){
        this.id = codigo;
        //System.out.println(this.id);
    }
    
    public void setID (){
        this.id =  UUID.nameUUIDFromBytes((this.codigo).getBytes()).toString();
        System.out.println(this.id);
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getAltitude() {
        return altitude;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    public Date getDataFundacao() {
        return dataFundacao;
    }
    
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    
    public void setDataFundacaoBR(String dataFundacao){
        try {   
            DateFormat dateFormat;
            if (dataFundacao.split("/")[2].length() == 2) {
                dateFormat =  new SimpleDateFormat("dd/MM/yy");

            }else {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            }

            this.dataFundacao =  dateFormat.parse(dataFundacao);
        } catch (ParseException ex) {
            System.out.println("Erro ao formatar data de fundacao na estacao " + this.codigo +". Menssagem: " + ex.getMessage());
        }
    }
    
    public String getDataFundacaoBR(){
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (this.dataFundacao!=null){
            return dateFormat.format(this.dataFundacao);
        } else {
            return null;
        }
    }
    public void setDataCadastroBR(String dataCadastro){
        try {   
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            

            this.dataFundacao =  dateFormat.parse(dataCadastro);
        } catch (ParseException ex) {
            System.out.println("Erro ao formatar data de cadastro na estacao " + this.codigo +". Menssagem: " + ex.getMessage());
        }
    }
    
    public String getDataCadastroBR(){
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (this.dataCadastro!=null){
            return dateFormat.format(this.dataCadastro);
        } else {
            return null;
        }
    }
    
}
