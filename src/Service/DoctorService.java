package Service;

import Model.Class.Appointment;
import Model.Class.Doctor;
import Model.Class.MedicalRecord;
import Repository.AppointmentRepo;
import Repository.MedicalRecordRepo;
import Repository.RepoFactory;
import Repository.ScheduleRepo;
import Exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Model.Enum.AppointmentStatus.*;

public class DoctorService {

    private final ScheduleRepo scheduleRepo;
    private final AppointmentRepo appointmentRepo;
    private final MedicalRecordRepo medicalRecordRepo;

    public DoctorService(RepoFactory repoFactory){
        scheduleRepo = repoFactory.getScheduleRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
    }

    // 1. Upload Daily Schedule
    public void uploadSchedule(Doctor doctor, ArrayList<String> timeSlot) throws ResourceNotFoundException, IOException {
        scheduleRepo.update(doctor.getUserID() , timeSlot);
    }

    // 2. Track Appointment
    public List<Appointment> getAppointments(Doctor doctor){
        List<Appointment> appointments =  appointmentRepo.getUserAppointments(doctor);

        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentStatus().equals(PENDING))
                .collect(Collectors.toList());
    }

    // 3. Cancel Appointment
    public void cancelAppointment(Appointment appointment) throws ResourceNotFoundException, IOException {
        appointmentRepo.update(appointment.getAppointmentId(), CANCELLED);
    }

    // 4. Track Patient's Medical Record
    public List<MedicalRecord> getPatientsMedicalRecord(Doctor doctor){
        return medicalRecordRepo.getMedicalRecord(doctor);
    }

    public MedicalRecord getMedicalRecord(MedicalRecord medicalRecord) throws ResourceNotFoundException, IOException {
        return medicalRecordRepo.find(medicalRecord.getMedicalRecordID())
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record Not Found"));
    }

    // 5. Add Medical Record
    public void createMedicalRecord(Appointment appointment, MedicalRecord medicalRecord) throws ResourceNotFoundException, IOException {
        medicalRecordRepo.create(medicalRecord);
        appointmentRepo.update(appointment.getAppointmentId(), COMPLETED);
    }


}
