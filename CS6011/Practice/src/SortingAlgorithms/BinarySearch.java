package SortingAlgorithms;

public class BinarySearch {

  public static int binarySearch(int[] ints, int goal) {
    int low = 0, high = ints.length - 1, i = 0;
      while (low <= high) {
        i = (low + high) / 2;
        if (ints[i] == goal) {
          return i;
        } else if (ints[i] > goal) {
          high = i - 1;
        } else {
          low = i + 1;
        }
      }
    return -1;
  }

}
