---
layout: pa
title: "PA4: Runtime, Measured and Modeled"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

This assignment will give you experience working with big-Ο/θ/Ω
representations, practice matching them to implementations, and perform
measurements of the runtime of different methods.

_This assignment is inspired by a lab in Swarthmore College's CS35_

## Deadlines and Milestones

You will submit a quiz and implementations of `measure` and `measurementsToCSV`
by _Monday midnight_, with the rest due by _Wednesday midnight_.

## Mystery Functions

We have provided you with a (FILL `.jar/.class`) file that contains implementations of the following methods:

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
different order, and we don't provide the source. You have two tasks:
determining a tight big-O bound for each method by analyzing the source, and
determining which mystery method corresponds to the implementations above by
measuring against provided (but hidden) implementation.

### Identifying Bounds from Code

Determine a _tight_ big-O bound for each function, and justify it with a few
sentences. Give only the most relevant term, so use, for example _O(n)_, not
_O(4n + 2)_ Provide this written up in a file called `MysteryBounds.pdf`, and
clearly label each argument with a number 1-6.

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

You _must_ write the following two methods in the `Measurements` class:

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

### The `measurementToCSV` method

The `measurementToCSV` method takes a list of measurements (for example, as
returned from `measure`) and generates a comma-separated-values `String` of the
measurements. It should have the following format, where the first row is a
literal header row and the other rows are example data. Note that this data is
completely made up, and may not match your measurements.

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

### Strategies for Measuring

You can use the `measure` and `measurementToCSV` methods to produce data about
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
implementations above, and generate two graphs to justify your answers. See
below for more explanation.

### Deliverables for Measurements

You must hand in:

- `Measure.java` with the implementations above
- A PDF file called `Matching.pdf` that contains:
  - A listing that matches each of mysteryA-F to an implementation f1-6 above 
  - Two graphs that justify a few choices above. These don't need to
    exhaustively describe all of your matchings, but they must be generated
    from real data that you measured using `measure`, and they must show an
    interesting relationship that helps justify the matching.

    FILL a good picture here



