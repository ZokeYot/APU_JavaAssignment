package Service;

import Model.Class.*;
import Model.Enum.PaymentStatus;
import Model.Enum.TimeSlotStatus;
import Repository.*;
import Exception.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static Model.Enum.AppointmentStatus.*;

public class PatientService {

    private final ScheduleRepo scheduleRepo;
    private final AppointmentRepo appointmentRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final PatientRepo patientRepo;
    private final UserRepo userRepo;
    private final PaymentMethodRepo paymentMethodRepo;
    private final PaymentRepo paymentRepo;


    public PatientService(RepoFactory repoFactory){
        scheduleRepo = repoFactory.getScheduleRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
        patientRepo = repoFactory.getPatientRepo();
        userRepo = repoFactory.getUserRepo();
        paymentMethodRepo = repoFactory.getPaymentInformationRepo();
        paymentRepo = repoFactory.getPaymentRepo();
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

    public List<PaymentMethod> getPaymentMethod(Patient patient){
        return paymentMethodRepo.getPatientPaymentMethod(patient);
    }

    public void addPaymentInformation(PaymentMethod paymentMethod) throws IOException{
        paymentMethodRepo.create(paymentMethod);
    }

    public void updatePaymentInformation(PaymentMethod paymentMethod) throws IOException, ResourceNotFoundException {
        paymentMethodRepo.update(paymentMethod.getPaymentMethodId(), paymentMethod);
    }

    public void deletePaymentInformation(PaymentMethod paymentMethod) throws IOException, ResourceNotFoundException {
        paymentMethodRepo.delete(paymentMethod.getPaymentMethodId());
    }

    public void updatePatientProfile(Patient patient) throws IOException, ResourceNotFoundException {
        patientRepo.update(patient.getUserID(), patient);
        userRepo.update(patient.getUserID(), patient);
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

    public void makePayment(Payment payment) throws ResourceNotFoundException, IOException{
        paymentRepo.update(payment);
        medicalRecordRepo.update(payment.getMedicalRecord().getMedicalRecordID(), PaymentStatus.PROCESSING);
    }


    public Payment getPayment(MedicalRecord medicalRecord) throws Exception{
        return paymentRepo.getPaymentList().stream()
                .filter(payment -> payment.getMedicalRecord().equals(medicalRecord))
                .findFirst().orElseThrow(() -> new Exception("Payment Record Not Found"));
    }

}
