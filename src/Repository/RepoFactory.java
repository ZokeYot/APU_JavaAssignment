package Repository;

import java.io.FileNotFoundException;
import Exception.*;

public class RepoFactory {

    private UserRepo userRepo;
    private AdminRepo adminRepo;
    private DoctorRepo doctorRepo;
    private PatientRepo patientRepo;
    private AppointmentRepo appointmentRepo;
    private MedicalRecordRepo medicalRecordRepo;
    private ScheduleRepo scheduleRepo;
    private PaymentInformationRepo paymentInformationRepo;
    private PaymentRepo paymentRepo;

   public RepoFactory(){

   }

   public UserRepo getUserRepo(){
       if(userRepo == null){
           try{
               userRepo = new UserRepo();
           }catch (FileNotFoundException error){
               System.out.println(error.getMessage());
           }
       }
       return userRepo;
   }

   public AdminRepo getAdminRepo(){
       if(adminRepo == null)
           adminRepo = new AdminRepo(getUserRepo());
       return adminRepo;
   }

   public DoctorRepo getDoctorRepo(){
       if(doctorRepo == null) {
           try {
               doctorRepo = new DoctorRepo(getUserRepo());
           } catch (FileNotFoundException error) {
               throw new RuntimeException(error.getMessage());
           }
       }
        return doctorRepo;
   }

   public PatientRepo getPatientRepo(){
       if(patientRepo == null) {
           try {
               patientRepo = new PatientRepo(getUserRepo());
           } catch (FileNotFoundException error) {
               throw new RuntimeException(error.getMessage());
           }
       }
       return patientRepo;
   }

   public AppointmentRepo getAppointmentRepo(){
       if(appointmentRepo == null){
           try{
               appointmentRepo = new AppointmentRepo(getDoctorRepo(), getPatientRepo());
           }catch (FileNotFoundException | ResourceNotFoundException error){
               throw new RuntimeException(error.getMessage());
           }
       }

       return appointmentRepo;
   }

   public MedicalRecordRepo getMedicalRecordRepo(){
       if(medicalRecordRepo == null){
           try{
                medicalRecordRepo = new MedicalRecordRepo(getAppointmentRepo());
           }catch (FileNotFoundException | ResourceNotFoundException error){
               throw new RuntimeException(error.getMessage());
           }
       }
       return medicalRecordRepo;
   }

   public ScheduleRepo getScheduleRepo(){
       if(scheduleRepo == null){
           try{
               scheduleRepo = new ScheduleRepo(getDoctorRepo());
           }catch (FileNotFoundException | ResourceNotFoundException error){
               throw new RuntimeException(error.getMessage());
           }
       }
       return scheduleRepo;
   }

   public PaymentInformationRepo getPaymentInformationRepo(){
        if(paymentInformationRepo == null){
            try{
                paymentInformationRepo = new PaymentInformationRepo(getPatientRepo());
            }catch (FileNotFoundException | ResourceNotFoundException error){
                throw new RuntimeException(error.getMessage());
            }
        }
        return paymentInformationRepo;
   }

   public PaymentRepo getPaymentRepo(){
        if(paymentRepo == null){
            try{
                paymentRepo = new PaymentRepo(getPaymentInformationRepo());
            }catch (FileNotFoundException | ResourceNotFoundException error){
                throw new RuntimeException(error.getMessage());
            }
        }
        return paymentRepo;
   }

}
