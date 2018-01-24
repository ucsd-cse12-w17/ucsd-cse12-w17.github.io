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

    assertEquals("W", q.dequeue());
    assertEquals("X", q.dequeue());
    assertEquals("Y", q.dequeue());
  }
}

