class Nd<E> {
  E v;
  Nd<E> succ, prev;
  public Nd(E v, Nd<E> succ, Nd<E> prev) {
    this.v = v;
    this.succ = succ; this.prev = prev;
  }
}

public class LList<E> implements List<E> {

  Nd<E> head;

  public LList() {
    this.head = new Nd<E>(null, null, null);
  }

  public void append(E e) {
    this.head.succ = new Nd<E>(e, null, null);
  }
  public void insert(int index, E e) { }

  public E get(int index) {
    return null;
  }
  public int indexOf(E element) {
    return -1;
  }


}

