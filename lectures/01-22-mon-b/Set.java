interface Set<T> extends Iterable<T> {

  // Iterable<T>
  //   Iterator<T> iterator();

  // Think like a mathematical set (of numbers, of other values)

  void add(T t);
  // makes more sense than 
  // void append(T t);

  int size();
  boolean contains(T t);

  // Does this method belong?
  // A: Yes
  // B: No
  // T get(int index);

  // Typically isn't included in Set interfaces!

}
