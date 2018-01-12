import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestCounterBox {

  @Test
  public void testCounter() {
    CounterBox<String> box = new CounterBox<String>("hello!");

    CounterBox<Integer> boxInt = new CounterBox<Integer>(5);


    assertEquals(0, box.readCount);
    assertEquals(0, box.writeCount);

    box.getRef();
    box.getRef();
    box.getRef();
    box.getRef();
    box.getRef();

    assertEquals(5, box.readCount);

    box.setRef("goodbye!");

    String s = box.getRef();

    assertEquals("goodbye!", s);
  }


}
