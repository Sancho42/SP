#include <iostream>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <stdio.h>

using namespace std;


struct sphere
{
    int position_x;
    int position_y;
};

main()
{
    srand(time(nullptr));
    int sphere_count = 0;
    while (true)
    {
        printf("PARENT: how many spheres? (between 1 and 9)\n");
        scanf("%d", &sphere_count);
        if (sphere_count < 10 && sphere_count > 0)
        {
            break;
        }
        printf("NO, between 1 and 9\n");
    }


    sphere* spheres = new sphere[sphere_count];

    for (int i = 0; i < sphere_count; ++i)
    {
			spheres[i].position_x = rand() % 10;
			spheres[i].position_y = rand() % 10;
    }

    pid_t pid;
    int rv;
    for (int i = 0; i < sphere_count; i++)
    {
        pid=fork();
        if (pid == 0)
        {
            rv = i;
            break;
        }
        printf("PARENT: created child with PID -- %d and id -- %d\n",pid, i);
    }

    switch(pid) {
    case -1:
        perror("fork");
        exit(1);
    case 0:
        printf("CHILD: my PID -- %d and my id -- %d\n", getpid(), rv);
        char buffer_x[2];
        char buffer_y[2];
        char buffer_id[2];
        buffer_x[0] = spheres[rv].position_x + '0';
        buffer_x[1] = 0;
        buffer_y[0] = spheres[rv].position_y + '0';
        buffer_y[1] = 0;
        buffer_id[0] = rv + '0';
        buffer_id[1] = 0;


        execl("prog2", "prog2", &buffer_x, &buffer_y, buffer_id, NULL);
        exit(0);
    default:
        printf("PARENT: finished creating children\n");
        printf("PARENT: my PID -- %d\n", getpid());
        printf("PARENT: I wait while my chaild exit()...\n");
        for (int i = 0; i < sphere_count; i++)
        {
            wait(&rv);
            printf("\nPARENT: child exit with code: %d\n", WEXITSTATUS(rv));
        }

        printf("PARENT: END!\n");
    }
}
