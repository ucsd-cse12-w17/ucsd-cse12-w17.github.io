#include <stdlib.h>
#include <stdbool.h>

int main(void) {
  int* someInts;
  while(true) {
    someInts = malloc(sizeof(int) * 100);
    free(someInts);
  }
}
