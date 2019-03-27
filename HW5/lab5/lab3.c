// Example using MPI_Send and MPI_Recv to pass a message around in a ring.
#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
int main(int argc, char** argv) {
	// Initialize the MPI environment
	MPI_Init(NULL, NULL);
	// Find out rank, size
int world_rank;
MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
int world_size;
MPI_Comm_size(MPI_COMM_WORLD, &world_size);
int i, j;
int matriz1[2][2]={{1,2},{3,4}};
int matriz2[2][2] = {{2,2},{2,2}};
int matriz3[2][2];
// Receive from the lower process and send to the higher process. Take care of the special case when you are the first process to prevent deadlock.
if (world_rank == 0) { //process 0
	printf("Process %d computed the sum equal to\n", world_rank);
	for(i =0;  i<= 0; i++){
		for(j=0;j<=1;j++){
			matriz3[i][j]=matriz1[i][j]+matriz2[i][j];
			printf("\t\t\t   %d", matriz3[i][j]);
		
		}
		printf("\t\t\t\n");
	}
	MPI_Send(&matriz3, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
	
}
if(world_rank==1){
	printf("Process %d computed the sum equal to\n", world_rank);
	for(i =1;  i<= 1; i++){
		for(j=0;j<=1;j++){
			matriz3[i][j]=matriz1[i][j]+matriz2[i][j];
			printf("\t\t\t   %d", matriz3[i][j]);
		
		}
		printf("\t\t\t\n");
	}
	MPI_Recv(&matriz3, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

}
MPI_Finalize();
}
