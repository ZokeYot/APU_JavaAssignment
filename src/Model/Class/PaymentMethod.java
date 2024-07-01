package Model.Class;

import java.util.UUID;

public class PaymentMethod {

    private UUID paymentMethodId;
    private Patient patient;
    private String title;
    private String card_number;
    private String bank;
    private String validUntil;


    public PaymentMethod(){

    }

    public PaymentMethod(Patient patient, String paymentMethodId, String title, String card_number, String bank, String validUntil){
        this.patient = patient;
        this.paymentMethodId = UUID.fromString(paymentMethodId);
        this.title = title;
        this.card_number = card_number;
        this.bank = bank;
        this.validUntil = validUntil;
    }

    public PaymentMethod(Patient patient, String title, String card_number, String bank, String validUntil){
        this.paymentMethodId = UUID.randomUUID();
        this.patient = patient;
        this.title = title;
        this.card_number = card_number;
        this.bank = bank;
        this.validUntil = validUntil;
    }

    public String toString(){
        return  paymentMethodId != null ? paymentMethodId.toString() : null +  "|" +
                patient.getUserID().toString() + "|" +
                title + "|" +
                card_number + "|" +
                bank + "|" +
                validUntil;
    }

    public String[] toArray(){
        return new String[]{getTitle(), getCard_number(), getBank(), getValidUntil()};
    }

    public UUID getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(UUID paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }





}
