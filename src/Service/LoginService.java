package Service;


import Model.Class.User;
import Repository.*;
import Exception.*;

import java.util.Optional;

public class LoginService {

    private final UserRepo userRepo;
    private final AdminRepo adminRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;

    public LoginService(RepoFactory repoFactory){
        userRepo = repoFactory.getUserRepo();
        adminRepo = repoFactory.getAdminRepo();
        doctorRepo = repoFactory.getDoctorRepo();
        patientRepo = repoFactory.getPatientRepo();
    }

    public User login(String email, String password) throws ResourceNotFoundException, IncorrectCredentialException{
        Optional<User> credential = userRepo.find(email);

        if(credential.isEmpty())
            throw new ResourceNotFoundException("Does not found an account associate with " + email);

        User user = credential.get();

        if(!user.getPassword().equals(password))
            throw new IncorrectCredentialException("Wrong Password");

        switch (user.getRole()) {
            case "admin":
                return adminRepo.find(user.getUserID())
                        .orElseThrow(() -> new ResourceNotFoundException("Admin Profile Not Found"));
            case "doctor":
                return doctorRepo.find(user.getUserID())
                        .orElseThrow(() -> new ResourceNotFoundException("Doctor Profile Not Found"));
            default:
                return patientRepo.find(user.getUserID())
                        .orElseThrow(() -> new ResourceNotFoundException("Patient Profile Not Found"));
        }
    }
}
