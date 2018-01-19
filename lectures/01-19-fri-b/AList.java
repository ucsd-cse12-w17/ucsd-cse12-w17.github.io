public class AList<E> implements List<E> {

  E[] contents;
  int size;

  public AList() {
    this.contents = (E[])(new Object[2]);
    this.size = 0;
  }

  public void append(E e) {
    if(this.size >= this.contents.length) {
      // make a new array! that's bigger
      // copy elements to the new array
      // set contents to new array
    }

    this.contents[this.size] = e; 
    this.size += 1;
  }

  public void insert(int index, E e) { }

  public E get(int index) {
    // What if index is not valid for contents?
    return this.contents[index];
  }
  public int indexOf(E element) {
    return -1;
  }


}

