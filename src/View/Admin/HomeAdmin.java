package View.Admin;


import Model.Class.Admin;
import Repository.RepoFactory;
import Service.AdminService;
import java.io.FileNotFoundException;

public class HomeAdmin extends javax.swing.JFrame {

    private final Admin admin;
    private final AdminService adminService;

    public HomeAdmin(Admin admin, RepoFactory repoFactory){
        initComponents();
        this.admin = admin;
        this.adminService = new AdminService(repoFactory);
    }

    public HomeAdmin(Admin admin, AdminService adminService){
        initComponents();
        this.admin = admin;
        this.adminService = adminService;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        RegisterWalkIn = new javax.swing.JButton();
        ManageRegistration = new javax.swing.JButton();
        TrackMedical = new javax.swing.JButton();
        ViewDailyAppointment = new javax.swing.JButton();
        CollectPayment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Admin Home Page");

        RegisterWalkIn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RegisterWalkIn.setText("Register Walk-In Appointment");
        RegisterWalkIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterWalkInActionPerformed(evt);
            }
        });

        ManageRegistration.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ManageRegistration.setText("Manage User Registration");
        ManageRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageRegistrationActionPerformed(evt);
            }
        });

        TrackMedical.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TrackMedical.setText("Track Medical Record");
        TrackMedical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrackMedicalActionPerformed(evt);
            }
        });

        ViewDailyAppointment.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ViewDailyAppointment.setText("View Daily Appointment");
        ViewDailyAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewDailyAppointmentActionPerformed(evt);
            }
        });

        CollectPayment.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CollectPayment.setText("Collect Payment");
        CollectPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CollectPaymentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RegisterWalkIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ManageRegistration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TrackMedical, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ViewDailyAppointment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CollectPayment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(ManageRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(RegisterWalkIn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(TrackMedical, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(ViewDailyAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(CollectPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterWalkInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterWalkInActionPerformed
        WalkInAppointment walkIn = new WalkInAppointment(admin, adminService);
        walkIn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegisterWalkInActionPerformed

    private void ManageRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageRegistrationActionPerformed
        ManageUserRegistration manageRegistration = new ManageUserRegistration(admin, adminService);
        manageRegistration.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ManageRegistrationActionPerformed

    private void TrackMedicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrackMedicalActionPerformed
        TrackMedicalRecord Trk = new TrackMedicalRecord(admin, adminService);
        Trk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_TrackMedicalActionPerformed

    private void ViewDailyAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewDailyAppointmentActionPerformed
        TrackDailyAppointment Appt = new TrackDailyAppointment(admin, adminService);
        Appt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ViewDailyAppointmentActionPerformed

    private void CollectPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CollectPaymentActionPerformed
        try{
            CollectPayment payment = new CollectPayment(admin, adminService);
            payment.setVisible(true);
            this.dispose();
        }catch(FileNotFoundException ex) {
            
        }
    }//GEN-LAST:event_CollectPaymentActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin(new Admin(), new AdminService(new RepoFactory())).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CollectPayment;
    private javax.swing.JButton ManageRegistration;
    private javax.swing.JButton RegisterWalkIn;
    private javax.swing.JButton TrackMedical;
    private javax.swing.JButton ViewDailyAppointment;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
