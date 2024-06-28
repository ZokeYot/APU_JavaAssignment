package Repository;

import Model.Class.Doctor;
import Model.Class.Schedule;
import Model.Class.TimeSlot;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.*;

import Exception.*;
import Model.Class.TimeTable;

public class ScheduleRepo {

    private final Map<UUID, Schedule> scheduleMap;
    private final DoctorRepo doctorRepo;
    private final Scanner scanner;

    public ScheduleRepo(DoctorRepo doctorRepo) throws FileNotFoundException, ResourceNotFoundException {
        this.doctorRepo = doctorRepo;
        scheduleMap = new HashMap<>();
        scanner = new Scanner(new File("src\\Text Files\\schedule.txt"));

        readFile();
    }

    private void readFile() throws  ResourceNotFoundException{
        while(scanner.hasNextLine()){
            System.out.println("Reading Schedule File.....");

            String[] line  = scanner.nextLine().trim().split("\\|");
            Doctor doctor = doctorRepo.find(line[0])
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
            String date = line[1];
            ArrayList<TimeSlot> timeSlots = new ArrayList<>();

            for(int i = 2; i < line.length; i++){
                String[] timeSlot = line[i].split(",");
                timeSlots.add(new TimeSlot(timeSlot[0], timeSlot[1]));
            }
            scheduleMap.put(doctor.getUserID(), new Schedule(doctor, date, timeSlots));
        }
    }

    private void updateFile() throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Text Files\\schedule.txt"));
        for(Schedule schedule : scheduleMap.values()){
            fileWriter.write(schedule.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }

    public Map<UUID, Schedule> getScheduleMap(){return scheduleMap;}

    public List<Schedule> getScheduleList(){ return scheduleMap.values().stream().toList();}

    public Optional<Schedule> find(UUID doctorId){
        return Optional.ofNullable(scheduleMap.get(doctorId));
    }

    private void create(Schedule schedule) throws IOException{
        FileWriter fileWriter = new FileWriter("Text Files\\schedule.txt");

        fileWriter.write(schedule.toString());
        fileWriter.write("\n");

        fileWriter.close();
        scheduleMap.put(schedule.getDoctor().getUserID() , schedule);
    }

    public void update(UUID doctorId, List<TimeSlot> timeSlots) throws ResourceNotFoundException, IOException{
        Optional<Schedule> schedule = find(doctorId);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        if(schedule.isEmpty()){
            Doctor doctor = doctorRepo.find(doctorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
            create(new Schedule(doctor, date, timeSlots));
        }
        else{
            schedule.get().setTimeslots(timeSlots);
            updateFile();
        }
    }

    public void delete(UUID doctorId) throws ResourceNotFoundException, IOException{
        Schedule schedule = find(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule Not Found !!"));

        scheduleMap.remove(doctorId);
        updateFile();
    }
}
