package com.playtika.noTests;

import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

public class OhceNoTests {

    public static void main(String[] args) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(20 <= hour && 24 >= hour || hour >= 0 && hour <= 6) {
            System.out.println("¡Buenas noches "+args[0]+"!");
        } else if(hour > 6 && hour <= 12) {
            System.out.println("¡Buenos días "+args[0]+"!");
        } else if (hour > 12 && hour < 20) {
            System.out.println("¡Buenas tardes "+args[0]+"!");
        }
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        while (!Objects.equals(word,"Stop!")) {
            char[] reverse = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                reverse[i] = word.charAt(word.length()-i-1);
            }
            System.out.println(reverse);

            if(Objects.equals(word, String.valueOf(reverse))) {
                System.out.println("¡Bonita palabra!");
            }

            word = scanner.nextLine();
        }

        System.out.println("Adios " + args[0]);
    }
}
