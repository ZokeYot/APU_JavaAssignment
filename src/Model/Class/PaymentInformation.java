package Model.Class;

import java.util.Date;
import java.util.UUID;

public class PaymentInformation {

    private UUID paymentInformationId;
    private Patient patient;
    private String title;
    private String card_number;
    private String bank;
    private String validUntil;


    public PaymentInformation(){}

    public PaymentInformation(Patient patient,String paymentInformationId, String title, String card_number, String bank, String validUntil){
        this.patient = patient;
        this.paymentInformationId = UUID.fromString(paymentInformationId);
        this.title = title;
        this.card_number = card_number;
        this.bank = bank;
        this.validUntil = validUntil;
    }

    public PaymentInformation(Patient patient, String title, String card_number, String bank, String validUntil){
        this.patient = patient;
        this.paymentInformationId = UUID.randomUUID();
        this.title = title;
        this.card_number = card_number;
        this.bank = bank;
        this.validUntil = validUntil;
    }

    public String toString(){
        return paymentInformationId.toString() + "|" +
                patient.getUserID().toString() + "|" +
                title + "|" +
                card_number + "|" +
                bank + "|" +
                validUntil;
    }

    public String[] toArray(){
        return new String[]{getTitle(), getCard_number(), getBank(), getValidUntil()};
    }

    public UUID getPaymentInformationId() {
        return paymentInformationId;
    }

    public void setPaymentInformationId(UUID paymentInformationId) {
        this.paymentInformationId = paymentInformationId;
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
