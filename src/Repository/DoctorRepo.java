package Repository;

import Model.Class.Doctor;
import Exception.ResourceNotFoundException;
import Model.Class.User;


import java.io.*;
import java.util.*;

public class DoctorRepo {

    private final UserRepo userRepo;
    private final Scanner scanner;
    private final Map<UUID, Doctor> doctorMap;

    public DoctorRepo(UserRepo userRepo) throws FileNotFoundException {
        this.userRepo = userRepo;
        doctorMap = new HashMap<>();
        scanner = new Scanner(new File("src\\Text Files\\doctor.txt"));

        readFile();
    }


    private void readFile(){
        while(scanner.hasNextLine()){
            System.out.println("Reading Doctor File.....");
            String[] line = scanner.nextLine().trim().split("\\|");
            UUID userId = UUID.fromString(line[0]);
            String gender = line[1];
            String department = line[2];
            User user = userRepo.getUserMap().get(userId);

            doctorMap.put(userId, new Doctor(user, gender, department));
        }
    }

    private void updateFile() throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Text Files\\doctor.txt"));

        for(Doctor doctor : doctorMap.values()){
            fileWriter.write(doctor.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }

    public Map<UUID, Doctor> getDoctorMap(){return doctorMap;}

    public List<Doctor> getDoctorList(){ return doctorMap.values().stream().toList();}

    public Optional<Doctor> find(UUID userId){ return Optional.ofNullable(doctorMap.get(userId));}

    public Optional<Doctor> find(String userId){ return Optional.ofNullable(doctorMap.get(UUID.fromString(userId)));}

    public void create(Doctor doctor) throws IOException{
        FileWriter fileWriter = new FileWriter("Text Files\\doctor.txt" , true);
        fileWriter.write(doctor.toString());
        fileWriter.write("\n");

        fileWriter.close();
        doctorMap.put(doctor.getUserID() , doctor);
    }


    public void update(UUID doctorId, Doctor newDoctor) throws ResourceNotFoundException, IOException {
        find(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor profile not found"));

        doctorMap.replace(doctorId, newDoctor);
        updateFile();
    }


    public void delete(UUID userId) throws ResourceNotFoundException, IOException{
        find(userId)
               .orElseThrow(() -> new ResourceNotFoundException("Doctor profile not found"));

        doctorMap.remove(userId);
        updateFile();
    }

}
