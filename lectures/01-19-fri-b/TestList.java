import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestList {
  @Test
  public void listExamples() {
    AList<String> l = new AList<>();
    l.append("a");
    l.append("b");
    assertEquals("b", l.get(1));

    l.append("c");

    //assertEquals(0, l.indexOf("a"));
  }
  @Test
  public void llistExamples() {
    LList<String> l = new LList<>();

    l.append("a");
  }
}
