package Model.Class;

import Model.Enum.PaymentStatus;

import java.util.UUID;

public class Payment {

    private UUID paymentId;
    private PaymentInformation paymentInformation;
    private Integer amount;
    private PaymentStatus status;

    public Payment(){

    }

    // For file reading one
    public Payment(String paymentId,  PaymentInformation paymentInformation, String amount, String status ){
        this.paymentId = UUID.fromString(paymentId);
        this.paymentInformation = paymentInformation;
        this.amount = Integer.parseInt(amount);
        this.status = PaymentStatus.valueOf(status);
    }

    // For create new payment one
    public Payment(PaymentInformation paymentInformation, Integer amount){
        this.paymentId = UUID.randomUUID();
        this.paymentInformation = paymentInformation;
        this.amount = amount;
        this.status = PaymentStatus.UNPAID;
    }

    public String toString(){
        return paymentId.toString() + "|" +
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


}
