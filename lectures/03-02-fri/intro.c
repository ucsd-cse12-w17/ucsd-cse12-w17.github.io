#include <stdio.h>

int max(int n, int m) {
  if(n > m) { return n; }
  else { return m; }
}

// This is a *function*
int main() {
  printf("Max of 4, 5: %d", max(4, 5));

  printf("hello");
  printf("everyone\n\n\n");
}
