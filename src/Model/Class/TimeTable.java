package Model.Class;

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
        timetable.put("1" , "Monday, 8.00 - 9.00");
        timetable.put("2" , "Monday, 9.00 - 10.00");
        timetable.put("3" , "Monday, 10.00 - 11.00");
        timetable.put("4" , "Monday, 11.00 - 12.00");
        timetable.put("5" , "Monday, 12.00 - 13.00");
        timetable.put("6" , "Monday, 13.00 - 14.00");
        timetable.put("7" , "Monday, 14.00 - 15.00");
        timetable.put("8" , "Monday, 15.00 - 16.00");
        timetable.put("9" , "Monday, 16.00 - 17.00");
        timetable.put("10" , "Tuesday, 8.00 - 9.00");
        timetable.put("11" , "Tuesday, 9.00 - 10.00");
        timetable.put("12" , "Tuesday, 10.00 - 11.00");
        timetable.put("13" , "Tuesday, 11.00 - 12.00");
        timetable.put("14" , "Tuesday, 12.00 - 13.00");
        timetable.put("15" , "Tuesday, 13.00 - 14.00");
        timetable.put("16" , "Tuesday, 14.00 - 15.00");
        timetable.put("17" , "Tuesday, 15.00 - 16.00");
        timetable.put("18" , "Tuesday, 16.00 - 17.00");
        timetable.put("19" , "Wednesday, 8.00 - 9.00");
        timetable.put("20" , "Wednesday, 9.00 - 10.00");
        timetable.put("21" , "Wednesday, 10.00 - 11.00");
        timetable.put("22" , "Wednesday, 11.00 - 12.00");
        timetable.put("23" , "Wednesday, 12.00 - 13.00");
        timetable.put("24" , "Wednesday, 13.00 - 14.00");
        timetable.put("25" , "Wednesday, 14.00 - 15.00");
        timetable.put("26" , "Wednesday, 15.00 - 16.00");
        timetable.put("27" , "Wednesday, 16.00 - 17.00");
        timetable.put("28" , "Thursday, 8.00 - 9.00");
        timetable.put("29" , "Thursday, 9.00 - 10.00");
        timetable.put("30" , "Thursday, 10.00 - 11.00");
        timetable.put("31" , "Thursday, 11.00 - 12.00");
        timetable.put("32" , "Thursday, 12.00 - 13.00");
        timetable.put("33" , "Thursday, 13.00 - 14.00");
        timetable.put("34" , "Thursday, 14.00 - 15.00");
        timetable.put("35" , "Thursday, 15.00 - 16.00");
        timetable.put("36" , "Thursday, 16.00 - 17.00");
        timetable.put("37" , "Friday, 8.00 - 9.00");
        timetable.put("38" , "Friday, 9.00 - 10.00");
        timetable.put("39" , "Friday, 10.00 - 11.00");
        timetable.put("40" , "Friday, 11.00 - 12.00");
        timetable.put("41" , "Friday, 12.00 - 13.00");
        timetable.put("42" , "Friday, 13.00 - 14.00");
        timetable.put("43" , "Friday, 14.00 - 15.00");
        timetable.put("44" , "Friday, 15.00 - 16.00");
        timetable.put("45" , "Friday, 16.00 - 17.00");

    }
}
