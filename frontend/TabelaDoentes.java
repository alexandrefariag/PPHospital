/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Doente;
import backend.ListaDoentes;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaDoentes extends AbstractTableModel{     //classe abstrata para tratar dos dados que aparecem nas tabelas
    private final ListaDoentes doentes;
    private static final String[] colunasTabela = new String[]{
        "Código", "Nome", "Enfermaria", "Gravidade", "Data de Entrada", "Data de Saída", "Cama"
    };

    public TabelaDoentes(ListaDoentes doentes)
    {
    this.doentes=doentes;
    }
    
    @Override
    public int getRowCount() {               //recebe o número de doentes na lista
        return doentes.getTotalDoentes();
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
       Doente p = doentes.getD(rowIndex);                        //vê na lista doentes, na determinada linha e com isso vai
        switch (columnIndex) {                                      //vai atribuir o valor a uma certa coluna
            case 0:
                return p.getCodDoente();
            case 1:
                return p.getNome();
            case 2:
                if(p.getEnfermaria() != null){
                    return p.getEnfermaria().getCodigoEnf();
                }
                else {
                   return "";
                }
            case 3:
                return p.getGravidade();
            case 4:
                return p.getDataEntrada();
            case 5:
                return p.getDataSaida();
            case 6:
                return p.getnCama();
            default:
                return null;
    }
    }
}
