#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Point {
  int x;
  int y;
};

typedef struct Point Point;

Point* newPoint(int x, int y) {
  Point* np = malloc(sizeof(Point));
  np->x = x;
  np->y = y;
  return np;
}

void translateInPlace(Point p, int dx) {
  p.x = p.x + dx;
}

void translateInPlacePtr(Point* p, int dx) {
  p->x = p->x + dx;
}

int main() {
  Point p = {1, 3};
  translateInPlace(p, 26);
  printf("p.x: %d\n", p.x);

  Point* ptr_p = newPoint(1, 3);
  translateInPlacePtr(ptr_p, 26);
  printf("p->x: %d\n", ptr_p->x);


}
