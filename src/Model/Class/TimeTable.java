package Model.Class;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeTable {

    private final static HashMap<String, String> timetable = new HashMap<>();

    static{
        init_timeTable();
    }

    public static String getTimeSlot(String index){
        return timetable.get(index);
    }

    public static String getIndex(String slot){
        for(Map.Entry<String, String> entry : timetable.entrySet()){
            if(entry.getValue().equals(slot))
                return entry.getKey();
        }
        return "Index Not Found !!";
    }


    private static void init_timeTable(){
        String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        timetable.put("1" , todayDate + ", 8.00 - 9.00");
        timetable.put("2" , todayDate + ", 9.00 - 10.00");
        timetable.put("3" , todayDate + ", 10.00 - 11.00");
        timetable.put("4" , todayDate + ", 11.00 - 12.00");
        timetable.put("5" , todayDate + ", 12.00 - 13.00");
        timetable.put("6" , todayDate + ", 13.00 - 14.00");
        timetable.put("7" , todayDate + ", 14.00 - 15.00");
        timetable.put("8" , todayDate + ", 15.00 - 16.00");
        timetable.put("9" , todayDate + ", 16.00 - 17.00");
    }
}
