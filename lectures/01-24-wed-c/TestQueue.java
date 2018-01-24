import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

interface Queue<T> {
  void enqueue(T t);
  T dequeue();
}
class AQueue<T> implements Queue<T> {
  List<T> contents = new ArrayList<>();
  public void enqueue(T t) {
    // TODO
  }
  public T dequeue() {
    // TODO
  }
}

public class TestQueue {
  @Test
  public void testQ() {
    Queue<String> q = new AQueue<>();
    q.enqueue("W");
    q.enqueue("X");
    q.enqueue("Y");

    // A:
    assertEquals("W", q.dequeue());
    assertEquals("X", q.dequeue());
    assertEquals("Y", q.dequeue());

    // B:
    assertEquals("Y", q.dequeue());
    assertEquals("X", q.dequeue());
    assertEquals("W", q.dequeue());

    // C:
    assertEquals("Y", q.dequeue());
    assertEquals("Y", q.dequeue());
    assertEquals("Y", q.dequeue());

    // D:
    assertEquals("W", q.dequeue());
    assertEquals("W", q.dequeue());
    assertEquals("W", q.dequeue());
  }
}



