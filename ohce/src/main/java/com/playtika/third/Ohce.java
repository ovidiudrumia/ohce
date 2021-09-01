package com.playtika.third;

import java.util.Calendar;

public class Ohce {

    public static void main(String[] args) {
        new Ohce().runOhce(args[0]);
    }

    public void runOhce(String name) {
        greeting(name);
        processWords();
        adios(name);
    }

    public void greeting(String name) {
        if(isMorning(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))) {
            writeLine("¡Buenos días " + name + "!");
        } else if(isAfternoon(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))) {
            writeLine("¡Buenos tardes " + name + "!");
        } else if(isNight(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))) {
            writeLine("¡Buenas noches " + name + "!");
        }
    }

    public void processWords() {

    }

    public void adios(String name) {

    }

    public boolean isMorning(int hour) {
        return hour>=7 && hour <=12;
    }

    public boolean isAfternoon(int hour) {
        return hour >= 13 && hour <= 19;
    }

    public boolean isNight(int hour) {
        return hour>=20 && hour <= 24 || hour >=0 && hour <= 6;
    }

    public void writeLine(String input) {
        System.out.println(input);
    }

}
