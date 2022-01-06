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
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author nunom
 */
public class HomeDash extends javax.swing.JFrame {
private Sistema sistema;
private Hospital hospital;
private TabelaStatsEquip modeloTabelaStatsEquip;
private Serializacao bd;
    /**
     * Creates new form HomeDash
     */
    public HomeDash(Sistema sistema,  Serializacao bd) {
        initComponents();
        this.sistema=sistema;
        hospital=sistema.getListaUtilizadores().getGestorHosp(sistema.getUtilizadorLigado().getCodigoU()).getHospital();
        this.bd=bd;
        jLabel7.setText(hospital.getNome());
        
        if (!hospital.getDoentes().getListaDoentes().isEmpty()){
        this.setLocationRelativeTo(null);
        pnChart.setLayout(new java.awt.BorderLayout());
        graficoPieChart();
       
        }
        if(hospital.getEquipamentos() != null){
            equipsOcupados();
        }
        if(hospital.getEnfermarias()!=null){
            camasOcupadas();
        }
        if(hospital.getEquipamentos()!=null){
            equipsLivres();
        }
        
        
    }
    
    private void guardarAlteracoes() {
        bd.guardar(sistema);
    }
   
      
    private void equipsOcupados(){
        //ArrayList<Equipamento> equip = new ArrayList<>();
        Set<String> equip = new HashSet<String>();
        for(int i=0; i<hospital.getEquipamentos().getTotalEquipamentos(); i++){          
            Equipamento e = hospital.getEquipamentos().getEquip(i);
            equip.add(e.getTipo());
        }
        ArrayList<String> equips = new ArrayList<String>(equip);
        
        int[] numeros = new int[equips.size()]; 
        int[] total = new int[equips.size()];
        for(int k=0; k<equips.size(); k++){
            for(int j=0; j<hospital.getEquipamentos().getTotalEquipamentos();j++){
                if(equips.get(k).equals(hospital.getEquipamentos().getEquip(j).getTipo())){
                    total[k]++;
                    if(hospital.getEquipamentos().getEquip(j).getEstadoEquip().equals("Ocupado")){
                        numeros[k]++;   
                    }
                }
            }             
        }

        for(int a=0; a<numeros.length;a++){
                System.out.println(numeros[a]);
            }
        System.out.println(equip);
        modeloTabelaStatsEquip = new TabelaStatsEquip(equips, numeros, total);
        jTable1.setModel(modeloTabelaStatsEquip);
    }
    
   
    
    private void camasOcupadas(){
        String maisProbl = "";
        for(int i=0; i< hospital.getEnfermarias().getTotalEnfermarias(); i++){
            int camasOcupadas=0;
            int mProb = 0;
            
            for(int j=0; j<hospital.getDoentes().getTotalDoentes(); j++){
                if(hospital.getDoentes().getD(j).getEnfermaria() != null){
                    if(hospital.getDoentes().getD(j).getEnfermaria().getCodigoEnf().equals(hospital.getEnfermarias().getEnf(i).getCodigoEnf())){
                    camasOcupadas++;
                    
                    }
                }
            }
            if(camasOcupadas>mProb){
                mProb = camasOcupadas;
                maisProbl = hospital.getEnfermarias().getEnf(i).getCodigoEnf();
            }
       }
        
        camasO.setText(maisProbl);
        
    }
    
    private void equipsLivres(){
        String maisLivres = "";
        for(int i=0; i<hospital.getEnfermarias().getTotalEnfermarias();i++){
            int equipsLivres =0;
            int mLivres = 999999999;
            for(int j=0; j<hospital.getEquipamentos().getTotalEquipamentos();j++){
                    if(hospital.getEquipamentos().getEquip(j).getEnfermaria().getCodigoEnf().equals(hospital.getEnfermarias().getEnf(i).getCodigoEnf())){
                       if(hospital.getEquipamentos().getEquip(j).getEstadoEquip().equals("Disponível")){
                           equipsLivres++;
                           
                       } 
                    }
                
            }
            if(equipsLivres<mLivres){
                mLivres = equipsLivres;
                maisLivres = hospital.getEnfermarias().getEnf(i).getCodigoEnf();
            }
            
        }
        
        equipamentosL.setText(maisLivres);
    }
 
      
    private void graficoPieChart(){
        int grave = 0;
        int mGrave = 0;
        int totalD = hospital.getDoentes().getTotalDoentes();
        for (int i = 0; i<totalD; i++){
            
            if (hospital.getDoentes().getD(i).getGravidade() != null && hospital.getDoentes().getD(i).getGravidade().equals("Grave")){
                grave++;
            }
            if (hospital.getDoentes().getD(i).getGravidade() != null && hospital.getDoentes().getD(i).getGravidade().equals("Muito Grave")){
                mGrave++;
            }
        }
        
        double percGraves = (grave*100)/totalD;
        double percMGraves = (mGrave*100)/totalD;
        String sGraves = String.valueOf(percGraves);
        String sMGraves = String.valueOf(percMGraves);
        if(grave==1){
            graveL.setText(grave+" doente ("+sGraves+"%)");
        }
        else {
            graveL.setText(grave+" doentes ("+sGraves+"%)");
        }
        if (mGrave == 1){
            mGraveL.setText(mGrave+" doente ("+sMGraves+"%)");
        } else {
            mGraveL.setText(mGrave+" doentes ("+sMGraves+"%)");
        }
        double outros = totalD-(grave+mGrave);
        
        //faz o gráfico, caso seja possível fazer    
    
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue("Grave", grave);
        dataset.setValue("Muito Grave", mGrave);
        dataset.setValue("Restantes", outros);
       
        JFreeChart chart = ChartFactory.createPieChart("", dataset, false, false, false);
            PiePlot plot = (PiePlot) chart.getPlot();
            
            plot.setSectionPaint("Grave", new Color(0, 83, 77));
            plot.setSectionPaint("Muito Grave", new Color(0,51,51));
            plot.setSectionPaint("Restantes", new Color(0,135,124));
            plot.setBackgroundPaint(new Color(240,240,240));
            chart.setBackgroundPaint(new Color(240,240,240));
            chart.setBorderVisible(false);
            ChartPanel chartPanel = new ChartPanel(chart);
            //Plot plot = chart.getPlot();
            
            pnChart.removeAll();
            pnChart.add(chartPanel, BorderLayout.CENTER);
            pnChart.validate();
    
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
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnChart = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        camasO = new javax.swing.JTextField();
        equipamentosL = new javax.swing.JTextField();
        graveL = new javax.swing.JTextField();
        mGraveL = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-logout-arredondado-acima-52.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dashboard");

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
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 608, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(24, 24, 24))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 100));

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-seringa-40.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-gráfico-combinado-40.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-secretary-woman-48.png"))); // NOI18N

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

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-doutor-em-medicina-48.png"))); // NOI18N
        jLabel10.setText("jLabel10");

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
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addGap(20, 20, 20))
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
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 250, 440));

        jLabel2.setText("Estado Muito Grave");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 120, 30));

        jLabel9.setText("Estado Grave");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 90, 30));

        pnChart.setForeground(new java.awt.Color(240, 240, 240));
        pnChart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(pnChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 290, 170));

        jLabel6.setText("Equipamentos Ocupados");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, 170, -1));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Número", "Percentagem"
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 290, 180));

        jLabel11.setText("Mais camas ocupadas");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 140, -1));

        jLabel12.setText("Menos equipamentos livres");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 170, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("Enfermaria mais problemática");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 220, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-seringa-40.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-administrador-masculino-40.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-hospital-3-40.png"))); // NOI18N
        jLabel16.setText("jLabel5");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 40, -1));

        camasO.setEditable(false);
        camasO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        camasO.setText("(sem dados)");
        jPanel1.add(camasO, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 140, 40));

        equipamentosL.setEditable(false);
        equipamentosL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        equipamentosL.setText("(sem dados)");
        jPanel1.add(equipamentosL, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 140, 40));

        graveL.setEditable(false);
        graveL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        graveL.setText("(sem dados)");
        graveL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graveLActionPerformed(evt);
            }
        });
        jPanel1.add(graveL, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 110, 30));

        mGraveL.setEditable(false);
        mGraveL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mGraveL.setText("(sem dados)");
        mGraveL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGraveLActionPerformed(evt);
            }
        });
        jPanel1.add(mGraveL, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 110, 30));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HomeDash p = new HomeDash(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente terminar a sessão?", "A terminar...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        guardarAlteracoes();
        dispose();
        Login p = new Login(sistema, bd);
        p.setVisible(true);}
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        HomeProfsSaude p = new HomeProfsSaude(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void graveLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graveLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_graveLActionPerformed

    private void mGraveLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGraveLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mGraveLActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        HomePerfilGestorHosp p = new HomePerfilGestorHosp(sistema, bd);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField camasO;
    private javax.swing.JTextField equipamentosL;
    private javax.swing.JTextField graveL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mGraveL;
    private javax.swing.JPanel pnChart;
    // End of variables declaration//GEN-END:variables
}
