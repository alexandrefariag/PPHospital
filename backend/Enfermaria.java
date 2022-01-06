
package backend;

import java.io.Serializable;

/**
 *
 * @author nunom
 */
public class Enfermaria implements Serializable {
    
    private String codigoEnf;
    private String tipo;
    private int numCamas;
    private Hospital hospital;
    private ListaEquipamentos equipamentos;
    private ListaUtilizadores psaude;

    public Enfermaria(String codigoEnf,String tipo, int numCamas, Hospital hospital){
        this.codigoEnf = codigoEnf;
        this.tipo = tipo;
        this.numCamas = numCamas;
        this.hospital=hospital;
        equipamentos = new ListaEquipamentos();
        psaude = new ListaUtilizadores();
    }
    
    public String getCodigoEnf() {
        return codigoEnf;
    }

    public void setCodigoEnf(String codigoEnf) {
        this.codigoEnf = codigoEnf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
    
    public ListaEquipamentos getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(ListaEquipamentos equipamentos) {
        this.equipamentos = equipamentos;
    }
    
      public ListaUtilizadores getPsaude() {
        return psaude;
    }

    public void setPsaude(ListaUtilizadores psaude) {
        this.psaude = psaude;
    }
    
    @Override
    public String toString() {
        return  
                ", Código: " + getCodigoEnf()+
                ", Tipo: " + getTipo()+
                ", Numero de camas: " + getNumCamas()+
                ", Hospital: " + getHospital()+
                "; \n";
    }

    
}
