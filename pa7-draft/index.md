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
PA6.

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
- `startCapacity` is the initial size of the array the implementation uses
for bucket storage. The array must be initialized to this size if it is
provided.
- `loadThreshold` is the ratio of elements to capacity at which the collection
will increase in capacity and rehash all elements. In each set, if the size
to capacity ratio exceeds the loadFactor _after_ performing the insert, a
rehash must be performed (after makes sense because at the beginning it's not
clear if a `set` will increase the size or update an existing key).
- `expansionFactor` is the factor to multiply the capacity by on each
expansion and rehash. For example, we used 2 in class to mean “double the
size on each expansion”. This should be positive, and be 2 or greater.
- `hasher` is an implementation of the `Hasher` interface which provides a
user-customized hash function. If `hasher` isn't provided or is `null`, the
hash code for each key should be determined by using the built-in
`hashCode()` method on the key. If `hasher` _is_ provided, the hash code for
each key should be determined by calling the `hasher.hash` method.

You _must_ use an array to store the buckets, and its length should always
represent the capacity of the table.

You may choose _any_ collection to represent the contents of the buckets
(e.g. built-in `ArrayList` or `LinkedList`, array). You may find some of
these easier than others.

Your implementation _must_ have the following worst-case behavior (you may
take into account, for `set`, that the worst-case re-hashing behavior is
shared across all copies):

- `set`: O(loadFactor)
- `get`: O(loadFactor)
- `containsKey`: O(loadFactor)
- `size`: O(1)
- `defaultValue`: O(1)
- `keys`: O(n * lg(n)) [ where n is the number of keys ]
- `values`: O(n * lg(n)) [ where n is the number of keys ]

Your implementation _must_ resize and rehash in `set` if _adding the element_
would exceed the specified load factor.

## Measurement Methods and README

In addition to the `DefaultMap` interface, you must provide several other
methods that are implemented on `HTDefaultMap`:

- `public int lifetimeCollisions()`: The total number of times a collision
has occurred in set (across all rehashes and all insertions)
- `public int currentCapacity()`: The current length of the array of buckets
- `public double currentLoadFactor()`: The current size / capacity ratio
- `public int deepestChain()`: The depth of the current deepest chain in the
hash table (this doesn't have to be particularly fast; it can work by walking
over the whole table)

You will use these to perform three interesting experiments. Your experiments
should each have a workload, and an independent variable, and a dependent
variable. Here are some examples, but you can pick anything you like:

- Idea 1:
    - Workload: 10000000 uses of `set` with 1000000 different keys
    - Independent: initial capacity
    - Dependent: number of total collisions
- Idea 2:
    - Workload: 10000000 sets as setup, followed by 10000 gets of existing keys
    - Independent: expansionFactor
    - Dependent: number of milliseconds for all the gets to complete

One of your experiments _must_ involve changing the hash function from the
default hashCode provided by Java.

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
tests against. You must write tests that pass on the reference and detect at least
one bad implementation by the milestone on Monday midnight.

