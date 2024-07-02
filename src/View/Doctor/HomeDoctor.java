/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Doctor;

import Model.Class.Doctor;
import Repository.RepoFactory;
import Service.DoctorService;

/**
 *
 * @author Siow
 */
public class HomeDoctor extends javax.swing.JFrame {

    private final Doctor doctor;
    private final DoctorService doctorService;


    public HomeDoctor(Doctor doctor, RepoFactory repoFactory){
        initComponents();
        setVisible(true);
        this.doctor = doctor;
        this.doctorService = new DoctorService(repoFactory);
    }

    public HomeDoctor(Doctor doctor, DoctorService doctorService){
        initComponents();
        setVisible(true);
        this.doctor = doctor;
        this.doctorService = doctorService;
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        uploadScheduleButton = new javax.swing.JButton();
        appointmentsButton = new javax.swing.JButton();
        medicalRecordsButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Doctor Home Page");

        uploadScheduleButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        uploadScheduleButton.setText("Upload Schedule");
        uploadScheduleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadScheduleButtonActionPerformed(evt);
            }
        });

        appointmentsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        appointmentsButton.setText("Manage Appointments");
        appointmentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentsButtonActionPerformed(evt);
            }
        });

        medicalRecordsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        medicalRecordsButton.setText("Manage Medical Records");
        medicalRecordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicalRecordsButtonActionPerformed(evt);
            }
        });

        profileButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        profileButton.setText("View Doctor Profile");
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
                .addGap(0, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(uploadScheduleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(appointmentsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(medicalRecordsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(155, 155, 155))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(profileButton)
                .addGap(18, 18, 18)
                .addComponent(uploadScheduleButton)
                .addGap(18, 18, 18)
                .addComponent(appointmentsButton)
                .addGap(18, 18, 18)
                .addComponent(medicalRecordsButton)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadScheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadScheduleButtonActionPerformed
        new UploadSchedule(doctor, doctorService);
        dispose();
    }//GEN-LAST:event_uploadScheduleButtonActionPerformed

    private void medicalRecordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicalRecordsButtonActionPerformed
        new ManageMedicalRecords(doctor, doctorService);
        dispose();
    }//GEN-LAST:event_medicalRecordsButtonActionPerformed

    private void appointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsButtonActionPerformed
        new ManageAppointments(doctor, doctorService);
        dispose();
    }//GEN-LAST:event_appointmentsButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        new DoctorUserProfile(doctor, doctorService);
        dispose();
    }//GEN-LAST:event_profileButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointmentsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton medicalRecordsButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton uploadScheduleButton;
    // End of variables declaration//GEN-END:variables
}
