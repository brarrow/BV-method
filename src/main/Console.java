package main;

import java.util.Scanner;

public class Console {

    public static void cycle() {

        Scanner input = new Scanner(System.in);
        String command;
        do {
            command = input.nextLine();
            switch (command){
                case "\\h":{
                    PrintHelp();
                    break;
                }
            }

        } while (!command.equals("\\q"));
    }

    private static void PrintHelp() {
        System.out.println(
                "\\q - Quit the program"
        );
    }
}