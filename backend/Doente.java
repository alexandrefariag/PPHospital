
package backend;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author nunom
 */
public class Doente  implements Serializable{

    /**
     *
     */
    private String codDoente;

    /**
     *
     */
    private String nome;

    /**
     *
     */
    private String localidade;

    /**
     *
     */
    private String gravidade; //(só pode ser entre moderado, grave ou muito grave, não sei se é declarado assim ou não)

    /**
     *
     */
    private LocalDate dataEntrada;

    /**
     *
     */
    private LocalDate dataSaida; 

    /**
     *
     */
    private Enfermaria enfermaria;

    /**
     *
     */
    private String nCama;
    
    /**
     *
     * @param codDoente
     * @param nome
     * @param localidade
     * @param dataEntrada
     */
    public Doente(String codDoente, String nome, String localidade, LocalDate dataEntrada) {
        this.codDoente = codDoente;
        this.nome = nome;
        this.localidade = localidade;
        this.gravidade = null;
        this.dataEntrada = dataEntrada;
        this.dataSaida = null;
        this.enfermaria = null;
        this.nCama = null;
    }

    /**
     *
     */
    public Doente(){}

    /**
     *
     * @return
     */
    public String getCodDoente() {
        return codDoente;
    }

    /**
     *
     * @param codDoente
     */
    public void setCodDoente(String codDoente) {
        this.codDoente = codDoente;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     *
     * @param localidade
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /**
     *
     * @return
     */
    public String getGravidade() {
        return gravidade;
    }

    /**
     *
     * @param gravidade
     */
    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    /**
     *
     * @return
     */
    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    /**
     *
     * @param dataEntrada
     */
    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     *
     * @return
     */
    public LocalDate getDataSaida() {
        return dataSaida;
    }

    /**
     *
     * @param dataSaida
     */
    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
    
    /**
     *
     * @return
     */
    public Enfermaria getEnfermaria() {
        return enfermaria;
    }

    /**
     *
     * @param enfermaria
     */
    public void setEnfermaria(Enfermaria enfermaria) {
        this.enfermaria = enfermaria;
    }
    
    /**
     *
     * @return
     */
    public String getnCama() {
        return nCama;
    }

    /**
     *
     * @param nCama
     */
    public void setnCama(String nCama) {
        this.nCama = nCama;
    }
    
   @Override
    public String toString() {
        return  "Nome: " + getNome() +
                ", Código: " + getCodDoente()+
                ", Localidade: " + getLocalidade()+
                ", Gravidade: " + getGravidade()+
                ", Data: " + getDataEntrada()+
                ", Enfermaria" + getEnfermaria()+
                ", Número da Cama:" + getnCama()+
                "; \n";
    }

   

    
    
}
