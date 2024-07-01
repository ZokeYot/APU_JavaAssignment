package Model.Class;

import Model.Enum.PaymentStatus;

import java.util.UUID;

public class Payment {

    private UUID paymentId;
    private MedicalRecord medicalRecord;
    private PaymentMethod paymentMethod;
    private Integer amount;
    private PaymentStatus status;

    public Payment(){

    }

    // For file reading one
    public Payment(String paymentId, MedicalRecord medicalRecordId, PaymentMethod paymentMethod, String amount, String status ){
        this.paymentId = UUID.fromString(paymentId);
        this.medicalRecord = medicalRecordId;
        this.paymentMethod = paymentMethod;
        this.amount = Integer.valueOf(amount);
        this.status = PaymentStatus.valueOf(status);
    }

    // For create new payment one
    public Payment(MedicalRecord medicalRecord,PaymentMethod paymentMethod, Integer amount){
        this.paymentId = UUID.randomUUID();
        this.paymentMethod = paymentMethod;
        this.medicalRecord = medicalRecord;
        this.amount = amount;
        this.status = PaymentStatus.UNPAID;
    }
    
    @Override
    public String toString(){
        String paymentMethodId = paymentMethod.getPaymentMethodId() != null ? paymentMethod.getPaymentMethodId().toString()  : null;
        return paymentId.toString() + "|" +
                medicalRecord.getMedicalRecordID() + "|" +
                paymentMethodId + "|" +
                amount.toString() + "|" +
                status.toString();
    }


    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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
