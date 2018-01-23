import java.util.Iterator;
public class AList<E> implements List<E> {

  E[] contents;
  int size;

	@SuppressWarnings(value = { "unchecked" })
  public AList() {
    this.contents = (E[])(new Object[200]);
    this.size = 0;
  }

  public Iterator<E> iterator() {
    return null;
  }

  public void append(E e) {
    // Need to copy contents over still
    this.contents[this.size] = e; 
    this.size += 1;
  }
  public E get(int index) {
    // What if index is not valid for contents?
    return this.contents[index];
  }
}

