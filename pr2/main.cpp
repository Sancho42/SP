#include <iostream>

using namespace std;

int main()
{
    short a[10] = {1, -2, 3, -4, -5, 6, -7, -8, -9, 10};
    short s = 0;
    for (int i = 0; i<10; i++)
    {
        s += abs(a[i]);
    }
    cout << s;
    return 0;
}
