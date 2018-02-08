---
layout: pa
title: "PA5: Sortacle"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

This assignment will teach you how to write tests in a thorough, automated way,
and will explore some properties of sorting implementations.

*This assignment is inspired by [an assignment from Brown University's
CS019](https://cs.brown.edu/courses/cs019/2016/sortaclesortacle.html).*

## Testing with Properties

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

Consider a list containing these `Item`s:

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
matchers](https://stackoverflow.com/questions/19004611/junit-assert-or-condition-in-my-case),
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

## Your Testing Task

We can ask, for any implementation of this interface, if it behaves as we'd
expect. We might express this with a method that takes a `PriceSorter` and
returns `true` if we believe it to be a good sorting method, and `false` if we
can find a counterexample:

```
boolean isGoodSorter(PriceSorter sorter);
```

You will write a version of `isGoodSorter` and use it to check multiple
different sorting implementations, some good and some bad. Note that, by the
argument above, there are _multiple possible wheat implementations_ of sorting.

You must implement two methods to help you; you can implement other helpers as
you see fit. The two methods you must implement are:

```
// Return true if maybeSorted is a sorted version of original
boolean isSortedVersionOf(List<Item> original, List<Item> maybeSorted)
```

`isSortedVersionOf` takes two lists and returns true if `maybeSorted` is a
valid sorted (by price) version of `original`.

```
// Generate a “random” list of items of size n
List<Item> generateInput(int n);
```

This method should create a list of items to use as input to purported sorting
algorithms algorithms. It's up to you how it generates the items; it should
produce a list of length `n`, however. As described above, consider having it
make items with duplicate names or weights.

## Code of all Sorts

There's a lot of code out there in the world. Much of it is available, with
permissive licensing, for free on the Web. When you're learning, it's often
useful to write implementations yourself to gain experience. However, there are
also skills related to finding and re-using code, rather than writing your own
from scratch. These skills are useful to develop, and come with their own set
of best practices.

When you re-use or re-purpose code, there are two main concerns:

- Are you allowed, legally and ethically? Your company or institution may have
  its own rules rules, and there are legal rules about how you can re-use or
  modify code depending on its software license. There are also simple
  intellectual honesty issues around giving credit to the right sources. If you
  can't answer these questions up front, it may be the case that you shouldn't
  even be _looking_ at other code that solves your problem until you have
  answers.
- More practically, does the code actually do what you want? If it's a method,
  are the inputs and outputs the types your program will expect? Does it match
  your performance expectations in terms of its runtime? If you need to change
  it to adapt to your application, what effects will that have on the original
  author's intentions?

For this assignment, you must go _find three sorting implementations in Java on
the Web_. You should document the source you got it from clearly, and adapt it
to fit the `PriceSorter` interface that sorts `Item`s by their price. For each
implementation you find, you write in a header comment with the method:

- Where it came from, as a URL and listing the author if you can identify the
  author
- If you could find a _license_ for the code, or other rules posted for how it
  is allowed to be re-used. If you can find it, link to the rules or license.
  If you can't find it, explain where you looked on the web site you found it
  on. For this assignment, if it's publicly available on the Web, you are
  unlikely to run afoul of any rules and regulations – it would be a different
  story if you were going to release a product with the code or make money off
  of it! You'd need to be much more careful then.
- Describe what changes you made to adapt it to this problem
- Indicate if it was buggy or not (by using your tester) and why
- Describe the worst case of its runtime behavior

Two of the three implementations should have different worst case big-Θ bounds;
for example, you shouldn't find three implementations of selection sort since
they will all be big-Θ(n<sup>2</sup>). Find an interesting mix. You're welcome
to find sorting algorithms that we didn't cover in class or the textbook.


## Do's, Don'ts, and Assumptions

**Assume** that there are no `null` items in the lists, that sorts won't put
`null` items in the lists, and that the variables holding lists of items won't
contain `null`. Basically, don't think too hard about `null` cases – there's
plenty of interesting behavior to consider without it!

**Don't** have your implementation of `isGoodSorter` take more than about 10
seconds per sorting implementation. You don't need to create million element
lists to find the issues, and it will just slow down grading. You should focus
on generating many small interesting lists rather than a few big ones.



