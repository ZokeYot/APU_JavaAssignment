package Service;

import Model.Enum.PaymentStatus;
import Repository.*;
import Model.Class.*;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
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


    public AdminService(RepoFactory repoFactory){
        userRepo = repoFactory.getUserRepo();
        patientRepo = repoFactory.getPatientRepo();
        doctorRepo = repoFactory.getDoctorRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
        scheduleRepo = repoFactory.getScheduleRepo();
        paymentRepo = repoFactory.getPaymentRepo();
    }




    /*
        All the functions with getXXXXs name one (with List<Object[]> as return type one), call it and then create a
        List<Object[]> to store it, then use the for loop to iterate the list and add each Object[] into the table

        * for the for loop u can use this:
        for(Object[] object : the List<Object[]> u create)

        then use addRow() to add into table

        but for medical records right, I assume the admin have to enter the patient name/email first then baru show, not
        showing all the medical records in the system by default
     */
    public List<Object[]> getDoctors(){
        return doctorRepo.getDoctorList().stream()
                .map(doctor -> new Object[]{
                        doctor.getUserID(),
                        doctor.getName(),
                        doctor.getEmail()
                })
                .collect(Collectors.toList());}


    public List<Object[]> getPatients(){
        return patientRepo.getPatientList().stream()
                .map(patient -> new Object[]{
                        patient.getUserID(),
                        patient.getName(),
                        patient.getEmail()
                })
                .collect(Collectors.toList());
    }

    public List<Object[]> getDailyAppointments(){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        return appointmentRepo.getAppointmentList().stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .map(appointment -> new Object[]{
                        appointment.getAppointmentId(),
                        appointment.getDoctor().getName(),
                        appointment.getPatient().getName(),
                        appointment.getAppointmentStatus(),
                        appointment.getWalkIn() ? "yes" : "no"
                })
                .collect(Collectors.toList());
    }

    public List<Object[]> getMedicalRecords(String input){
        return medicalRecordRepo.getMedicalRecordList().stream()
                .filter(
                        medicalRecord -> medicalRecord.getPatient().getName().toLowerCase().contains(input) ||
                                        medicalRecord.getPatient().getEmail().toLowerCase().contains(input))
                .map(medicalRecord -> new Object[]{
                        medicalRecord.getMedicalRecordID(),
                        medicalRecord.getDoctor().getName(),
                        medicalRecord.getPatient().getName(),
                        medicalRecord.getDate()
                })
                .collect(Collectors.toList());
    }


    public List<Object[]> getPayments(){
        return paymentRepo.getPaymentList().stream()
                .filter(payment -> payment.getStatus().equals(PaymentStatus.PROCESSING))
                .map(payment -> new Object[]{
                                payment.getPaymentId(),
                                payment.getMedicalRecord().getMedicalRecordID(),
                                payment.getPaymentInformation().getPatient().getName(),
                                payment.getAmount()
                        }
                )
                .collect(Collectors.toList());
    }

    /*
        Function with the name getXXXXXX name one is for u get the details of the object when u select a row from table
        so cause for the table one I did not display all the information
        (For example patient and doctor only display id, name and email. Patient got more info like age, height and gender something)
        Then when u select a row, u get the id of it from the row and then pass to its getXXXX function, it will return the object
        then u display other information under table section
     */

    public Patient getPatient(UUID patientId) throws ResourceNotFoundException{
        return patientRepo.find(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient Profile Not Found"));
    }

    public Doctor getDoctor(UUID doctorId) throws ResourceNotFoundException{
        return doctorRepo.find(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Profile Not Found"));
    }

    public Appointment getAppointment(UUID appointmentId) throws ResourceNotFoundException{
        return appointmentRepo.find(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment Record Not Found"));
    }

    public MedicalRecord getMedicalRecord(UUID medicalRecordID) throws ResourceNotFoundException{
        return medicalRecordRepo.find(medicalRecordID)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record Not Found"));
    }


    public Payment getPayment(UUID paymentID) throws ResourceNotFoundException{
        return paymentRepo.find(paymentID)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Record Not Found"));
    }

    // 1. Register Doctor / Patient one

    public void checkDuplicateEmail(String email) throws DuplicateUserEmailException{
        Optional<User> duplicate = userRepo.find(email);

        if(duplicate.isPresent())
            throw new DuplicateUserEmailException("Email " + email + " has been taken");
    }

    // Register Doctor one use this
    public void register(Doctor doctor) throws DuplicateUserEmailException, IOException{
        checkDuplicateEmail(doctor.getEmail());
        userRepo.create(doctor);
        doctorRepo.create(doctor);
    }

    // Register patient one use this
    public void register(Patient patient) throws DuplicateUserEmailException, IOException{
        checkDuplicateEmail(patient.getEmail());
        userRepo.create(patient);
        patientRepo.create(patient);
    }
    /*
     For the  delete function right, u can use the getXXXX function to get the selected object first, then pass
     the object to the function

     for delete one just pass then settle lah
     */
    public void delete(Doctor doctor) throws IOException, ResourceNotFoundException {
        userRepo.delete(doctor.getUserID());
        doctorRepo.delete(doctor.getUserID());
    }

    public void delete(Patient patient) throws IOException, ResourceNotFoundException{
        userRepo.delete(patient.getUserID());
        patientRepo.delete(patient.getUserID());
    }

    // 2. Manage Walk In Appointment (This one I do)


    // 3. Track Patient Medical Records
    // Erm, this one cause only view mah, so use the getMedicalRecords() and getMedicalRecord enough
    // (If u want to add a search function let me know )


    // 4. Track Daily Appointment
    // This one same as the medical records lah, use the getAppointments() and getAppointment enough already,
    // no need perform any update or delete actions mah

    // 5. Collect Payment
    // This one simple lah, use the getPayment() to get the selected payment then pass to this function settle
    public void updatePaymentStatus(Payment payment) throws IOException, ResourceNotFoundException {
        paymentRepo.update(payment.getPaymentId(), PaymentStatus.PAID);
    }

}
