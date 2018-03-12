#include <stdlib.h>
#include <stdio.h>

int main(void) {

  int littleSize = 2;
  int bigSize = 6;

  int* smallArr = calloc(littleSize, sizeof(int));
  int* bigArr = calloc(bigSize, sizeof(int));

  int i;
  for(i = 0; i < bigSize; i += 1) {
    printf("%d ", bigArr[i]);
  }

  printf("\n");

  // What do you expect to see?
  // A: 0 0 0 0 0 0
  // B: Some consistent across run, but weird values
  // C: It is unknowable


  return 0;
}
