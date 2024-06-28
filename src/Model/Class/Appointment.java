package Model.Class;

import Model.Enum.AppointmentStatus;
import java.util.UUID;

public class Appointment {

    private UUID appointmentId;
    private Doctor doctor;
    private Patient patient;
    private String date;
    private String slot;
    private String message;
    private AppointmentStatus appointmentStatus;
    private Boolean walkIn;
   


    public Appointment(){

    }


    // This one is for medical record class one
    public Appointment(Appointment appointment){
        this.appointmentId = appointment.getAppointmentId();
        this.doctor = appointment.getDoctor();
        this.patient = appointment.getPatient();
        this.date = appointment.getDate();
        this.slot = appointment.getSlot();
        this.message = appointment.getMessage();
        this.appointmentStatus = appointment.appointmentStatus;
        this.walkIn = appointment.getWalkIn();
    }

    // This one is for file reading one
    public Appointment(String appointmentId, Doctor doctor, Patient patient, String date, String slot, String message, String status, String walkIn){
        this.appointmentId = UUID.fromString(appointmentId);
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.message = message;
        this.appointmentStatus = AppointmentStatus.valueOf(status);
        this.walkIn = Boolean.valueOf(walkIn);
    }

    // This one is for creating new appointment one
    public Appointment(Doctor doctor, Patient patient, String date, String slot, String message, boolean walkIn){
        this.appointmentId = UUID.randomUUID();
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.slot = slot;
        this.message = message;
        this.appointmentStatus = AppointmentStatus.PENDING;
        this.walkIn = walkIn;
    }

    @Override
    // This one convert into a text form to store into the text file
    public String toString(){
        return appointmentId.toString() + "|" +
                doctor.getUserID().toString() + "|" +
                patient.getUserID().toString() + "|" +
                date + "|" +
                slot + "|" +
                message + "|" +
                appointmentStatus.toString() + "|" +
                walkIn.toString();
    }

    public String[] toArray(){
        return new String[]
                {appointmentId.toString(), doctor.getName(), patient.getName(),
                        date,slot, appointmentStatus.toString(), walkIn ? "yes" : "no"};
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setAppointmentId(String appointmentId){ this.appointmentId = UUID.fromString(appointmentId);}

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {this.appointmentStatus = appointmentStatus;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Boolean getWalkIn() {
        return walkIn;
    }

    public void setWalkIn(Boolean walkIn) {
        this.walkIn = walkIn;
    }
}
