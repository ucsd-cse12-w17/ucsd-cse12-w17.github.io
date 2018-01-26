---
layout: pa
title: "PA3: Worklists are A MAZE ING"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

**This is in progress; we'll announce when it is final.**

This assignment will teach you how to use stacks and queues as worklists, how
to implement an important search algorith, and how the worklist choice affects
the algorithm.

This assignment is closer in how involved it is to PA1 than to PA2.

## The Structure of a Maze Solver

There are a few components to the maze solver:

- The data used to represent the maze
- A choice of worklist to use for keeping track of the spaces that still need
  visiting
- An algorithm that uses the worklist to traverse the maze and find a solution

You will implement _two_ versions of the worklist, and _one_ algorithm that
will be parameterized to work with either type. Then you can put them together
to see the different versions work, and compare them.

## The Worklists

You will implement the `SearchWorklist` interface _twice_. Once you will
implement it with stack semantics, so `add` will “push” and `remove` will
“pop”, and then you will implement it with queue semantics, so `add` will
“enqueue” and `remove` will “dequeue.” In both cases, the `isEmpty` method
should return `true` when the worklist has no items in it. These are the _only_
three methods that should be implemented on these classes.

You are free to use any built-in Java collections to implement these using the
adapter pattern (`LinkedList`, `Stack`, etc), as long as they have the
appropriate behavior. This may mean that your implementation is no more than a
dozen lines of code, plus some header comments! There is one constraint we'd
like you to respect – make sure the constructors take no arguments, and
initialize the worklist to be empty.

We have provided some tests in `TestSolvers.java` that make it clear what the
behavior of the two worklist implementations should be.

## The `Maze` and `Square` Classes

There are several classes provided for you that both represent the maze and
help create it.

### `Square`

A `Square` represents a single square in the maze. It has the
following fields:

- `row` and `col`, which represent its coordinates
- `isWall`, which is true if the square represents a wall, false if it is an
  empty space
- `previous`, which you will use in the search algorithm to keep track of the
  path from the finish back to the start
- `visited`, which is initially false, and you will use in the search algorithm
  to keep track of squares that have been searched already and shouldn't be
  re-searched

You should read the methods on the `Square` class, as you will use them to
manipulate and access these fields during the search algorithm.

### `Maze`

The `Maze` class represents a rectangular maze with obstacles, a start, and a
finish. Since it just represents data, and the fields don't change via any
methods on the class (indeed, it has no methods), we make them all `final` and
`public`, which makes access easier. So to access the `cols` field of a `Maze`
instance with a reference stored in a variable `m`, just write `m.cols`.

The fields are:

- `rows` and `cols`, which represent the number of rows and columns in the maze
- `contents`, which contains a reference to an array of arrays of `Square`s, or
  `Square[][]`. This represents the entire maze, and the inner arrays represent
  the _rows_. This means:
  - The upper left corner of the maze is at `contents[0][0]`
  - The bottom left corner is at `contents[this.rows - 1][0]`
  - The bottom right corner is at `contents[this.rows - 1][this.cols - 1]`
  - The top right corner is at `contents[0][this.cols - 1]`
- `start` and `finish`, which represent the start square and end square for
  searching. They contain references to the corresponding `Square`s that are in
  the `contents` array.

The `Maze` class has a useful constructor just for testing, which we describe
in the testing section below.

## The Search Algorithm

The search algorithm we will use was presented in class, and is rewritten here:

```
initialize wl to be a new empty worklist (stack _or_ queue)
add the start square to wl
while wl is not empty:
  let current = remove the first element from wl (pop or dequeue)
  mark current as visited
  if current is the finish square
    return current
  else
    for each available neighbor of current
      set the previous of the neighbor to current
      add previous to the worklist (push or enqueue)

if the loop ended, return null (no path found)
```

You will implement this algorithm, in Java, in the `solve` method of
`MazeSolver`. The parameters of `MazeSolver` are a `Maze` instance and a
worklist to use. To test the maze, you can pass in different implementations of
the worklist, and sample mazes.

There is one constraint on your implementation: When checking neighbors, you
_must_ add them to the worklist in the order North, East, South, West. So you
should first add (if it is not a wall or out of bounds) the `Square` one row
higher, then the `Square` one column higher, then one row lower, then one
column lower. Our reference implementation uses this order and you should as
well. Note that this is the order in which `add` should be called, which is
different from the order they will appear in the worklist!


##Testing

You will use JUnit to test your Maze solutions. The output of your solution
will be of type `String[]` (see method `showSolution()`). To build your
expected maze output, you can type it out by hand, or print a Maze to Console,
copy it and make changes to it.

You can use the `assertArrayEquals()` method to check if your Maze solution
matches the expected solution. If you want a more detailed solution that tells
you exactly which parts of your maze are incorrect, simply run the
`formatMaze()` helper function to your actual and expected Mazes, and
`assertEquals()` them.

