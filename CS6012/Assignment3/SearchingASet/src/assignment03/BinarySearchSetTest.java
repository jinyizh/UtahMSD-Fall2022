package assignment03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {

  BinarySearchSet<String> stringBinarySearchSet;
  BinarySearchSet<Integer> integerBinarySearchSet;
  BinarySearchSet<Integer> comparatorIntegerSet;
  ArrayList<Integer> integerArrayList;
  ArrayList<Integer> integerArrayList1;
  ArrayList<String> stringArrayList;

  @BeforeEach
  void setUp() {
    stringBinarySearchSet = new BinarySearchSet<>();
    integerBinarySearchSet = new BinarySearchSet<>();
    comparatorIntegerSet = new BinarySearchSet<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1; // reversed order
      }
    });
    integerArrayList = new ArrayList<>();
    integerArrayList1 = new ArrayList<>();
    stringArrayList = new ArrayList<>();
  }

  @AfterEach
  void tearDown() {
    stringBinarySearchSet = null;
    integerBinarySearchSet = null;
    comparatorIntegerSet = null;
    integerArrayList = null;
    integerArrayList1 = null;
    stringArrayList = null;
  }

  @Test
  void first() {
    assertThrows(NoSuchElementException.class, () -> {integerBinarySearchSet.first();});
    integerBinarySearchSet.add(1);
    assertDoesNotThrow(() -> integerBinarySearchSet.first()); // a useful method
    assertEquals(integerBinarySearchSet.first(), 1);
  }

  @Test
  void last() {
    assertThrows(NoSuchElementException.class, () -> {integerBinarySearchSet.last();});
    integerBinarySearchSet.add(1);
    integerBinarySearchSet.add(8);
    assertDoesNotThrow(() -> integerBinarySearchSet.last());
    assertEquals(integerBinarySearchSet.last(), 8);
  }

  @Test
  void add() {
    // test of duplicate elements
    stringBinarySearchSet.add("test");
    stringBinarySearchSet.add("test"); // already exists
    stringBinarySearchSet.add("test1");
    assertEquals(stringBinarySearchSet.getValue(0), "test");
    assertEquals(stringBinarySearchSet.getValue(1), "test1");

    // test if works with integer
    integerBinarySearchSet.add(5);
    assertEquals(integerBinarySearchSet.getValue(0), 5);

    // test if sorted (natural order)
    integerBinarySearchSet.add(9);
    integerBinarySearchSet.add(4);
    assertEquals(integerBinarySearchSet.getValue(0), 4);

    // test if sorted (comparator, which is reverse ordered)
    comparatorIntegerSet.add(4);
    comparatorIntegerSet.add(1);
    comparatorIntegerSet.add(9);
    assertEquals(comparatorIntegerSet.getValue(0), 9);
    assertEquals(comparatorIntegerSet.getValue(1), 4);
  }

  @Test
  void addAll() {
    integerArrayList.add(8);
    integerArrayList.add(12);
    integerArrayList.add(4);
    integerBinarySearchSet.addAll(integerArrayList);
    assertEquals(integerBinarySearchSet.size(), 3);
    assertEquals(integerBinarySearchSet.first(), 4);
    assertEquals(integerBinarySearchSet.last(), 12);

    // test adding subset
    integerArrayList1.add(12);
    integerArrayList1.add(4);
    assertFalse(integerBinarySearchSet.addAll(integerArrayList1));

    // test union set
    integerArrayList1.add(6);
    assertTrue(integerBinarySearchSet.addAll(integerArrayList1));
    integerBinarySearchSet.addAll(integerArrayList1);
    assertEquals(integerBinarySearchSet.size(), 4);
    assertEquals(integerBinarySearchSet.getValue(1), 6);
  }

  @Test
  void clear() {
    stringBinarySearchSet.add("a");
    stringBinarySearchSet.add("b");
    stringBinarySearchSet.add("c");
    assertFalse(stringBinarySearchSet.isEmpty());
    stringBinarySearchSet.clear();
    assertTrue(stringBinarySearchSet.isEmpty());
  }

  @Test
  void contains() {
    assertFalse(stringBinarySearchSet.contains(2));
    stringBinarySearchSet.add(1);
    assertFalse(stringBinarySearchSet.contains(2));
    assertTrue(stringBinarySearchSet.contains(1));
  }

  @Test
  void containsAll() {
    stringBinarySearchSet.add("a");
    stringBinarySearchSet.add("b");
    stringBinarySearchSet.add("c");
    stringArrayList.add("a");
    stringArrayList.add("c");
    assertTrue(stringBinarySearchSet.containsAll(stringArrayList));

    stringArrayList.add("d");
    assertFalse(stringBinarySearchSet.containsAll(stringArrayList));
  }

  @Test
  void isEmpty() {
    assertTrue(stringBinarySearchSet.isEmpty());
    stringBinarySearchSet.add("hi");
    assertFalse(stringBinarySearchSet.isEmpty());
  }

  @Test
  void remove() {
    stringBinarySearchSet.add("a");
    stringBinarySearchSet.add("b");
    stringBinarySearchSet.add("c");
    stringBinarySearchSet.remove("b");
    assertEquals(stringBinarySearchSet.size(), 2);
    assertFalse(stringBinarySearchSet.remove("z"));
  }

  @Test
  void removeAll() {
    integerBinarySearchSet.add(1);
    integerBinarySearchSet.add(2);
    integerBinarySearchSet.add(3);

    // subset
    integerArrayList.add(1);
    integerArrayList.add(3);
    assertTrue(integerBinarySearchSet.removeAll(integerArrayList));
    assertEquals(integerBinarySearchSet.size(), 1);

    integerArrayList1.add(9); // not in set
    assertFalse(integerBinarySearchSet.removeAll(integerArrayList1));

    // union set
    stringBinarySearchSet.add("a");
    stringBinarySearchSet.add("b");
    stringBinarySearchSet.add("c");
    stringArrayList.add("c");
    stringArrayList.add("d");
    stringBinarySearchSet.removeAll(stringArrayList); // a b c -> a b
    assertEquals(stringBinarySearchSet.size(), 2);
    assertEquals(stringBinarySearchSet.getValue(1), "b");
  }

  @Test
  void size() {
    assertEquals(stringBinarySearchSet.size(), 0);
    stringBinarySearchSet.add("a");
    stringBinarySearchSet.add("b");
    stringBinarySearchSet.add("c");
    assertEquals(stringBinarySearchSet.size(), 3);
  }

  @Test
  void toArray() {
    stringBinarySearchSet.add("a");
    stringBinarySearchSet.add("b");
    stringBinarySearchSet.add("c");
    assertEquals(stringBinarySearchSet.toArray()[1], "b");
  }

  @Test
  void iterator() {
    stringBinarySearchSet.add("a");
    stringBinarySearchSet.add("b");
    stringBinarySearchSet.add("c");
    Iterator<String> stringIterator = stringBinarySearchSet.iterator();
    assertEquals(stringIterator.next(), "a");
  }

  @Test
  void testContains() {
    // test the running time of .contains() method which implements binary search
    // used in analysis report
    int loopTime = 10000;
    long totalTime = 0;
    int size = 1024;

    for (long i = 0; i < size; i++) {
      integerBinarySearchSet.add(1);
    }
    integerBinarySearchSet.add(2); // the element to find

    for (int i = 0; i < loopTime; i++) {
      long lastTime = System.nanoTime();
      boolean contains = integerBinarySearchSet.contains(2); // call
      long currentTime = System.nanoTime();
      totalTime += currentTime - lastTime;
    }

    System.out.println(totalTime / loopTime);
  }
}