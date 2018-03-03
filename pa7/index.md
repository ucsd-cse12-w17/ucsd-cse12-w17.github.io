---
layout: pa
title: "PA7: Making a Hash of It"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

In this assignment you'll implement the `Map` interface using a hash table,
and use it to make several performance measurements.

## `DefaultMap` Redux

As we saw in class, a hash table is a useful implementation of the same `Map`
interface we used to motivate BSTs. To drive this point home, you will
implement a hash table to the very same `DefaultMap` interface we used in
PA6, with only a single change. The single change (mainly just to focus on
the more interesting parts of the problem) is that `keys` and `values` do not
have to return things in sorted order; any order is fine.

You will implement a hash table using separate chaining and a
configurable initial capacity, load factor, expansion factor, and hash
function. This will allow you to do a few measurements of the relative
performance of various operations.

You _must_ support the following constructor for your implementation:

```
public HTDefaultMap(V defaultValue, int startCapacity, double loadThreshold, int expansionFactor, Hasher<K> hasher)
```

The meaning of each parameter is:

- `defaultValue` has the same meaning as in PA6 (including the meaning of
  `null`)
- `startCapacity` is the initial size of the array the implementation uses for
  bucket storage. The array must be initialized to this size if it is provided.
- `loadThreshold` is the ratio of elements to capacity at which the collection
  will increase in capacity and rehash all elements. In each set, if the size
  to capacity ratio equals or exceeds the loadFactor _after_ performing the
  insert, a rehash must be performed (after makes sense because at the
  beginning it's not clear if a `set` will increase the size or update an
  existing key).
- `expansionFactor` is the factor to multiply the capacity by on each expansion
  and rehash. For example, we used 2 in class to mean “double the size on each
  expansion”. This should be positive, and be 2 or greater.
- `hasher` is an implementation of the `Hasher` interface which provides a
  user-customized hash function. If `hasher` isn't provided or is `null`, the
  hash code for each key should be determined by using the built-in
  `hashCode()` method on the key. If `hasher` _is_ provided, the hash code for
  each key should be determined by calling the `hasher.hash` method. Note that
  `hash` might return a negative value: use `Math.abs` on the result in your
  hash table implementation to avoid a negative index.

You _must_ use an array to store the buckets, and its length should always
represent the capacity of the table.

You may choose _any_ collection to represent the contents of the buckets
(e.g. built-in `ArrayList` or `LinkedList`, array). You may find some of
these easier than others. We have provided a _suggested_ field that you can
use, which has type `List<Pair<K,V>>`; you can use this as is or change it.

Here are notes, including required runtimes, for each of the methods you will
implement from the `DefaultMap` interface. Your implementation _must_ have
the given worst-case behavior:

- `set`: O(loadFactor)

  `set` should insert the given key/value association if it isn't present, and
  _then_, if the table's loadFactor equals or exceeds the threshold, expand the buckets
  array and rehash all elements.

  If the key is already present, `set` should update the value stored there.
  
  
  [Here](pa7.png) is an example of the structure of data when some keys are set, and when the HashTable expands and rehashes.

- `get`: O(loadFactor)


  `get`, as in PA6, should throw `NoSuchElement` if `defaultValue` is `null`
  and the key is not found. It should return the `defaultValue` if it is
  non-`null` and the key isn't found.
- `size`: O(1)

   `size` should return the number of keys stored in the table.
- `keys`: O(n) _where n is the number of keys_

  `keys` should return all the keys stored in the table in a list, in any
  order.
- `values`: O(n) _where n is the number of keys_

  `values` should return all the values stored in the table in a list, in any
  order.
- `defaultValue`: O(1)
- `containsKey`: O(loadFactor)

You will submit your implementation under the `pa7` assignment by Wednesday
midnight.

## Measurement Methods and README

In addition to the `DefaultMap` interface, you must provide several other
methods that are implemented only on `HTDefaultMap`:

- `public int totalCollisions()`: The total number of times a collision
has occurred in set (across all rehashes and all insertions)
- `public int currentCapacity()`: The current length of the array of buckets
- `public double currentLoadFactor()`: The current size / capacity ratio
- `public int deepestChain()`: The depth of the current deepest chain in the
hash table (this doesn't have to be particularly fast; it can work by walking
over the whole table)

You will use these to perform three interesting experiments. Your experiments
should each have a configuration, a workload, and an independent variable,
and a dependent variable. Here are some examples, but you can pick anything
you like:

- Idea 1:
    - Configuration: built-in `hashCode()` method, load factor 0.75, expansion factor 4
    - Workload: 10000000 uses of `set` with 1000000 different keys
    - Independent: initial capacity
    - Dependent: number of total collisions
- Idea 2:
    - Configuration: built-in `hashCode()` method, initial capacity 40, load factor 0.66
    - Workload: 10000000 sets as setup, followed by 10000 gets of existing keys
    - Independent: expansionFactor
    - Dependent: number of milliseconds for all the gets to complete

One of your experiments _must_ involve changing the hash function from the
default hashCode provided by Java, by providing a non-`null` value for
`hasher`.

For each of the three experiments, provide a plot describing the data, and a
few sentences about why it is interesting. You might discuss if you think the
pattern shown will extend beyond the data you graphed it for, if you think it
demonstrates evidence for a best practice in using hash tables, if you
learned something particularly interesting from it, and so on.

You will submit these as `pa7-written`, as a single PDF file, by Wednesday
midnight.

## Testing

You will write unit tests for your implementation, and we will provide a
handful of bad implementations, along with a reference, for you to run your
tests against. You must write tests that pass on the references and detect at
least one bad implementation by the milestone on Monday midnight.

Note that for `keys` and `values`, any returned order is acceptable! Keep
this in mind for testing, since the reference implementations might not
return things in the same order.

You will submit your tests (by submitting your whole repo as usual) to the
`pa7-milestone` assignment Monday midnight, and the `pa7` assignment (along
with the rest of your code) by Wednesday midnight.

## Grading

- 3% A writeup quiz
- 15% Milestone
- 27% Testing
- 55% Implementation
