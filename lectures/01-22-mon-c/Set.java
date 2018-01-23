interface Set<T> extends Iterable<T> {
  // Like a mathematical "set" of numbers, for example

  void add(T t);
  boolean contains(T t);
  int size();

  // void remove(T t); Come back to this if we have time

  // Does this method belong?
  // B: No!
  // T get(int index);

}
