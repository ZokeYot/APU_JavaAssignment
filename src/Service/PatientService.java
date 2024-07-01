package Service;

import Model.Class.*;
import Model.Enum.TimeSlotStatus;
import Repository.*;
import Exception.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static Model.Enum.AppointmentStatus.*;

public class PatientService {

    private final ScheduleRepo scheduleRepo;
    private final AppointmentRepo appointmentRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final PatientRepo patientRepo;
    private final PaymentInformationRepo paymentInformationRepo;


    public PatientService(RepoFactory repoFactory){
        scheduleRepo = repoFactory.getScheduleRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
        patientRepo = repoFactory.getPatientRepo();
        paymentInformationRepo = repoFactory.getPaymentInformationRepo();
    }

    // Get patient's appointments
    public List<Appointment> getAppointments(Patient patient){
        return appointmentRepo.getUserAppointments(patient);
    }


    // Get the available doctors schedule
    public List<Schedule> getSchedules(){
        return scheduleRepo.getScheduleList().stream()
                .filter(schedule -> schedule.getTimeslots().stream()
                        .anyMatch(timeSlot -> timeSlot.getStatus().equals(TimeSlotStatus.AVAILABLE)))
                .collect(Collectors.toList());
    }


    // 2. Create Appointment
    public void createAppointment(Appointment appointment) throws IOException, ResourceNotFoundException {
        appointmentRepo.create(appointment);
        scheduleRepo.updateTimeSlotStatus(appointment.getDoctor(), appointment.getSlot(), TimeSlotStatus.TAKEN);
    }

    // 3. Cancel Appointment

    public void cancelAppointment(Appointment appointment) throws IOException, ResourceNotFoundException{
        appointmentRepo.update(appointment.getAppointmentId(), CANCELLED);
        scheduleRepo.updateTimeSlotStatus(appointment.getDoctor(),appointment.getSlot(), TimeSlotStatus.AVAILABLE);
    }

    public List<PaymentInformation> getPaymentInformation(Patient patient){
        return paymentInformationRepo.getPatientPaymentInformation(patient);
    }

    public void addPaymentInformation(PaymentInformation paymentInformation) throws IOException{
        paymentInformationRepo.create(paymentInformation);
    }

    public void updatePaymentInformation(PaymentInformation paymentInformation) throws IOException, ResourceNotFoundException {
        paymentInformationRepo.update(paymentInformation.getPaymentInformationId(), paymentInformation);
    }

    public void deletePaymentInformation(PaymentInformation paymentInformation) throws IOException, ResourceNotFoundException {
        paymentInformationRepo.delete(paymentInformation.getPaymentInformationId());
    }

    public void updatePatientProfile(Patient patient) throws IOException, ResourceNotFoundException {
        patientRepo.update(patient.getUserID(), patient);
    }

    public List<MedicalRecord> getMedicalRecords(Patient patient){
        return medicalRecordRepo.getMedicalRecord(patient);
    }

    // 5. Track Historical Appointment
    public List<Appointment> viewAppointmentHistory(Patient patient){
        List<Appointment> appointments = appointmentRepo.getUserAppointments(patient);

        return appointments.stream()
                .filter(appointment ->
                        (appointment.getAppointmentStatus().equals(CANCELLED) | appointment.getAppointmentStatus().equals(COMPLETED)))
                .collect(Collectors.toList());
    }


}
