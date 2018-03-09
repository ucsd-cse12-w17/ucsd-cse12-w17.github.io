  #include <stdio.h>
  #include <stdlib.h>
  #include <string.h>

  // Map from strings (char*) to ints
  // Use an array of pairs to store data

  struct Pair {
    char* key;
    int value;
  };
  typedef struct Pair Pair;

  struct ArrayMap {
    int size;       // the number of key/value pairs in the map
    int capacity;   // the total number of elements in the array
    Pair* elements; // holds the elements of the map (may be *many* pairs)
  };
  typedef struct ArrayMap ArrayMap;

  int main() {

    // Construct a map with "a": 5, "b": 10, capacity 5
    ArrayMap* m = malloc(sizeof(ArrayMap));
    m->size = 2;
    m->elements = malloc(sizeof(Pair) * 5);

    m->elements[0].key = "a"; 
    m->elements[0].value = 5;
    m->elements[1].key = "b";
    m->elements[1].value = 10;

  }



