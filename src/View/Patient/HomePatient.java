/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Patient;


import Model.Class.Patient;
import Repository.RepoFactory;
import Service.PatientService;

public class HomePatient extends javax.swing.JFrame {

    private final Patient patient;
    private final PatientService patientService;

    public HomePatient(Patient patient, RepoFactory repoFactory) {
        initComponents();
        this.patient = patient;
        this.patientService = new PatientService(repoFactory);
    }

    public HomePatient(Patient patient, PatientService patientService){
        initComponents();
        this.patient = patient;
        this.patientService = patientService;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        homePageLabel = new javax.swing.JLabel();
        scheduleButton = new javax.swing.JButton();
        appointmentButton = new javax.swing.JButton();
        medicalRecordButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        homePageLabel.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        homePageLabel.setText("Patient Home Page");

        scheduleButton.setText("View Time Slot");
        scheduleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleButtonActionPerformed(evt);
            }
        });

        appointmentButton.setText("Manage Appointments");
        appointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentButtonActionPerformed(evt);
            }
        });

        medicalRecordButton.setText("View Medical Record");
        medicalRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicalRecordButtonActionPerformed(evt);
            }
        });

        profileButton.setText("View Patient Profile");
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(homePageLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(appointmentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scheduleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(medicalRecordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(homePageLabel)
                .addGap(28, 28, 28)
                .addComponent(profileButton)
                .addGap(20, 20, 20)
                .addComponent(scheduleButton)
                .addGap(18, 18, 18)
                .addComponent(appointmentButton)
                .addGap(18, 18, 18)
                .addComponent(medicalRecordButton)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleButtonActionPerformed
        new ViewTimeSlot(patient, patientService);
        dispose();
    }//GEN-LAST:event_scheduleButtonActionPerformed

    private void appointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentButtonActionPerformed
        new ManageAppointmets(patient, patientService);
        dispose();
    }//GEN-LAST:event_appointmentButtonActionPerformed

    private void medicalRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicalRecordButtonActionPerformed
        new ViewMedicalRecord(patient, patientService);
        dispose();
    }//GEN-LAST:event_medicalRecordButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        new PatientUserProfile(patient, patientService);
        dispose();
    }//GEN-LAST:event_profileButtonActionPerformed

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
            java.util.logging.Logger.getLogger(HomePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePatient(new Patient(), new RepoFactory()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointmentButton;
    private javax.swing.JLabel homePageLabel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JButton medicalRecordButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton scheduleButton;
    // End of variables declaration//GEN-END:variables
}
