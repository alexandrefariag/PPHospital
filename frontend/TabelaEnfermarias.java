/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;


import backend.Enfermaria;
import backend.ListaEnfermarias;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaEnfermarias extends AbstractTableModel{     //classe abstrata para tratar dos dados que aparecem nas tabelas
    private final ListaEnfermarias enfermarias;
    private static final String[] colunasTabela = new String[]{
        "Código", "Tipo", "Número de Camas"
    };
    
    public TabelaEnfermarias(ListaEnfermarias enfermarias){
        
    this.enfermarias=enfermarias;
        
    }
    
    @Override
    public int getRowCount() {               //recebe o número de enfermarias na lista
        return enfermarias.getTotalEnfermarias();
    }

    @Override
    public int getColumnCount() {           //recebe o número de colunas que vão existir
        return colunasTabela.length;
    }
    
    @Override
    public String getColumnName(int column) {       //retorna o nome da coluna pelo indice delas
        return colunasTabela[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {       //retorna o valor da lista na determinada
       Enfermaria p = enfermarias.get(rowIndex);                        //vê na lista enfermaria, na determinada linha e com isso vai
        switch (columnIndex) {                                      //vai atribuir o valor a uma certa coluna
            case 0:
                return p.getCodigoEnf();
            case 1:
                return p.getTipo();
            case 2:
                return p.getNumCamas();
            default:
                return null;
    }
    }
    
}
