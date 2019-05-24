/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.gestio;

import info.infomila.portaventura.classes.Atraccio;
import info.infomila.portaventura.classes.Incidencia;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ListModel;

/**
 *
 * @author Gorka
 */
public class FrameGestioAtraccions extends javax.swing.JFrame {

    private List<Atraccio> llAtraccions;
    private List<Incidencia> llIncidencies = new ArrayList<Incidencia>();
    private int posAtraccioSeleccionada;
    private Atraccio atraccioSeleccionada;
    private Incidencia incidenciaActual;
    private boolean novaIncidencia = true;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Creates new form FrameGestioAtraccions
     */
    public FrameGestioAtraccions() {
        initComponents();
        connectarJPA();
        initComponentsPers();
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
        cboAtraccions = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstIncidencies = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPersonesCua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAfegirCua = new javax.swing.JTextField();
        btnTreureCua = new javax.swing.JButton();
        btnAfegirCua = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEstatIncidencia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDataFiPrevista = new javax.swing.JTextField();
        btnTancarIncidencia = new javax.swing.JButton();
        txtIncidenciaActual = new javax.swing.JTextField();
        btnNovaIncidencia = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Atraccions:");

        cboAtraccions.setName("cboAtraccions"); // NOI18N
        cboAtraccions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAtraccionsActionPerformed(evt);
            }
        });

        lstIncidencies.setToolTipText("");
        jScrollPane1.setViewportView(lstIncidencies);

        jLabel2.setText("Incidencies");

        jLabel3.setText("Persones a la cua:");

        txtPersonesCua.setEditable(false);
        txtPersonesCua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersonesCuaActionPerformed(evt);
            }
        });

        txtAfegirCua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAfegirCuaActionPerformed(evt);
            }
        });

        btnTreureCua.setText("-");
        btnTreureCua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreureCuaActionPerformed(evt);
            }
        });

        btnAfegirCua.setText("+");
        btnAfegirCua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAfegirCuaActionPerformed(evt);
            }
        });

        jLabel5.setText("Incidencia Activa:");

        jLabel6.setText("Missatge estat:");

        txtEstatIncidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstatIncidenciaActionPerformed(evt);
            }
        });

        jLabel7.setText("Data fi prevista:(yyyy-MM-dd HH:mm:ss)");

        btnTancarIncidencia.setText("TANCAR INCIDENCIA");
        btnTancarIncidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTancarIncidenciaActionPerformed(evt);
            }
        });

        txtIncidenciaActual.setEditable(false);

        btnNovaIncidencia.setText("NOVA INCIDENCIA");
        btnNovaIncidencia.setActionCommand("");
        btnNovaIncidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaIncidenciaActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel9.setText("Afegir/Treure cua:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel4)
                                .addGap(49, 49, 49)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnTreureCua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAfegirCua))
                            .addComponent(txtAfegirCua, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPersonesCua, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(cboAtraccions, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(72, 72, 72)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIncidenciaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(txtEstatIncidencia)))
                    .addComponent(btnTancarIncidencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNovaIncidencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDataFiPrevista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboAtraccions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtIncidenciaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtPersonesCua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtAfegirCua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnTreureCua)
                                    .addComponent(btnAfegirCua)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtEstatIncidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtDataFiPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnNovaIncidencia)
                                    .addComponent(btnGuardar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTancarIncidencia)))
                        .addContainerGap())))
        );

        cboAtraccions.getAccessibleContext().setAccessibleName("cboAtraccions");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("GESTIO ATRACCIONS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPersonesCuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonesCuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonesCuaActionPerformed

    private void txtAfegirCuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAfegirCuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAfegirCuaActionPerformed

    private void btnTreureCuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTreureCuaActionPerformed
        //Botó per eliminar gent de la cua
        if(atraccioSeleccionada != null){
            int qGent=0;
            try{
                qGent = Integer.parseInt(txtAfegirCua.getText());
            }catch(NumberFormatException ex){
                txtAfegirCua.setText("");
                return;
            }
            if(qGent > atraccioSeleccionada.getClientsEnCua() || qGent < 0){
                //En aquests moments hem d'informar via textField de l'error
                txtAfegirCua.setText("");
                return;
            }
            em.getTransaction().begin();
            atraccioSeleccionada.setClientsEnCua(atraccioSeleccionada.getClientsEnCua()-qGent);
            //em.flush();
            em.getTransaction().commit();
            //em.refresh(atraccioSeleccionada);
            txtAfegirCua.setText("");
            carregarCua();
        }
    }//GEN-LAST:event_btnTreureCuaActionPerformed

    private void btnAfegirCuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAfegirCuaActionPerformed
        //Botó per afegir gent a la cua
        
        if(atraccioSeleccionada != null){
            int qGent=0;
            try{
                qGent = Integer.parseInt(txtAfegirCua.getText());
            }catch(NumberFormatException ex){
                txtAfegirCua.setText("");
                return;
            }
            
            if(qGent< 0){
                txtAfegirCua.setText("");
                return;
            }
            
            em.getTransaction().begin();
            atraccioSeleccionada.setClientsEnCua(atraccioSeleccionada.getClientsEnCua()+qGent);
            //em.flush();
            em.getTransaction().commit();
            //em.refresh(atraccioSeleccionada);
            txtAfegirCua.setText("");
            carregarCua();
        }  
    }//GEN-LAST:event_btnAfegirCuaActionPerformed

    private void txtEstatIncidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstatIncidenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstatIncidenciaActionPerformed

    private void cboAtraccionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAtraccionsActionPerformed
        // SelectionChanged del combo d'atraccions
        System.out.println("Atraccio canviada");
        posAtraccioSeleccionada = cboAtraccions.getSelectedIndex();
        if(posAtraccioSeleccionada < 0) return;
        atraccioSeleccionada = llAtraccions.get(posAtraccioSeleccionada);
        
        //Per la atraccio seleccionada carreguem les incidencies.
        carregarLlistaIncidencies();
        carregarCua();
        carregarIncidenciaActual();
    }//GEN-LAST:event_cboAtraccionsActionPerformed

    private void btnTancarIncidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTancarIncidenciaActionPerformed
        // Tancar incidencia
        if(incidenciaActual!=null){
            incidenciaActual.setDataFi(new Date());
            em.getTransaction().begin();
            //atraccioSeleccionada.addIncidencia(incidenciaActual);
            atraccioSeleccionada.setIncidencia(null);
            em.getTransaction().commit();
            incidenciaActual = null;
            carregarIncidenciaActual();
            carregarLlistaIncidencies();
        }
    }//GEN-LAST:event_btnTancarIncidenciaActionPerformed

    private void btnNovaIncidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaIncidenciaActionPerformed
        // Nova incidencia
        if(incidenciaActual!= null){
            return;
        }

        txtEstatIncidencia.setText("");
        txtDataFiPrevista.setText("");
        txtIncidenciaActual.setText("");
        novaIncidencia = true;
    }//GEN-LAST:event_btnNovaIncidenciaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Guardar incidencia
        
        System.out.println(txtEstatIncidencia.getText());
        System.out.println(txtDataFiPrevista.getText());
        System.out.println(txtIncidenciaActual.getText());
        
        Date dataFiPrevista = null;
        try{
            dataFiPrevista = sdf.parse(txtDataFiPrevista.getText());
        }catch(Exception ex){
            txtDataFiPrevista.setText("");
            return;
        }
        
        if(novaIncidencia){
            
            System.out.println(dataFiPrevista);
            String cad = "select max(p.num) from Incidencia p";
            Query q = em.createQuery(cad);
            
            int codi = (int)q.getSingleResult();
            codi ++;
            
            System.out.println("Atraccio: "+atraccioSeleccionada.getCodi());
            Incidencia incidencia = new Incidencia(codi,atraccioSeleccionada,new Date(),null,txtEstatIncidencia.getText(),dataFiPrevista);
            System.out.println("Incidencia: "+incidencia);
            
            //atraccioSeleccionada.addIncidencia(incidencia);
            atraccioSeleccionada.setIncidencia(incidencia);
            
        }else{
            
            incidenciaActual.setDataFiPrevista(dataFiPrevista);
            incidenciaActual.setMissatgeEstat(txtEstatIncidencia.getText());
        }
        //em.refresh(atraccioSeleccionada);
        em.getTransaction().begin();
        em.getTransaction().commit();
        //atraccioSeleccionada.addIncidencia(incidenciaActual);
        
        carregarIncidenciaActual();
        carregarLlistaIncidencies();
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameGestioAtraccions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGestioAtraccions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGestioAtraccions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGestioAtraccions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGestioAtraccions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAfegirCua;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNovaIncidencia;
    private javax.swing.JButton btnTancarIncidencia;
    private javax.swing.JButton btnTreureCua;
    private javax.swing.JComboBox<String> cboAtraccions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstIncidencies;
    private javax.swing.JTextField txtAfegirCua;
    private javax.swing.JTextField txtDataFiPrevista;
    private javax.swing.JTextField txtEstatIncidencia;
    private javax.swing.JTextField txtIncidenciaActual;
    private javax.swing.JTextField txtPersonesCua;
    // End of variables declaration//GEN-END:variables

    private EntityManagerFactory emf;
    private EntityManager em;
    
    protected void connectarJPA() {
        String up = "UP-MySQL";
        emf = null;
        em = null;
        try {
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");
            
            String cad = "select p from Atraccio p";
            Query q = em.createQuery(cad);
            llAtraccions = q.getResultList();
            
            //jList1.setModel((ListModel<String>) llista);
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
            StackTraceElement [] st = ex.getStackTrace();
            /*for(int i=0;i<st.length;i++){
                System.out.println(st[i]);
            }*/
        }/* finally {
            if (em != null) {
                em.close();
                System.out.println("EntityManager tancat");
            }
            if (emf != null) {
                emf.close();
                System.out.println("EntityManagerFactory tancada");
            }
        }
        */
    }

    private void initComponentsPers() {
        //ArrayList<String> llista = new ArrayList<String>();
        cboAtraccions.removeAllItems();
        for(Atraccio a:llAtraccions){
            //llista.add(a.getNom());
            cboAtraccions.addItem(a.getNom());
        }
    }

    private void carregarLlistaIncidencies() {
        
        List<String> llIncidenciesToString = new ArrayList<String>();
        
        for(Incidencia i:atraccioSeleccionada.iteIncidencies()){
            llIncidencies.add(i);
            llIncidenciesToString.add(i.toString());
            //System.out.println("Entro");
        }
        
        //System.out.println("llIncidenciesToString: "+llIncidenciesToString);
        String [] llista = llIncidenciesToString.toArray(new String[llIncidenciesToString.size()]);
        //System.out.println(llista);
        lstIncidencies.setListData(llista);
    }

    private void carregarCua() {
        if(atraccioSeleccionada!=null){
            txtPersonesCua.setText(atraccioSeleccionada.getClientsEnCua()+"");
        }
        
    }

    private void carregarIncidenciaActual() {
        boolean activarBotoTancarIncidencia = false;
        boolean activarBotoNovaIncidencia = true;
        txtEstatIncidencia.setText("");
        txtDataFiPrevista.setText("");
        txtIncidenciaActual.setText("");
        if(atraccioSeleccionada!=null){
            incidenciaActual = atraccioSeleccionada.getIncidencia();
            if(incidenciaActual != null){
                txtIncidenciaActual.setText(incidenciaActual.toString());
                txtEstatIncidencia.setText(incidenciaActual.getMissatgeEstat());
                
                txtDataFiPrevista.setText(sdf.format(incidenciaActual.getDataFiPrevista()));
                activarBotoTancarIncidencia = true;
                activarBotoNovaIncidencia = false;
                
            }
            btnTancarIncidencia.setEnabled(activarBotoTancarIncidencia);
            novaIncidencia = activarBotoNovaIncidencia;
            btnNovaIncidencia.setEnabled(activarBotoNovaIncidencia);
        }
    }
}
