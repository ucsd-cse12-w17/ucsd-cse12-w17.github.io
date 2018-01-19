interface List<T> {
  /*
    Which of these are misplaced as methods on the List interface?
    (Choose the one(s) that DO NOT fit the interface)
  */

  void append(T element);

  T get(int index);

  // void append(int element);

  void insert(int index, T element);

  int indexOf(T element);
}

