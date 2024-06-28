package Model.Class;

import Model.Enum.PaymentStatus;
import java.util.UUID;

public class MedicalRecord extends Appointment {

    private UUID medicalRecordID;
    private String diagnosisResult;
    private String recommendation;
    private PaymentStatus paymentStatus;

    public MedicalRecord(){

    }

    // This one is for creating a new medical record one
    public MedicalRecord(Appointment appointment, String diagnosisResult, String recommendation){
        super(appointment);
        this.medicalRecordID = UUID.randomUUID();
        this.diagnosisResult = diagnosisResult;
        this.recommendation = recommendation;
        this.paymentStatus = PaymentStatus.UNPAID;
    }

    // This one is for reading file one
    public MedicalRecord(Appointment appointment, String medicalRecordId, String diagnosisResult, String recommendation, PaymentStatus status){
        super(appointment);
        this.medicalRecordID = UUID.fromString(medicalRecordId);
        this.diagnosisResult = diagnosisResult;
        this.recommendation = recommendation;
        this.paymentStatus = status;
    }


    public String toString(){
        return medicalRecordID.toString() + "|" +
                getAppointmentId().toString() + "|" +
                diagnosisResult + "|" +
                recommendation + "|" +
                paymentStatus.toString();
    }

    public String[] toArray(){
        return new String[]
                {medicalRecordID.toString(), getDoctor().getName(), getPatient().getName(), getDate(),
                getDiagnosisResult(), getRecommendation(), getPaymentStatus().toString()};
    }



    public UUID getMedicalRecordID() {
        return medicalRecordID;
    }

    public void setMedicalRecordID(UUID medicalRecordID) {
        this.medicalRecordID = medicalRecordID;
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public PaymentStatus getPaymentStatus(){
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus){
        this.paymentStatus = paymentStatus;
    }

}
