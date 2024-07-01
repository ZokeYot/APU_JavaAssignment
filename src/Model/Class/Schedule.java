package Model.Class;


import Model.Enum.TimeSlotStatus;

import java.sql.Time;
import java.util.List;


public class Schedule {

    private Doctor doctor;
    private String date;
    private List<TimeSlot> timeslots;

    public Schedule(){}

    public Schedule(Doctor doctor,String date, List<TimeSlot> timeSlots){
       this.doctor = doctor;
       this.date = date;
       this.timeslots = timeSlots;
    }


    public String toString(){
        return doctor.getUserID().toString() + "|" +
                date + "|" +
                String.join("|",
                        timeslots.stream().map(timeSlot -> TimeTable.getIndex(timeSlot.getTimeslot()) + "," + timeSlot.getStatus()).toList());
    }



    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<TimeSlot> timeslots) {
        this.timeslots = timeslots;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
