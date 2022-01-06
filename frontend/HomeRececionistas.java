/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;


import backend.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nunom
 */
public class HomeRececionistas extends javax.swing.JFrame {
private Sistema sistema;
private Hospital hospital;
private Serializacao bd;
private TabelaFiltrada modeloTabelaFiltrada;
private TabelaRececionistas modeloTabelaRececionistas;
    /**
     * Creates new form HomePacientes
     */
    public HomeRececionistas(Sistema sistema, Serializacao bd) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.sistema=sistema;
        this.bd=bd;
        hospital=sistema.getListaUtilizadores().getGestorHosp(sistema.getUtilizadorLigado().getCodigoU()).getHospital();
        jLabel3.setText(hospital.getNome());
        modeloTabelaRececionistas = new TabelaRececionistas(hospital.getRececionistas());
        jTable1.setModel(modeloTabelaRececionistas);
        
    }

    private void guardarAlteracoes() {
        bd.guardar(sistema);
    }
   /* 
    private void filtrarCodigo(){
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
                
        //pesquisa por codigo
        for(int i=0; i<hospital.getDoentes().getTotalDoentes(); i++){
            if(hospital.getDoentes().getD(i).getCodDoente().contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
            }
        }
        System.out.println(pesq);
        System.out.println(hospital.getDoentes());
        System.out.println(pesq.getClass().getName());
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
    }*/
    /* 
    private void filtrarNome(){
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        
        //pesquisa por nome
        for(int i=0; i<hospital.getDoentes().getTotalDoentes(); i++){
            if(hospital.getDoentes().getD(i).getNome().contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
            }
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
    }
*/
    /*
    private void filtrarEnfermaria(){
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por enf
        for(int i=0; i<hospital.getDoentes().getTotalDoentes(); i++){
            if(hospital.getDoentes().getD(i).getEnfermaria().getCodigoEnf().contains(pesquisar)){
                Doente d = hospital.getDoentes().getD(i);
                pesq.add(d);
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
    */
    private void editarDados(){
        int col = 0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Rececionista!");
            return;
        }
        
        String codigo = (String) jTable1.getValueAt(row, col);
        System.out.println(codigo);
        System.out.println(sistema.getListaUtilizadores().getListaUtilizadores());
        Rececionista r = hospital.getRececionistas().getRececionista(codigo);
        System.out.println(r);
        EditRececionista p = new EditRececionista(sistema, bd, r, new javax.swing.JFrame(), true);
        p.setVisible(true);
        dispose();
    }
    
    private void removerRececionista(){
        int row = jTable1.getSelectedRow();
        int col = 0;
        
        if (row == -1){
            JOptionPane.showMessageDialog(this,"Selecione um Rececionista!");
            return;
        }
        
        String value = (String) jTable1.getValueAt(row, col);
        Rececionista rec = hospital.getRececionistas().getRececionista(value);
        hospital.getRececionistas().remover(rec);
        sistema.getListaUtilizadores().remover(rec);
        JOptionPane.showMessageDialog(this,"Rececionista removido com sucesso!");
        modeloTabelaRececionistas = new TabelaRececionistas(hospital.getRececionistas());
        jTable1.setModel(modeloTabelaRececionistas);
    }
 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-logout-arredondado-acima-52.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Rececionistas");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-editar-usuário-masculino-52.png"))); // NOI18N
        jLabel17.setText("jLabel2");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 590, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel17))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 100));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 480, 290));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("LISTA DE RECECIONISTAS");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 250, -1));

        jButton7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton7.setText("Adicionar ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, -1, -1));

        jButton10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton10.setText("Editar ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, 110, -1));

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setForeground(new java.awt.Color(240, 240, 240));

        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Dashboard");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Enfermaria");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Equipamento");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Rececionistas");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-seringa-40.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-gráfico-combinado-40.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-secretary-woman-48.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-hospital-3-40.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        jButton6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Prof. Saúde");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-doutor-em-medicina-48.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel10))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 250, 440));

        jButton11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton11.setText("Eliminar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        AddRececionista p = new AddRececionista(sistema, bd, new javax.swing.JFrame(), true);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       editarDados();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HomeDash p = new HomeDash(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        HomeEnf p = new HomeEnf(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        HomeEquipamento p = new HomeEquipamento(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        HomeRececionistas p = new HomeRececionistas(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        removerRececionista();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        HomeProfsSaude p = new HomeProfsSaude(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        HomePerfilGestorHosp p = new HomePerfilGestorHosp(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
