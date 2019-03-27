#include "mpi.h"
#include <stdio.h>
#include <time.h>
 
int main(){
    MPI_Init(NULL, NULL);
    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD,&world_rank);
    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);
    int sum=0;
    int sum2=0;
	int start;
	int end;
    int arr[]={12,2,1,4,5,23,1,2,4,1};
    int localData[5];
	start = MPI_Wtime();
    MPI_Scatter(arr,5,MPI_INT,localData,5,MPI_INT,0,MPI_COMM_WORLD);
    if(world_rank == 0){
        for(int i=0;i<5;i++){
            sum+=localData[i];
        } 
    MPI_Send(&sum,1,MPI_INT,2,0,MPI_COMM_WORLD);
    }
    if(world_rank == 1){
        for(int i=0;i<5;i++){
            sum2+=localData[i];
        }
        MPI_Send(&sum2,1,MPI_INT,2,0,MPI_COMM_WORLD);
    }
    if(world_rank == 2){
    int sums1;
    int sums2;
        MPI_Recv(&sums1,1,MPI_INT,0,0,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
        MPI_Recv(&sums2,1,MPI_INT,1,0,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
        printf("Sum is :%d\n",sums1+sums2);
		end = MPI_Wtime();
		printf("Time is:%d",end-start);
    }
 
    MPI_Finalize();
    return 0;  
}