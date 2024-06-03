package Repository;


import Model.Class.Admin;

import java.util.*;

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
}
