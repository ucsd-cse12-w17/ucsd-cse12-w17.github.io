import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

interface Stack<T> {
  void push(T t);
  T pop();
}
class AStack<T> implements Stack<T> {
  
  List<T> contents = new ArrayList<>();

  public void push(T t) {
    // TODO
  }
  public T pop() {
    // TODO
  }
}

public class TestStack {
  @Test
  public void testStack() {
    Stack<String> s = new AStack<>();
    s.push("W");
    s.push("X");
    s.push("Y");

    assertEquals("Y", s.pop());
    assertEquals("X", s.pop());
    assertEquals("W", s.pop());

  }
}
