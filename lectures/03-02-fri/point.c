#include <stdio.h>
#include <string.h>

struct Point {
  int x;
  int y;
};

typedef struct Point        Point;
        /* any type */      /* The name we'd like to use */

Point makePoint(int initX, int initY) {
  Point p = { initX, initY };
  return p;
}

Point translateX(Point p, int dx) {
  Point newPoint = { p.x + dx, p.y };
  return newPoint;
}

void translateInPlace(Point p, int dx) {
  printf("Right before we assigned it!!! %d\n", p.x);
  p.x = p.x + dx;
  printf("Right after we assigned it!!! %d\n", p.x);
}

int main() {

  Point p = makePoint(1, 3);
  printf("p.x: %d, p.y: %d\n", p.x, p.y);

  Point t = translateX(p, 5);
  printf("t.x: %d, t.y: %d\n", t.x, t.y);
  // What should print for x value of t?
  // A: 1 B: 5 C: 6 D: Something else?

  printf("p.x: %d, p.y: %d\n", p.x, p.y);
  // What should print for x value of p?
  // A: 1 B: 5 C: 6 D: Something else?


  translateInPlace(p, 25);
  printf("p.x: %d, p.y: %d\n", p.x, p.y);
  // What should print for x value of p?
  // A: 26 B: 31 C: 1 D: Something else?






}

