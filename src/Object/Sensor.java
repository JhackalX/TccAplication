/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

/**
 *
 * @author Aires
 */
public class Sensor {
    private String id;
    private String nome;
    private String unidadeMedida;
    private String TextoCarga;
    
    public Sensor() {
    }

    public Sensor(String id, String nome, String unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }

        public Sensor(String id, String nome, String unidadeMedida, String TextoCarga) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.TextoCarga = TextoCarga;
    }
    
    public String getTextoCarga() {
        return TextoCarga;
    }

    public void setTextoCarga(String TextoCarga) {
        this.TextoCarga = TextoCarga;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    
}
