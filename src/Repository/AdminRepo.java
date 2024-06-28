package Repository;


import Model.Class.Admin;

import java.io.IOException;
import java.util.*;
import Exception.*;

public class AdminRepo {

    private final UserRepo userRepo;
    private final Map<UUID, Admin> adminMap;

    public AdminRepo(UserRepo userRepo){
        this.userRepo = userRepo;
        adminMap = new HashMap<>();
        readFile();
    }


    public Map<UUID, Admin> getAll(){
        return adminMap;
    }

    public Optional<Admin> find(UUID userId){
        return Optional.ofNullable(adminMap.get(userId));
    }

    public Optional<Admin> find(String userId){
        return Optional.ofNullable(adminMap.get(UUID.fromString(userId)));
    }

    private void readFile(){
        userRepo.getUserList()
                .stream()
                .filter(user -> user.getRole().equalsIgnoreCase("admin"))
                .forEach(user -> adminMap.put(user.getUserID(), new Admin(user)));

    }


    private void update(UUID userID, Admin newAdmin) throws ResourceNotFoundException, IOException {
        find(userID)
                .orElseThrow(() -> new ResourceNotFoundException("Admin Profile Not Found !!"));

        adminMap.replace(userID, newAdmin);
        userRepo.update(userID, newAdmin);
    }
}
