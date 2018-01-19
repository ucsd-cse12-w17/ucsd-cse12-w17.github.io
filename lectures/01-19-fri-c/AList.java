public class AList<E> implements List<E> {

  E[] contents;
  int size;

  public AList() {
    this.contents = (E[])(new Object[2]);
    this.size = 0;
  }

  public void append(E e) {
    // check size, maybe make a new array? 
    int len = this.contents.length;
    if(this.size >= len) {
      E[] newC = (E[])(new Object[len * 2]);
      // copy old elements over to new array
      // set this.contents to the new array
    }

    this.contents[this.size] = e;
    this.size += 1;
  }

  public void insert(int index, E e) { }

  public E get(int index) {
    return null;
  }
  // Should return -1 if element not found
  public int indexOf(E element) {
    for(int i = 0; i < this.size; i+=1){
      if(this.contents[i].equals(element)) {
        return i;
      }
    }
    return -1;
  }


}

