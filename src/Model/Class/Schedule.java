package Model.Class;


import java.util.ArrayList;
import java.util.HashMap;



public class Schedule {

    private Doctor doctor;
    private ArrayList<String> timeslots;

    public Schedule(){}

    public Schedule(Doctor doctor, ArrayList<String> timeSlots){
       this.doctor = doctor;
       this.timeslots = timeSlots;
    }


    public String toString(){
        return doctor.getUserID().toString() + "|" +
                String.join("\\|" , timeslots);
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public ArrayList<String> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(ArrayList<String> timeslots) {
        this.timeslots = timeslots;
    }
}
