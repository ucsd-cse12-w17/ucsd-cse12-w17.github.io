---
layout: pa
title: "PA8: Heaps of Fun with C"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

In this assignment you'll get some practice with C, and you'll implement and
test a min-heap in C, paying careful attention to memory allocation.

Note that this assignment is _entirely autograded_, and there will be _no
regrades_. We'll include all the information about your grade with the
autograder, so you'll know your grade before submission.

## Some Drill Exercises

First, you're going to do several warm-up exercises to get used to programming
in C. These are be due by Monday midnight and worth 15% of your grade.

- `sum.c`: In this file you'll write a simple function that sums every other
  number in an array of pairs.
- `insert.c`: This file contains an implementation of inserting into an array
  that appears to work, but has some memory errors. You will use `valgrind` to
  detect and fix the memory errors.
- `struct_of_picture.c`: In this file, you will write initialization code to
  construct data that matches the pictures shown below.


## The Heap Interface In C

Unlike Java, C doesn't have language support for a feature called an
“interface.” That said, the concept of a collection of functions that implement
a particular feature independent of the underlying representation is still a
reasonable one to consider. For this heap implementation, we'll take a
collection of function headers as our interface:

```
Heap* makeHeap();
void add(Heap* heap, int priority, char* value);
char* removeMin(Heap* heap);
char* peek(Heap* heap);
int size(Heap* heap);
```


