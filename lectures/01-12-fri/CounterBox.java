/*

Define a class CounterBox that:

- stores a reference privately
- provides methods to update and get the stored ref
- counts the number of times the ref is read or
  written, with methods to access the counts

*/
public class CounterBox<T> {
  public int readCount;
  public int writeCount;

  private T ref;

  public CounterBox() { this.ref = null; }
  public CounterBox(T ref) {
    this.ref = ref;
  }

  T getRef() {
    this.readCount += 1;
    return this.ref; 
  }
  void setRef(T ref) {
    this.writeCount += 1;
    this.ref = ref;  
  }

}



