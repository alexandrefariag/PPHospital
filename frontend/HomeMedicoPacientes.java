/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Doente;
import backend.Equipamento;
import backend.Hospital;
import backend.Serializacao;
import backend.Sistema;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nunom
 */
public class HomeMedicoPacientes extends javax.swing.JFrame {
private Sistema sistema;
private Serializacao bd;
private TabelaDoentes modeloTabelaDoentes;
private Hospital hospital;
private TabelaFiltrada modeloTabelaFiltrada;
    /**
     * Creates new form HomeMedicoPacientes
     */
    public HomeMedicoPacientes(Sistema sistema, Serializacao bd) {
        initComponents();
        this.sistema=sistema;
        this.bd=bd;
        hospital = sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getEnfermaria().getHospital();
        modeloTabelaDoentes = new TabelaDoentes(sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes());
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
    private void darAlta(){
        int col=0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String cod = (String) jTable1.getValueAt(row, col);
        Doente doente = sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes().getDoente(cod);
        if(sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes().getDoente(cod).getDataSaida()!=null){
            JOptionPane.showMessageDialog(this,"Este doente já teve alta!");
            return;
        }
        LocalDate dataSaida = LocalDate.now();
        doente.setnCama(null);
        doente.setEnfermaria(null);
        doente.setGravidade(null);
        for(int i=0; i<hospital.getEquipamentos().getTotalEquipamentos();i++){
            if(hospital.getEquipamentos().getEquip(i).getDoente()!=null){
                if(hospital.getEquipamentos().getEquip(i).getDoente().getCodDoente().equals(cod)){
                    hospital.getEquipamentos().getEquip(i).setDoente(null);
                    hospital.getEquipamentos().getEquip(i).setEstadoEquip("Disponível"); 
                }
            }
        }
        
        sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes().remover(doente);
        doente.setDataSaida(dataSaida);
        JOptionPane.showMessageDialog(this,"Alta dada com sucesso!");
        modeloTabelaDoentes = new TabelaDoentes(sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes());
        jTable1.setModel(modeloTabelaDoentes);
    }
    
    private void escolherPacienteEquipamento(){
        int col=0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String cod = (String) jTable1.getValueAt(row, col);
        Doente doente = hospital.getDoentes().getDoente(cod);
        
        AssociarEquipamento p = new AssociarEquipamento(sistema, bd, doente, new javax.swing.JFrame(), true);
        p.setVisible(true);
        dispose();
    }
    
    private void escolherPacienteGravidade(){
        int col=0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String cod = (String) jTable1.getValueAt(row, col);
        Doente doente = hospital.getDoentes().getDoente(cod);
        DefinirGravidade p = new DefinirGravidade(sistema, bd, doente, new javax.swing.JFrame(), true);
        p.setVisible(true);
        dispose();
    }
    
    private void removerEquipamento(){
        int col=0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String cod = (String) jTable1.getValueAt(row, col);
        Doente doente = sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes().getDoente(cod);
        RemEquipamento h = new RemEquipamento(sistema, bd, doente, new javax.swing.JFrame(), true);
        h.setVisible(true);
        dispose();
        /*if (JOptionPane.showConfirmDialog(null, "Deseja realmente remover o equipamento deste paciente?", "A terminar...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            for(int i=0; i<hospital.getEquipamentos().getTotalEquipamentos(); i++){
                
                if(hospital.getEquipamentos().getEquip(i).getDoente() != null && doente.getCodDoente().equals(hospital.getEquipamentos().getEquip(i).getDoente().getCodDoente())){
                    hospital.getEquipamentos().getEquip(i).setDoente(null);
                    hospital.getEquipamentos().getEquip(i).setEstadoEquip("Disponível");               
                }
            }
            
            JOptionPane.showMessageDialog(this,"Equipamento removido com sucesso!");
        }*/
        
    }
    
    private void removerPaciente(){
        int col=0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        String cod = (String) jTable1.getValueAt(row, col);
        Doente doente = sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes().getDoente(cod);
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente remover o paciente da sua lista?", "A terminar...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            for(int i=0; i<hospital.getEquipamentos().getTotalEquipamentos(); i++){
                
                if(hospital.getEquipamentos().getEquip(i).getDoente() != null && doente.getCodDoente().equals(hospital.getEquipamentos().getEquip(i).getDoente().getCodDoente())){
                    hospital.getEquipamentos().getEquip(i).setDoente(null);
                    hospital.getEquipamentos().getEquip(i).setEstadoEquip("Disponível");               
                }
            }
            
            sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes().remover(doente);
            doente.setEnfermaria(null);
            doente.setEnfermaria(null);
            doente.setnCama(null);
            JOptionPane.showMessageDialog(this,"Paciente removido com sucesso!");
            modeloTabelaDoentes = new TabelaDoentes(sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes());
            jTable1.setModel(modeloTabelaDoentes); 
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        filtro = new javax.swing.JComboBox<>();
        pesquisa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-editar-usuário-masculino-52.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-hospital-2-40.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 100));

        jButton9.setText("Remover Equipamento");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 300, 200, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Lista de Pacientes");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        jButton6.setText("Dar Alta");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 200, 40));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Gravidade", "Data de Entrada", "Data de Saída"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 630, 290));

        jButton1.setText("Remover Paciente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, 200, 40));

        jButton2.setText("Definir Gravidade");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 200, 40));

        jButton10.setText("Associar Equipamento");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 200, 40));

        filtro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enfermaria", "Localidade", "Estado", "Data de Entrada" }));
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });
        jPanel1.add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 120, 30));

        pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaActionPerformed(evt);
            }
        });
        jPanel1.add(pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 150, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
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
        HomePerfilMedico m = new HomePerfilMedico(sistema, bd);
        m.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        HomeMedico h = new HomeMedico(sistema, bd);
        h.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        removerEquipamento();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        darAlta();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removerPaciente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        escolherPacienteGravidade();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        escolherPacienteEquipamento();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if (filtro.getSelectedItem().equals("Data de Entrada")){
            filtrarDataEntrada();
        } else if (filtro.getSelectedItem().equals("Localidade")){
            filtrarLocalidade();
        } else if (filtro.getSelectedItem().equals("Enfermaria")){
            filtrarEnfermaria();
        } else if (filtro.getSelectedItem().equals("Estado")){
            filtrarGravidade();
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroActionPerformed

    private void pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filtro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pesquisa;
    // End of variables declaration//GEN-END:variables
}
