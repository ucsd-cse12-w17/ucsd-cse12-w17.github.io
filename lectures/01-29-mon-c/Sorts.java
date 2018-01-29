class Sorts {

  static boolean isSorted1(int[] nums) {
    for(int i = 0; i < nums.length; i += 1)
      for(int j = i; j < nums.length; j += 1)
        if(nums[i] > nums[j]) return false;
    return true;
  }

  static boolean isSorted2(int[] nums) {
    for(int i = 0; i < nums.length - 1; i += 1)
      if(nums[i] > nums[i + 1]) return false;

    return true;
  }

  // Consider array = {1, 2, 3}. What is the result?

->// A: isSorted1(array) -> true,  isSorted2(array) -> true 
  // B: isSorted1(array) -> false, isSorted2(array) -> true
  // C: isSorted1(array) -> true,  isSorted2(array) -> false
  // D: isSorted1(array) -> false, isSorted2(array) -> false

  // Consider array = {3, 1, 2}. What is the result?

  // A: isSorted1(array) -> true,  isSorted2(array) -> true
  // B: isSorted1(array) -> false, isSorted2(array) -> true
  // C: isSorted1(array) -> true,  isSorted2(array) -> false
->// D: isSorted1(array) -> false, isSorted2(array) -> false

































  static int[] makeSortedArray(int size) {
    int[] arr = new int[size];
    for(int i = 0; i < arr.length; i += 1) {
      arr[i] = i * 10;
    }
    return arr;
  }

  public static void main(String[] args) {
    int[] array = makeSortedArray(Integer.parseInt(args[0]));

    long start1 = System.nanoTime();
    System.out.println("isSorted1 result: " + isSorted1(array));
    long end1 = System.nanoTime();
    System.out.println("isSorted1 took: " + ((end1 - start1) / 1000000.0));

    long start2 = System.nanoTime();
    System.out.println("isSorted2 result: " + isSorted2(array));
    long end2 = System.nanoTime();
    System.out.println("isSorted2 took: " + ((end2 - start2) / 1000000.0));

  }

}
