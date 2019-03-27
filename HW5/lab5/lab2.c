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
int i;
int array[10]={1,2,3,4,5,6,7,8,9,10};
int sum = 0;
// Receive from the lower process and send to the higher process. Take care of the special case when you are the first process to prevent deadlock.
if (world_rank == 0) { //process 0
	for(i =0; i < 10; i++)
		sum += array[i];
	sum=sum/10;
	MPI_Send(&sum, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
	printf("Process %d computed the sum equal to %d\n", world_rank, sum);
}
if(world_rank==1){
	MPI_Recv(&sum, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
	printf("Process %d computed the final sum equal to %d\n", world_rank, sum);
}
MPI_Finalize();
}
