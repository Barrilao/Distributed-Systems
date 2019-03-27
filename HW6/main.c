#include "mpi.h"
#include <stdio.h>
#include <stdlib.h>

void srandom (unsigned seed);
double dboard (int darts);
#define DARTS 50000
#define ROUNDS 100
#define MASTER 0

int main (int argc, char *argv[])
{
double	homepi,
	pisum,
	pi,
	avepi;
int	taskid,
	numtasks,
	rc,
	i;
MPI_Status status;


MPI_Init(&argc,&argv);
MPI_Comm_size(MPI_COMM_WORLD,&numtasks);
MPI_Comm_rank(MPI_COMM_WORLD,&taskid);
printf ("MPI task %d began...\n", taskid);


srandom (taskid);

avepi = 0;
for (i = 0; i < ROUNDS; i++) {

   homepi = dboard(DARTS);



   rc = MPI_Reduce(&homepi, &pisum, 1, MPI_DOUBLE, MPI_SUM,
                   MASTER, MPI_COMM_WORLD);

   if (taskid == MASTER) {
      pi = pisum/numtasks;
      avepi = ((avepi * i) + pi)/(i + 1);
      printf("   After %8d throws, average value of pi = %10.8f\n",
              (DARTS * (i + 1)),avepi);
   }
}
if (taskid == MASTER)
   printf ("\nPI= 3.14 \n");

MPI_Finalize();
return 0;
}





double dboard(int darts)
{

long random(void);
double x_coord, y_coord, pi, r;
int score, n;
unsigned int cconst;

if (sizeof(cconst) != 4) {
   printf("You have inputed the wrong data size for cconst variable in dboard routine!\n");
   printf("See comments in source file. Quitting.\n");
   exit(1);
   }

   cconst = 2 << (31 - 1);
   score = 0;

   for (n = 1; n <= darts; n++)  {

      r = (double)random()/cconst;
      x_coord = (2.0 * r) - 1.0;
      r = (double)random()/cconst;
      y_coord = (2.0 * r) - 1.0;


      if ((sqr(x_coord) + sqr(y_coord)) <= 1.0)
           score++;
      }

pi = 4.0 * (double)score/(double)darts;
return(pi);
}


