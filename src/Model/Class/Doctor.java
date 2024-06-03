package Model.Class;

public class Doctor extends User {

    private String gender;
    private String department;

    public Doctor(){}

    // This one for file reading
    public Doctor(User user, String gender, String department){
        super(user);
        this.gender = gender;
        this.department = department;
    }


    @Override
    public String toString(){
        return getUserID().toString()  + "|" + gender  + "|" + department;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
