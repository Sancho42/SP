package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

class Sphere
{
    int position_x;
    int position_y;
}

public class Main {
    public static void main(String[] args) {
        final Random random = new Random(System.nanoTime());
        int sphere_count = 0;
        while (true)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("PARENT: how many spheres? (between 1 and 9)\n");
            sphere_count = input.nextInt();

            if (sphere_count < 10 && sphere_count > 0)
            {
                break;
            }
            System.out.print("NO, between 1 and 9\n");
        }

        Sphere[] spheres = new Sphere[sphere_count];

        for (int i = 0; i < sphere_count; ++i)
        {
            spheres[i] = new Sphere();
            spheres[i].position_x = random.nextInt(10);
            spheres[i].position_y = random.nextInt(10);

        }

        for (int i = 0; i < sphere_count; i++) {
            int id = i;
            new Thread(new Runnable() {
                public void run() {
                    try {

                        System.out.printf("PARENT: created child with id -- %d\n", id);
                        String[] commands = {"java","-jar", "prog2.jar", "prog2.jar",
                                String.valueOf(spheres[id].position_x),
                                String.valueOf(spheres[id].position_y),
                                String.valueOf(id)};
                        Process p = Runtime.getRuntime().exec(commands);

                        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String s = null;
                        while ((s = br.readLine()) != null) {
                            System.out.println(s);
                        }

                        br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String line = br.readLine();
                        while (line != null) {
                            line = br.readLine();
                        }

                    } catch (IOException e) {
                        System.out.print(e);
                    }

                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("PARENT: END!\n");
    }
}
