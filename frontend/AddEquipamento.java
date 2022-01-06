/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;


import backend.Equipamento;
import backend.Hospital;
import backend.Serializacao;
import backend.Sistema;
import frontend.HomeEquipamento;
import javax.swing.JOptionPane;
/**
 *
 * @author Alexandre
 */
public class AddEquipamento extends javax.swing.JDialog {
 private Sistema sistema;
 private Hospital hospital;
 private Serializacao bd;
    /**
     * Creates new form AddEquipamento
     */
    public AddEquipamento(Sistema sistema, Serializacao bd, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.sistema = sistema;
        hospital=sistema.getListaUtilizadores().getGestorHosp(sistema.getUtilizadorLigado().getCodigoU()).getHospital();
        this.bd=bd;
        initComponents();
        carregarOpcoes();
    }
    
    
    private void carregarOpcoes(){      //carregar as opções da lista das enfermarias
    
    for (int i = 0; i < hospital.getEnfermarias().getTotalEnfermarias(); i++){
       listaE.addItem(hospital.getEnfermarias().getEnf(i).getCodigoEnf()+", "+hospital.getEnfermarias().getEnf(i).getTipo());    //vai à lista das enfermarias, recebe    
    }
    
    }
    
        
    private void addEqui(){
    if(codigoe.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(this,"Formato do código: Eqxxx!");
        codigoe.requestFocus();
        return;
    }
        String s = codigoe.getText().trim();
        if(s.length() >= 2){
            
            String vazio = s.substring(2);
        
            //verifica se o código a seguir à primeira letra está preenchido
        
            if (vazio.equals("")){
                JOptionPane.showMessageDialog(this,"Formato do código: Eqxxx!");
                codigoe.requestFocus();
                return;
            }
        
        
            //verifica se começa com Eq
            String cod = s.substring(0,2);
        
            if (!cod.equals("Eq")){
                JOptionPane.showMessageDialog(this,"Formato do código: Eqxxx!");
                codigoe.requestFocus();
                return;
            }
            } else {
                JOptionPane.showMessageDialog(this,"Formato do código: Eqxxx!");
                codigoe.requestFocus();
                return;
            }
    
    if(tipoe.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(this,"Introduza o tipo por favor");
        tipoe.requestFocus();
        return;
    }
    
   /* if(enfermariae.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(this,"Introduza a enfermaria por favor");
        enfermariae.requestFocus();
        return;
    }
    */
   
   for(int i=0; i<hospital.getEquipamentos().getTotalEquipamentos(); i++){
       if (codigoe.getText().trim().equals(hospital.getEquipamentos().getEquip(i).getCodigoEquip())){
           JOptionPane.showMessageDialog(this,"Código de Equipamento já em uso!");
           codigoe.requestFocus();
           return;
       }
   }
    String hosp = (String) listaE.getSelectedItem();
        int a = hosp.indexOf(',');
        String codigo = hosp.substring(0,a);
    Equipamento e = new Equipamento(codigoe.getText().trim(),tipoe.getText().trim(), "Disponível", hospital.getEnfermarias().getEnfermaria(codigo));
    hospital.getEquipamentos().adicionar(e);
    JOptionPane.showMessageDialog(this,"Equipamento adicionado com sucesso!");
    dispose();
    HomeEquipamento p = new HomeEquipamento(sistema, bd);
    p.setVisible(true);
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        codigoe = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tipoe = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        listaE = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tipo");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("ADICIONAR EQUIPAMENTO");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Enfermaria");

        jButton1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconfinder-doctorhealthcare-and-medicalmedical-historystethoscopeclipboardmedical-4394752_119515.png"))); // NOI18N

        listaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGap(66, 66, 66)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(codigoe, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(tipoe, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(listaE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codigoe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipoe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(listaE, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addEqui();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        HomeEquipamento h = new HomeEquipamento(sistema, bd);
        h.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void listaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listaEActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigoe;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> listaE;
    private javax.swing.JTextField tipoe;
    // End of variables declaration//GEN-END:variables
}
