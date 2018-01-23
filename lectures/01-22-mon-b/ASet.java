import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Iterator<T>:
//   boolean hasNext();
//   T next();

class ASetIterator<T> implements Iterator<T> {
  ArrayList<T> contents;
  int currentIndex;
  public ASetIterator(ArrayList<T> contents) {
    this.currentIndex = 0;
    this.contents = contents;
  }

  /*

    At any point in the iterator, there are a number of visited elements, and a
    number that haven't been seen yet. currentIndex tracks the _next_ element
    to return. Elements at earlier indices have already been returned by a call
    to next()

    Note that an iterator for a different kind of set might choose a different
    order. It just so happens that this Set implementation uses an array list,
    so the order works out to be in insertion order. That's not required! We
    could visit the elements in the Set in any order, and it would be a
    perfectly appropriate iterator for sets.

    Here's the Java Set interface:
    
    https://docs.oracle.com/javase/7/docs/api/java/util/Set.html

    Note that on the iterator method it asserts that no particular order is
    assumed:

    https://docs.oracle.com/javase/7/docs/api/java/util/Set.html#iterator()
  */

  public boolean hasNext() {
    return this.currentIndex < this.contents.size();
  }
  public T next() {
    if(!this.hasNext()) { throw new NoSuchElementException(); }
    T toReturn = this.contents.get(this.currentIndex);
    this.currentIndex += 1;
    return toReturn;
  }

}

public class ASet<T> implements Set<T> {
  ArrayList<T> contents;
  public ASet() {
    this.contents = new ArrayList<T>();
  }
  public void add(T t) {
    if(!this.contains(t)) {
      this.contents.add(t);
    }
  }
  public int size() {
    return this.contents.size();
  }
  public boolean contains(T t) {
    return this.contents.contains(t);
  }
  public Iterator<T> iterator() {
    return new ASetIterator<T>(this.contents);
  }
}
