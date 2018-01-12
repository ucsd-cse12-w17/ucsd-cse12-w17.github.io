/*

Define a class CounterBox that:

- stores a reference privately
- provides methods to update and get the stored ref
- counts the number of times the ref is accessed or
  updated, with methods to access the counts

*/
public class CounterBox<T> {

  private T t;
  private int accesses = 0;
  private int updates = 0;

  public CounterBox(T t) {
    this.t = t;
  }
  public T get() {
    this.accesses += 1;
    return this.t;
  }
  public void set(T t) {
    this.updates += 1;
    this.t = t;
  }
  public int getAccesses() {
    return this.accesses;
  }
  public int getUpdates() {
    return this.updates;
  }
}
