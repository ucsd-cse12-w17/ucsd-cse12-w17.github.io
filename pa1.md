---
layout: pa
title: "PA1: Testing Shopping Bags"
---

<h1>{{ page.title }}</h1>

*This assignment will be released at some point on Jan 11, 2018 and
announced clearly when it is; until the announcement this is under
development.*

This assignment will teach you to use JUnit to test implementations of an
interface, and review a number of Java concepts.

## Bags and Interns

Imagine that you work for Nile, an hot new Web shopping company. You know
it's critical to have shopping cart functionality in Nile so users can keep
track of items before they check out.  The market research team lets you
know that Shopping “Bag” is the word that resonates most with the site's
expected userbase. So you set out to implement a shopping bag interface. The
inventory team has already decided that all items will be constructed from
the class `Item` (given in `Item.java`), which you need to work with.

As an excellent software designer, you consider things interface-first, and
come up with the following interface for the `ShoppingBag`:

```java
public interface ShoppingBag {
	/*
	 * @return the total count of all items, counting duplicates, in the bag.
	 */
	int totalCount();

	/*
	 * @param i The item to count
	 * 
	 * @return The number of the provided Item that are in the cart
	 */
	int itemCount(Item i);

	/*
	 * @return the total price of all items in the bag (counting duplicates)
	 */
	int totalPrice();

	/*
	 * @param i The item to add
	 */
	void add(Item t);

	/*
	 * Remove a single copy of an item from the bag
	 * 
	 * @param i The Item to remove
	 * 
	 * @return false if the item was not in the bag, true otherwise
	 */
	boolean removeOne(Item i);

	/*
	 * Remove all copies of an item from the bag
	 * 
	 * @param i The Item to remove
	 * 
	 * @return false if the item was not in the bag, true otherwise
	 */
	boolean removeAll(Item i);

	/*
	* Remove all items from the bag
	*/
	void empty();

}
```

You're strapped for time because you're working on a number of projects, but
you figure you can leverage your interns to get this done on time and under
budget. You send a message with the interface above to your team of
interns and tell them to implement it.

A few days later, you realize you sent the message to _all_ the interns in
your department, and you now have 12 _different_
implementations of `ShoppingBag`. All of them indeed implement the interface
in terms of Java types, but as you begin trying them out, you notice that
they don't all have the same behavior.

You want to understand the situations that make each of these
implementations differ, in order to decide which one to use. In addition,
you figure it would be useful to give all the interns some feedback. You
want to be able to tell them, specifically, why their implementation
differed. So you set a goal for yourself: You will come up with a set of
tests such that, for each implementation, the tests pass and fail in a way
that is _unique_ to that implementation. This will truly demonstrate how
they differ.

## Getting the Code

FILL

## Code Layout

There are a number of files provided in the starter code:

- `Bag0-11.java`: These files hold the interns' implementations of
  `ShoppingBag`. You are free to read and inspect them as much as you'd
  like. You should *not* change them.
- `ShoppingBag.java`: The interface we defined above. You should *not*
  change this.
- `ShoppingBagTest.java`: This is where you will do your work, described in
  detail below.

## Writing Tests

You will write your tests as JUnit tests in the file `ShoppingBagTest.java`.
There is some pre-existing code in this file that you shouldn't change, and
an example that follows to get you started.

The top of the file sets things up so that the tests will run once against
each provided implementation of `Bag`. This is what the `@Parameterized` and
related methods are doing. The main feature that is relevant to your work is
that the method `makeBag`, which can be called to create a new, empty `Bag`
of the current type under test. You will use `makeBag` to create the objects
you test against.

Your work will happen in methods annotated with `@Test`, below the
definition of `makeBag`. We've gotten you started with an example. Intern 0
_really_ phoned it in (go look at `Bag0.java` to see just how much). The
implementation Bag0 is the only one that will fail this test:

```java
@Test
public void addedHasCount1() {
  ShoppingBag bagToTest = makeBag();

  Item i = new Item("Shampoo", 5);
  bagToTest.add(i);
  assertEquals(bagToTest.totalCount(), 1);
}
```

That is, if we create a new empty `Bag` and add an `Item` to it, we should
expect that the total count of items is `1` after. If you run the program
with just this test defined, you will see that it fails _only_ on
`Bag0`-created bags. It works just fine on the other implementations, whose
mistakes and differences are more subtle.

Your job is to write more methods like `addedHasCount1` with more
sophisticated assertions that fail on the different implementations in
different ways. Here are some things to think about; they don't exhaustively
cover the space of issues, but they help.

- Adding duplicate items to the bags
- Adding lots of items to the bags
- Different kinds of removal from the bags
- Adding and removing the same item

## Running and Reading JUnit Results

To run the tests, you can click the green arrow button in Eclipse with
`ShoppingBagTest.java` open. The left-hand pane will show a tree view of
which tests succeeded and failed on each Bag implementation. You can click
on the dropdown arrow next to each Bag name to se which specific tests
suceeded and failed, and click on the individual tests to see them in the
source window and see a description of the failures.

Note that in this assignment, _a failing test is not (necessarily) a bad
thing_. You are _trying_ to write tests that fail on some implementations
and not others, in order to distinguish their behavior. As a result, you
should not expect JUnit to be responding with all successes, because there's
no “right” set of tests that will succeed all the time.

## Submitting

FILL

## README

1. Pick three of the `Bag` implementations other than `Bag0`. In 150 words
or less, describe the tests that differ across them, and why the
implementations produce those different results. You don't have to talk in
detail about _all_ of your tests, just the ones that usefully distinguish
the three implementations you chose.

2. Some of the `Bag` implementations are buggy – they have clear mistakes in
some situations. Others simply differ in behavior. For each implementation,
indicate if you think it has a clear _bug_, and describe the problem, or if
it's simply an implementation _choice_. Give one sentence for each bag. Note
that this requires exercising your own judgment, which we cannot do for you.

Here's an example: “Bag0 is clearly buggy, because under no reasonable
implementation should the bag claim to be empty after having something
added.”


