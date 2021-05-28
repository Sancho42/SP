package com.company;

import java.io.BufferedReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String mode;
        while (true)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Start server or connect as client?\ns/c?\n");
            mode = input.next();

            if (mode.contentEquals("s") || mode.contentEquals("c"))
            {
                break;
            }
            System.out.print("NO, s - server, c - client\n");
        }

        switch (mode){
            case "s": Server.start(); break;
            case "c": Client.connect(); break;
        }
    }
}
