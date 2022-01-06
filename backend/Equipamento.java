
package backend;

import java.io.Serializable;

/**
 *
 * @author nunom
 */
public class Equipamento  implements Serializable{
    
    private String codigoEquip;
    private String tipo;
    private String estado;
    private Enfermaria enfermaria;
    private Doente doente;
    
    public Equipamento(){}
    
    public Equipamento(String codigoEquip, String tipo, String estado, Enfermaria enfermaria){
    this.codigoEquip=codigoEquip;
    this.tipo=tipo;
    this.estado=estado;
    this.enfermaria=enfermaria;
    this.doente=null;
    }

    public String getCodigoEquip() {
        return codigoEquip;
    }

    public void setCodigoEquip(String codigoEquip) {
        this.codigoEquip = codigoEquip;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstadoEquip() {
        return estado;
    }

    public void setEstadoEquip(String estado) {
        this.estado = estado;
    }

    public Enfermaria getEnfermaria() {
        return enfermaria;
    }

    public void setEnfermaria(Enfermaria enfermaria) {
        this.enfermaria = enfermaria;
    }
    
    public Doente getDoente() {
        return doente;
    }

    public void setDoente(Doente doente) {
        this.doente = doente;
    }
    
    @Override
    public String toString() {
        return  
                ", Código: " + getCodigoEquip() +
                ", Tipo: " + getTipo()+
                ", Estado: " + getEstadoEquip()+
                ", Enfermaria " + getEnfermaria()+
                ", Paciente: " + getDoente()+
                ";\n";
    }

    
    
}
