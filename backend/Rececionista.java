/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.Serializable;

/**
 *
 * @author Alexandre
 */
public class Rececionista extends Utilizador implements Serializable {
    
    private Hospital hospital;
   
    public Rececionista() {}
    
    public Rececionista(String codigoU, String nome, String password, Hospital hospital) {
        super(codigoU, nome, password);
        this.hospital=hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Rececionista{" + "hospital=" + hospital + '}';
    }    
}
