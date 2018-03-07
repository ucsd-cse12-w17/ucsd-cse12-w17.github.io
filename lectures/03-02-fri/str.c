#include <stdio.h>
#include <string.h>

int main() {
  char* astr = "astring"; // NOTE: Don't try to modify this string
  char* another = "another string";

  printf("%s\n", another);
  printf("%s\n", astr);

  int compared = strcmp(astr, another); // Like compareTo in Java!

  printf("%d\n", compared);
  // What will print?
  // A: -1
  // B: 0
  // C: 1
  // D: Something else
}
