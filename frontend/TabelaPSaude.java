/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Enfermeiro;
import backend.ListaUtilizadores;
import backend.Medico;
import backend.Utilizador;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nunom
 */
public class TabelaPSaude extends AbstractTableModel{     //classe abstrata para tratar dos dados que aparecem nas tabelas
    private final ListaUtilizadores pSaude;
    private static final String[] colunasTabela = new String[]{
        "Código", "Nome", "Especialidade", "Doentes"
    };

    public TabelaPSaude(ListaUtilizadores pSaude)
    {
    this.pSaude=pSaude;
    }
    
    @Override
    public int getRowCount() {               //recebe o número de hospitais na lista
        return pSaude.getTotalUtilizadores();
       
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
       Utilizador p = pSaude.get(rowIndex);
       for(int i = 0; i<pSaude.getTotalUtilizadores(); i++){
            if(p.getClass().getName().equals("backend.Medico")){
            Medico m = (Medico) pSaude.get(rowIndex);
             switch (columnIndex) {                                  //vai atribuir o valor a uma certa coluna
                case 0:
                return m.getCodigoU();
                case 1:
                    return m.getNome();
                case 2:
                    return m.getEspecialidade();
                case 3:
                    return m.getDoentes().getTotalDoentes();
                }
            }
       }
       for(int i = 0; i<pSaude.getTotalUtilizadores(); i++){
            if(p.getClass().getName().equals("backend.Enfermeiro")){
  
            Enfermeiro e = (Enfermeiro) pSaude.get(rowIndex);
            
             switch (columnIndex) {                                  //vai atribuir o valor a uma certa coluna
                case 0:
                return e.getCodigoU();
                case 1:
                    return e.getNome();
            }
        }  
    }
        return null;
    }

    
}
