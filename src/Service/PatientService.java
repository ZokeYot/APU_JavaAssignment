package Service;

import Model.Class.Appointment;
import Model.Class.MedicalRecord;
import Model.Class.Patient;
import Model.Class.Schedule;
import Repository.AppointmentRepo;
import Repository.MedicalRecordRepo;
import Repository.RepoFactory;
import Repository.ScheduleRepo;
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


    public PatientService(RepoFactory repoFactory){
        scheduleRepo = repoFactory.getScheduleRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
    }


    // 1. View Available Time Slot
    public List<Schedule> getTimeSlot(){
        return scheduleRepo.getScheduleList();
    }

    // 2. Make Appointment
    public void createAppointment(Appointment appointment) throws IOException {
        appointmentRepo.create(appointment);
    }

    // 3. Cancel Appointment

    // Get all the current pending appointments
    public List<Appointment> viewAppointments(Patient patient){
        List<Appointment> appointments = appointmentRepo.getUserAppointments(patient);

        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentStatus().equals(PENDING))
                .collect(Collectors.toList());
    }


    public void cancelAppointment(UUID appointmentId) throws ResourceNotFoundException, IOException {
        appointmentRepo.update(appointmentId, CANCELLED);
    }

    // 4. Track Medical Record
    public List<MedicalRecord> viewMedicalRecord(Patient patient){
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
