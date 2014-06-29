#include <stdio.h>

void tester(int N){

  int sum = 0;

  for(int i = 1; i*i <= N; i = i*4)
    for(int j = 0; j < i; j++)
      sum++;

  printf("N: %d\t\t sum: %d\n", N, sum);

}


int main(){


  printf("\n");

  int N = 100000;
  for(int i = 1; i <= N; i++)
    tester(i);


  printf("\n");

  return 0;

}
