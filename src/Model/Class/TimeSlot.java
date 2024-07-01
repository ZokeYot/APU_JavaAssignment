package Model.Class;

import Model.Enum.TimeSlotStatus;

public class TimeSlot {
    private String timeslot;
    private TimeSlotStatus status;

    public TimeSlot(String timeslot, String status){
        this.timeslot = timeslot;
        this.status = TimeSlotStatus.valueOf(status);
    }

    public TimeSlot(String timeslot){
        this.timeslot = timeslot;
        this.status = TimeSlotStatus.AVAILABLE;
    }

    public String toString(){
        return timeslot + "," + status.toString();
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public TimeSlotStatus getStatus() {
        return status;
    }

    public void setStatus(TimeSlotStatus status) {
        this.status = status;
    }
}
