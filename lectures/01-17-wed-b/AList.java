
/*
  Which of these will start our implementation of ALists,
  which implement the List interface?
*/

//public class AList implements List<T> {     // A
//public class AList implements List {        // B
//public class AList<T> extends List<T> {     // C
public class AList<E> implements List<E> {  // D
//public class AList extends List {           // E

public class AList<E> implements List {
// Compiles, but might run into E vs. Object problems
public class AList<E> implements List<Object> {  // D

}

