class Nd<E> {
  E v;
  Nd<E> succ;
  public Nd(E v, Nd<E> succ) {
    this.v = v;
    this.succ = succ;
  }
}

public class LList<E> implements List<E> {

  Nd<E> head;

  public LList() {
    this.head = new Nd<E>(null, null);
  }

  public void append(E e) {
    // A
    this.head.succ = new Nd<E>(e, ...);  

    // B
    Nd<E> cur = ...;
    while(...) {
      ... cur.succ = ...
    }
    ...

    // C something else
    
  }
  public void insert(int index, E e) { }

  public E get(int index) {
    return null;
  }
  public int indexOf(E element) {
    return -1;
  }


}

