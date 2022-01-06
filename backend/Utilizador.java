
package backend;

import java.io.Serializable;

/**
 *
 * @author nunom
 */
public class Utilizador  implements Serializable{
    
    private String codigoU;
    private String nome;
    private String password;
    
    public Utilizador(){
    }
    
    public Utilizador(String codigoU, String nome, String password){
        this.codigoU=codigoU;
        this.nome=nome;
        this.password=password;
    }

    public String getCodigoU() {
        return codigoU;
    }

    public void setCodigoU(String codigoU) {
        this.codigoU = codigoU;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return  "Nome: " + getNome() +
                ", Código: " + getCodigoU() +
                ";\n";
    }
    
    
    
}
