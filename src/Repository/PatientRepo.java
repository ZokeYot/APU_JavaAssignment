package Repository;

import Model.Class.Patient;
import Model.Class.User;
import Exception.*;

import java.io.*;
import java.util.*;

public class PatientRepo {

    private final List<Patient> patient_list;
    private final UserRepo userRepo;
    private final Scanner scanner;
    private final Map<UUID, Patient> patientMap;


    public PatientRepo(UserRepo userRepo) throws FileNotFoundException {
        this.userRepo = userRepo;
        scanner = new Scanner(new File("src\\Text Files\\patient.txt"));
        patient_list = new ArrayList<>();
        patientMap = new HashMap<>();

        readFile();
    }

    private void readFile(){
        while(scanner.hasNextLine()){
            System.out.println("Reading Patient File.....");
            String[] line = scanner.nextLine().trim().split("\\|");

            UUID userId = UUID.fromString(line[0]);
            String gender = line[1];
            int age = Integer.parseInt(line[2]);
            double height = Double.parseDouble(line[3]);
            double weight = Double.parseDouble(line[4]);
            User user = userRepo.getUserMap().get(userId);

            patientMap.put(userId, new Patient(user, gender, age, height, weight));
        }
    }



    private void updateFile() throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Text Files\\patient.txt"));
        for (Patient patient : patientMap.values()){
            fileWriter.write(patient.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }

    public Map<UUID, Patient> getPatientMap(){ return patientMap;}

    public List<Patient> getPatientList(){ return patientMap.values().stream().toList();}

    public Optional<Patient> find(UUID userId){ return Optional.ofNullable(patientMap.get(userId));}

    public Optional<Patient> find(String userId){ return Optional.ofNullable(patientMap.get(UUID.fromString(userId)));}

    public void create(Patient patient) throws IOException {
        FileWriter fileWriter = new FileWriter("Text Files\\patient.txt" , true);

        fileWriter.write(patient.toString());
        fileWriter.write("\n");

        fileWriter.close();
        patientMap.put(patient.getUserID(), patient);
    }

    public void delete(UUID userId) throws ResourceNotFoundException, IOException{
        find(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient Profile Not Found"));

        patientMap.remove(userId);
        updateFile();
    }




}
