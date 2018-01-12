import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MyTest {

  Time twopm = new Time(14, 0);
  Time fivepm = new Time(17, 0);

  Time threepm = new Time(15, 0);
  Time fourpm = new Time(16, 0);

  @Test
  public void testConflictSameTime() {

    Event e1=new Event(twopm, fivepm, "WLH25");
    Event e2=new Event(twopm, fivepm, "WLH25");

    assertEquals(true, e1.conflict(e2));

  }

  @Test
  public void testConflictWithinOtherEvent() {

    Event e1=new Event(twopm, fivepm, "WLH25");
    Event e2=new Event(threepm, fourpm, "WLH25");

    assertEquals(true, e1.conflict(e2));

  }

}
