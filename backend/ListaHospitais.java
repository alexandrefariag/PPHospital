
package backend;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author nunom
 */
public class ListaHospitais  implements Serializable{
    private ArrayList<Hospital> lista;
    
    public ListaHospitais() {
        lista = new ArrayList<> ();
    }
    
    public ListaHospitais(int limite) {
        lista = new ArrayList<> (limite);
    }

    public Hospital get(int index) {
        return lista.get(index);
    }

    public ArrayList<Hospital> getListaHospitais() {
        return lista;
    }

    public void setListaHospitais(ArrayList<Hospital> lista) {
        this.lista = lista;
    }
    
    public void adicionar(Hospital hospital) {
        lista.add(hospital);
    }
    
    public void remover(Hospital hospital) {
        lista.remove(hospital);
    }
    
    public int getTotalHospitais() {
        return lista.size();
    }
    
    public Hospital getHospital(String cod){
    
        for(Hospital h: lista){
            if(h != null && h.getCodigoH().equals(cod)){
            return h;
            
            }
        
        }
        return null;
    
    }
    
     public Hospital getHosp(int i){                //vai retornar as enfermarias, dependendo do índice, para no frontend carregar
        if (i<0 || i>getTotalHospitais()){        //as opções na combobox quando for a criar um utilizador
        return null;
        }
        return lista.get(i);
    }
    
    
}
