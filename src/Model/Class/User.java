package Model.Class;

import java.util.UUID;

public class User {


    private UUID userID;
    private String name;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(User user){
        userID = user.userID;
        name = user.name;
        email = user.email;
        password = user.password;
        role = user.role;
    }

    public User(String name, String email, String password, String role) {
        this.userID = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(UUID userID, String name, String email, String password, String role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String toString(){
        return userID.toString() + "|" + name  + "|" + email  + "|" + password  + "|"  + role;
    }


    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
