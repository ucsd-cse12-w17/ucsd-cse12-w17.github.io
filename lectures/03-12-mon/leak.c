#include <stdlib.h>
#include <stdbool.h>

int main(void) {
  int* someInts;
  while(true) {
    someInts = malloc(sizeof(int) * 100);
    // free(): tells the memory system that the memory at a pointer
    // is OK to re-use in future calls to malloc
    free(someInts);
  }
}

