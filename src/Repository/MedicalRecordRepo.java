package Repository;

import Model.Class.Appointment;
import Model.Class.Doctor;
import Model.Class.MedicalRecord;
import Model.Class.Patient;
import Model.Enum.PaymentStatus;
import Exception.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MedicalRecordRepo {

    private final AppointmentRepo appointmentRepo;
    private final Map<UUID, MedicalRecord> medicalRecordMap;
    private final Scanner scanner;

    public MedicalRecordRepo(AppointmentRepo appointmentRepo) throws FileNotFoundException, ResourceNotFoundException{
        this.appointmentRepo = appointmentRepo;
        medicalRecordMap = new HashMap<>();
        scanner = new Scanner(new File("src\\Text Files\\medical record.txt"));

        readFile();
    }


    private void readFile() throws ResourceNotFoundException{
        System.out.println("Reading Medical Record File.....");
        while (scanner.hasNextLine()){
            String[] line = scanner.nextLine().trim().split("\\|");

            String medicalRecordId = line[0];
            UUID appointmentId = UUID.fromString(line[1]);
            String diagnosisResult = line[2];
            String recommendation = line[3];
            PaymentStatus status = PaymentStatus.valueOf(line[4]);

            Appointment appointment = appointmentRepo.find(appointmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment Not Found"));

            MedicalRecord medicalRecord = new MedicalRecord(appointment, medicalRecordId, diagnosisResult, recommendation, status);
            medicalRecordMap.put(medicalRecord.getMedicalRecordID(), medicalRecord);
        }
    }


    private void updateFile() throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\medical record.txt"));
        for(MedicalRecord medicalRecord : medicalRecordMap.values()){
            fileWriter.write(medicalRecord.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }

    public Map<UUID, MedicalRecord> getMedicalRecordMap(){return medicalRecordMap;}

    public List<MedicalRecord> getMedicalRecordList(){ return medicalRecordMap.values().stream().toList();}

    public List<MedicalRecord> getMedicalRecord(Patient patient){
        return medicalRecordMap.values()
                .stream()
                .filter(medicalRecord -> medicalRecord.getPatient().equals(patient))
                .collect(Collectors.toList());}

    public List<MedicalRecord> getMedicalRecord(Doctor doctor){
        return medicalRecordMap.values()
                .stream()
                .filter(medicalRecord -> medicalRecord.getDoctor().equals(doctor))
                .collect(Collectors.toList());}

    public Optional<MedicalRecord> find(UUID medicalRecordId){ return Optional.ofNullable(medicalRecordMap.get(medicalRecordId));}


    public Optional<MedicalRecord> find(String medicalRecordId){ return Optional.ofNullable(medicalRecordMap.get(UUID.fromString(medicalRecordId)));}

    public void create(MedicalRecord medicalRecord) throws IOException{
        FileWriter fileWriter = new FileWriter("src\\Text Files\\medical record.txt", true);
        fileWriter.write(medicalRecord.toString());
        fileWriter.write("\n");

        fileWriter.close();
        medicalRecordMap.put(medicalRecord.getMedicalRecordID(), medicalRecord);
        getMedicalRecord(medicalRecord.getPatient()).add(medicalRecord);
    }

    public void update(UUID medicalRecordId, PaymentStatus status) throws ResourceNotFoundException, IOException{
        MedicalRecord medicalRecord= find(medicalRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record Not Found"));

        medicalRecord.setPaymentStatus(status);
        updateFile();
    }

}
