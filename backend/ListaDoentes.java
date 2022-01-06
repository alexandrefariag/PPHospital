
package backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nunom
 */
public class ListaDoentes implements Serializable{

    private ArrayList<Doente> lista;
    
    public ListaDoentes() {
    lista = new ArrayList<> ();
    }
    
    public ListaDoentes(ArrayList<Doente> lista) {
        this.lista = lista;
    }
    
    public ArrayList<Doente> getListaDoentes() {
        return lista;
    }
    
    public void adicionar(Doente doente){
    lista.add(doente);
    }
    
    public void remover(Doente doente){
    lista.remove(doente);
    }
    
    public int getTotalDoentes() {
        return lista.size();
    }
    
    public boolean existeDoente(String Nome, Doente d) {
        for (Doente a : lista) {
            if (a != null && a.getNome().equals(Nome) && a.equals(d)) {
                return true;
            }
        }
        return false;
    }
    
    public Doente getDoente(String codDoente) {
        for (Doente a : lista) {
            if (a != null && a.getCodDoente().equals(codDoente)) {
                return a;
            }
        }       
        return null;
    }
    
     public Doente getD(int i){                //vai retornar os doentes, dependendo do índice, para no frontend carregar
        if (i<0 || i>getTotalDoentes()){        //as opções na combobox quando for a criar um equipamento
        return null;
        }
        return lista.get(i);
    }
}
