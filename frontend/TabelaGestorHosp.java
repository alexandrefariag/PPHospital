/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.GestorHosp;
import backend.ListaUtilizadores;
import backend.Utilizador;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaGestorHosp  extends AbstractTableModel {
        
    private final ListaUtilizadores utilizadores;
    private static final String[] colunasTabela = new String[]{
        "Código", "Nome", "Hospital"
    };

    public TabelaGestorHosp(ListaUtilizadores utilizadores)
    {
    this.utilizadores=utilizadores;
    }
    
    
    @Override
    public int getRowCount() {          //recebe o número de utilizadores na lista
       return utilizadores.getTotalUtilizadores();
    }
        

    @Override
    public int getColumnCount() {   //recebe o número de colunas que vão existir
       return colunasTabela.length;
    }
    
    @Override
    public String getColumnName(int column) {   //retorna o nome da coluna pelo indice delas
        return colunasTabela[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {   //retorna o valor da lista na determinada
        
        Utilizador p = utilizadores.get(rowIndex);
        for(int i = 0; i<utilizadores.getTotalUtilizadores(); i++){
            if(p.getClass().getName().equals("backend.GestorHosp")){
            GestorHosp m = (GestorHosp) utilizadores.get(rowIndex);
             switch (columnIndex) {                                  //vai atribuir o valor a uma certa coluna
                case 0:
                return m.getCodigoU();
                case 1:
                    return m.getNome();
                case 2:
                    if(m.getHospital() != null){
                        return m.getHospital().getNome();
                    }
                default:
                    return null;
                }
            }
       }
        return null;
    
    
}
}
