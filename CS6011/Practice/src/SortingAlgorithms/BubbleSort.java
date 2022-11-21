package SortingAlgorithms;

import java.util.Comparator;

public class BubbleSort {

  public static void bubSort(int[] ints) {

    if (ints.length == 0) return;

    for (int i = 0; i < ints.length - 1; i++) {
      for (int j = 0; j < ints.length - i - 1; j++) {

        if (ints[j] > ints[j + 1]) {
          int temp = ints[j];
          ints[j] = ints[j + 1];
          ints[j + 1] = temp;
        }
      }
    }
  }
}
