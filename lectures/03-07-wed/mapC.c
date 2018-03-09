#include <stdio.h>
#include <stdlib.h>
#include <string.h>


// Want to represent map with an array of pairs
// Mapping from strings (char* in C) to ints

struct Pair {
  char* key;
  int value;
};
typedef struct Pair Pair;

struct ArrayMap {
  Pair* contents;
  int capacity; // Always match the length of contents (because C can't)
  int size;     // The number of elements in the map
};
typedef struct ArrayMap ArrayMap;

// Constructor
//ArrayMap* makeMap() {
//
//}

void printMap(ArrayMap* map) {

}

int main() {
  // Construct an ArrayMap with "a" : 5 and "b" : 6
  ArrayMap* theMap = malloc(sizeof(ArrayMap));

  theMap->contents = malloc(sizeof(Pair) * 5);
  theMap->size = 2;
  theMap->capacity = 5;

  theMap->contents[0].key = "a";
  theMap->contents[0].value = 5;

  theMap->contents[1].key = "b";
  theMap->contents[1].value = 6;
}



