/*

Define a class CounterBox that:

- stores a reference privately
- provides methods to update and get the stored ref
- counts the number of times the ref is accessed or
  updated, with methods to access the counts

*/
public class CounterBox<T> {
  private int touched;
  private T ref;

  // Need a constructor? A: Yes, Not A: No

  int getAccesses() {
    return this.touched;
  }
  T getRef() {
    this.touched += 1;
    return this.ref;
  }
  void setRef(T ref) {
    this.touched += 1;
    this.ref = ref;
  }
}



