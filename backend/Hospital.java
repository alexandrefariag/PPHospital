
package backend;

import java.io.Serializable;

/**
 *
 * @author nunom
 */
public class Hospital implements Serializable {
    
    private String codigoH;
    private String nome;
    private String localidade;
    private ListaEnfermarias enfermarias;
    private ListaEquipamentos equipamentos;
    private ListaDoentes doentes;
    private ListaUtilizadores rececionistas;
    
    public Hospital(){
        
        
    }
    
   
    
    public Hospital(String codigoH, String nome, String localidade){
    
        this.codigoH=codigoH;
        this.nome=nome;
        this.localidade=localidade;
        enfermarias = new ListaEnfermarias();
        doentes = new ListaDoentes();
        equipamentos = new ListaEquipamentos();
        rececionistas = new ListaUtilizadores();
    
    }

    public String getCodigoH() {
        return codigoH;
    }

    public void setCodigoH(String codigoH) {
        this.codigoH = codigoH;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public ListaEnfermarias getEnfermarias() {
        return enfermarias;
    }

    public void setEnfermarias(ListaEnfermarias enfermarias) {
        this.enfermarias = enfermarias;
    }

    public ListaDoentes getDoentes() {
        return doentes;
    }

    public void setDoentes(ListaDoentes doentes) {
        this.doentes = doentes;
    }
    
    public ListaEquipamentos getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(ListaEquipamentos equipamentos) {
        this.equipamentos = equipamentos;
    }
    
       public ListaUtilizadores getRececionistas() {
        return rececionistas;
    }

    public void setRececionistas(ListaUtilizadores rececionistas) {
        this.rececionistas = rececionistas;
    }
    
    
    @Override
    public String toString() {
        return  "Nome: " + getNome() +
                ", Código: " + getCodigoH()+
                ", Localidade: " + getLocalidade()+
                ";\n";
    }
    
}
