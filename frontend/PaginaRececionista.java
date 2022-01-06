/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Doente;
import backend.Hospital;
import backend.Rececionista;
import backend.Serializacao;
import backend.Sistema;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandra Ferreira
 */
public class PaginaRececionista extends javax.swing.JFrame {
private Sistema sistema;
private Serializacao bd;
private Hospital hospital;
private TabelaDoentes modeloTabelaDoentes;
private TabelaFiltrada modeloTabelaFiltrada;
    /**
     * Creates new form PaginaRececionista
     */
    public PaginaRececionista(Sistema sistema, Serializacao bd) {
        initComponents();
        this.sistema=sistema;
        this.bd=bd;
        hospital=sistema.getListaUtilizadores().getRececionista(sistema.getUtilizadorLigado().getCodigoU()).getHospital();
        modeloTabelaDoentes = new TabelaDoentes(hospital.getDoentes());
        jTable1.setModel(modeloTabelaDoentes);
    }
    
     private void guardarAlteracoes() {
        bd.guardar(sistema);
    }
     
    private void filtrarEnfermaria(){
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por enf
        for(int i=0; i<hospital.getDoentes().getTotalDoentes(); i++){
            if(!pesquisar.equals("")){
            if(hospital.getDoentes().getD(i).getEnfermaria()!= null){
                
                if(hospital.getDoentes().getD(i).getEnfermaria().getCodigoEnf().contains(pesquisar)){
                    Doente d = hospital.getDoentes().getD(i);
                    pesq.add(d);
                }
            }   
            } else if(pesquisar.equals("")){
                if(hospital.getDoentes().getD(i).getEnfermaria()== null){
                    Doente d = hospital.getDoentes().getD(i);
                    pesq.add(d);
                } 
            }
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
    }

    private void filtrarGravidade(){
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por gravidade
        for(int i=0; i<hospital.getDoentes().getTotalDoentes(); i++){
            if(hospital.getDoentes().getD(i).getGravidade() != null){
            if(hospital.getDoentes().getD(i).getGravidade().contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
            }
            }if(hospital.getDoentes().getD(i).getGravidade() == null){
                if(pesquisar == null){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
                }
            }
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
        
    }
    
    private void filtrarLocalidade(){
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por gravidade
        for(int i=0; i<hospital.getDoentes().getTotalDoentes(); i++){
            
            if(hospital.getDoentes().getD(i).getLocalidade().contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
            }
            
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
        
    }
    
    private void filtrarDataEntrada(){
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por gravidade
        for(int i=0; i<hospital.getDoentes().getTotalDoentes(); i++){
            //verificar data
            int dias = hospital.getDoentes().getD(i).getDataEntrada().getDayOfMonth();
            String dia = String.valueOf(dias);
            int m = hospital.getDoentes().getD(i).getDataEntrada().getMonthValue();
            String mes = String.valueOf(m);
            int a = hospital.getDoentes().getD(i).getDataEntrada().getYear();
            String ano = String.valueOf(a);
            if(dia.contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
            } else if (mes.contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
            } else if (ano.contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
            }
            
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
        
    } 
     
    private void editarDados(){
        int col = 0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String codigo = (String) jTable1.getValueAt(row, col);
        System.out.println(codigo);
        Doente d = hospital.getDoentes().getDoente(codigo);
        System.out.println(d);
        EditDoente p = new EditDoente(sistema, bd, d);
        p.setVisible(true);
        dispose();
    } 
     
     
     private void removerPaciente(){
        int row = jTable1.getSelectedRow();
        int col = 0;
        
        if (row == -1){
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String value = (String) jTable1.getValueAt(row, col);
        Doente doente = hospital.getDoentes().getDoente(value);
        hospital.getDoentes().remover(doente);
       
  
        JOptionPane.showMessageDialog(this,"Paciente removido com sucesso!");
        modeloTabelaDoentes = new TabelaDoentes(hospital.getDoentes());
        jTable1.setModel(modeloTabelaDoentes);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        filtro = new javax.swing.JComboBox<>();
        pesquisa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-logout-arredondado-acima-52.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-editar-usuário-masculino-52.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(778, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 100));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Lista de Pacientes");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Gravidade", "Data de Entrada", "Data de Saída"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 640, 280));

        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, 160, 40));

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 160, 40));

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, 160, 40));

        filtro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enfermaria", "Localidade", "Gravidade", "Data de Entrada" }));
        jPanel2.add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 120, 30));
        jPanel2.add(pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 150, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente terminar a sessão?", "A terminar...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            guardarAlteracoes();
            dispose();
            Login p = new Login(sistema, bd);
            p.setVisible(true);}
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        dispose();
        HomePerfilRececionista e = new HomePerfilRececionista(sistema, bd);
        e.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        AddDoente e = new AddDoente(sistema, bd, new javax.swing.JFrame(), true);
        e.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        removerPaciente();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editarDados();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if (filtro.getSelectedItem().equals("Data de Entrada")){
            filtrarDataEntrada();
        } else if (filtro.getSelectedItem().equals("Localidade")){
            filtrarLocalidade();
        } else if (filtro.getSelectedItem().equals("Enfermaria")){
            filtrarEnfermaria();
        } else if (filtro.getSelectedItem().equals("Gravidade")){
            filtrarGravidade();
        }
    }//GEN-LAST:event_jLabel6MouseClicked

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filtro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pesquisa;
    // End of variables declaration//GEN-END:variables
}
