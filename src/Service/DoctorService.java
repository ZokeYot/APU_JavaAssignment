package Service;

import Model.Class.*;
import Model.Enum.TimeSlotStatus;
import Repository.*;
import Exception.*;

import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

import static Model.Enum.AppointmentStatus.*;

public class DoctorService {

    private final ScheduleRepo scheduleRepo;
    private final AppointmentRepo appointmentRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final PaymentRepo paymentRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public DoctorService(RepoFactory repoFactory){
        scheduleRepo = repoFactory.getScheduleRepo();
        appointmentRepo = repoFactory.getAppointmentRepo();
        medicalRecordRepo = repoFactory.getMedicalRecordRepo();
        paymentRepo = repoFactory.getPaymentRepo();
        patientRepo = repoFactory.getPatientRepo();
        doctorRepo = repoFactory.getDoctorRepo();
    }

    // 1. Upload Daily Schedule
    public void uploadSchedule(Doctor doctor, List<String> timeSlot) throws ResourceNotFoundException, IOException {
        List<TimeSlot> slots = new ArrayList<>();
        timeSlot.forEach(slot -> slots.add(new TimeSlot(TimeTable.getTimeSlot(slot))));
        scheduleRepo.update(doctor.getUserID() , slots);
    }

    public Set<String> getDoctorSchedule(Doctor doctor){
        Optional<Schedule> schedule = scheduleRepo.find(doctor.getUserID());

        return schedule.map(value -> value.getTimeslots().stream()
                .map(timeSlot -> TimeTable.getIndex(timeSlot.getTimeslot()))
                .collect(Collectors.toSet())).orElseGet(Collections::emptySet);
    }

    // 2. Track Appointment
    public List<Appointment> getAppointments(Doctor doctor){
        return appointmentRepo.getUserAppointments(doctor);
    }

    public Appointment getAppointment(UUID appointmentId) throws Exception{
        return appointmentRepo.getAppointmentList().stream()
                .filter(appointment -> appointment.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElseThrow(() -> new Exception("Appointment Not Found"));
    }

    public Optional<Patient> searchPatient(String search){
        return patientRepo.getPatientList().stream()
                .filter(patient -> patient.getUserID().equals(
                        UUID.fromString(search)) ||
                        patient.getName().toLowerCase().contains(search) ||
                        patient.getEmail().toLowerCase().contains(search))
                .findFirst();
    }

    // 3. Cancel Appointment
    public void cancelAppointment(Appointment appointment) throws ResourceNotFoundException, IOException {
        appointmentRepo.update(appointment.getAppointmentId(), CANCELLED);
        scheduleRepo.updateTimeSlotStatus(appointment.getDoctor(),appointment.getSlot(), TimeSlotStatus.AVAILABLE);
    }

    // 4. Track Patient's Medical Record
    public List<MedicalRecord> getMedicalRecords(Doctor doctor){
        return medicalRecordRepo.getMedicalRecord(doctor);
    }

    public List<MedicalRecord> getPatientMedicalRecords(Patient patient){
        return medicalRecordRepo.getMedicalRecordList().stream()
                .filter(medicalRecord -> medicalRecord.getPatient().equals(patient))
                .collect(Collectors.toList());
    }

    public MedicalRecord getMedicalRecord(UUID medicalRecordId) throws ResourceNotFoundException{
        return medicalRecordRepo.find(medicalRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record Not Found"));
    }

    // 5. Add Medical Record
    public void createMedicalRecord(MedicalRecord medicalRecord) throws ResourceNotFoundException, IOException {
        medicalRecordRepo.create(medicalRecord);
        appointmentRepo.update(medicalRecord.getAppointmentId(), COMPLETED);
        paymentRepo.create(new Payment(medicalRecord,new PaymentMethod(), 100));
    }

    public void updateDoctorProfile(Doctor doctor) throws ResourceNotFoundException, IOException {
        doctorRepo.update(doctor.getUserID(), doctor);
    }


}
