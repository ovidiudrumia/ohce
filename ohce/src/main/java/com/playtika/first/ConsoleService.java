package com.playtika.first;

import java.util.Scanner;

public class ConsoleService {
    public void writeLine(String message) {
        System.out.println(message);
    }

    public String readLine() {
        Scanner keyBoardInput = new Scanner(System.in);
        return keyBoardInput.nextLine();
    }
}
