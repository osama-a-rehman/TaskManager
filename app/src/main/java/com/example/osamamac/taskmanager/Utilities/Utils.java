package com.example.osamamac.taskmanager.Utilities;

public final class Utils {
    public static final int PROJECT_FRAGMENT_ID = 1;
    //public static final int DATE_FRAGMENT_ID = 2;
    public static final int PRIORITY_FRAGMENT_ID = 3;
    /*public static final int LABELS_FRAGMENT_ID = 4;
    public static final int COMMENTS_FRAGMENT_ID = 5;
    public static final int REMINDERS_FRAGMENT_ID = 6;
    public static final int LOCATIONS_FRAGMENT_ID = 7;*/

    public static final String SET_LABEL_EXTRA = "SET_LABEL_EXTRA";
    public static final String SET_COMMENTS_EXTRA = "SET_COMMENTS_EXTRA";
    public static final String SET_REMINDERS_EXTRA = "SET_REMINDERS_EXTRA";
    public static final String SET_LOCATIONS_EXTRA = "SET_LOCATIONS_EXTRA";

    public static final String LABEL_RESULT_EXTRA = "LABEL_RESULT";
    public static final int LABEL_RESULT_CODE = 1000;
    public static final int LABEL_REQUEST_CODE = 1000;

    public static final String COMMENTS_RESULT_EXTRA = "COMMENTS_RESULT";
    public static final int COMMENTS_RESULT_CODE = 1001;
    public static final int COMMENTS_REQUEST_CODE = 1001;

    public static final String LOCATIONS_RESULT_EXTRA = "LOCATIONS_RESULT";
    public static final int LOCATIONS_RESULT_CODE = 1003;
    public static final int LOCATIONS_REQUEST_CODE = 1003;



    public static String getStringMonth(int month){

        switch (month){
            case 0:
                return "Jan";

            case 1:
                return "Feb";

            case 2:
                return "Mar";

            case 3:
                return "Apr";

            case 4:
                return "May";

            case 5:
                return "June";

            case 6:
                return "July";

            case 7:
                return "Aug";

            case 8:
                return "Sept";

            case 9:
                return "Oct";

            case 10:
                return "Nov";

            case 11:
                return "Dec";

            default:
                return null;
        }
    }

    public static Time getTime(int hourOfDay, int minute){
        int hours = hourOfDay;
        String sHour, sMin, mode;

        if(hours < 12){
            mode = "AM";
        }else{
            mode = "PM";
            hours -= 12;
        }

        if(hours < 10){
            sHour = "0" + hours;
        }else{
            sHour = "" + hours;
        }

        if(minute < 10){
            sMin = "0" + minute;
        }else{
            sMin = "" + minute;
        }

        return new Time(sHour, sMin, mode);
    }
}
