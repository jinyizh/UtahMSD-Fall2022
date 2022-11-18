package assignment04;

import java.util.ArrayList;
import java.util.Comparator;

public class SortUtil {

  private static int threshold;

  public static <T> void mergesort(ArrayList<T> arrayList, Comparator<? super T> comparator) {
    // create sub-arrays to be merged
    int mid = arrayList.size() / 2;
    ArrayList<T> lArrayList = new ArrayList<>(mid);
    ArrayList<T> rArrayList = new ArrayList<>(arrayList.size() - mid);

    // divide
    for (int i = 0; i < mid; i++) {
      lArrayList.add(arrayList.get(i));
    }
    for (int i = mid; i < arrayList.size(); i++) {
      rArrayList.add(arrayList.get(i));
    }

    // recursion
    mergesort(lArrayList, comparator);
    mergesort(rArrayList, comparator);

  }

  private static <T> void merge
      (ArrayList<T> arrayList, ArrayList<T> lArrayList, ArrayList<T> rArrayList, int left, int right) {
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
      Comparable<T> li = (Comparable<T>) lArrayList.get(i);
      if (li.compareTo(rArrayList.get(j)) <= 0) {
        arrayList.set(k++, lArrayList.get(i++));
      } else {
        arrayList.set(k++, rArrayList.get(j++));
      }
    }
    while (i < left) {
      arrayList.set(k++, lArrayList.get(i++));
    }
    while (j < right) {
      arrayList.set(k++, rArrayList.get(j++));
    }
  }
}
