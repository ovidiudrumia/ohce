package com.playtika.second;


import java.util.Objects;

public class Ohce {

    private ConsoleService consoleService = new ConsoleService();
    private TimeService timeService = new TimeService();

    public static void main(String[] args) {
        new Ohce().startOhce(args[0]);
    }

    public void startOhce(String name) {
        greeting(name);
        processWords();
        adios(name);
    }

    public void greeting(String name) {
        if(timeService.isMorning()) {
            consoleService.writeLine("¡Buenos días " + name + "!") ;
        } else if(timeService.isAfternoon()) {
            consoleService.writeLine("¡Buenos tardes " + name + "!") ;
        } else if(timeService.isNight()) {
            consoleService.writeLine("¡Buenos noches " + name + "!") ;
        }
    }

    public void processWords() {
        String input = consoleService.readLine();
        while (!Objects.equals(input, "Stop!")) {
            if(!Objects.equals(input,reverse(input))) {
                consoleService.writeLine(reverse(input));
            } else {
                consoleService.writeLine(reverse(input) + "\r\n¡Bonita palabra!");
            }
            input = consoleService.readLine();
        }
    }

    public void adios(String name) {
        consoleService.writeLine("Adios " + name);
    }

    public String reverse(String word) {
        char[] chars = word.toCharArray();
        char[] reverse = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            reverse[i] = chars[chars.length-i-1];
        }
        return String.valueOf(reverse);
    }
}
