/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Doente;
import backend.Hospital;
import backend.Serializacao;
import backend.Sistema;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author nunom
 */
public class AddDoente extends javax.swing.JDialog {
private Sistema sistema;
private Hospital hospital;
private Serializacao bd;
    /**
     * Creates new form AddDoente
     */
    public AddDoente(Sistema sistema, Serializacao bd, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.sistema= sistema;
        this.hospital=sistema.getListaUtilizadores().getRececionista(sistema.getUtilizadorLigado().getCodigoU()).getHospital();
        this.bd=bd;
    }
    
    
        private void adicionarDoente () {
            
        if (codigo.getText().trim().isEmpty()){                                //verifica se o espaço do código é preenchido
            JOptionPane.showMessageDialog(this,"Formato do código: Dxxx!");
            codigo.requestFocus();
            return;
        }
            
        String s = codigo.getText().trim();
        String vazio = s.substring(1);
            
        //verifica se o código a seguir à primeira letra está preenchido
        
        if (vazio.equals("")){
            JOptionPane.showMessageDialog(this,"Formato do código: Dxxx!");
            codigo.requestFocus();
            return;
        }
        //verifica se a primeira letra é um D
        String cod = s.substring(0,1);
        
        if (!cod.equals("D")){
            JOptionPane.showMessageDialog(this,"Formato do código: Dxxx!");
            codigo.requestFocus();
            return;
        }        
        
        if(nome.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Introduza o nome!");
            nome.requestFocus();
            return;
        }
        if(localidade.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Introduza a localidade!");
            localidade.requestFocus();
            return;
        }
        
        for(int i=0; i<hospital.getDoentes().getTotalDoentes();i++){
            if(codigo.getText().trim().equals(hospital.getDoentes().getD(i).getCodDoente())){
                JOptionPane.showMessageDialog(this,"Código de Doente já em uso!");
                codigo.requestFocus();
                return;
            }
        }
        
    /*    
        if(nCama.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Introduza um número!");
            nCama.requestFocus();
            return;
        }
        
        String a = nCama.getText().trim();
        for (char c: a.toCharArray())
        {
            if (!Character.isDigit(c))
            {
            JOptionPane.showMessageDialog(null, "Introduza um número!", "Erro", JOptionPane.ERROR_MESSAGE);
            nCama.requestFocus();
            return;
            }
        }
        
        
        for(int i = 0; i < hospital.getDoentes().getTotalDoentes(); i++){
            if (hospital.getDoentes().getD(i).getEnfermaria().getCodigoEnf().equals(listaEnf.getSelectedItem())){
                if(hospital.getDoentes().getD(i).getnCama().equals(nCama.getText().trim())){
               JOptionPane.showMessageDialog(null, "Cama em uso!", "Erro", JOptionPane.ERROR_MESSAGE);
                nCama.requestFocus();
                return; 
                }
            }
        }
        
        int b = Integer.parseInt(nCama.getText());
        
        if (b > hospital.getEnfermarias().getEnfermaria((String) listaEnf.getSelectedItem()).getNumCamas()){
            JOptionPane.showMessageDialog(null, "O número é maior que o número de camas disponíveis", "Erro", JOptionPane.ERROR_MESSAGE);
            nCama.requestFocus();
            return;
        }
        
        if(gravidade.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(this,"Introduza a gravidade!");
            gravidade.requestFocus();
            return;
        }
      */  
       LocalDate dataEntrada = LocalDate.now();
       Doente d = new Doente(codigo.getText().trim(), nome.getText().trim(), localidade.getText().trim(), dataEntrada);
       hospital.getDoentes().adicionar(d);
       //sistema.getListaDoentes().adicionar(d);
       JOptionPane.showMessageDialog(this,"Doente adicionado com sucesso!");
       dispose();
       PaginaRececionista p = new PaginaRececionista(sistema, bd);
       p.setVisible(true);
       
        System.out.println(hospital.getDoentes().getListaDoentes());
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        localidade = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nome");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ADICIONAR DOENTE");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Localidade");

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

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sick_fever_thermometure_temperature_avatar_coronavirus_icon_140387.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(localidade, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                .addGap(111, 111, 111))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(localidade, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        PaginaRececionista p = new PaginaRececionista(sistema, bd);
        p.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        adicionarDoente();
    }//GEN-LAST:event_jButton1ActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField localidade;
    private javax.swing.JTextField nome;
    // End of variables declaration//GEN-END:variables
}
