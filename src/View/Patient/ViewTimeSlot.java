/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Patient;

import Model.Class.Doctor;
import Model.Class.Patient;
import Model.Class.Schedule;
import Model.Class.TimeSlot;
import Model.Enum.AppointmentStatus;
import Model.Enum.TimeSlotStatus;
import Service.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Siow
 */
public class ViewTimeSlot extends javax.swing.JFrame {

    private final Patient patient;
    private final PatientService patientService;
    private final DefaultTableModel table;
    private final DefaultComboBoxModel comboBox;
    private final List<Schedule> scheduleList;

    public ViewTimeSlot(Patient patient, PatientService patientService) {
        initComponents();
        setVisible(true);
        this.patient = patient;
        this.patientService = patientService;
        this.table = (DefaultTableModel) scheduleTable.getModel();
        this.comboBox = (DefaultComboBoxModel) doctorInput.getModel();
        this.scheduleList = patientService.getSchedules();
        init();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        doctorInput = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        doctorDisplay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        timeSlotDisplay = new javax.swing.JTextField();
        makeAppointmentButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("View Time Slot");

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DoctorID ", "Doctor ", "Date", "Time Slot"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scheduleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(scheduleTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Doctor :");

        doctorInput.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        doctorInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all" }));
        doctorInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorInputActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Doctor:");

        doctorDisplay.setEditable(false);
        doctorDisplay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Time Slot: ");

        timeSlotDisplay.setEditable(false);
        timeSlotDisplay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        makeAppointmentButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        makeAppointmentButton.setText("Make Appointment");
        makeAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeAppointmentButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(makeAppointmentButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(doctorInput, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(doctorDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeSlotDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(backButton))
                        .addGap(392, 392, 392)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(doctorInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doctorDisplay)
                    .addComponent(jLabel4)
                    .addComponent(timeSlotDisplay))
                .addGap(27, 27, 27)
                .addComponent(makeAppointmentButton)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void init(){
        table.setRowCount(0);
        showAllTimeSlot();

        scheduleList.forEach(schedule -> comboBox.addElement(schedule.getDoctor().getName()));
    }
    private void showAllTimeSlot(){
        scheduleList.stream()
                .flatMap(schedule -> schedule.getTimeslots().stream()
                        .filter(timeSlot -> timeSlot.getStatus().equals(TimeSlotStatus.AVAILABLE))
                        .map(slot -> new Object[]{schedule.getDoctor().getUserID(), schedule.getDoctor().getName(), schedule.getDate(), slot.getTimeslot()}))
                .forEach(table::addRow);
    }

    private void showDoctorTimeSlot(String doctorName){
        scheduleList.stream()
                .filter(schedule -> schedule.getDoctor().getName().equals(doctorName))
                .flatMap(schedule -> schedule.getTimeslots().stream()
                        .filter(timeSlot -> timeSlot.getStatus().equals(TimeSlotStatus.AVAILABLE))
                        .map(slot -> new Object[]{schedule.getDoctor().getUserID(),schedule.getDoctor().getName(), schedule.getDate(), slot.getTimeslot()}))
                .forEach(table::addRow);
    }

    private Doctor getSelectedDoctor(UUID doctorId) throws Exception{
        return scheduleList.stream()
                .map(Schedule::getDoctor)
                .filter(doctor -> doctor.getUserID().equals(doctorId))
                .findFirst()
                .orElseThrow(() -> new Exception("Doctor not found"));
    }

    private void scheduleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleTableMouseClicked
        try{
            int index = scheduleTable.getSelectedRow();
            UUID doctorId = (UUID) table.getValueAt(index, 0);

            if(index == -1)
                throw new Exception("No Row Selected");

            Doctor doctor = getSelectedDoctor(doctorId);
            String selectedSlot = scheduleTable.getValueAt(index, 3).toString();

            doctorDisplay.setText(doctor.getName());
            timeSlotDisplay.setText(selectedSlot);

        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_scheduleTableMouseClicked

    private void doctorInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorInputActionPerformed
        String selected = comboBox.getSelectedItem().toString();
        table.setRowCount(0);
        if(selected.equals("all"))
            showAllTimeSlot();
        else
            showDoctorTimeSlot(selected);
    }//GEN-LAST:event_doctorInputActionPerformed

    private void makeAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeAppointmentButtonActionPerformed
        try{
            int index = scheduleTable.getSelectedRow();
            UUID doctorId = (UUID) table.getValueAt(index, 0);

            if(index == -1)
                throw new Exception("No Row Selected");

            Doctor doctor = getSelectedDoctor(doctorId);
            String selectedSlot = scheduleTable.getValueAt(index, 3).toString();

            new MakeAppointment(patient, patientService, doctor, selectedSlot);
            dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_makeAppointmentButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new HomePatient(patient, patientService);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField doctorDisplay;
    private javax.swing.JComboBox<String> doctorInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton makeAppointmentButton;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JTextField timeSlotDisplay;
    // End of variables declaration//GEN-END:variables
}
