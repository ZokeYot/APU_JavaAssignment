package Repository;

import Model.Class.Appointment;
import Model.Class.Doctor;
import Model.Class.Patient;
import Exception.*;

import Model.Enum.AppointmentStatus;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;

public class AppointmentRepo{

    private final Scanner scanner;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final Map<UUID, Appointment> appointmentMap;




    public AppointmentRepo(DoctorRepo doctorRepo, PatientRepo patientRepo) throws FileNotFoundException, ResourceNotFoundException {
        this.scanner = new Scanner(new File("src\\Text Files\\appointment.txt"));
        appointmentMap = new HashMap<>();
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;

        readFile();
    }

    private void readFile() throws ResourceNotFoundException {
        System.out.println("Reading Appointment File.....");
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().trim().split("\\|");
            String appointmentID = line[0];
            Optional<Doctor> doctor = doctorRepo.find(line[1]);
            Optional<Patient> patient = patientRepo.find(line[2]);
            String date = line[3];
            String slot = line[4];
            String message = line[5];
            String status = line[6];
            String walkIn = line[7];

            if (!(doctor.isPresent() && patient.isPresent()))
                throw new ResourceNotFoundException("Resources Not Found");
            Appointment appointment = new Appointment(appointmentID, doctor.get(), patient.get(), date, slot, message, status, walkIn);
            System.out.println(appointment.getSlot());
            appointmentMap.put(UUID.fromString(appointmentID), appointment);
        }
    }

    private void updateFile() throws IOException{
       BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\appointment.txt"));

       for(Appointment appointment : appointmentMap.values()) {
           fileWriter.write(appointment.toString());
           fileWriter.newLine();
       }
       fileWriter.close();
    }

    public Map<UUID, Appointment> getAppointmentMap(){ return appointmentMap;}

    public List<Appointment> getAppointmentList(){ return appointmentMap.values().stream().toList();}

    public List<Appointment> getUserAppointments(Patient patient){
        return appointmentMap
                .values().stream()
                .filter(appointment -> appointment.getPatient().getUserID().equals(patient.getUserID()))
                .collect(Collectors.toList());
    }

    public List<Appointment> getUserAppointments(Doctor doctor){
        return appointmentMap
                .values().stream()
                .filter(appointment -> appointment.getDoctor().getUserID().equals(doctor.getUserID()))
                .collect(Collectors.toList());
    }

    public Optional<Appointment> find(UUID appointmentId){ return Optional.ofNullable(appointmentMap.get(appointmentId));}


    public void create(Appointment appointment) throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\appointment.txt", true));

        fileWriter.write(appointment.toString());
        fileWriter.newLine();

        fileWriter.close();
        appointmentMap.put(appointment.getAppointmentId(), appointment);
    }


    public void update(UUID appointmentId, AppointmentStatus status) throws ResourceNotFoundException, IOException{
            Appointment appointment = find(appointmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment Not Found"));

            appointment.setAppointmentStatus(status);
            updateFile();
    }

}
