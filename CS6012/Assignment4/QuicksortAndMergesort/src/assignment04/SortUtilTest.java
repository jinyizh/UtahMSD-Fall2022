package assignment04;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {

  ArrayList<Integer> integers = new ArrayList<>();
  ArrayList<String> strings = new ArrayList<>();

  Comparator<Integer> integerComparator = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
      if (o1 > o2) return 1;
      if (o1 < o2) return -1;
      return 0;
    }
  };

  Comparator<String> stringComparator = new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
      return o1.compareTo(o2);
    }
  };

  @BeforeEach
  void setUp() {
    integers.add(-12);
    integers.add(42);
    integers.add(2);
    integers.add(63);
    integers.add(-9);
    integers.add(17);
    integers.add(91);
    integers.add(89);
    integers.add(22);
    integers.add(45);

    strings.add("da");
    strings.add("2014");
    strings.add("okay I will do that");
    strings.add("*");
    strings.add("iPhone SE");
    strings.add("3.1416");
    strings.add("xylophone");
    strings.add("Aardvark");
    strings.add("!!?");
    strings.add(":");
  }

  @AfterEach
  void tearDown() {
    integers = null;
    strings = null;
  }

  @Test
  void testMerge() {
    SortUtil.mergesort(integers, integerComparator);

    assertEquals(integers.size(), 10);
    assertEquals(integers.get(0), -12);
    assertEquals(integers.get(1), -9);
    assertEquals(integers.get(2), 2);
    assertEquals(integers.get(3), 17);
    assertEquals(integers.get(4), 22);
    assertEquals(integers.get(5), 42);
    assertEquals(integers.get(6), 45);
    assertEquals(integers.get(7), 63);
    assertEquals(integers.get(8), 89);
    assertEquals(integers.get(9), 91);

    SortUtil.mergesort(strings, stringComparator);

    assertEquals(strings.size(), 10);
    assertEquals(strings.get(0), "!!?");
    assertEquals(strings.get(1), "*");
    assertEquals(strings.get(2), "2014");
    assertEquals(strings.get(3), "3.1416");
    assertEquals(strings.get(4), ":");
    assertEquals(strings.get(5), "Aardvark");
    assertEquals(strings.get(6), "da");
    assertEquals(strings.get(7), "iPhone SE");
    assertEquals(strings.get(8), "okay I will do that");
    assertEquals(strings.get(9), "xylophone");
  }

  @Test
  void testQuick() {
    SortUtil.quicksort(integers, integerComparator);

    assertEquals(integers.size(), 10);
    assertEquals(integers.get(0), -12);
    assertEquals(integers.get(1), -9);
    assertEquals(integers.get(2), 2);
    assertEquals(integers.get(3), 17);
    assertEquals(integers.get(4), 22);
    assertEquals(integers.get(5), 42);
    assertEquals(integers.get(6), 45);
    assertEquals(integers.get(7), 63);
    assertEquals(integers.get(8), 89);
    assertEquals(integers.get(9), 91);

    SortUtil.quicksort(strings, stringComparator);
    assertEquals(strings.size(), 10);
    assertEquals(strings.get(0), "!!?");
    assertEquals(strings.get(1), "*");
    assertEquals(strings.get(2), "2014");
    assertEquals(strings.get(3), "3.1416");
    assertEquals(strings.get(4), ":");
    assertEquals(strings.get(5), "Aardvark");
    assertEquals(strings.get(6), "da");
    assertEquals(strings.get(7), "iPhone SE");
    assertEquals(strings.get(8), "okay I will do that");
    assertEquals(strings.get(9), "xylophone");
  }

  @Test
  void testMergeRunTime() {
    for (int i = 10; i < 21; i++) {
      double size = Math.pow(2, i); // size of array

      ArrayList<Integer> bestCase = SortUtil.generateBestCase((int) size);
      ArrayList<Integer> averageCase = SortUtil.generateAverageCase((int) size);
      ArrayList<Integer> worstCase = SortUtil.generateWorstCase((int) size);

      int loops = 100;

      long totalTime = 0;
      for (int k = 0; k < loops; k++) {
        ArrayList<Integer> test = (ArrayList<Integer>) bestCase.clone();
        long startTime = System.nanoTime();
        SortUtil.mergesort(test, integerComparator);
        long endTime = System.nanoTime();
        totalTime += endTime - startTime;
      }

      System.out.println("merge sort best case running time: " + (int) size + " " + totalTime / loops);

      totalTime = 0; // re-initialize
      for (int k = 0; k < loops; k++) {
        ArrayList<Integer> test = (ArrayList<Integer>) averageCase.clone();
        long startTime = System.nanoTime();
        SortUtil.mergesort(test, integerComparator);
        long endTime = System.nanoTime();
        totalTime += endTime - startTime;
      }

      System.out.println("merge sort average case running time: " + i + " " + totalTime / loops);

      for (int k = 0; k < loops; k++) {
        ArrayList<Integer> test = (ArrayList<Integer>) worstCase.clone();
        long startTime = System.nanoTime();
        SortUtil.mergesort(test, integerComparator);
        long endTime = System.nanoTime();
        totalTime += endTime - startTime;
      }

      System.out.println("merge sort worst case running time: " + (int) size + " " + totalTime / loops);
    }
  }

  @Test
  void testQuickRunTime() {
    for (int i = 10; i < 21; i++) {
      double size = Math.pow(2, i); // size of array

      ArrayList<Integer> bestCase = SortUtil.generateBestCase((int) size);
      ArrayList<Integer> averageCase = SortUtil.generateAverageCase((int) size);
      ArrayList<Integer> worstCase = SortUtil.generateWorstCase((int) size);

      int loops = 100;

      long totalTime = 0;
      for (int k = 0; k < loops; k++) {
        ArrayList<Integer> test = (ArrayList<Integer>) bestCase.clone();
        long startTime = System.nanoTime();
        SortUtil.quicksort(test, integerComparator);
        long endTime = System.nanoTime();
        totalTime += endTime - startTime;
      }

      System.out.println("quick sort best case running time: " + (int) size + " " + totalTime / loops);

      totalTime = 0; // re-initialize
      for (int k = 0; k < loops; k++) {
        ArrayList<Integer> test = (ArrayList<Integer>) averageCase.clone();
        long startTime = System.nanoTime();
        SortUtil.quicksort(test, integerComparator);
        long endTime = System.nanoTime();
        totalTime += endTime - startTime;
      }

      System.out.println("quick sort average case running time: " + (int) size + " " + totalTime / loops);

      for (int k = 0; k < loops; k++) {
        ArrayList<Integer> test = (ArrayList<Integer>) worstCase.clone();
        long startTime = System.nanoTime();
        SortUtil.quicksort(test, integerComparator);
        long endTime = System.nanoTime();
        totalTime += endTime - startTime;
      }

      System.out.println("quick sort worst case running time: " + (int) size + " " + totalTime / loops);
    }
  }
}