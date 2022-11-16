package assignment03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {

  BinarySearchSet<String> stringBinarySearchSet;
  BinarySearchSet<Integer> integerBinarySearchSet;
  BinarySearchSet<Integer> comparatorIntegerSet;

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
  }

  @AfterEach
  void tearDown() {
    stringBinarySearchSet = null;
    integerBinarySearchSet = null;
  }

  @Test
  void comparator() {
  }

  @Test
  void first() {
    assertThrows(NoSuchElementException.class, () -> {integerBinarySearchSet.first();});
    integerBinarySearchSet.add(1);
    assertDoesNotThrow(() -> integerBinarySearchSet.first());
  }

  @Test
  void last() {
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

    // test if sorted (comparator)
    comparatorIntegerSet.add(1);
    comparatorIntegerSet.add(9);
    assertEquals(comparatorIntegerSet.getValue(0), 9);
  }

  @Test
  void addAll() {
  }

  @Test
  void clear() {
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
  }

  @Test
  void isEmpty() {
    assertTrue(stringBinarySearchSet.isEmpty());
    stringBinarySearchSet.add("hi");
    assertFalse(stringBinarySearchSet.isEmpty());
  }

  @Test
  void iterator() {
  }

  @Test
  void forEach() {
  }

  @Test
  void spliterator() {
  }

  @Test
  void remove() {
  }

  @Test
  void removeAll() {
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
}