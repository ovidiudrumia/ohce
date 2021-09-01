package com.playtika.second;

import java.util.Scanner;

public class ConsoleService {

    private Scanner scanner = new Scanner(System.in);

    public void writeLine(String input) {
        System.out.println(input);
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
