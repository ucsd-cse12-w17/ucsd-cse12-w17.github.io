import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class Merge {

  /*
    ASSUME: Both arrays are sorted
    
    @return A new array containing all the elements
    from a1 and a2 in order
  */
  public static int[] merge(int[] a1, int[] a2) {
    int[] newarr = new int[a1.length + a2.length];
    int i = 0, i1 = 0, i2 = 0;
    while(i1 < a1.length && i2 < a2.length) {
      if(a1[i1] < a2[i2]) {
        newarr[i] = a1[i1];
        i1 += 1;
      }
      else {
        newarr[i] = a2[i2];
        i2 += 1;
      }
      i += 1;
    }
    while(i1 < a1.length) {
      newarr[i] = a1[i1]; i += 1; i1 += 1;
    }
    while(i2 < a2.length) {
      newarr[i] = a2[i2]; i += 1; i2 += 1;
    }
    return newarr;
  }


  int[] msort(int[] arr) {
    int l = arr.length;
    if(l < 2) {
      return arr;
    }
    int[] left = Arrays.copyOfRange(arr, 0, l / 2);
    int[] right = Arrays.copyOfRange(arr, l / 2, l);
    int[] leftSorted = msort(left);
    int[] rightSorted = msort(right);
    int[] merged = merge(leftSorted, rightSorted);
    return merged;
  }



























  // Which of these tests FAIL?
  // A: 1_1   B: 1_2    C: 2_1    D: 2_2
  // E: More than one of these


  @Test
  public void testMerge1_1() {
    int[] a1 = {1}, a2 = {3}, a3 = {1, 3};
    assertArrayEquals(a3, merge(a1, a2));
  }
  @Test
  public void testMerge1_2() {
    int[] a1 = {1}, a2 = {3, 8}, a3 = {1, 3, 8};
    assertArrayEquals(a3, merge(a1, a2));
  }
  @Test
  public void testMerge2_1() {
    int[] a1 = {1, 9}, a2 = {3}, a3 = {1, 3, 9};
    assertArrayEquals(a3, merge(a1, a2));
  }
  @Test
  public void testMerge2_2() {
    int[] a1 = {1, 9}, a2 = {3, 4}, a3 = {1, 3, 4, 9};
    assertArrayEquals(a3, merge(a1, a2));
  }

  @Test
  public void testMS() {
    int[] a = { 5, 9, 2, 13, 6, 9, 2, -1 };
    int[] as = { -1, 2, 2, 5, 6, 9, 9, 13 };
    assertArrayEquals(as, msort(a));
  }

}
