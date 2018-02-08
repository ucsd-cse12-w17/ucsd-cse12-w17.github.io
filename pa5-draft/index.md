---
layout: pa
title: "PA5: Sortacle"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

This assignment will teach you how to write tests in a thorough, automated way,
will explore some properties of sorting implementations, and will give you
structured practice in re-using code you find on the Web.

*This assignment is inspired by [an assignment from Brown University's
CS019](https://cs.brown.edu/courses/cs019/2016/sortaclesortacle.html).*

## Deadlines

You will submit a PA quiz and meet an implementation milestone by Monday at
11:59pm, and submit the rest of the assignment by Wednesday at 11:59pm.



## Part I: A Bad Implementation Detector

### Testing with Properties

So far in this class, we have usually written tests by following this process:

1. Construct the input data
2. Perform an operation
3. Check that the resulting data is equal to some expected value

This works well for writing a small or medium number of tests targeted at
particularly interesting cases. Checking specific output values, however, isn't
the only or necessarily the best way to test and gain confidence in an
implementation. In fact, sometimes it won't work at all.

Consider sorting a list of `Item`s, defined by:

```
class Item {
  String name;
  int priceInCents;
  int weightInGrams;
}
```

Consider a list containing these `Item`s (the second argument represents
`priceInCents`, the third `weightInGrams`):

```
new Item("iClicker", 3500, 250);
new Item("Java Beans, 1lb", 1000, 1000);
new Item("(Computer) Mouse", 1000, 350);
new Item("Sunglasses", 2000, 150);
```

If we sort by price, which should come first, the item with name `"Java Beans,
1lb"`, or the item with name `"(Computer) Mouse"`? Does the heavier item have
to come first, or the one with the name earlier in the alphabet? If the only
specification is “sort by price” _either answer makes sense_. That is, if we
were to write this method:

```
// Change input to be in sorted order by price
void sortByPrice(List<Item> input)
```

then we should expect that calling `sortByPrice` on a list made up of the
elements above could result in a list with those two elements in _either
order_.  How do we write a test for such a method?

```
@Test
public void testSortByPrice() {
  List<Item> l = new ArrayList<>();
  l.add(new Item("iClicker", 3500, 250));
  l.add(new Item("Java Beans, 1lb", 1000, 1000));
  l.add(new Item("(Computer) Mouse", 1000, 350));
  l.add(new Item("Sunglasses", 2000, 150));

  sortByPrice(l);
  
  assertEquals(???, sorted.get(1)); // There are _two_ valid values to expect!
}
```

For two items, there are some clever solutions. You can use [special
matchers](https://stackoverflow.com/a/19064484/2718315),
for instance. But if there are five items with the same price, there would be
five options in each position! This would quickly become infeasible to write.

Instead of writing out all the tests by hand, we should step back from the
problem. We really care that the list is _sorted_ – there shouldn't be elements
out of order, for instance. There are other important properties, too, like all
the elements that were in the input list should appear somewhere in the output
list – if a sorting method loses elements, it isn't doing its job!

So, instead of writing single tests, we should write methods that, given a
sorting algorithm, check if it satisfies some desired _properties_ that sorting
algorithms ought to. We'll use the following interface to define an
implementation of the sorting algorithm:

```
public interface PriceSorter {

  // Sorts the list in ascending order by price.  Elements of the same price
  // may appear in any order relative to other elements of the same price in
  // after sorting.

	void sortByPrice(List<Item> input);
}
```

### Your Task


We can ask, for any implementation of this interface, if it behaves as we'd
expect. We might express this with a method that takes a `PriceSorter` and
returns `null` if we believe it to be a good sorting method, and a
`Counterexample` if we can find an input list that sorted incorrectly:

```
Counterexample isGoodSorter(PriceSorter sorter);
```

`Counterexample` is defined to contain two lists, an original and a version
that was incorrectly sorted.

You will write a version of `isGoodSorter` and use it to check multiple
different sorting implementations, some good and some bad. Note that, by the
argument above about items with the same price and different names, there are
_multiple possible wheat implementations_ of sorting.

You must implement two methods to help you; you can implement other helpers as
you see fit. The two methods you must implement are:

```
/*
 * Return true if maybeSorted is a sorted version of original
 */
boolean isSortedVersionOf(List<Item> original, List<Item> maybeSorted)
```

`isSortedVersionOf` takes two lists and returns true if `maybeSorted` is a
valid sorted (by price) version of `original`.

```
/*
 * Generate a “random” list of items of size n
 */
List<Item> generateInput(int n);
```

This method should create a list of items to use as input to purported sorting
algorithms algorithms. It's up to you how it generates the items; it should
produce a list of length `n`, however. As described above, consider having it
make items with duplicate names or weights.

### An Overall Strategy

Here's one way you might approach this problem:

- First, implement and test `isSortedVersionOf`. Think of several interesting
  individual cases you can imagine in a first pass, and test it on those cases.
  Note that to test `isSortedVersionOf`, you will be creating pairs of lists of
  items (at first, by hand), and checking _both_ for success and for failure:
  you should have some tests where the `maybeSorted` parameter refers to a list
  that isn't actually a sorted version of the one referred to by `original`.
- Second, implement `generateInput` in a simple way – make `n` items with
  random prices and weights, with a constant name for all of them. Test that
  the method returns the right number of elements without any errors.
- Implement a (really) bad version of `PriceSorter`, that makes no changes at
  all to the underlying list in its `sortByPrice` method. Implement a _good_
  version of `PriceSorter` as well, by using one of the definitions of sorting
  we have from class, adapted to work as a `PriceSorter`.
- Try putting together a first version of `isGoodSorter`. It could create a
  single list using `generateInput`, sort it with the given sorter, check if it
  was sorted correctly using `isSortedVersionOf`, and return `null` if it
  sorted correctly or a `Counterexample` if it didn't. Note: you will need to
  _save_ the original list, since sorters can and will make changes to them!
  You can use the `ArrayList` constructor that consumes another `Collection` as
  an argument to make a copy of a list. With this flow, you can test that
  `isGoodSorter` returns `null` when passed the good sorter, and a
  `Counterexample` when given the bad sorter. The testing methods `assertNull`
  and `assertNotNull` can be helpful here.

You can write these tests in `TestSortTester.java` (yes, the tester has its own
tests!). This will get you through the beginning of the problem, and familiar
with all the major interfaces. With this in hand, you can proceed with more
refined tests. Here are some ideas:

- Make a copy of the good `PriceSorter` you wrote, and change it in a subtle
  way, maybe change a < to a <= in comparison. Is it still a good sort? Can
  your `isGoodSorter` check that?
- Make a copy of the good `PriceSorter` you wrote and change it in an obviously
  breaking way, maybe by changing a loop bound to be `< lst.size() - 1` instead
  of `lst.size()`, as an example. Does `isGoodSorter` correctly return
  some `Counterexample` for this implementation?
- Change `isGoodSorter` to call `generateInput` many times, and check that
  _all_ the generated lists sort correctly, returning the first failure as a
  `Counterexample` if it didn't.
- Feel free to add some interesting hand-written cases to `isGoodSorter` where
  you use interesting input lists that you construct by hand. You can combine
  whether they sort correctly or not (e.g. sort them and then check
  `isSortedVersionOf`).
- Use the sorters that you find on the Web (below) and check if they are good
  or bad.
- The `java.util.Random` class has useful tools for generating random numbers.
  You can create a random number generator and use it to get random integers from
  0 to a bound:

  ```
      Random r = new Random();
      r.nextInt(10);  // Generates a random number from 0 to 9
  ```

  Random integers are useful for filling in values of `priceInCents` and
  `weightInGrams`, but especially price, to generate lists with values in
  interesting different orders. With random numbers:

  - You could create a list of default names to use, and use a random number to
    select which one will be used for a particular `Item`.
  - You could generate random _strings_: create numbers in the range of ASCII
    values, cast them to bytes, and concatenate them together

Overall, your goal is to make it so `isGoodSorter` will return `null` for any
reasonable good sorting implementation, and find a `Counterexample` for any bad
sorting implemenation with extremely high probability. We will provide you with
a bunch of them to test against while the assignment is out, and we may test on
more than we provide you in the initial autograder.

We won't test on truly crazy situations, like a sorter that only fails when
passed lists of 322 elements, or when an Item's name is `"Henry"`. The bad
implementations will involve things logically related to sorting and
manipulating lists, like boundary cases, duplicates, ordering, length, base
cases, and comparisons, as a few examples.

**Assume** that there are no `null` items in the lists, that sorts won't put
`null` items in the lists, and that the variables holding lists of items won't
contain `null`. Basically, don't think too hard about `null` cases for list
contents – there are plenty of interesting behavior to consider without it!

**Don't** have your implementation of `isGoodSorter` take more than a few
seconds per sorting implementation. You don't need to create million element
lists to find the issues, and it will just slow down grading. You should focus
on generating (many, maybe hundreds or thousands of) small interesting lists
rather than a few big ones, which should process very quickly.

## Part II: Code of all Sorts

There's a lot of code out there in the world. Much of it is available, with
permissive licensing, for free on the Web. When you're learning, it's often
useful to write implementations yourself to gain experience. However, there are
also skills related to finding and re-using code, rather than writing your own
from scratch. These skills are useful to develop, and come with their own set
of best practices.

When you re-use or repurpose code, there are two main concerns:

- Are you allowed, legally and ethically? Your course, company, or institution
  may have its own rules, and there are laws about how you can re-use or modify
  code depending on its software license. There are also simple intellectual
  honesty issues around giving credit to the right sources. If you can't answer
  these questions up front, it may be the case that you shouldn't even be
  _looking_ at other code that solves your problem until you have answers. This
  is usually the case in programming courses, for example.
- More practically, does the code actually do what you want? If it's a method,
  are the inputs and outputs the types your program will expect? Does it match
  your performance expectations in terms of its runtime? If you need to change
  it to adapt to your application, what effects will that have on the original
  version?

For this assignment, you must go _find three sorting implementations in Java on
the Web_. You should document the source you got it from clearly, and adapt it
to fit the `PriceSorter` interface that sorts `Item`s by their price. For each
implementation you find, you write in a header comment with the method:

- Where it came from as a URL, and list the author (usernames or emails count!)
  if you can identify the author
- A URL for the _license_ or other rules posted for the re-use of the code. In
  code repositories like those on Github, this will usually be in a file called
  `LICENSE` or `LICENSE.txt` in the root of the repository. Here's one for
  [openjdk](https://github.com/dmlloyd/openjdk/blob/jdk/jdk/LICENSE), a free
  and open source Java implementation, for example. Don't use code for which
  you can't find the rules of re-use!
- Describe what changes you made to adapt it to this problem
- Indicate if it was buggy or not (by using handwritten tests, or potentially
  by using your tester, if you have it ready) and why
- Describe the worst case of its runtime behavior using a tight big-O bound

Put these implementations in the provided files `WebSorter1-3.java`.

**NOTE: This part of the assignment comes with a deliberate, narrow exception
to the Academic Integrity policy for the course. You shouldn't, in any other
assignment (or other parts of this assignment) go hunting for code on the Web
that solves the assignment for you.  You certainly shouldn't do it in other
classes or at your job – you should always know and consult the policies
relevant to your current context. We (the instructors) know how to search for
code on the Web. So do intellectual property attorneys, to extend the analogy
to the professional context.**


## Submission

You will only hand in your repository (the header comments for the
implementations you find on the Web serve as your README).

Grading breakdown:
- 10%: For `isGoodSorter` catching at least one chaff and passing on at least
  one wheat by Monday midnight
- 50%: `isGoodSorter`, graded by how it performs on wheat and chaff sorting
  algorithms
- 10%: Test and code readability and style
- 30%: (10% each) for the sort implementations you find online and describe


