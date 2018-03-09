
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
  Pair* elements; // holds the elements (may be *many* pairs)
};
typedef struct ArrayMap ArrayMap;


int get(ArrayMap* am, char* key) {
  // Find the pair corresponding to the given key, return its value
  int i;
  for(i = 0; i < am->size; i += 1) {
    if(strcmp(am->elements[i].key, key) == 0) {
      return am->elements[i].value;
    }
  }
  // ASSUMING the context of PA6, where the values are
  // counts so -1 is a meaningful "nothing" value
  return -1;
}

void set(ArrayMap* am, char* key, int value) {
  // If the key is there, update it and return
  // Check if there's room, expand if not
  // Put it at the end
  // Increment the size
}


int main() {
  // Construct a map with "a": 6, "b": 10, capacity 5
  ArrayMap* m = malloc(sizeof(ArrayMap));
  m->size = 2;
  m->elements = malloc(sizeof(Pair) * 5);
  m->capacity = 5;

  m->elements[0].key = "a"; 
  m->elements[0].value = 6;
  m->elements[1].key = "b";
  m->elements[1].value = 10;

  printf("Get 'a': %d\n", get(m, "a"));
  printf("Get 'b': %d\n", get(m, "b"));
  printf("Get 'd': %d\n", get(m, "d"));

  Pair p2 = m->elements[0];   // This is what C does

}



