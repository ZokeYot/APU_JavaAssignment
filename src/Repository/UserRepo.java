package Repository;

import Model.Class.Admin;
import Model.Class.Doctor;
import Model.Class.Patient;
import Model.Class.User;
import Exception.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

public class UserRepo {

    private final Scanner scanner;
    private final Map<UUID, User> userMap;

    public UserRepo() throws FileNotFoundException {
        File user_file = new File("src\\Text Files\\user.txt");
        this.scanner = new Scanner(user_file);
        userMap = new HashMap<>();
        readFile();
    }


    private void readFile(){
        System.out.println("Reading User File.....");
        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().trim().split("\\|");
            UUID userId = UUID.fromString(line[0]);
            String name = line[1];
            String email = line[2];
            String password = line[3];
            String role = line[4];

            userMap.put(userId, new User(userId, name, email, password, role));
        }
    }

    private void updateFile() throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\user.txt"));
        for(User user : userMap.values()){
            fileWriter.write(user.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }

    public Map<UUID, User> getUserMap(){ return userMap;}

    public List<User> getUserList(){ return userMap.values().stream().toList();}

    public Optional<User> find(String email){
        return userMap.values()
                .stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<User> find(UUID userId) {
        return Optional.ofNullable(userMap.get(userId));
    }


    public void create(User user) throws IOException {

        FileWriter fileWriter = new FileWriter("src\\Text Files\\user.txt", true);

        fileWriter.write(user.toString());
        fileWriter.write("\n");

        fileWriter.close();
        userMap.put(user.getUserID(), user);
    }

    public void update(UUID userId , User newUser) throws ResourceNotFoundException, IOException {
        User user = find(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        userMap.replace(userId, user);
        updateFile();
    }

    public void delete(UUID userId) throws ResourceNotFoundException, IOException {
         find(userId)
                 .orElseThrow(() -> new ResourceNotFoundException("User Not Found") );

        userMap.remove(userId);
        updateFile();
    }
}
