#include <stdio.h>

int max(int n, int m) {
  if(n > m) { return n; }
  else { return m; }
}

// This is a main function!
// NOTE: I didn't write class anywhere!
int main() {
  printf("The max of 5, 4 is %d", max(5, 4));
  printf("Hello");
  printf("everyone\n\n");
}
