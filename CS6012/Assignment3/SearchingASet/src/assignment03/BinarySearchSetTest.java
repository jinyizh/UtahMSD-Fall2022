package assignment03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {

  BinarySearchSet<String> binarySearchSet;

  @BeforeEach
  void setUp() {
    binarySearchSet = new BinarySearchSet<>();
  }

  @AfterEach
  void tearDown() {
    binarySearchSet = null;
  }

  @Test
  void comparator() {
  }

  @Test
  void first() {
  }

  @Test
  void last() {
  }

  @Test
  void add() {
    binarySearchSet.add("test");
    assertEquals(binarySearchSet.first(), "test");
  }

  @Test
  void addAll() {
  }

  @Test
  void clear() {
  }

  @Test
  void contains() {
  }

  @Test
  void containsAll() {
  }

  @Test
  void isEmpty() {
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
  }

  @Test
  void toArray() {
  }

  @Test
  void ensureCapacity() {
  }
}