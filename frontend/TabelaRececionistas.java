/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.ListaUtilizadores;
import backend.Utilizador;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaRececionistas extends AbstractTableModel{
        
    private final ListaUtilizadores utilizadores;
    private static final String[] colunasTabela = new String[]{
        "Código", "Nome"
    };

    public TabelaRececionistas(ListaUtilizadores utilizadores)
    {
    this.utilizadores=utilizadores;
    }
    
    
    @Override
    public int getRowCount() {          //recebe o número de utilizadores na lista
       int total=0;
       for(int i=0; i<utilizadores.getTotalUtilizadores();i++)
        if(utilizadores.get(i).getClass().getName().equals("backend.Rececionista")){
           total++;
       }
        return total;
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
        Utilizador p = utilizadores.get(rowIndex);              //vê na lista utilizadores, na determinada linha e com isso vai 
        
        if(p.getClass().getName().equals("backend.Rececionista")){
        
        switch (columnIndex) {                                  //vai atribuir o valor a uma certa coluna
            case 0:
                return p.getCodigoU();
            case 1:
                return p.getNome();
               
            default:
                return null;
            }
        }
        return null;
    
    
}
}
