---
layout: pa
title: "PA6: An N-Gram Viewer"
doodle: "/doodle.png"
---

<h1>{{ page.title }}</h1>

In this assignment you'll implement the `DefaultMap` interface by using a BST. You'll
use the implementation to build an n-gram viewer, which shows data about common
words and phrases in text over time.

## Deadlines

- **Friday, Feb 23, 11:59pm**
  - Your implementation of `BSTDefaultMap.java`
  - A writeup quiz
- **Wednesday, Feb 28, 11:59pm**
	- Your implementation of `Loader.java`, and some associated README
		questions

Note that we will provide an implementation of `BSTDefaultMap.java` after the
first deadline, so no late submissions will be accepted for that milestone.

## The `DefaultMap` Interface

You'll provide a fast implementation of the `DefaultMap` in
`BSTDefaultMap.java`.  You _must_ implement a binary search tree
implementation of this interface, and you can't use any built-in Java
collections other than `List` to do so.

There is one twist to the `Map` implemenation you will write: it will be
constructed with a _default value_ that it returns for keys that aren't found.
So, for example, a default map constructed with a `0` default value will return
`0` for all keys that aren't present:

```
BSTDefaultMap<String, Integer> thisTree = new BSTDefaultMap<>(0);
assertEquals(0, (int)thisTree.get("C"));
assertEquals(0, (int)thisTree.get("A"));
thisTree.set("C", 3000);
assertEquals(3000, (int)thisTree.get("C"));
assertEquals(0, (int)thisTree.get("A"));
```

In addition, if `null` is provided as the default value, any attempt to get a
key that isn't set should result in a `NoSuchElement` exception. 

```
BSTDefaultMap<String, Integer> thisTree = new BSTDefaultMap<>(null);
thisTree.get("A"); // Throws NoSuchElementException
```

You will implement _all_ the methods defined in the `DefaultMap` interface for
`BSTDefaultMap`: `set`, `get`, `keys`, `values`, `size`, and `defaultValue`.
Their implementations are defined in header comments in the `DefaultMap.java`
file, which you should follow.

Your implementation of `BSTDefaultMap` will be graded automatically by tests
that we provide. We'll provide a subset of the tests in the grader, and you
must submit your implementation to the `pa6-bst` project by the end of Friday,
February 23.

## N-Grams

An n-gram is a sequence of N adjacent words in a document. The 1-grams (when
N=1) in a document are just all the words within it. The 2-grams are all the
sequences of two adjacent words, and so on. Here's an example:

    this sentence is an example sentence

- 1-grams: `"this"`, `"sentence"`, `"is"`, `"an"`, `"example"`, `"sentence"`
- 2-grams: `"this sentence"`, `"sentence is"`, `"is an"`, `"an example"`, `"example sentence"`
- 3-grams: `"this sentence is"`, `"sentence is an"`, `"is an example"`, `"an example sentence"`

One way that text is analyzed is by breaking it into n-grams and counting the
frequency at which various n-grams appear. Breaking this data into a _time
series_ is particularly interesting – how do particular terms change in
frequency in documents over time?

Your task is to build the data structures needed to load a corpus of n-grams
and visualize them. You will build several methods in a class called `Loader`
to load a corpus of words, and then use a graphing library to plot them. You
will use the implementation of `BSTDefaultMap.java` to do so.

The data is a sample of English language from the years 1990 to 2012,
containing a few million words in total. It is the [COCA sample from this web
site](https://www.corpusdata.org/formats.asp). It is laid out in plain text
files within a single directory, and the filenames all have the format:

```
w_<type>_<year>.txt
```

Where `<type>` says something about the source of data (interesting, but not
relevant for the assignment), and `<year>` says what year the data is from.
For example `w_mag_1991.txt` contains snippets of text from magazines in 1991,
and `w_fic_2002.txt` contains snippets from fictional works in 2002.

The files themselves each contain several lines with text and other
formatting characters on them, for example:

```
##4000054 Section : Features Research sheds new light on the origin of humanity 's most intimate quadruped ally <p> The poor dog , " wrote poet Lord Byron in a flight of emotion , " in life the firmest friend , The first to welcome , foremost to defend . " And certainly , few animal lovers would care to differ . The dog , after all , is commonly referred to as man 's best friend , and unquestionably serves a wide range of human purposes . Thanks to artificial selection , there are dogs that guard houses and dogs that herd livestock , dogs that locate game birds for shooting and dogs that retrieve game birds that have been shot , dogs that pull sleds and dogs that sit languidly in human laps . <p> Clearly , the relationship between dog and human runs deep in our culture and our psyches . No surprise , then , that the origin of the domestic dog has long been a matter for speculation and inquiry . But now , new techniques of molecular biology are allowing researchers to trace @ @ @ @ @ @ @ @ @ @ ways previously unavailable to traditional wildlife biologists , taxonomists , and archeologists . Investigators are making great strides in understanding the origin of the domestic dog , even though results are often subject to dispute and controversy , as might be expected of research on a creature that is genetically complex . <p> " No other species is so diverse , " says Robert Wayne , a University of California-Los Angeles evolutionary biologist who has just completed the largest study ever on dog genetics and evolution . " Dogs are a model for how rapid morphological change might take place in a natural population . " They also offer clues as to how genetic vigor can be maintained in domestic species . <p> One of the key questions of dog evolution focuses on the source : From what wild creature did the domestic dog arise ? Charles Darwin suggested that the close relationship between wolves , coyotes , and jackals-all of which can interbreed-so muddies questions of which species yielded the dog that " we shall probably never be able to ascertain the dog 's origins with certainty . " Austrian @ @ @ @ @ @ @ @ @ @ 
```

You'll notice a few things about the format:

- Punctuation has spaces added around it
- There are series of `@` characters used to separate passages
- There is an id number (irrelevant for this assignment) at the beginning of each line

This format where be your source for retrieving the n-grams from the data
set, and these files are all in the directory `data/` included with your
repository.  Your program will consume this data and produce graphs like
this:

<img src="./screenshot.png" width="100%">

That is, a user can type in multiple n-grams separated by semicolons, and your
program will produce a graph that shows the relationship.

**Get started on Part I now! This is as a two-part assignment, with full
instructions for Part II ready very soon. Do read the starter code for
Loader.java, have a look at the data, and start thinking about what will go
into building the interface above!**

## Grading

- 25% initial submission of BST implementation
- 3% writeup quiz
- 45% implementation of Loader.java/main
- 20% README
- 7% overall testing & style
