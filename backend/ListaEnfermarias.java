
package backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nunom
 */
public class ListaEnfermarias  implements Serializable{
   
    private ArrayList<Enfermaria> lista;
    
    public ListaEnfermarias(){
    lista = new ArrayList<> ();
    }

    public ArrayList<Enfermaria> getListaEnfermarias() {
        return lista;
    }

    public void setListaEnfermarias(ArrayList<Enfermaria> lista) {
        this.lista = lista;
    }
    
    public void adicionar(Enfermaria enfermaria) {
        lista.add(enfermaria);
    }
    
    public void remover(Enfermaria enfermaria) {
        lista.remove(enfermaria);
    }
    
    
    public int getTotalEnfermarias(){           //retorna o tamanho da lista, para depois construir uma tabela
        return lista.size();
    }
    
    public Enfermaria getEnf(int i){                //vai retornar as enfermarias, dependendo do índice, para no frontend carregar
        if (i<0 || i>getTotalEnfermarias()){        //as opções na combobox quando for a criar um utilizador
        return null;
        }
        return lista.get(i);
    }
    
    public Enfermaria get(int index) {          //é para criar a tabela no frontend
        return lista.get(index);
    }
    
    public Enfermaria getEnfermaria(String codEnf) {       //vai retornar o objeto enfermaria através do código que é enviado do frontend para adicionar médico ou enfermeiro
        for (Enfermaria e : lista) {
            if (e != null && e.getCodigoEnf().equals(codEnf)) {
                return e;
            }
        }       
        return null;
    }
}
