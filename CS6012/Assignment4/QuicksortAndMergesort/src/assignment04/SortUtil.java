package assignment04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class SortUtil {

  private static int mergesortThreshold = 100;
  private static int quicksortThreshold = 100;
  //

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
         insertionSort(list, start, end, comparator);
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
      int middle = partition(list, start, end, comparator);
      quickSortRecursive(list, start, middle - 1, comparator);
      quickSortRecursive(list, middle + 1, end, comparator);
    }
  }

  private static <T> int partition(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
    int pivPos = pivot3(list, start, end, comparator);
    T pivot = list.get(pivPos);
    swap(list, start, pivPos);

    int left = start + 1;
    int right = end;

    while (true) {
      while (left <= right) {
        if (comparator.compare(list.get(left), pivot) < 0) {
          left++;
        } else {
          break;
        }
      }

      while (right > left) {
        if (comparator.compare(list.get(right), pivot) > 0) {
          right--;
        } else {
          break;
        }
      }

      if (left >= right) break;
      swap(list, left, right);
    }

    list.set(start, list.get(left - 1));
    list.set(left - 1, pivot);

    return left - 1;
  }

  public static <T> int pivot1(ArrayList<T> list, int start, int end) { // pivot is the middle element
    return (start + end) / 2;
  }

  public static <T> int pivot2(ArrayList<T> list, int start, int end) { // pivot is randomly selected
    Random rng = new Random();
    return start + rng.nextInt(end - start);
  }

  public static <T> int pivot3(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
    // pivot is the smallest between the start element, the middle element and the ending element
    int middle = (end + start) / 2;

    if (comparator.compare(list.get(middle), list.get(start)) < 0) {
      swap(list, start, middle);
    }
    if (comparator.compare(list.get(end), list.get(start)) < 0) {
      swap(list, start, end);
    }
    if (comparator.compare(list.get(end), list.get(middle)) < 0) {
      swap(list, middle, end);
    }
    return middle;
  }

  public static ArrayList<Integer> generateBestCase(int size) {
    ArrayList<Integer> temp = new ArrayList<>();

    for (int i = 1; i <= size; i++) {
      temp.add(i);
    }

    return temp;
  }

  public static ArrayList<Integer> generateAverageCase(int size) {
    Random rng = new Random();
    ArrayList<Integer> temp = generateBestCase(size);

    for (int i = 0; i < size; i++) {
      swap(temp, i, rng.nextInt(size));
    }

    return temp;
  }

  public static ArrayList<Integer> generateWorstCase(int size) {
    ArrayList<Integer> temp = new ArrayList<>();

    for (int i = size; i > 0; i--) {
      temp.add(i);
    }

    return temp;
  }

  //

  private static <T> void swap(ArrayList<T> list, int index1, int index2) {
    T temp = list.get(index1);
    list.set(index1, list.get(index2));
    list.set(index2, temp);
  }

}
