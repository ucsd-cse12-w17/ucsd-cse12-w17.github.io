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


The Write-up quiz can be found here, and is due before Monday midnight.

[https://docs.google.com/forms/d/e/1FAIpQLScMLQm3JTwOj5j-hZ_4iQYcqkpIOw5APnz83uwkXoogorwR4Q/viewform](https://docs.google.com/forms/d/e/1FAIpQLScMLQm3JTwOj5j-hZ_4iQYcqkpIOw5APnz83uwkXoogorwR4Q/viewform)

## Using C on the Lab Machines

For this PA, you should use the lab machines. If you want to set up C on your
own laptop, you're welcome to, but we probably won't be able to help.

This document has instructions on finding your account and getting logged in,
and working remotely if you'd like:

[https://docs.google.com/document/d/1zxXlY36LOfpI4Z3zr70u3AImMirsUUBaomX9Vf7T0xg/edit?usp=sharing](https://docs.google.com/document/d/1zxXlY36LOfpI4Z3zr70u3AImMirsUUBaomX9Vf7T0xg/edit?usp=sharing)


Note that it also has some background information on running C from the command
line. We've provided the commands you need in a `Makefile` and detailed them
below, but the background in the document explains the underlying commands.


Also note that the PA is not set up with Eclipse, so directly importing the code into Eclipse will not create a project. Instead, you can `git clone <url_to_repo>` in Terminal to pull the code, and then use any Text Editor or Eclipse to edit the files.


## Two Practice Exercises

First, you're going to do two warm-up exercises. These are be due by Monday
midnight and worth 10% of your grade.

- `insert.c`: This file contains an implementation of inserting into an array
  that appears to work, but has some memory errors. You will use `valgrind` to
  detect and fix the memory errors. You can run this example with `make insert`, which will run the necessary valgrind command. You'll hand in the corresponding fixed C file.
- `struct_of_picture.c`: In this file, you will write initialization code to
  construct data that matches the pictures shown below. You'll hand in a C file
  that makes the `main` method print both `"You got ans1!"` and `"You got
  ans2!"` Run with `make struct_of_picture`.

<img src="ans1.png" width="100%">
<img src="ans2.png" width="100%">


## The Heap Interface In C

Unlike Java, C doesn't have language support for a feature called an
“interface.” That said, the concept of a collection of functions that implement
a particular feature independent of the underlying representation is still a
reasonable one to consider. For this heap implementation, we'll take a
collection of function headers as our interface:

```
Heap* makeHeap(int capacity);
void add(Heap* heap, int priority, char* value);
char* removeMin(Heap* heap);
char* peek(Heap* heap);
int size(Heap* heap);
void cleanupHeap(Heap* heap);
```

In addition, we've defined a few structs for you:

```
struct Pair {
  int key;
  char* value;
};
typedef struct Pair Pair;

struct Heap {
  int capacity;
  int size;
  Pair* elements;
};
typedef struct Heap Heap;
```

The `heap.h` file holds all these definitions, and is called a _header file_. C
programs are often organized with definitions in one file and declarations in
another. This is done (at a high level) to help C's compiler, which doesn't
have the same features Java's compiler has for traversing the filesystem to
find all the relevant source files.

In `heap.c`, you will write implementations of these functions. Your
implementation will be a **min heap** (the mapping with the lowest key will be
on top). Some notes on the required algorithms are at

- [Wed, Feb 14th lecture](https://ucsd-cse12-w17.github.io/lectures/02-14-wed-c/notes.html)
- [Fri, Feb 16th lecture](https://ucsd-cse12-w17.github.io/lectures/02-16-fri-c/notes.html)

Descriptions of each function are:

- `makeHeap`: Should return a pointer to a newly allocated `Heap` with the
  given `capacity`, a `size` of `0`, and an `elements` array allocated with the
  given capacity.
- `add`: Should add a new pair mapping the given `priority` as a key to the
  given value `value`. This should work by inserting `add` at the end of the
  storage array (`elements`) and bubbling up. Should run in `O(lg(size))` time,
  except when resizing. If the size is equal to the capacity, this should take
  `O(size)` time, and expand the capacity by allocating a new array of twice
  the current capacity and copying old elements over before performing the
  insertion.
- `removeMin`: Should return the value stored at the top of the heap (the one
  with the lowest priority). That element should then be removed from the heap,
  and the heap should be restored to the correct shape by moving the last
  element to the top and bubbling down. When performing bubble down, prefer
  swapping with the **left** child if both children have the same priority.
  If the heap is empty, return `NULL`. Should run in `O(lg(size))` time.
- `peek`: Should return the value stored at the top of the heap (the one with
  the lowest priority) and make no changes to the heap. If the heap is empty,
  should return `NULL`. Should run in constant time.
- `size`: Should return the number of elements in the heap (not the capacity),
  and run in constant time.
- `cleanupHeap`: Should use `free` to reclaim the memory used by the given Heap
  pointer, including its `elements` array.

## Testing

You can write tests in `test_heap.c`, where there are some examples given of
using arithmetic and checking numeric equality, and checking equality between
strings. You should write tests that create heaps, add and remove items, and
check that the operations work as expected. The two relevant functions for
writing assertions are `CuAssertIntEquals` and `CuAssertStrEquals`. In this
testing framework we need to use the equality method that matches the type we
are testing.

You can run the tests with

```
make test
```

## Checking Memory

You'll see output like this when the tests run:

```
[cs12w@ieng6-203]:pa8-implementation:642$ make test
gcc -g -Wall -o test_heap.run heap.c test_heap.c cutest/CuTest.c
valgrind --leak-check=full --undef-value-errors=no ./test_heap.run
==32451== Memcheck, a memory error detector
==32451== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==32451== Using Valgrind-3.12.0 and LibVEX; rerun with -h for copyright info
==32451== Command: ./test_heap.run
==32451==
.

OK (1 test)

==32451==
==32451== HEAP SUMMARY:
==32451==     in use at exit: 176 bytes in 2 blocks
==32451==   total heap usage: 8 allocs, 6 frees, 16,913 bytes allocated
==32451==
==32451== 176 (16 direct, 160 indirect) bytes in 1 blocks are definitely lost in loss record 2 of 2
==32451==    at 0x4C29BE3: malloc (vg_replace_malloc.c:299)
==32451==    by 0x400FCD: makeHeap (heap.c:101)
==32451==    by 0x401017: TestHeap (test_heap.c:7)
==32451==    by 0x40167E: CuTestRun (CuTest.c:144)
==32451==    by 0x401CE4: CuSuiteRun (CuTest.c:292)
==32451==    by 0x4010F1: RunAllTests (test_heap.c:29)
==32451==    by 0x401156: main (test_heap.c:40)
==32451==
==32451== LEAK SUMMARY:
==32451==    definitely lost: 16 bytes in 1 blocks
==32451==    indirectly lost: 160 bytes in 1 blocks
==32451==      possibly lost: 0 bytes in 0 blocks
==32451==    still reachable: 0 bytes in 0 blocks
==32451==         suppressed: 0 bytes in 0 blocks
==32451==
==32451== For counts of detected and suppressed errors, rerun with: -v
==32451== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
```

The part that says "OK (1 test)" will reflect the number of tests you've
written, and is like the JUnit output for your tests.

The rest is the output of a tool called `valgrind`, which checks the memory
usage of programs. In this case, it's saying that there are 176 bytes of memory
left in use at the end of the program. You'll probably see something like this
early on, until you implement `cleanupHeap`. It means that memory that was
earlier `malloc`ed was never freed. You should make sure to free all memory
that is allocated in each test by using `cleanupHeap` on any heaps you create.
When there are no memory errors and no memory leaks, you'll see a message like this:

```
[cs12w@ieng6-203]:pa8-implementation:649$ make test
gcc -g -Wall -o test_heap.run heap.c test_heap.c cutest/CuTest.c
valgrind --leak-check=full --undef-value-errors=no ./test_heap.run
==5758== Memcheck, a memory error detector
==5758== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==5758== Using Valgrind-3.12.0 and LibVEX; rerun with -h for copyright info
==5758== Command: ./test_heap.run
==5758==
.

OK (1 test)

==5758==
==5758== HEAP SUMMARY:
==5758==     in use at exit: 0 bytes in 0 blocks
==5758==   total heap usage: 8 allocs, 8 frees, 16,913 bytes allocated
==5758==
==5758== All heap blocks were freed -- no leaks are possible
==5758==
==5758== For counts of detected and suppressed errors, rerun with: -v
==5758== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
```

This is the kind of output you should see when things are in a happy state – no
memory errors, all heap blocks freed, and all tests passing!

It's important to point out that `valgrind` can catch even more than unfreed
memory. Consider this silly test:

```
void SillyTest(CuTest *tc) {
  Heap* h = NULL;
  CuAssertIntEquals(tc, 0, h->capacity);
}
```

If we run `make test` on this, we'll see this output:


```
[cs12w@ieng6-203]:pa8-implementation:664$ make test
gcc -g -Wall -o test_heap.run heap.c test_heap.c cutest/CuTest.c
valgrind --leak-check=full --undef-value-errors=no ./test_heap.run
==22849== Memcheck, a memory error detector
==22849== Copyright (C) 2002-2015, and GNU GPL'd, by Julian Seward et al.
==22849== Using Valgrind-3.12.0 and LibVEX; rerun with -h for copyright info
==22849== Command: ./test_heap.run
==22849==
==22849== Invalid read of size 4
==22849==    at 0x40101A: SillyTest (test_heap.c:8)
==22849==    by 0x4016EB: CuTestRun (CuTest.c:144)
==22849==    by 0x401D51: CuSuiteRun (CuTest.c:292)
==22849==    by 0x40115E: RunAllTests (test_heap.c:34)
==22849==    by 0x4011C3: main (test_heap.c:45)
==22849==  Address 0x0 is not stack'd, malloc'd or (recently) free'd
==22849==
==22849==
==22849== Process terminating with default action of signal 11 (SIGSEGV)
==22849==  Access not within mapped region at address 0x0
==22849==    at 0x40101A: SillyTest (test_heap.c:8)
==22849==    by 0x4016EB: CuTestRun (CuTest.c:144)
==22849==    by 0x401D51: CuSuiteRun (CuTest.c:292)
==22849==    by 0x40115E: RunAllTests (test_heap.c:34)
==22849==    by 0x4011C3: main (test_heap.c:45)
==22849==  If you believe this happened as a result of a stack
==22849==  overflow in your program's main thread (unlikely but
==22849==  possible), you can try to increase the size of the
==22849==  main thread stack using the --main-stacksize= flag.
==22849==  The main thread stack size used in this run was 8388608.
... more output down here ...
```

One important thing to note is that this is **not** a stack overflow – this
message will show up in some valgrind output. This output is actually giving
the same kind of meaning as `NullPointerException` in Java. the `Invalid read
of size 4` pinpoints the line on which it happens, and means that we tried
reading an invalid pointer (at address `0x0`, which is address `0`, which is
the same as `NULL`).


## Grading

An autograder for the heaps portion will be posted on Saturday, March 10.

The practice problems and writeup quiz are due by Monday midnight (March 12).
The entire assignment is due by Wednesday midnight (March 14).

- 10%: Practice problems
- 3%: A PA writeup quiz
- 27%: Quality of testing
- 60%: Implementation correctness

