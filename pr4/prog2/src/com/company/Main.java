package com.company;

import java.util.Random;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        final Random random = new Random(System.nanoTime());
        System.out.printf("CHILD %s: get %d parameters: ", args[3], args.length);

        if (args.length != 4)
        {
            System.out.printf("\n CHILD %s: FAULT, so few parameters\n", args.length);
            exit(-1);
        }

        for (String arg : args) System.out.printf("%s ", arg);

        int position_x = Integer.parseInt(args[1]);
        int position_y = Integer.parseInt(args[2]);

        int new_position_x = position_x + random.nextInt(100) - 50;
        int new_position_y = position_y + random.nextInt(100) - 50;

        if (new_position_y < 0)
        {
            System.out.printf("\nCHILD %s: move from (%d, %d) to (%d, %d) and was destroyed\n",
                    args[3], position_x, position_y, new_position_x, new_position_y);
            exit(1);
        }

        System.out.printf("\nCHILD %s: move from (%d, %d) to (%d, %d)\n",
                args[3], position_x, position_y, new_position_x, new_position_y);
        exit(0);


    }
}
