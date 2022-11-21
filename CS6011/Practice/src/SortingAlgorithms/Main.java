package SortingAlgorithms;

public class Main {
  public static void main(String[] args) {

    int[] ints = {1, 3, 6, 2, 46, 34, 1, 34, 42, 4, 7, 9, 9, 67, 7, 0, 1};

    for (int i : ints) System.out.print(i + " ");

    BubbleSort.bubSort(ints);

    System.out.println();
    for (int i : ints) System.out.print(i + " ");

  }
}
