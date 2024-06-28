package Model.Class;

public class Patient extends User {
    private int age;
    private String gender;
    private double height;
    private double weight;

    // This one is for file reading
    public Patient(User user, String gender, int age, double height, double weight){
        super(user);
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }



    public Patient(){

    }

    public String toString(){
        return getUserID().toString() + "|" + gender + "|" + age + "|" + height + "|" + weight;
    }

    public String[] toArray(){
        return new String[]{getName(), getEmail(), getPassword(), getGender(),
                String.valueOf(getHeight()), String.valueOf(getWeight())};
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
