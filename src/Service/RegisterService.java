package Service;

import Model.Class.Patient;
import Model.Class.User;
import Repository.PatientRepo;
import Repository.RepoFactory;
import Repository.UserRepo;

import java.io.IOException;
import java.util.Optional;

public class RegisterService {

    private final UserRepo userRepo;
    private final PatientRepo patientRepo;

    public RegisterService(RepoFactory repoFactory){
        userRepo = repoFactory.getUserRepo();
        patientRepo = repoFactory.getPatientRepo();
    }

    public Optional<Patient> register(User user, String gender, int age, double height, double weight){
      try{
          userRepo.create(user);

          Patient patient = new Patient(user, gender, age, height, weight);
          patientRepo.create(patient);

          return Optional.of((Patient) patient);
      }catch (IOException error){
          return Optional.empty();
      }
    }

}
