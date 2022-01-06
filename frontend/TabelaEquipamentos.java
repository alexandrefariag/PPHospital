/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Equipamento;
import backend.ListaEquipamentos;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexandra Ferreira
 */
public class TabelaEquipamentos extends AbstractTableModel{
    private final ListaEquipamentos equipamentos;
    private static final String [] colunasTabela = new String []{
       "Código", "Tipo", "Enfermaria", "Estado", "Doente"
    };
    
    public TabelaEquipamentos (ListaEquipamentos equipamentos){
        this.equipamentos=equipamentos;
    }

    @Override
    public int getRowCount() {
       return equipamentos.getTotalEquipamentos(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return colunasTabela.length; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getColumnName (int column){
        return colunasTabela [column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Equipamento p = equipamentos.getEquip(rowIndex);
       switch (columnIndex){
           case 0:
           return p.getCodigoEquip();
           case 1:
           return p.getTipo();
           case 2:
               if(p.getEnfermaria() != null){
                    return p.getEnfermaria().getCodigoEnf()+", "+p.getEnfermaria().getTipo();
                }
                else {
                   return "";
                }

           case 3:
                return p.getEstadoEquip();
           case 4:
               if(p.getEstadoEquip().equals("Ocupado")){
                   return p.getDoente().getNome();
               }
           default:
               return null;//To change body of generated methods, choose Tools | Templates.
    }
    
}
}

