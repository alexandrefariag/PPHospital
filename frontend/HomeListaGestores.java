/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.GestorHosp;
import backend.ListaUtilizadores;
import backend.Serializacao;
import backend.Sistema;
import backend.Utilizador;
import javax.swing.JOptionPane;

/**
 *
 * @author nunom
 */
public class HomeListaGestores extends javax.swing.JFrame {
private Sistema sistema;
private Serializacao bd;
private TabelaGestorHosp modeloTabelaGestorHosp;
    /**
     * Creates new form HomeListaGestores
     */
    public HomeListaGestores(Sistema sistema, Serializacao bd) {
        initComponents();
        this.sistema = sistema;
        this.bd=bd;
        System.out.println(sistema.getListaUtilizadores().getListaUtilizadores());
        ListaUtilizadores gestor = new ListaUtilizadores();
        for(int i=0; i<sistema.getListaUtilizadores().getTotalUtilizadores();i++){
            if(sistema.getListaUtilizadores().get(i).getClass().getName().equals("backend.GestorHosp")){
                Utilizador e = sistema.getListaUtilizadores().get(i);
                gestor.adicionar(e);
            }
        }
        modeloTabelaGestorHosp = new TabelaGestorHosp(gestor);
        jTable1.setModel(modeloTabelaGestorHosp);
    }

    public GestorHosp getGestorSelecionado() {
        int utilizadorSelecionado = jTable1.getSelectedRow();
        ListaUtilizadores gestor = new ListaUtilizadores();
        for(int i=0; i<sistema.getListaUtilizadores().getTotalUtilizadores();i++){
            if(sistema.getListaUtilizadores().get(i).getClass().getName().equals("backend.GestorHosp")){
                Utilizador e = sistema.getListaUtilizadores().get(i);
                gestor.adicionar(e);
            }
        }
        if (utilizadorSelecionado > -1) {
            return gestor.getG(utilizadorSelecionado);
        }

        return null;
    }

    public void editarDados() {
        GestorHosp selecionado = getGestorSelecionado();
        if (selecionado != null) {
            dispose();
            EditGestorHosp j = new EditGestorHosp(sistema, selecionado, bd, new javax.swing.JFrame(), true);
            j.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Gestor de Hospital!");
        }
    }
    
    private void guardarAlteracoes() {
        bd.guardar(sistema);
    }
    
    private void retirarCargo(){
        GestorHosp selecionado = getGestorSelecionado();
        if (selecionado != null) {
            selecionado.setHospital(null);
            JOptionPane.showMessageDialog(this, "Gestor retirado do cargo!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Gestor de Hospital!");
        }
        ListaUtilizadores gestor = new ListaUtilizadores();
        for(int i=0; i<sistema.getListaUtilizadores().getTotalUtilizadores();i++){
            if(sistema.getListaUtilizadores().get(i).getClass().getName().equals("backend.GestorHosp")){
                Utilizador e = sistema.getListaUtilizadores().get(i);
                gestor.adicionar(e);
            }
        }
        modeloTabelaGestorHosp = new TabelaGestorHosp(gestor);
        jTable1.setModel(modeloTabelaGestorHosp);
        
        
    }
    
    private void removerGestor(){
        int col = 0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Gestor de Hospital!");
            return;
        }
        
        String codigo = (String) jTable1.getValueAt(row, col);
        
        sistema.getListaUtilizadores().remover(sistema.getListaUtilizadores().getGestorHosp(codigo));
        JOptionPane.showMessageDialog(this,"Gestor de Hospital removido com sucesso!");
        ListaUtilizadores gestor = new ListaUtilizadores();
        for(int i=0; i<sistema.getListaUtilizadores().getTotalUtilizadores();i++){
            if(sistema.getListaUtilizadores().get(i).getClass().getName().equals("backend.GestorHosp")){
                Utilizador e = sistema.getListaUtilizadores().get(i);
                gestor.adicionar(e);
            }
        }
        modeloTabelaGestorHosp = new TabelaGestorHosp(gestor);
        jTable1.setModel(modeloTabelaGestorHosp);
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("LISTA DE HOSPITAIS");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-logout-arredondado-acima-52.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-editar-usu??rio-masculino-52.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-hospital-2-40.png"))); // NOI18N
        jLabel4.setText(" Voltar");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 685, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C??digo", "Nome", "Localiza????o"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("LISTA DE GESTORES");

        jButton7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton7.setText("Adicionar ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton8.setText("Editar ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton10.setText("Eliminar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton11.setText("Retirar Cargo");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11)))
                .addGap(59, 59, 59))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente terminar a sess??o?", "A terminar...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            guardarAlteracoes();
            dispose();
            Login p = new Login(sistema, bd);
            p.setVisible(true);}
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        HomePerfilGestor p = new HomePerfilGestor(sistema, bd);
        p.setVisible(true);
        dispose();

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        AddGestorHospital p = new AddGestorHospital(sistema, bd, new javax.swing.JFrame(), true);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        editarDados();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        removerGestor();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dispose();
        HomeGestorGeral p = new HomeGestorGeral(sistema, bd);
        p.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        retirarCargo();
    }//GEN-LAST:event_jButton11ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
