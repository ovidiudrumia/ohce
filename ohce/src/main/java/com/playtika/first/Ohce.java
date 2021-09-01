package com.playtika.first;

import java.util.Objects;

public class Ohce {

    public static final String BONITA_PALABRA = "\r\n¡Bonita palabra!";
    public static final String BUENAS_NOCHES = "¡Buenas noches ";
    public static final String BUENAS_TARDES = "¡Buenas tardes ";
    public static final String BUENAS_DÍAS = "¡Buenas días ";
    private WordService wordService = new WordService();
    private TimeService timeService = new TimeService();
    private ConsoleService consoleService = new ConsoleService();

    public static void main(String... args) {
        Ohce ohce = new Ohce();
        ohce.startOhce(args[0]);
    }

    public void startOhce(String name) {
        greeting(name);
        processWords();
        adios(name);
    }

    public void greeting(String name) {
        if(timeService.isMorning()) {
            consoleService.writeLine(buenasDias(name));
        } else if (timeService.isAfternoon()) {
            consoleService.writeLine(buenasTardes(name));
        } else if (timeService.isNight()) {
            consoleService.writeLine(buenasNoches(name));
        }
    }

    public void processWords() {
        String readWord = consoleService.readLine();
        while(!Objects.equals(readWord,"Stop!")) {
            consoleService.writeLine(getReverseWordResponse(readWord));
            readWord = consoleService.readLine();
        }
    }

    public void adios(String name) {
        consoleService.writeLine("Adios " + name);
    }

    public String buenasNoches(String name) {
        return BUENAS_NOCHES + name + "!";
    }

    public String buenasTardes(String name) {
        return BUENAS_TARDES + name + "!";
    }

    public String buenasDias(String name) {
        return BUENAS_DÍAS + name + "!";
    }

    public String getReverseWordResponse(String word) {
        try {
            boolean isPalindrome = wordService.isPalindrome(word);
            if(isPalindrome) return wordService.reverseWord(word) + BONITA_PALABRA;
            return wordService.reverseWord(word);
        } catch (IllegalArgumentException e) {
            return "Please enter a word that is made up only of letters! " +
                    "To stop enter the following command: Stop!";
        }
    }
}
