package assignment04;

import java.util.ArrayList;
import java.util.Comparator;

public class SortUtil {

  private static int mergesortThreshold;
  private static int quicksortThreshold;
  public static int choose = 3;

   public static void setMergesortThreshold(int desiredThreshold) {
     mergesortThreshold = desiredThreshold;
   }

   public static void setQuicksortThreshold(int desiredThreshold) {
     quicksortThreshold = desiredThreshold;
   }

  /**
   * insertion sort method
   * @param list
   * @param comparator
   * @param <T>
   */
   private static <T> void insertionSort(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
     int j;
     for (int i = start; i <= end; i++) {
       for (j = i; j > start && comparator.compare(list.get(i), list.get(j - 1)) < 0; j--) {
         list.set(j, list.get(j - 1));
       }
       list.set(j, list.get(i));
     }
   }

  /**
   * merge sort driver method
   * @param list
   * @param comparator
   * @param <T>
   */
   public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator) {
     ArrayList<T> temp = new ArrayList<>(list.size());
     for (int i = 0; i < list.size(); i++) {
       temp.add(null);
     }
     mergeSortRecursive(list, temp, 0, list.size() - 1, comparator);
   }

   private static <T> void mergeSortRecursive(ArrayList<T> list, ArrayList<T> temp, int start, int end, Comparator<? super T> comparator) {
     if (start < end) {
       if (end - start < mergesortThreshold) {
         // insertionSort(list, start, end);
       } else {
         int center = (start + end) / 2;
         mergeSortRecursive(list, temp, start, center, comparator);
         mergeSortRecursive(list, temp, center + 1, end, comparator);
         merge(list, temp, start, end, comparator);
       }
     }
   }

   private static <T> void merge(ArrayList<T> list, ArrayList<T> temp, int start, int end, Comparator<? super T> comparator) {
     int middle = (start + end) / 2 + 1;
     int right = middle - 1;
     int numElements = end - start + 1;
     int tempPos = start;

     while (start <= right && middle <= end) {
       if (comparator.compare(list.get(start), list.get(middle)) <= 0) {
         temp.set(tempPos++, list.get(start++));
       } else {
         temp.set(tempPos++, list.get(middle++));
       }
     }

     while (start <= right) {
       temp.set(tempPos++, list.get(start++));
     }

     while (middle <= end) {
       temp.set(tempPos++, list.get(middle++));
     }

     for (int i = 0; i < numElements; i++, end--) {
       list.set(end, temp.get(end));
     }
   }

  /**
   * quicksort driver method
   * @param list
   * @param comparator
   * @param <T>
   */
  public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator) {
     if (list.size() <= 1) return;
     quickSortRecursive(list, 0, list.size() - 1, comparator);
  }

  private static <T> void quickSortRecursive(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
    if (end - start <= 0) return;
    if (end - start < quicksortThreshold) {
      insertionSort(list, start, end, comparator);
    } else {
      // TODO
    }
  }

  private static <T> int partition(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
    return -1; // TODO
  }

  public static <T> int goodPivotStrategy(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
    return (start + end) / 2;
  }

}
