package com.playtika.first;

import java.util.Calendar;

public class TimeService {

    private int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);;

    public boolean isMorning() {
        return isMorning(hour);
    }

    public boolean isAfternoon() {
        return isAfternoon(hour);
    }

    public boolean isNight() {
        return isNight(hour);
    }

    public boolean isMorning(int hour) {
        if(hour > 6 && hour <= 12) return true;
        return false;
    }

    public boolean isAfternoon(int hour) {
        if(hour >= 13 && hour <= 19) return true;
        return false;
    }

    public boolean isNight(int hour) {
        if(hour >= 20 || hour <= 6) return true;
        return false;
    }
}
