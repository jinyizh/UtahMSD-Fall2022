package assignment05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

  BinarySearchTree<String> stringBinarySearchTree;
  BinarySearchTree<Integer> integerBinarySearchTree;
  ArrayList<String> stringArrayList;
  ArrayList<Integer> integerArrayList;

  @BeforeEach
  void setUp() {
    stringBinarySearchTree = new BinarySearchTree<>();
    integerBinarySearchTree = new BinarySearchTree<>();
    stringArrayList = new ArrayList<>();
    integerArrayList = new ArrayList<>();
  }

  @AfterEach
  void tearDown() {
    stringBinarySearchTree = null;
    integerBinarySearchTree = null;
    stringArrayList = null;
    integerArrayList = null;
  }

  @Test
  void add() {
    assertEquals(stringBinarySearchTree.size(), 0);
    assertThrows(NullPointerException.class, () -> {stringBinarySearchTree.add(null);});
    stringBinarySearchTree.add("a");
    assertEquals(stringBinarySearchTree.size(), 1);
    stringBinarySearchTree.add("a");
    assertFalse(stringBinarySearchTree.add("a"));
    assertEquals(stringBinarySearchTree.size(), 1);
    stringBinarySearchTree.add("b");
    assertEquals(stringBinarySearchTree.size(), 2);
  }

  @Test
  void addAll() {
    stringArrayList.add("a");
    stringBinarySearchTree.addAll(stringArrayList);
    assertEquals(stringBinarySearchTree.size(), 1);
    stringArrayList.add("b");
    stringArrayList.add("c");
    stringBinarySearchTree.addAll(stringArrayList);
    assertEquals(stringBinarySearchTree.size(), 3);
  }

  @Test
  void clear() {
    stringBinarySearchTree.add("a");
    stringBinarySearchTree.clear();
    assertEquals(stringBinarySearchTree.size(), 0);
    assertThrows(NoSuchElementException.class, () -> {stringBinarySearchTree.first();});
  }

  @Test
  void contains() {
    assertFalse(integerBinarySearchTree.contains(1));
    integerBinarySearchTree.add(1);
    assertTrue(integerBinarySearchTree.contains(1));
    assertFalse(stringBinarySearchTree.contains("a"));
    assertFalse(stringBinarySearchTree.contains("a"));
    stringBinarySearchTree.add("a");
    assertTrue(stringBinarySearchTree.contains("a"));
  }

  @Test
  void containsAll() {
    integerBinarySearchTree.add(2);
    integerBinarySearchTree.add(1);
    integerBinarySearchTree.add(3);
    integerArrayList.add(2);
    assertTrue(integerBinarySearchTree.containsAll(integerArrayList));
    integerArrayList.add(3);
    assertTrue(integerBinarySearchTree.containsAll(integerArrayList));
    integerArrayList.add(4);
    assertFalse(integerBinarySearchTree.containsAll(integerArrayList));
  }

  @Test
  void first() {
    integerBinarySearchTree.add(2);
    integerBinarySearchTree.add(1);
    integerBinarySearchTree.add(3);
    assertEquals(integerBinarySearchTree.first(), 1);
  }

  @Test
  void isEmpty() {
    assertTrue(stringBinarySearchTree.isEmpty());
    stringBinarySearchTree.add("k");
    assertFalse(stringBinarySearchTree.isEmpty());
  }

  @Test
  void last() {
    integerBinarySearchTree.add(2);
    integerBinarySearchTree.add(1);
    integerBinarySearchTree.add(4);
    integerBinarySearchTree.add(3);
    integerBinarySearchTree.add(5);
    assertEquals(integerBinarySearchTree.last(), 5);
  }

  @Test
  void remove() {
    stringBinarySearchTree.add("a");
    assertEquals(stringBinarySearchTree.size(), 1);
    stringBinarySearchTree.remove("a");
    assertEquals(stringBinarySearchTree.size(), 0);
    integerBinarySearchTree.add(4);
    integerBinarySearchTree.add(2);
    integerBinarySearchTree.add(1);
    integerBinarySearchTree.add(3);
    integerBinarySearchTree.add(6);
    integerBinarySearchTree.add(5);
    integerBinarySearchTree.add(7);
    /*
                      4
              2              6
         1         3     5          7
     */
    integerBinarySearchTree.remove(6);
    assertEquals(integerBinarySearchTree.getValue(integerBinarySearchTree.getRight(integerBinarySearchTree.getRoot())), 7);
    integerBinarySearchTree.remove(2);
    assertEquals(integerBinarySearchTree.getValue(integerBinarySearchTree.getLeft(integerBinarySearchTree.getRoot())), 3);
    integerBinarySearchTree.clear();
    assertTrue(integerBinarySearchTree.isEmpty());
    // add again to test removing the root
    integerBinarySearchTree.add(4);
    integerBinarySearchTree.add(2);
    integerBinarySearchTree.add(1);
    integerBinarySearchTree.add(3);
    integerBinarySearchTree.add(6);
    integerBinarySearchTree.add(5);
    integerBinarySearchTree.add(7);
    /*
                      4
              2              6
         1         3     5          7
     */
    integerBinarySearchTree.remove(4);
    assertEquals(integerBinarySearchTree.getValue(integerBinarySearchTree.getRoot()), 5);
  }

  @Test
  void removeAll() {
    integerBinarySearchTree.add(4);
    integerBinarySearchTree.add(2);
    integerBinarySearchTree.add(1);
    integerBinarySearchTree.add(3);
    integerBinarySearchTree.add(6);
    integerBinarySearchTree.add(5);
    integerBinarySearchTree.add(7);
    /*
                      4
              2              6
         1         3     5          7
     */
    assertFalse(integerBinarySearchTree.removeAll(integerArrayList));
    integerArrayList.add(1);
    assertTrue(integerBinarySearchTree.removeAll(integerArrayList));
    assertEquals(integerBinarySearchTree.size(), 6);
    integerArrayList.add(7);
    assertTrue(integerBinarySearchTree.removeAll(integerArrayList));
    assertEquals(integerBinarySearchTree.size(), 5);
    assertFalse(integerBinarySearchTree.contains(7));
  }

  @Test
  void size() {
    assertEquals(stringBinarySearchTree.size(), 0);
    stringBinarySearchTree.add("da");
    assertEquals(stringBinarySearchTree.size(), 1);
    stringBinarySearchTree.add("a");
    assertEquals(stringBinarySearchTree.size(), 2);
  }

  @Test
  void toArrayList() {
    integerBinarySearchTree.add(4);
    integerBinarySearchTree.add(2);
    integerBinarySearchTree.add(1);
    integerBinarySearchTree.add(3);
    integerBinarySearchTree.add(6);
    integerBinarySearchTree.add(5);
    integerBinarySearchTree.add(7);
    /*
                      4
              2              6
         1         3     5          7
     */
    assertEquals(integerBinarySearchTree.toArrayList().get(0), 1);
    assertEquals(integerBinarySearchTree.toArrayList().get(1), 2);
    assertEquals(integerBinarySearchTree.toArrayList().get(6), 7);
  }
}