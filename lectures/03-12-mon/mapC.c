
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

void expand(ArrayMap* am) {
  // Make a BIGGER array
  Pair* newElts = malloc(sizeof(Pair) * am->capacity * 2);
  int i;
  // Copy the old elements over
  for(i = 0; i < am->size; i += 1) {
    newElts[i] = am->elements[i];
  }
  // Update the reference/pointer to the new array,
  // and update capacity
  am->elements = newElts;
  am->capacity *= 2;
}
void set(ArrayMap* am, char* key, int value) {
  // Linear search first (in case key exists)
  int i;
  for(i = 0; i < am->size; i += 1) {
    if(strcmp(am->elements[i].key, key) == 0) {
      am->elements[i].value = value;
      return;
    }
  }
  // If not, check space
  if(am->capacity <= am->size) {
    expand(am);
  }
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

ArrayMap* makeMap() {
  ArrayMap* m = malloc(sizeof(ArrayMap));
  m->size = 0;
  m->elements = malloc(sizeof(Pair) * 10);
  m->capacity = 10;
  return m;
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

  printf("Get of 'a': %d\n", get(m, "a"));
  printf("Get of 'b': %d\n", get(m, "b"));

  set(m, "c", 12);
  printf("Get of 'c': %d\n", get(m, "c"));
  set(m, "b", 40);
  printf("Get of 'b': %d\n", get(m, "b"));

  set(m, "d", 14);
  set(m, "e", 15);
  set(m, "f", 16);
  printf("Get of 'd': %d\n", get(m, "d"));
  printf("Get of 'e': %d\n", get(m, "e"));
  printf("Get of 'f': %d\n", get(m, "f"));
  printf("capacity : %d\n", m->capacity);
}



