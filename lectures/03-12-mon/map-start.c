#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Map from strings (char*) to ints
// Use an array of pairs to store data

typedef struct {
  char* key;
  int value;
} Pair;
typedef struct {
  int size;       // the number of key/value pairs in the map
  int capacity;   // the total number of elements in the array
  Pair* elements; // holds the elements (may be *many* pairs)
} ArrayMap;
void expand(ArrayMap* am) {
  // Make a BIGGER array
  Pair* newElts = malloc(sizeof(Pair) * am->capacity * 2);
  int i;
  // Copy the old elements over
  for(i = 0; i < am->size; i += 1) {
    newElts[i] = am->elements[i];
  }
  am->elements = newElts;   // Update relevant fields
  am->capacity *= 2;
}
void set(ArrayMap* am, char* key, int value) {
  int i; // Linear search first in case key exists
  for(i = 0; i < am->size; i += 1) {
    if(strcmp(am->elements[i].key, key) == 0) {
      am->elements[i].value = value;
      return;
    }
  }
  // If not, check space
  if(am->capacity <= am->size) { expand(am); }
  // Add at the end
  am->elements[am->size].key = key;
  am->elements[am->size].value = value;
  am->size += 1;
}

int get(ArrayMap* am, char* key) {
  int i;
  for(i = 0; i < am->size; i += 1) {
    if(strcmp(am->elements[i].key, key) == 0) {
      return am->elements[i].value; 
    }
  }
  // throw exception? We can't in C
  // ASSUME: context of PA6 where value represents count,
  // so -1 is a reasonable "no answer" value
  return -1;
}

ArrayMap* makeMap(int startCapacity) {
  ArrayMap* m = malloc(sizeof(ArrayMap));
  m->size = 0;
  m->elements = malloc(sizeof(Pair) * startCapacity);
  m->capacity = startCapacity;
  return m;
}

int main() {
  ArrayMap* am = makeMap(2);
  set(am, "apple", 2);
  set(am, "banana", 1);
  set(am, "orange", 9);
  set(am, "kiwi", 6);
  set(am, "grape", 3);
}


