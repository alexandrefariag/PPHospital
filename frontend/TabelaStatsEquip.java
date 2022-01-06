/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Equipamento;
import backend.ListaEquipamentos;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaStatsEquip extends AbstractTableModel{     //classe abstrata para tratar dos dados que aparecem nas tabelas
    
    private final ArrayList<String> equipamentos;
    private final int[] numeros;
    private final int[] total;
    private static final String[] colunasTabela = new String[]{
        "Tipo", "Número", "Percentagem"
    };

    public TabelaStatsEquip(ArrayList<String> equipamentos, int[] numeros, int[] total )
    {
    this.equipamentos=equipamentos;
    this.numeros=numeros;
    this.total=total;
    }
    
    @Override
    public int getRowCount() {               //recebe o número de doentes na lista
        return equipamentos.size();
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
        
       switch (columnIndex) {                                      //vai atribuir o valor a uma certa coluna
            case 0:
                 return equipamentos.get(rowIndex);
            case 1:
                 return numeros[rowIndex];  
            case 2:
                double a = numeros[rowIndex]*100/total[rowIndex];
                return a +"%";
            default:
                return null;
    }
    }

}
