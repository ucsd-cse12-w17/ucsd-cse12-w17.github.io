---
layout: pa
title: "PA4: Runtime, Measured and Modeled"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

This assignment will give you experience working with big-Ο/θ/Ω
representations, practice matching them to implementations, and perform
measurements of the runtime of different methods.

_This assignment is inspired by a combination of a lab in Swarthmore College's
CS35, and by a similar assignment by Marina Langlois in CSE12 at UCSD_

## Deadlines and Milestones

You will submit a quiz and implementations of `measure` and `measurementsToCSV`
(described below) by _Monday at 11:59pm_, with the rest due by _Wednesday at
11:59pm_.

We will provide implementations of `measure` and `measurementsToCSV` on
Tuesday; as a result no late points will be given for submissions of that code
submitted past Monday night, no exceptions.

There will be three submission entries in Gradescope, one for your initial code
(due Monday midnight), and two others for your final submission: one for for
your writeup (as a PDF) and one for your final code. Make sure you know how to
generate and submit a PDF _early on_ in the assignment process.

Read the whole writeup before starting – there are several different pieces of
the assignment you will need to hand in.

## Big-O Justification

Justify your answers for questions 1, 3, 7, and 9 in the [PA review quiz.](https://docs.google.com/forms/d/e/1FAIpQLSeV9_uv4Aw7wAOzwQMD84SCVxRdRyMfCkZT91731NZeP7Oocg/viewform)

If you are justifying the positive direction, give choices of `n0` and `C`. For
big-Θ, make sure to justify both big-O and big-Ω.

If you are justifying the negative direction, indicate which term(s) can't work
because one is guaranteed to grow faster or slower than the other.

As a quick guide, here is an ordering of functions from slowest-growing
(indeed, the first two _shrink_ as n increases) to fastest-growing that you
might find helpful:

- f(n) = 1/(n<sup>2</sup>)
- f(n) = 1/n
- f(n) = 1
- f(n) = log(n)
- f(n) = sqrt(n)
- f(n) = n
- f(n) = n<sup>2</sup>
- f(n) = n<sup>3</sup>
- f(n) = n<sup>4</sup>
- ... and so on for constant polynomials ...
- f(n) = 2<sup>n</sup>
- f(n) = n!
- f(n) = n<sup>n</sup>

Provide this written up on the _first_ page of `pa4.pdf`.

## List Analysis

Consider the two files [CSE12ArrayList.java](./CSE12ArrayList.java) and
[CSE12DLList.java](./CSE12DLList.java), which are the starter files from PA2.
Answer the following questions, and justify them with one or two sentences
each:

- Give a tight big-O bound for the _best case_ running time of `prepend` in
  CSE12ArrayList
- Give a tight big-O bound for the _best case_ running time of `prepend` in
  CSE12DLList
- Give a tight big-O bound for the _worst case_ running time of `prepend` in
  CSE12ArrayList
- Give a tight big-O bound for the _worst case_ running time of `prepend` in
  CSE12DLList
- Give a tight big-O bound for the _best case_ running time of `append` in
  CSE12ArrayList
- Give a tight big-O bound for the _best case_ running time of `append` in
  CSE12DLList
- Give a tight big-O bound for the _worst case_ running time of `append` in
  CSE12ArrayList
- Give a tight big-O bound for the _worst case_ running time of `append` in
  CSE12DLList

In all cases, give answers in terms of the _current size of the list_.

Notable points to consider:

- Creating an array takes time proportional to the length of the array
- When considering the running time of a method, make sure to take into
  account any helpers methods it uses!

Example for `getAt` in the `CSE12DLList` class:

    The getAt method is O(1) in the best case, when the index is 0. In this
    case it will do constant work checking the index and immediately return the
    first element, never entering the while loop.

    The getAt method is O(n) in the worst case, because the index could be at
    the end of the list (for example, index n - 1). In this case, the while
    loop will run n times, spending constant time on each iteration, resulting
    in overall O(n) number of steps taken.

Provide this written up on the _second and third_ pages of `pa4.pdf`.

## Mystery Functions

We have provided you with a `.jar` file that contains implementations of the
following methods:

```
	public static void f1(int n) {
		int a = 0;
		for (int i = 0; i < n; i += 1) {
			a = i;
		}
	}
	public static void f2(int n) {
		int a = 0;
		for(int i = 0; i < n; i += 2) {
			a = i;
		}
	}
	public static void f3(int n) {
		int a = 0;
		for(int i = 0; i < n * n; i += 1) {
			a = i;
		}
	}
	public static void f4(int n) {
		int a = 0;
		for(int i = 0; i < n; i += 1) {
			for(int j = i; i < n; j += 1) {
				a = i + j;
			}
		}
	}
	public static void f5(int n) {
		int a = 0;
		for(int i = 0; i < n * n; i += 1) {
			for(int j = 0; j <= i / 2; j += 1) {
				a = i + j;
			}
		}
	}
	public static void f6(int n) {
		int k = 1, a = 0;
		for(int i = 0; i < n; i += 1) {
			for(int j = 0; j <= k * 2; j += 1) {
				a = i + j;
			}
			k = k * 2;
		}
	}
```

However, in that file, they are called `mysteryA-F`, and they are in a
different order, and we don't provide the source of that file. You have two
tasks: determining a tight big-O bound for each method labeled 1-6 analyzing
the source above, and determining which mystery method A-F corresponds to the
implementations above by measuring against provided (but hidden)
implementation.

### Identifying Bounds from Code

Determine a _tight_ big-O bound for each function, and justify it with a few
sentences. Give only the most relevant term, so use, for example _O(n)_, not
_O(4n + 2)_ Provide this written up on the _fourth_ page of `pa4.pdf`.

### Measuring Implementations

You will write a program to:

- Measure the mystery methods
- Use your measurements to match the mystery methods to the sources above
- Generate several graphs to justify your work

You have a lot of freedom in how you do this; the deliverables you need to
produce are specified at the end of this section. There are a few methods that
we _require_ that you write in order to do this, and they will help guide you
through the measurement process.

#### The `measure` Method

You _must_ write the following two methods in the `Measurement` class:

```
public static List<Measurement> measure(String[] toRun, int startN, int stopN)`
public static String measurementsToCSV(List<Measurement> measurements)
```

where `Measurement` is defined in `Measurement.java`.

- `measure` should work as follows:

  1. It assumes each string in `toRun` is one of the letters A-F.
  
  2. For each of the implementations to run, it runs the corresponding
  `mysteryX` method `stopN - startN` times, providing a value of `n` starting
  at `startN` and ending at `stopN` each time.

  3. For each of these runs, it _measures_ the time it takes to run. You can do
  this by using the method `System.nanoTime()` (see an example in [lecture
  notes](https://github.com/ucsd-cse12-w17/ucsd-cse12-w17.github.io/blob/master/lectures/01-29-mon-b/Sorts.java))
  
  4. For each of the measured runs, it creates a `Measurement` whose `valueOfN`
  field is the value that was used for the given run, whose `name` field is the
  single-letter string of the implementation that ran, and whose
  `nanosecondsToRun` field is a measurement, and adds it to a running list of
  measurements.

  5. The final result is the list of measurements.

**Example**:

This call:

```
		measure(new String[]{"A", "B"}, 40, 100);
```

Should produce a list that has 122 measurements, 61 of which will have `name`
equal to `"A"` and 61 of which will have `name` equal to `"B"`. Each of the 61
for each name will have a different `valueOfN` from 40 to 100, and each will
have a different number of nanoseconds (as was measured).

### The `measurementsToCSV` method

The `measurementsToCSV` method takes a list of measurements (for example, as
returned from `measure`) and generates a comma-separated-values `String` of the
measurements. It should have the following format, where the first row is a
literal header row and the other rows are example data. Note that this data is
completely made up, and may not match your measurements.

You might choose to put all of the measurements for a single letter together:

```
name,n,nanoseconds
A,40,1034
A,41,1039
A,42,2033
... many rows for A ...
A,100,432
B,40,1034
B,41,4038
... many rows for B ...
```

You might also choose to put all of the measurements for a single round of `n`
together:

```
name,n,nanoseconds
A,40,1034
B,40,1034
A,41,1039
B,41,4038
A,42,2033
B,42,4038
... many alternating rows of A, B ...
A,100,432
B,100,8038
```

Either layout is fine, do what makes sense to you, or what matches your
`measure` function best, etc.

### Strategies for Measuring

You can use the `measure` and `measurementsToCSV` methods to produce data about
how the functions behaved in terms of their runtime. You should fill in the
`main` method with whatever you find useful for using your measuring methods to
compare the mystery implementations. You have total choice in how you implement
this – you can add new helpers, print the CSV format out to a file, copy/paste
it into a spreadsheet, use a tool you like for plotting, etc. The goal is to
use measurements to identify the different implementations. Feel free to look
up documentation for writing Strings out to files and use it, or use
`System.out.println` and copy/paste the output, etc. It's probably pretty
expedient to copy the data into Excel or a Google Sheet.

There are a few high-level strategies to consider:

- If an implementation is very slow, it could take a really long time to
  measure it for large n. If you notice something is taking a long time, stop
  the program and try the same mystery methods on a smaller input range. Does
  the smaller range tell you anything useful?
- Some of the methods might have similar big-O bounds, but have different
  constants that can be measured in terms of absolute time.
- Some of the methods might take vastly different times to run on certain
  inputs, so plotting them next to one another will show one with a flat line
  at 0 and the other with some interesting curve. Make sure to check what the
  relative numbers are when inspecting the output.

You will use these measurements to figure out which mystery method matches the
implementations above, and generate three graphs to justify your answers.
 

### Deliverables for Measurements

You will hand in:

- Your code with the implementations above, by Monday 11:59pm, by the usual
  Gradescope handin, in the assignment `pa4`.
- All of your code, in the assignment `pa4-final-code`, by the usual gradescope
  handin by Wednesday 11:59pm.
- On the _fourth page and beyond_ in your `pa4.pdf`:
  - The BigO bounds for each implementation f1-6.
  - A listing that matches each of mysteryA-F to an implementation f1-6 above 
  - Three graphs that justify a few choices above. These don't need to
    exhaustively describe all of your matchings, but they must be generated
    from real data that you measured using `measure`, and they must show an
    interesting relationship that helps justify the matching.

If you want a guide on how to get from the CSV data to a graph, look here:

[https://docs.google.com/document/d/1vwckO76TrBT8B5E4xQ2-v2OXncLa6SQWuaQkNZaCPB0/edit](https://docs.google.com/document/d/1vwckO76TrBT8B5E4xQ2-v2OXncLa6SQWuaQkNZaCPB0/edit)

## Submission Instructions

There are four artifacts to submit for this PA:

By Monday, 11:59pm
- The review quiz linked above
- The `pa4` assignment in Gradescope, where you will submit your code as usual
  from Github.

By Wednesday, 11:59pm
- The `pa4-final-code` assignment in Gradescope, where you will submit your
  final code for performing measurements, along with a README describing how
  you measured things. This README should just contain a few sentences
  describing how you ran your program to generate data.
- The `pa4-written` assignment in Gradescope, where you will submit a _single_
  PDF file called `pa4.pdf`.
  - The _first_ page should have your big-O justifications, which should take
    up one page (you don't have to write a page of text! But don't put any
    other answers on the first page)
  - The _second_ and _third_ pages should have your List analysis, which should
    take up pages 2 and 3 (you don't have to write two pages of text! But don't
    put any other answers on the second or third pages)
  - The _rest_ of the pages should have your matchings for the mystery
    functions, along with your graphs and justifications

## Grade Breakdown

- 15% startup work
  - 3% writeup quiz
  - 4% `measurementsToCSV`
  - 8% `measure`
- 20% big-O justifications
- 20% list big-O analysis
- 5% final code and README – we will just check that you did _something_
  resonable here, to help show where the data for the matching came from
- 40% matching activity
  - 24% big-O descriptions of f1-6
  - 6% correct matching
  - 10% three relevant graphs
