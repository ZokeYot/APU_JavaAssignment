package Model.Class;

import Model.Enum.AppointmentStatus;
import java.util.UUID;

public class Appointment {

    private UUID appointmentId;
    private Doctor doctor;
    private Patient patient;
    private String timeSlot;
    private String message;
    private AppointmentStatus appointmentStatus;




    public Appointment(){

    }


    // This one is for medical record class one
    public Appointment(Appointment appointment){
        this.appointmentId = appointment.getAppointmentId();
        this.doctor = appointment.getDoctor();
        this.patient = appointment.getPatient();
        this.timeSlot = appointment.getTimeSlot();
        this.message = appointment.getMessage();
        this.appointmentStatus = appointment.appointmentStatus;
    }

    // This one is for file reading one
    public Appointment(String appointmentId, Doctor doctor, Patient patient, String timeSlot, String message, String status){
        this.appointmentId = UUID.fromString(appointmentId);
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
        this.message = message;
        this.appointmentStatus = AppointmentStatus.valueOf(status);
    }

    // This one is for creating new appointment one
    public Appointment(Doctor doctor, Patient patient, String timeSlot,  String message){
        this.appointmentId = UUID.randomUUID();
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
        this.message = message;
        this.appointmentStatus = AppointmentStatus.PENDING;
    }

    @Override
    // This one convert into a text form to store into the text file
    public String toString(){
        return appointmentId.toString() + "|" +
                doctor.getUserID().toString() + "|" +
                patient.getUserID().toString() + "|" +
                TimeTable.getIndex(timeSlot) + "|" +
                message + "|" +
                appointmentStatus.toString();
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

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String index) {

    }




}
