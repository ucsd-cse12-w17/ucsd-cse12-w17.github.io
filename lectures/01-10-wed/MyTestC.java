import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MyTest {

  Time eight = new Time(8, 0);
  Time nine = new Time(9, 0);
  Time ten = new Time(10, 0);

  @Test
  public void testConflict() {

    Event c1 = new Event(eight, nine, "C113");

    Event c2 = new Event(eight, nine, "C113");

    assertEquals(c1.conflict(c2), true);

  }

  @Test
  public void testBackToBack() {

    Event c1 = new Event(eight, nine, "C113");

    Event c2 = new Event(nine, ten, "C113");

    assertEquals(c1.conflict(c2), true);

  }

}
