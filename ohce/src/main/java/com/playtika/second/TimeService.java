package com.playtika.second;

import java.util.Calendar;

public class TimeService {
    public boolean isMorning() {
        return isMorning(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }

    public boolean isAfternoon() {
        return isAfternoon(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }

    public boolean isNight() {
        return isNight(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }

    public boolean isMorning(int hour) {
        return hour >= 6 && hour <= 12;
    }

    public boolean isAfternoon(int hour) {
        return hour >= 13 && hour <= 19;
    }

    public boolean isNight(int hour) {
        return hour >=20 && hour <=24 || hour >= 0 && hour <=6;
    }
}
