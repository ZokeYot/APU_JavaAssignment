package Service;

import Model.Enum.PaymentStatus;
import Repository.*;
import Model.Class.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import Exception.*;
import java.io.IOException;

public class AdminService {

    private final UserRepo userRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final ScheduleRepo scheduleRepo;
    private final PaymentRepo paymentRepo;
    private final PaymentMethodRepo paymentMethodRepo;


    public AdminService(RepoFactory repoFactory) {
        userRepo = repoFactory.getUserRepo();
        patientRepo = repoFactory.getPatientRepo();
        doctorRepo = repoFactory.getDoctorRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
        scheduleRepo = repoFactory.getScheduleRepo();
        paymentRepo = repoFactory.getPaymentRepo();
        paymentMethodRepo = repoFactory.getPaymentInformationRepo();
    }

    public List<Doctor> getDoctors() {
        return doctorRepo.getDoctorList();
    }

    public List<Patient> getPatients() {
        return patientRepo.getPatientList();
    }

    public void createDoctor(Doctor doctor, User user) throws Exception {
        Optional<User> duplicate = userRepo.find(doctor.getEmail());

        if (duplicate.isPresent())
            throw new Exception("The email has already been taken by another account");

        userRepo.create(user);
        doctorRepo.create(doctor);
    }

    public void createPatient(Patient patient, User user) throws Exception {
        Optional<User> duplicate = userRepo.find(patient.getEmail());

        if (duplicate.isPresent())
            throw new Exception("The email has already been taken by another account");

        userRepo.create(user);
        patientRepo.create(patient);
    }

    public void deleteDoctor(Doctor doctor) throws Exception {
        userRepo.delete(doctor.getUserID());
        doctorRepo.delete(doctor.getUserID());
        scheduleRepo.delete(doctor.getUserID());
        appointmentRepo.delete(doctor.getUserID());
        medicalRecordRepo.delete(doctor.getUserID());
    }

    public void deletePatient(Patient patient) throws Exception {
        userRepo.delete(patient.getUserID());
        patientRepo.delete(patient.getUserID());
        appointmentRepo.delete(patient.getUserID());
        medicalRecordRepo.delete(patient.getUserID());
        paymentMethodRepo.deleteAll(patient.getUserID());

        List<UUID> medicalRecordIDList = medicalRecordRepo.getMedicalRecordMap().keySet().stream().toList();
        paymentRepo.delete(medicalRecordIDList);
    }

    public List<Appointment> getDailyAppointment(){
        String todayDate = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return appointmentRepo.getAppointmentList().stream()
                .filter(appointment -> appointment.getDate().equals(todayDate))
                .collect(Collectors.toList());
    }

    public List<MedicalRecord> getPatientMedicalRecord(Patient patient){
        return medicalRecordRepo.getMedicalRecordList().stream()
                .filter(medicalRecord -> medicalRecord.getPatient().equals(patient))
                .collect(Collectors.toList());
    }

    public List<Appointment> getWalkInAppointment(){
        String todayDate = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return appointmentRepo.getAppointmentList().stream()
                .filter(appointment -> appointment.getDate().equals(todayDate) && appointment.getWalkIn().equals(true))
                .collect(Collectors.toList());
    }


    public List<Payment> getPayments(){
        return paymentRepo.getPaymentList().stream()
                .filter(payment -> payment.getStatus().equals(PaymentStatus.PROCESSING))
                .collect(Collectors.toList());
    }

    public Optional<User> checkUserAccount(String email){
        return userRepo.find(email);
    }

    public void createWalkInAppointment(Appointment appointment) throws IOException {
        appointmentRepo.create(appointment);
    }

    public List<Schedule> getDoctorSchedule(){
        String today = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return scheduleRepo.getScheduleList().stream()
                .filter(schedule -> schedule.getDate().equals(today))
                .collect(Collectors.toList());
    }

    public void acceptPayment(Payment payment) throws IOException, ResourceNotFoundException {
        paymentRepo.updateStatus(payment.getPaymentId(), PaymentStatus.PAID);
        medicalRecordRepo.update(payment.getMedicalRecord().getMedicalRecordID(), PaymentStatus.PAID);
    }

    public void updateAdminProfile(Admin admin) throws IOException, ResourceNotFoundException {
        userRepo.update(admin.getUserID(), admin);
    }







}



