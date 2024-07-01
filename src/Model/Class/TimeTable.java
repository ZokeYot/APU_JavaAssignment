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

        timetable.put("1" , "8.00 - 9.00");
        timetable.put("2" , "9.00 - 10.00");
        timetable.put("3" , "10.00 - 11.00");
        timetable.put("4" , "11.00 - 12.00");
        timetable.put("5" , "12.00 - 13.00");
        timetable.put("6" , "13.00 - 14.00");
        timetable.put("7" , "14.00 - 15.00");
        timetable.put("8" , "15.00 - 16.00");
        timetable.put("9" , "16.00 - 17.00");
    }
}
