package Model.Class;

import Model.Enum.PaymentStatus;

import java.util.UUID;

public class Payment {

    private UUID paymentId;
    private MedicalRecord medicalRecord;
    private PaymentInformation paymentInformation;
    private Integer amount;
    private PaymentStatus status;

    public Payment(){

    }

    // For file reading one
    public Payment(String paymentId, MedicalRecord medicalRecordId, PaymentInformation paymentInformation, String amount, String status ){
        this.paymentId = UUID.fromString(paymentId);
        this.medicalRecord = medicalRecordId;
        this.paymentInformation = paymentInformation;
        this.amount = Integer.valueOf(amount);
        this.status = PaymentStatus.valueOf(status);
    }

    // For create new payment one
    public Payment(MedicalRecord medicalRecord, PaymentInformation paymentInformation, Integer amount){
        this.paymentId = UUID.randomUUID();
        this.medicalRecord = medicalRecord;
        this.paymentInformation = paymentInformation;
        this.amount = amount;
        this.status = PaymentStatus.UNPAID;
    }
    
    @Override
    public String toString(){
        return paymentId.toString() + "|" +
                medicalRecord.getMedicalRecordID() + "|" +
                paymentInformation.getPaymentInformationId().toString() + "|" +
                amount.toString() + "|" +
                status.toString();
    }


    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public MedicalRecord getMedicalRecord(){
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecord = medicalRecord;
    }




}
