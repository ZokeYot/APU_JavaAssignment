/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Doctor;

import Model.Class.Doctor;
import Repository.RepoFactory;
import Service.DoctorService;
import View.Patient.HomePatient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.*;

public class UploadSchedule extends javax.swing.JFrame {
    
    private final Doctor doctor;
    private final DoctorService doctorService;
    private final Set<String> selectedSlots;

    
    private final String[] COL_HEADER = {
            "Day / Time", "8.00 - 9.00", "9.00 - 10.00", "10.00 - 11.00", "11.00 - 12.00",
            "12.00 - 13.00", "13.00 - 14.00", "14.00 - 15.00", "15.00 - 16.00", "16.00 - 17.00"};
    private final String[][] ROWS_DATA = {
            {"Today", "", "", "", "", "", "", "", "", ""}};


    public UploadSchedule(Doctor doctor, DoctorService doctorService) {
        initComponents();
        setVisible(true);
        this.doctor = doctor;
        this.doctorService = doctorService;
        selectedSlots = new HashSet<>();
        setupTable();

    }

    private void setupTable() {
        if(!doctorService.getDoctorSchedule(doctor).isEmpty())
            selectedSlots.addAll(doctorService.getDoctorSchedule(doctor));
        DefaultTableModel model = new DefaultTableModel(ROWS_DATA, COL_HEADER){
            @Override
            public Object getValueAt(int row, int column) {
                if (column == 0)
                    return ROWS_DATA[row][0];

                return selectedSlots.contains(getSlotNumber(row, column)) ? "X" : "";
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scheduleTable.setModel(model);
        scheduleTable.getColumnModel().getColumn(0).setPreferredWidth(100);

        scheduleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int row = scheduleTable.rowAtPoint(e.getPoint());
                    int col = scheduleTable.columnAtPoint(e.getPoint());
                    String slotNumber = getSlotNumber(row, col);

                    if (col == 0)
                        throw new Exception("Please select a valid time slot");

                    if (selectedSlots.contains(slotNumber))
                        selectedSlots.remove(slotNumber);
                    else
                        selectedSlots.add(slotNumber);

                    model.fireTableCellUpdated(row, col);
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private String getSlotNumber(int row, int col){
        return String.valueOf(9 * row + col);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();
        resetButton = new javax.swing.JButton();
        uploadButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Upload Schedule");

        scheduleTable.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scheduleTable.setColumnSelectionAllowed(true);
        scheduleTable.setRowHeight(30);
        scheduleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(scheduleTable);
        scheduleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        resetButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        uploadButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        uploadButton.setText("Upload");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
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
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(resetButton)
                                .addGap(18, 18, 18)
                                .addComponent(uploadButton)
                                .addGap(56, 56, 56)))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(backButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton)
                    .addComponent(uploadButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

                                            
    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        try{
            if(selectedSlots.isEmpty())
                throw new Exception("No time slot get selected !!");

            doctorService.uploadSchedule(doctor, selectedSlots.stream().toList());
            JOptionPane.showMessageDialog(this, "Schedule Uploaded");
            new HomeDoctor(doctor, doctorService);
            dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_uploadButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new HomeDoctor(doctor, doctorService);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void scheduleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scheduleTableMouseClicked

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        selectedSlots.clear();
        for (int col = 1; col < COL_HEADER.length; col++)
            scheduleTable.getModel().setValueAt("", 0, col);
    }//GEN-LAST:event_resetButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JButton uploadButton;
    // End of variables declaration//GEN-END:variables
}
