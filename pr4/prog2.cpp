#include <iostream>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <stdio.h>


using namespace std;

int main(int argc, char *argv[])
{
    srand(time(nullptr));
    printf("CHILD %s: get %d parameters: ", argv[3], argc);
	for (int i = 0; i < argc; i++)
		printf("%s ", argv[i]);

	if (argc != 4)
    {
        printf("\n CHILD %s: FAULT, so few parameters\n", argv[3]);
        exit(-1);
    }
    int position_x = (int)*argv[1] - '0';
    int position_y = (int)*argv[2] - '0';
	//return 0;
	int new_position_x = position_x + (rand()%101) - 50;
	int new_position_y = position_y + (rand()%101) - 50;

	if (new_position_y < 0)
    {
        printf("\nCHILD %s: move from (%d, %d) to (%d, %d) and was destroyed\n",
            argv[3], position_x, position_y, new_position_x, new_position_y);
        exit(1);
    }

    printf("\nCHILD %s: move from (%d, %d) to (%d, %d)\n",
            argv[3], position_x, position_y, new_position_x, new_position_y);
    exit(0);
}
