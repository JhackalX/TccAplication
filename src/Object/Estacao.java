/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

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
    private Float longitude;
    private Float latitude;
    private Float altitude;
    

    public Estacao() {
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
        System.out.println(this.id);
    }
    
    public void setID (){
        this.id =  UUID.nameUUIDFromBytes((this.nome+this.codigo).getBytes()).toString();
        System.out.println(this.id);
    }
    
    public String getId(){
        return this.id;
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
    
}
