
package backend;

import java.io.Serializable;

/**
 *
 * @author nunom
 */
public class Enfermeiro extends Utilizador implements Serializable {
    
    private Enfermaria enfermaria;
    
    public Enfermeiro(){
    }
    
    public Enfermeiro(String codigoU, String nome, String password, Enfermaria enfermaria){
    super(codigoU, nome, password);
    this.enfermaria = enfermaria;
    }

    public Enfermaria getEnfermaria() {
        return enfermaria;
    }

    public void setEnfermaria(Enfermaria enfermaria) {
        this.enfermaria = enfermaria;
    }
    
     @Override
    public String toString() {
        return  "Nome: " + getNome() +
                ", Código: " + getCodigoU() +
                ", Enfermaria: " + getEnfermaria() +
                ";\n";
    }
    
}
