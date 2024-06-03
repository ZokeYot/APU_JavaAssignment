package Service;

import Model.Enum.PaymentStatus;
import Repository.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import Exception.*;

public class AdminService {

    private final UserRepo userRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final ScheduleRepo scheduleRepo;
    private final PaymentRepo paymentRepo;


    public AdminService(RepoFactory repoFactory){
        userRepo = repoFactory.getUserRepo();
        patientRepo = repoFactory.getPatientRepo();
        doctorRepo = repoFactory.getDoctorRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
        scheduleRepo = repoFactory.getScheduleRepo();
        paymentRepo = repoFactory.getPaymentRepo();
    }


    // 1. Manage User Registration

    // Return all the users in the system
//    public List<User> getUsers(){
//        return userRepo.getUserList();
//    }
//
//
//    // Register a new user into the system (either patient / doctor)
//    public void register(User user) throws DuplicateUserEmailException{
//        Optional<User> duplicate = userRepo.find(user.getEmail());
//
//        if(duplicate.isPresent())
//            throw new DuplicateUserEmailException("Duplicate Email !!");
//
//        userRepo.create(user);
//        if(user instanceof Doctor)
//            doctorRepo.create((Doctor) user);
//        else if(user instanceof Patient)
//            patientRepo.create((Patient) user);
//    }
//
//    // Delete a user from the system (either patient / doctor)
//    public void delete(User user) throws ResourceNotFoundException{
//        Optional<User> credential =  userRepo.find(user.getUserID());
//
//        if(credential.isEmpty())
//            throw new ResourceNotFoundException("User Not Found");
//
//        userRepo.delete(user.getUserID());
//        if(user instanceof Doctor){
//            doctorRepo.delete(user.getUserID());
//            scheduleRepo.delete(user.getUserID());
//        } else if(user instanceof Patient){
//            patientRepo.delete(user.getUserID());
//        }
//
//
//    }
//
//    // 2. Manage Walk In Appointment
//
//    // Get all the available timeslots
//    public List<Schedule> getDoctorSchedule(){
//        return scheduleRepo.getScheduleList();
//    }
//
//    public void createWalkInAppointment(Appointment appointment){
//        appointmentRepo.create(appointment);
//    }
//
//    // 3. Track Patient's Medical Record
//
//    // Get all the patients
//    public List<Patient> getPatients(){
//        return patientRepo.getPatientList();
//    }
//
//
//    // Get the patient's medical record
//    public List<MedicalRecord> getMedicalRecords(Patient patient){
//       return medicalRecordRepo.getMedicalRecord(patient);
//    }
//
//    // 4. Track Daily Appointment
//    // Get Daily Appointment
//    public List<Appointment> getDailyAppointments(){
//        LocalDate today = LocalDate.now();
//        DayOfWeek dayOfWeek = today.getDayOfWeek();
//
//        List<Appointment> appointments = appointmentRepo.getAppointmentList();
//
//        return appointments.stream()
//                .filter(appointment ->
//                        appointment.getTimeSlot().split(",")[0].equalsIgnoreCase(dayOfWeek.toString()))
//                .collect(Collectors.toList());
//    }
//
//    // Get Appointment Details
//    public Appointment getAppointment(Appointment appointment) throws ResourceNotFoundException {
//        return appointmentRepo.find(appointment.getAppointmentId())
//                .orElseThrow(() -> new ResourceNotFoundException("Appointment Not Found"));
//    }
//
//
//    // 5. Collect Payment
//    // Get all the payment from the patient
//    public List<Payment> getPayments(){
//        return paymentRepo.getPaymentList();
//    }
//
//    // Update the payment status
//    public void updatePaymentStatus(Payment payment, PaymentStatus status) throws ResourceNotFoundException{
//        paymentRepo.update(payment.getPaymentId(), status);
//    }












}
