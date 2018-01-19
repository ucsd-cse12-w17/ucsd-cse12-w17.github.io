import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestList {
  @Test
  public void listExamples() {
    AList<String> l = new AList<>();
    l.append("a");
    l.append("b");
    assertEquals(0, l.indexOf("a"));
    assertEquals(1, l.indexOf("b"));

  }

  public void llistExamples() {
    LList<String> l = new LList<>();

    l.append("a");
    l.append("b");
    assertEquals(0, l.indexOf("a"));
    assertEquals(1, l.indexOf("b"));

  }
}
