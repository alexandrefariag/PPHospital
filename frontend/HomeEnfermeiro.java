/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;
import backend.Sistema;
import javax.swing.JOptionPane;
import backend.Doente;
import backend.Enfermaria;
import backend.Hospital;
import backend.ListaDoentes;
import backend.Serializacao;
import java.time.LocalDate;
import java.util.ArrayList;



/**
 *
 * @author Alexandra Ferreira
 */
public class HomeEnfermeiro extends javax.swing.JFrame {
    private Sistema sistema;
    private Serializacao bd;
    private TabelaDoentes modeloTabelaDoentes;
    private Enfermaria enfermaria;
    private Hospital hospital;
    private TabelaFiltrada modeloTabelaFiltrada;
    /**
     * Creates new form HomeEnfermeiro
     */
    public HomeEnfermeiro(Sistema sistema,Serializacao bd) {
        initComponents();
        this.sistema=sistema;
        this.bd=bd;
        hospital = sistema.getListaUtilizadores().getEnfermeiro(sistema.getUtilizadorLigado().getCodigoU()).getEnfermaria().getHospital();
        enfermaria = sistema.getListaUtilizadores().getEnfermeiro(sistema.getUtilizadorLigado().getCodigoU()).getEnfermaria();
        preencherTabela();
    }
    
     private void guardarAlteracoes() {
        bd.guardar(sistema);
    }
     
    private void preencherTabela(){
      ListaDoentes doente = new ListaDoentes ();
      for (int i=0; i<enfermaria.getHospital().getDoentes().getTotalDoentes();i++){
          if (enfermaria.getHospital().getDoentes().getD(i).getEnfermaria()!= null && enfermaria.getHospital().getDoentes().getD(i).getEnfermaria().getCodigoEnf().equals(enfermaria.getCodigoEnf())){
              Doente d = enfermaria.getHospital().getDoentes().getD(i);
              doente.adicionar(d);
          }
      }   
      modeloTabelaDoentes = new TabelaDoentes(doente);
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
    
     private void filtrarGravidade(){
        ListaDoentes doente = new ListaDoentes ();
        for (int i=0; i<enfermaria.getHospital().getDoentes().getTotalDoentes();i++){
            if (enfermaria.getHospital().getDoentes().getD(i).getEnfermaria()!= null && enfermaria.getHospital().getDoentes().getD(i).getEnfermaria().getCodigoEnf().equals(enfermaria.getCodigoEnf())){
                Doente d = enfermaria.getHospital().getDoentes().getD(i);
                doente.adicionar(d);
            }
        }  
         
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por gravidade
        for(int i=0; i<doente.getTotalDoentes(); i++){
            if(doente.getD(i).getGravidade() != null){
            if(doente.getD(i).getGravidade().contains(pesquisar)){
                Doente d = doente.getD(i);
                pesq.add(d);
            }
            }
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
        
    }
    
    private void filtrarLocalidade(){
        ListaDoentes doente = new ListaDoentes ();
        for (int i=0; i<enfermaria.getHospital().getDoentes().getTotalDoentes();i++){
            if (enfermaria.getHospital().getDoentes().getD(i).getEnfermaria()!= null && enfermaria.getHospital().getDoentes().getD(i).getEnfermaria().getCodigoEnf().equals(enfermaria.getCodigoEnf())){
                Doente d = enfermaria.getHospital().getDoentes().getD(i);
                doente.adicionar(d);
            }
        }  
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por gravidade
        for(int i=0; i<doente.getTotalDoentes(); i++){
            
            if(doente.getD(i).getLocalidade().contains(pesquisar)){
                Doente d = doente.getD(i);
                pesq.add(d);
            }
            
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
        
    }
    
    private void filtrarDataEntrada(){
        ListaDoentes doente = new ListaDoentes ();
        for (int i=0; i<enfermaria.getHospital().getDoentes().getTotalDoentes();i++){
            if (enfermaria.getHospital().getDoentes().getD(i).getEnfermaria()!= null && enfermaria.getHospital().getDoentes().getD(i).getEnfermaria().getCodigoEnf().equals(enfermaria.getCodigoEnf())){
                Doente d = enfermaria.getHospital().getDoentes().getD(i);
                doente.adicionar(d);
            }
        }  
        String pesquisar = pesquisa.getText().trim();
        ArrayList<Doente> pesq = new ArrayList<>();
        //pesquisa por gravidade
        for(int i=0; i<doente.getTotalDoentes(); i++){
            //verificar data
            int dias = doente.getD(i).getDataEntrada().getDayOfMonth();
            String dia = String.valueOf(dias);
            int m = doente.getD(i).getDataEntrada().getMonthValue();
            String mes = String.valueOf(m);
            int a = doente.getD(i).getDataEntrada().getYear();
            String ano = String.valueOf(a);
            if(dia.contains(pesquisar)){
                Doente d = doente.getD(i);
                pesq.add(d);
            } else if (mes.contains(pesquisar)){
                Doente d = doente.getD(i);
                pesq.add(d);
            } else if (ano.contains(pesquisar)){
                Doente d = doente.getD(i);
                pesq.add(d);
            }
            
        }
        System.out.println(pesq);
        modeloTabelaFiltrada = new TabelaFiltrada(pesq);
        jTable1.setModel(modeloTabelaFiltrada);
        
    }
    
        private void removerEquipamento(){
        int col=0;
        int row = jTable1.getSelectedRow();
        
        if(row == -1){ 
            JOptionPane.showMessageDialog(this,"Selecione um Paciente!");
            return;
        }
        
        String cod = (String) jTable1.getValueAt(row, col);
        Doente doente = hospital.getDoentes().getDoente(cod);
               
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
        jButton9 = new javax.swing.JButton();

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
        jLabel5.setText("Pacientes");
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

        jButton1.setText("Associar Equipamento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 200, 40));

        filtro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Localidade", "Gravidade", "Data de Entrada" }));
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });
        jPanel1.add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 120, 30));
        jPanel1.add(pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 150, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 30, 30));

        jButton9.setText("Remover Equipamento");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 240, 200, 40));

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
        HomePerfilEnfermeiro e = new HomePerfilEnfermeiro(sistema, bd);
        e.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      escolherPacienteEquipamento();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if (filtro.getSelectedItem().equals("Data de Entrada")){
            filtrarDataEntrada();
        } else if (filtro.getSelectedItem().equals("Localidade")){
            filtrarLocalidade();
        } else if (filtro.getSelectedItem().equals("Gravidade")){
            filtrarGravidade();
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        removerEquipamento();
    }//GEN-LAST:event_jButton9ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filtro;
    private javax.swing.JButton jButton1;
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
