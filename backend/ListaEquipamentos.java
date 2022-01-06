
package backend;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author nunom
 */
public class ListaEquipamentos  implements Serializable{
    
    private ArrayList<Equipamento> lista;
    
    public ListaEquipamentos (){
        lista = new ArrayList<> ();
    }
    
    public ArrayList<Equipamento> getListaEquipamentos (){
        return lista;
    }
    
    public void setListaEquipamentos(ArrayList<Equipamento>lista){
        this.lista = lista;
    }
    
    public void adicionar (Equipamento equipamento){
        lista.add(equipamento);
    }
    
    public void remover (Equipamento equipamento){
        lista.remove (equipamento);
    }
    
    
    public int getTotalEquipamentos (){
        return lista.size();
    }
    
    public Equipamento getEquip (int i){
        if (i<0|| i> getTotalEquipamentos()){
            return null;
        }
        return lista.get(i);
    }
    
    public Equipamento getEquipamento (String codEquip){
        for (Equipamento e : lista){
            if(e != null && e.getCodigoEquip ().equals(codEquip)){
                return e;
            }
        }
        
        return null;
    }
    
   
    
}
