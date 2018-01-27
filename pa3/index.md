---
layout: pa
title: "PA3: Worklists are A MAZE ING"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

This assignment will teach you how to use stacks and queues as worklists, how
to implement an important search algorithm, and how the worklist choice affects
the algorithm.

_This assignment draws ideas from an assignment by Prof Langlois and Alvarado,
which in turn drew from a CSCI 151 lab assignment from Oberlin college._

## Writeup Quiz

You must submit, by Monday at 11:59pm and _before asking for tutor help_, the
following quiz about this writeup. Read the whole writeup and do this quiz
before starting to code. Submitting the quiz and getting at least half the
questions right is worth 2% of your assignment grade.

<a href="https://docs.google.com/forms/d/e/1FAIpQLSeJ7wS4o-2shvnV3Sz99c3E3nHDmqtcurURlHg3sHK-MwJqTw/viewform">https://docs.google.com/forms/d/e/1FAIpQLSeJ7wS4o-2shvnV3Sz99c3E3nHDmqtcurURlHg3sHK-MwJqTw/viewform</a>

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
methods on the class, we make them all `final` and
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

Note that, for testing, returning `null` is how your implementation indicates
that there is no possible path from the source to the target.

There is one constraint on your implementation: When checking neighbors, you
_must_ add them to the worklist in the order East, South, West, North. So you
should first add (if it is not a wall or out of bounds) the `Square` one column
to the right, then the `Square` one row below (one lower index, because the top
row is row 0), then one column to the left, then one row above (one higher
index). Our reference implementation uses this order and you should as well.
Note that this is the order in which `add` should be called, which may be
different from the order they will appear in the worklist.

One place where our implementation got surprisingly complicated, and where we
introduced a helper method, is in checking for available neighbors. It might be
useful to introduce a method that checks if an offset from a particular
coordinate is an empty square; this method might have a signature like

```
// Return true if the location of s, offset by rowOffset and colOffset, is in
// bounds and not a wall, false otherwise
boolean availableNeighbor(Square[][] contents, Square s, int rowOffset, int colOffset)
```

You're free to not write this method, but doing it first could give you some
useful practice, and come in handy later. You also might find variations on it
helpful, that return a `Square` if it is available, or that take a
`SearchWorklist` and add the element if it's available, etc.

## Testing

You should test your solver. Here's some advice and help on doing it.

First, there is a constructor for `Maze` that accepts a `String[]` as an
argument. There is an example—the one we did in class—provided for you. The
input uses a plain text format where:

- `#` indicates a wall
- `_` indicates an empty space
- `F` indicates the finish square
- `S` indicates the start square

For example, the maze from class would be written:

```
#_#_
____
_##S
F___
```

See the example provided test for how to express this as a use of the `Maze`
constructor.

On a successful run of a solvable maze, your `solve` method will have set
previous pointers from `finish` back to `start`. We wrote a method called
`showSolution` that will produce a similar array as a result, but with a `*`
for each square that was part of the path from start to finish. You can
construct these arrays (again, see the example for how), and use a helper we
provided to test them. For this example, the solution with a stack, and the add
order specified above, is:

```
#_#_
****
*##S
F___
```

With a `QueueWorklist`, the answer should be

```
#_#_
____
_##S
F***
```

Note that these tests fail in the starter code, because `solve` is
unimplemented! It will be your job to make them pass, and to thoroughly test
the rest of your implementation.


You can use the `assertArrayEquals()` method to check if your Maze solution
matches the expected solution. If you want a more detailed solution that tells
you exactly which parts of your maze are incorrect, simply run the
`formatMaze()` helper function to your actual and expected Mazes, and
`assertEquals()` them.

Here is what the JUnit output looks like on a failed solution:

<img src="./junit%20output.png">

Note that this is assuming a StackWorklist was used. The JUnit output will show you what segments of your mazes were different (in this case, rows 1-3).



## README

You must write a `README` file that contains answers to the following
questions:

- In your implementation, could the `setPrevious()` method ever be called twice
  on the same square during a single run of `solve()`? Give an example of when
  it would happen, or argue why it can't.

- Argue for or against this statement: “Solving a solvable maze with a queue
  worklist will always produce a path with length less than or equal to solving
  the maze with the stack worklist.” Either provide a counterexample, or write
  a sentence or two about why this must be true.

- Argue for or against this statement: “Solving a solvable maze with a queue
  worklist will always visit equal or fewer squares than solving the maze with
  a stack worklist.” Either provide a counterexample, or write a sentence or
  two about why this must be true.


## Grading

A grader with wheat and chaff implementations will be made available by Monday,
Jan 29, along with a complete grade breakdown. The rough grade breakdown is:

- 2% quiz
- 15% README
- 25% testing
- 8% style
- 50% implementation correctness

