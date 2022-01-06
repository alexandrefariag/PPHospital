/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Doente;
import backend.Enfermaria;
import backend.Hospital;
import backend.Serializacao;
import backend.Sistema;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandra Ferreira
 */
public class HomeMedico extends javax.swing.JFrame {
private Sistema sistema;
private Serializacao bd;
private TabelaDoentes modeloTabelaDoentes;
private Hospital hospital;
private TabelaFiltrada modeloTabelaFiltrada;
    /**
     * Creates new form HomeMedico
     */
    public HomeMedico(Sistema sistema, Serializacao bd) {
        initComponents();
        this.sistema=sistema;
        this.bd=bd;
        hospital = sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getEnfermaria().getHospital();
        modeloTabelaDoentes = new TabelaDoentes(sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getEnfermaria().getHospital().getDoentes());
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
    
    private void adicionarDoente(){
        int col=0;
        int row = jTable1.getSelectedRow();
        int coluna=5;
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String cod = (String) jTable1.getValueAt(row, col);
        LocalDate dataS = (LocalDate) jTable1.getValueAt(row, coluna);
        System.out.println(dataS);
        if(dataS != null){
            JOptionPane.showMessageDialog(this,"Este paciente já teve alta!");
            return;
        }
        Doente doente = hospital.getDoentes().getDoente(cod);
        if(doente.getEnfermaria() != null){
            JOptionPane.showMessageDialog(this,"O Paciente já está a ser tratado!");
            return;
        }
        
        Enfermaria enfermaria = sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getEnfermaria();
      
        
        int totalCamas = enfermaria.getNumCamas();
        int camasOcupadas = 0;
        for(int i=0; i<enfermaria.getHospital().getDoentes().getTotalDoentes(); i++){
            if(enfermaria.getHospital().getDoentes().getD(i).getEnfermaria() != null &&
                    enfermaria.getCodigoEnf().equals(enfermaria.getHospital().getDoentes().getD(i).getEnfermaria().getCodigoEnf())) {
                camasOcupadas++;
            }   
        }
        
        int camasLivres = totalCamas - camasOcupadas;
        if(camasLivres <1){
            JOptionPane.showMessageDialog(this,"A enfermaria não tem camas disponíveis!");
            return;
        }
        
        Hospital hospital = enfermaria.getHospital();
        
        int camaAOcupar = 1;
        boolean ocupada = false;
        for(int i = 0; i<hospital.getDoentes().getTotalDoentes(); i++){
            if (ocupada == false){
                if(hospital.getDoentes().getD(i).getnCama() == null){
                    camaAOcupar=i+1;
                    ocupada = true;
                }
            }
        }
        
        
        
        
        String c = String.valueOf(camaAOcupar);
        
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente adicionar o paciente à sua lista?", "A terminar...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getDoentes().adicionar(doente);
            doente.setEnfermaria(enfermaria);
            doente.setnCama(c);
            
            JOptionPane.showMessageDialog(this,"Paciente adicionado com sucesso!");
            modeloTabelaDoentes = new TabelaDoentes(sistema.getListaUtilizadores().getMedico(sistema.getUtilizadorLigado().getCodigoU()).getEnfermaria().getHospital().getDoentes());
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
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        filtro = new javax.swing.JComboBox<>();
        pesquisa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-administrador-masculino-40.png"))); // NOI18N
        jLabel5.setText("Os meus Pacientes");
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

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Lista de Pacientes");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Gravidade", "Data de Entrada", "Data de Saída"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 640, 290));

        jButton1.setText("Adicionar à sua lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, 160, 40));

        filtro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enfermaria", "Localidade", "Estado", "Data de Entrada" }));
        jPanel1.add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 120, 30));
        jPanel1.add(pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 150, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 30, 30));

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

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        HomeMedicoPacientes h = new HomeMedicoPacientes(sistema, bd);
        h.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        adicionarDoente();
    }//GEN-LAST:event_jButton1ActionPerformed

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



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filtro;
    private javax.swing.JButton jButton1;
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
