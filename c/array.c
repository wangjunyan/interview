#include <stdio.h>

void main()
{
    int A[5][5];
    int i,j;
    for (i=0; i<5; i++)
    {
        for (j=0; j<5; j++)
        {
            A[i][j] = i + j;
        }
    }

    for (i=0; i<5; i++)
    {
        for (j=0; j<5; j++)
        {
            printf("%d\t", A[i][j]);
        }
        printf("\n");
    }
}
