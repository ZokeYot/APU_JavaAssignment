/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Patient;

import Model.Class.MedicalRecord;
import Model.Class.Patient;
import Model.Class.PaymentMethod;
import Service.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author Siow
 */
public class AddPaymentMethod extends javax.swing.JFrame {


    private final Patient patient;
    private final PatientService patientService;
    private final DefaultTableModel table;
    private List<PaymentMethod> paymentMethodList;
    private MedicalRecord medicalRecord;

    public AddPaymentMethod(Patient patient, PatientService patientService, MedicalRecord medicalRecord) {
        initComponents();
        setVisible(true);
        this.patient = patient;
        this.patientService = patientService;
        this.table = (DefaultTableModel) paymentTable.getModel();
    }

    private void init(){
        table.setRowCount(0);
        paymentMethodList = patientService.getPaymentMethod(patient);
        paymentMethodList.forEach(paymentInformation -> table.addRow(new Object[]{
                paymentInformation.getPaymentMethodId(),
                paymentInformation.getTitle(),
                paymentInformation.getCard_number(),
                paymentInformation.getValidUntil()
        }));
    }


    private void checkForm(String title, String bank, String cardNumber, String year, String month) throws Exception{
        String cardNumberRegex = "^\\d{10}$";
        String yearRegex = "^\\d{4}$";

        String yearNow = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy"));
        String monthNow = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MM"));



        if(title.trim().isEmpty())
            throw new Exception("The title cannot be empty");
        if(bank.trim().isEmpty())
            throw new Exception("The bank cannot be empty");
        if(cardNumber.trim().isEmpty())
            throw new Exception("The card number cannot be empty");
        if(!cardNumber.matches(cardNumberRegex))
            throw new Exception("Incorrect format for card number. The card number should be a 10 digit numbers");
        if(year.trim().isEmpty())
            throw new Exception("The valid until year cannot be empty");
        if(!year.matches(yearRegex))
            throw new Exception("Incorrect format for the valid until year");
        if(Integer.parseInt(yearNow) > Integer.parseInt(year))
            throw new Exception("The year has already passed");
        if(Integer.parseInt(yearNow) == Integer.parseInt(year) && Integer.parseInt(monthNow) >= Integer.parseInt(month))
            throw new Exception("The valid until date is already expired");


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        cardNumberInput = new javax.swing.JTextField();
        monthInput = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        yearInput = new javax.swing.JTextField();
        bankInput = new javax.swing.JTextField();
        nameInput = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Add Payment Methods");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID ", "Name", "Card Number", "Valid Until"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paymentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(paymentTable);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Valid Until : ");

        monthInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("/");

        nameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameInputActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Payment Information");

        addButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Name : ");

        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Card Number: ");

        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Bank:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(242, 242, 242)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardNumberInput, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bankInput, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel12))
                                .addGap(207, 207, 207)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addButton)
                .addGap(18, 18, 18)
                .addComponent(deleteButton)
                .addGap(18, 18, 18)
                .addComponent(updateButton)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(cardNumberInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(monthInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(yearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(deleteButton)
                    .addComponent(updateButton))
                .addGap(19, 19, 19))
        );

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
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paymentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentTableMouseClicked
        int index = paymentTable.getSelectedRow();

        PaymentMethod paymentMethod = paymentMethodList.get(index);
        String[] validUntil =  paymentMethod.getValidUntil().split("/");
        String month = validUntil[0];
        String year = validUntil[1];

        nameInput.setText(paymentMethod.getTitle());
        bankInput.setText(paymentMethod.getBank());
        cardNumberInput.setText(paymentMethod.getCard_number());
        monthInput.setSelectedItem(month);
        yearInput.setText(year);
    }//GEN-LAST:event_paymentTableMouseClicked

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try{
            String title = nameInput.getText();
            String bank  = bankInput.getText();
            String cardNumber = cardNumberInput.getText();
            String month = (String) monthInput.getSelectedItem();
            String year = yearInput.getText();

            checkForm(title, bank, cardNumber, year,month);

            PaymentMethod paymentMethod = new PaymentMethod(patient, title, cardNumber, bank, month + "/" + year);
            patientService.addPaymentInformation(paymentMethod);
            JOptionPane.showMessageDialog(this, "New Payment Method Added", "Ok", JOptionPane.INFORMATION_MESSAGE);

            nameInput.setText("");
            bankInput.setText("");
            cardNumberInput.setText("");
            yearInput.setText("");
            init();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try{
            int index = paymentTable.getSelectedRow();

            if (index == -1)
                throw new Exception("No Row Selected");

            String title = nameInput.getText();
            String bank  = bankInput.getText();
            String cardNumber = cardNumberInput.getText();
            String month = (String) monthInput.getSelectedItem();
            String year = yearInput.getText();

            checkForm(title, bank, cardNumber, year,month);

            PaymentMethod paymentMethod = paymentMethodList.get(index);

            paymentMethod.setTitle(title);
            paymentMethod.setBank(bank);
            paymentMethod.setCard_number(cardNumber);
            paymentMethod.setValidUntil(month + "/" + year);

            JOptionPane.showMessageDialog(this, "New Payment Method Added", "Ok", JOptionPane.INFORMATION_MESSAGE);
            patientService.updatePaymentInformation(paymentMethod);
            nameInput.setText("");
            bankInput.setText("");
            cardNumberInput.setText("");
            yearInput.setText("");
            init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try{
            int index = paymentTable.getSelectedRow();

            if(index == -1)
                throw new Exception("No Row Selected");

            PaymentMethod paymentMethod = paymentMethodList.get(index);
            patientService.deletePaymentInformation(paymentMethod);
            JOptionPane.showMessageDialog(this, "Payment Method Deleted", "Ok", JOptionPane.INFORMATION_MESSAGE);
            init();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new MakePayment(patient, patientService, medicalRecord);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void nameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameInputActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField bankInput;
    private javax.swing.JTextField cardNumberInput;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> monthInput;
    private javax.swing.JTextField nameInput;
    private javax.swing.JTable paymentTable;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField yearInput;
    // End of variables declaration//GEN-END:variables
}
