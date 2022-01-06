/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Utilizador;
import backend.ListaUtilizadores;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaUtilizadores extends AbstractTableModel{      //classe abstrata para tratar dos dados que aparecem nas tabelas
    
    private final ListaUtilizadores utilizadores;
    private static final String[] colunasTabela = new String[]{
        "Código", "Nome", "Cargo"
    };

    public TabelaUtilizadores(ListaUtilizadores utilizadores)
    {
    this.utilizadores=utilizadores;
    }
    
    
    @Override
    public int getRowCount() {          //recebe o número de utilizadores na lista
       int total=0;
       for(int i=0; i<utilizadores.getTotalUtilizadores();i++)
        if(utilizadores.get(i).getClass().getName().equals("backend.Utilizador")){
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
        
        if(p.getClass().getName().equals("backend.Utilizador")){
        
        switch (columnIndex) {                                  //vai atribuir o valor a uma certa coluna
            case 0:
                return p.getCodigoU();
            case 1:
                return p.getNome();
                
            case 2:
                if(p.getCodigoU().equals("A001")){
                    return "Administrador";
                }
                
                else {
                    return "Gestor";
                }
               
            default:
                return null;
            }
        }
        return null;
    
    
}
}