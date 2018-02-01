---
layout: pa
title: "PA4: Runtime, Measured and Modeled"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

This assignment will give you experience working with big-Ο/θ/Ω
representations, practice matching them to implementations, and perform
measurements of the runtime of different methods.

## Mystery Functions

We have provided you with a (FILL `.jar/.class`) file that contains implementations of the following methods:

```
	public static void m1(int n) {
		int a = 0;
		for (int i = 0; i < n; i += 1) {
			a = i;
		}
	}
	public static void m2(int n) {
		int a = 0;
		for(int i = 0; i < n; i += 2) {
			a = i;
		}
	}
	public static void m3(int n) {
		int a = 0;
		for(int i = 0; i < n * n; i += 1) {
			a = i;
		}
	}
	public static void m4(int n) {
		int a = 0;
		for(int i = 0; i < n; i += 1) {
			for(int j = i; i < n; j += 1) {
				a = i + j;
			}
		}
	}
	public static void m5(int n) {
		int a = 0;
		for(int i = 0; i < n * n; i += 1) {
			for(int j = 0; j <= i / 2; j += 1) {
				a = i + j;
			}
		}
	}
	public static void m6(int n) {
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
how the functions behaved in terms of their runtime.

## Deliverables

You must hand in:

- `Measure.java` with the implementations above
- A PDF file called `Matching.pdf` that contains:
  - 
