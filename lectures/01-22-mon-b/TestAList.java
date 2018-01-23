import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Iterator;
import java.util.ArrayList;

public class TestAList {

  /*

    Here's a "by-hand" use of Iterable/Iterator

    The method takes any Iterable as an argument, constructs an
    iterator for it, and then uses next() and hasNext() to iterate
    over its contents.

    Note a few things:

    - We construct a new iterator each time this method is called.
      This makes it possible to iterate over the same collection
      many times. For exampe, we may have many methods that need
      to iterate over a set (to get the product of elements, to
      calculate a union, etc). For that reason, every time we do
      an iteration, we need a _fresh, new_ iterator that can
      traverse the elements starting at the beginning again.

    - The while/hasNext/next pattern is going to be repeated many,
      many times in programs that iterate over collections

  */
  public int sumOfIterable1(Iterable<Integer> collection) {
    Iterator<Integer> iter = collection.iterator(); // implicit below
    int total = 0;
    while(iter.hasNext() // implicit below
      ) {
      Integer num = iter.next(); // implicit below
      total += num;
    }
    return total;
  }

  /*

    Java provides special syntax, called a for-each loop or an
    enhanced for-loop, which translates into the above code.

    We can view the lines above that are marked // implicit below
    as being inserted by the Java compiler when it sees an
    enhanced for loop. So the syntax

      for(SomeType t: collection) {
        ... body ...
      }

    means the same thing as

      Iterator<SomeType> iter = collection.iterator();
      while(iter.hasNext()) {
        SomeType t = iter.next();
        ... body ...
      }

    This allows us to write, much more simply, code that iterates
    over the elements in a collection.

  */
  public int sumOfIterable2(Iterable<Integer> collection) {
    int total = 0;
    for(Integer num: collection) {
      total += num;
    }
    return total;
  }

  @Test
  public void setExamples() {
    Set<Integer> s = new ASet<>();
    s.add(3);
    assertEquals(1, s.size());
    assertEquals(true, s.contains(3));

    s.add(3);

    assertEquals(1, s.size());
    //assertEquals(2, s.size());

    s.add(5);
    s.add(6);

    assertEquals(14, sumOfIterable1(s));
    assertEquals(14, sumOfIterable2(s));

  }

  @Test
  public void listExamples() {
    ArrayList<Integer> l = new ArrayList<>();
    l.add(1);
    l.add(5);
    l.add(2);
    l.add(6);

    assertEquals(14, sumOfIterable1(l));
    assertEquals(14, sumOfIterable2(l));

  }
}
