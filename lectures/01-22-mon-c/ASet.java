import java.util.ArrayList;
class ASet<T> implements Set<T> {
  ArrayList<T> contents;
  public ASet() {
    this.contents = new ArrayList<T>();
  }

  public void add(T t) {
    if(!this.contains(t)) {
      this.contents.add(t);  
    }
  }
  public boolean contains(T t) {
    return this.contents.contains(t);
  }
  public int size() {
    return this.contents.size();
  }

  class ASetIterator<T> implements Iterator<T> {
    int currentIndex;
    ArrayList<T> contents;
    public ASetIterator(ArrayList<T> contents) {
      this.contents = contents;
      this.currentIndex = 0;
    }
    public boolean hasNext() {
      return this.currentIndex < this.contents.size();
    }
    public T next() {
      T toReturn = this.contents.get(this.currentIndex);
      this.currentIndex += 1;
      return toReturn;
    }
  }

  // Iterator: T next(), boolean hasNext()
  public Iterator<T> iterator() {
    
  }

}
