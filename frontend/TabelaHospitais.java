/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Hospital;
import backend.ListaHospitais;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaHospitais extends AbstractTableModel{     //classe abstrata para tratar dos dados que aparecem nas tabelas
    private final ListaHospitais hospitais;
    private static final String[] colunasTabela = new String[]{
        "Código", "Nome", "Localização"
    };

    public TabelaHospitais(ListaHospitais hospitais)
    {
    this.hospitais=hospitais;
    }
    
    @Override
    public int getRowCount() {               //recebe o número de hospitais na lista
        return hospitais.getTotalHospitais();
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
       Hospital p = hospitais.get(rowIndex);                        //vê na lista hospitais, na determinada linha e com isso vai
        switch (columnIndex) {                                      //vai atribuir o valor a uma certa coluna
            case 0:
                return p.getCodigoH();
            case 1:
                return p.getNome();
            case 2:
                return p.getLocalidade();
            default:
                return null;
    }
    }
}
