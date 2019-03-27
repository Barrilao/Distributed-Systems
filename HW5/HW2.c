#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"

int main()
{
    int world_rank;
    int sum[10][10];
    int m, n, first[10][10], second[10][10], sum[10][10];
    MPI_Comm_Rank(MPI_COMM_WORLD,&world_rank);

   printf("Enter the number of rows and columns of matrix\n");
   scanf("%d%d", &m, &n);
   printf("Enter the elements of first matrix\n");
   for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
         scanf("%d", &first[i][j]);

   printf("Enter the elements of second matrix\n");

   for (int i = 0; i < m; i++)
      for (int j = 0 ; j < n; j++)
            scanf("%d", &second[i][j]);

    for (int i = 0; i < m; i++)
      for (int j = 0 ; j < n; j++)
            sum[i][j] = 0;

    if(world_rank == 0)
    {
        for (int i = 0; i < m/2; i++)
            for(int j = 0; j < n/2; j++)
                sum[i][j] += first[i][j] + second[i][j];

        MPI_Send(&sum, MPI_INT, 1, 0, MPI_COMM_WORLD);
    }

    if(world_rank == 1)
    {   MPI_Recv(&sum, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        for (int i = m/2; i < m; i++)
            for(int j = n/2; j < n; j++)
                sum[i][j] += first[i][j] + second[i][j];

        for (int i = m/2; i < m; i++)
            for(int j = n/2; j < n; j++)
                printf("%d", sum[i][j]);
    }

    MPI_Finalize();
    return 0;
}




