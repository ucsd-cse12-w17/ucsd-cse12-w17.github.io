import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Iterator;

public class TestAList {

  @Test
  public void setExamples() {
    Set<Integer> s = new ASet<>();
    s.add(1);
    s.add(4);
    assertEquals(true, s.contains(4));
    assertEquals(true, s.contains(1));
    assertEquals(2, s.size());

    Set<Integer> s2 = new ASet<>();
    s2.add(1);
    s2.add(4);
    s2.add(1);

    // assertEquals(3, s2.size()); // A will PASS.... but
    assertEquals(2, s2.size()); // B should



  }

  @Test
  public void listExamples() {
    List<Integer> l = new AList<>();
    l.append(1);
    l.append(5);
    l.append(3);
    l.append(2);
    // Adds up elements in a list
    int total;
    for(int i = 0; i < l.size(); i += 1) {
      total += l.get(i);
    }

  }
}
