
package backend;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author nunom
 */
public class ListaUtilizadores  implements Serializable{
    private ArrayList<Utilizador> lista;

    public ListaUtilizadores(ArrayList<Utilizador> lista) {
        this.lista = lista;
    }

    public ListaUtilizadores() {
        lista = new ArrayList<> ();
    }

    public Utilizador get(int index) {          //é para criar a tabela no frontend
        return lista.get(index);
    }
    
    public ArrayList<Utilizador> getListaUtilizadores() {
        return lista;
    }

    public void setListaUtilizadores(ArrayList<Utilizador> lista) {
        this.lista = lista;
    }
    
 
    
    public void adicionar(Utilizador utilizador) {          //vai adicionar uma linha à lista com as variaveis daa classe Utilizador
        lista.add(utilizador);
    }
    
    public void remover(Utilizador utilizador) {            //vai remover a linha da lista
        lista.remove(utilizador);
    }
    
    public int getTotalUtilizadores() {                     //vai retornar o tamanho da lista
        return lista.size();
    }
    
    public boolean existeUtilizador(String codigoU) {       //recebe o codigo do front e vai verificar se ele existe na lista
        for (Utilizador u : lista) {
            if (u != null && u.getCodigoU().equals(codigoU)) {
                return true;
            }
        }
        return false;
    }
    
    
    public Utilizador getUtilizador(String codigoU) {       //vai retornar o utilizador caso ele exista na lista
        for (Utilizador u : lista) {
            if (u != null && u.getCodigoU().equals(codigoU)) {
                return u;
            }
        }       
        return null;
    }
    
    public Medico getMedico(String codigoU) {       //é para quando for editar o médico, conseguir editar os dados de um certo
        for (Utilizador m : lista) {
            if (m != null && m.getCodigoU().equals(codigoU)) {
                return (Medico) m;
            }
        }       
        return null;
    }
        
    public Enfermeiro getEnfermeiro(String codigoU) {       //é para quando for editar o enfermeiro, conseguir editar os dados de um certo
        for (Utilizador e : lista) {
            if (e != null && e.getCodigoU().equals(codigoU)) {
                return (Enfermeiro) e;
            }
        }       
        return null;
    }
    
    public Rececionista getRececionista(String codigoU) {  //é para quando for editar o rececionista, conseguir editar os dados de um certo
        for (Utilizador e : lista) {
            if (e != null && e.getCodigoU().equals(codigoU)) {
                return (Rececionista) e;
            }
        }       
        return null;
    }
    
    public GestorHosp getGestorHosp(String codigoU) {
        for (Utilizador e : lista) {
            if (e != null && e.getCodigoU().equals(codigoU)) {
                return (GestorHosp) e;
            }
        }       
        return null;
    }
    
     public Medico getM(int index) {          
        return (Medico) lista.get(index);
    }
     
    public Enfermeiro getE(int index) {
        return (Enfermeiro) lista.get(index);
    }
     
    public GestorHosp getG(int index) {
        return (GestorHosp) lista.get(index);
    }
    
    public Rececionista getR(int index) {
        return (Rececionista) lista.get(index);
    }
}
