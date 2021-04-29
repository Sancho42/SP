package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

class Sphere
{
    int position_x;
    int position_y;
}

class MyThread extends Thread {
    private final int sphere_index;

    MyThread (int sphere_index){
        this.sphere_index = sphere_index;
    }

    @Override
    public void run() {
        Main.transformRandomly(sphere_index);
    }
}

public class Main {

    static Sphere[] spheres;
    static final Random random = new Random(System.nanoTime());

    public static void main(String[] args) throws InterruptedException {
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

        spheres = new Sphere[sphere_count];

        for (int i = 0; i < sphere_count; ++i)
        {
            spheres[i] = new Sphere();
            spheres[i].position_x = random.nextInt(10);
            spheres[i].position_y = random.nextInt(10);

        }
        MyThread[] threads = new MyThread[sphere_count];
        for (int i = 0; i < sphere_count; i++) {
            threads[i] = new MyThread(i);
        }
        for (MyThread i: threads) {
            i.start();
        }
        for (MyThread i: threads) {
            i.join();
        }

        System.out.print("PARENT: END!\n");
    }

    public static int transformRandomly(int sphere_index) {
        System.out.printf("CHILD %s: start\n", sphere_index);
        synchronized (spheres){
            System.out.printf("CHILD %s: start change memory\n", sphere_index);
            int old_position_x = spheres[sphere_index].position_x;
            int old_position_y = spheres[sphere_index].position_y;

            spheres[sphere_index].position_x += random.nextInt(100) - 50;
            spheres[sphere_index].position_y += random.nextInt(100) - 50;


            if (spheres[sphere_index].position_y < 0) {
                System.out.printf("CHILD %s: move from (%d, %d) to (%d, %d) and was destroyed\n",
                        sphere_index, old_position_x, old_position_y,
                        spheres[sphere_index].position_x, spheres[sphere_index].position_y);
                spheres[sphere_index] = null;
                System.out.printf("CHILD %s: final change memory\n", sphere_index);
                return 1;
            }

            System.out.printf("CHILD %s: move from (%d, %d) to (%d, %d)\n",
                    sphere_index, old_position_x, old_position_y,
                    spheres[sphere_index].position_x, spheres[sphere_index].position_y);
            System.out.printf("CHILD %s: final change memory\n", sphere_index);
            return 0;
        }
    }

}
