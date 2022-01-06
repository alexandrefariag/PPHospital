
package backend;

import java.io.Serializable;

/**
 *
 * @author nunom
 */
public class Medico extends Utilizador implements Serializable{
    
    private Enfermaria enfermaria;
    private String especialidade;
    private ListaDoentes doentes;
    
    public Medico(){}
    
    public Medico(String codigoU, String nome, String password, String especialidade, Enfermaria enfermaria ){
        super(codigoU, nome, password);
        this.especialidade=especialidade;
        this.enfermaria=enfermaria;
        doentes = new ListaDoentes();
    }

    public Enfermaria getEnfermaria() {
        return enfermaria;
    }

    public void setEnfermaria(Enfermaria enfermaria) {
        this.enfermaria = enfermaria;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public ListaDoentes getDoentes() {
        return doentes;
    }

    public void setDoentes(ListaDoentes doentes) {
        this.doentes = doentes;
    }
    
     @Override
    public String toString() {
        return  "Código: " + getCodigoU() +
                ", Nome: " + getNome() +
                ", Enfermaria: " + getEnfermaria() +
                ", Especialidade: " + getEspecialidade() +
                ", Doentes: " + getDoentes()+
                ";\n";
    }
    
    
}
