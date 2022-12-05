package assignment06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {

  ChainingHashTable badTable;
  ChainingHashTable mediocreTable;
  ChainingHashTable goodTable;
  ArrayList<String> list; // for testing addAll() and other methods
  ArrayList<String> wordList; // for testing collision

  @BeforeEach
  void setUp() {
    badTable = new ChainingHashTable(2000, new BadHashFunctor());
    mediocreTable = new ChainingHashTable(2000, new MediocreHashFunctor());
    goodTable = new ChainingHashTable(2000, new GoodHashFunctor());
    list = new ArrayList<>();
    wordList = readFromFile("dictionary.txt");
  }

  @AfterEach
  void tearDown() {
    badTable = null;
    mediocreTable = null;
    goodTable = null;
    list = null;
    wordList = null;
  }

  @Test
  void add() {
    badTable.add("hi");
    assertEquals(badTable.size(), 1);
    assertTrue(badTable.remove("hi"));
  }

  @Test
  void addAll() {
    list.add("a");
    list.add("bc");
    badTable.addAll(list);
    assertEquals(badTable.size(), 2);
    assertTrue(badTable.removeAll(list));
  }

  @Test
  void clear() {
    goodTable.add("haha");
    goodTable.add("ok");
    assertEquals(goodTable.size(), 2);
    goodTable.clear();
    assertEquals(goodTable.size(), 0);
  }

  @Test
  void contains() {
    assertFalse(mediocreTable.contains("what"));
    mediocreTable.add("ij");
    mediocreTable.add("~");
    assertTrue(mediocreTable.contains("ij"));
    assertFalse(mediocreTable.contains("no"));
  }

  @Test
  void containsAll() {
    list.add("ut");
    list.add("ah");
    list.add("msd");
    mediocreTable.addAll(list);
    assertTrue(mediocreTable.contains("ut"));
    assertTrue(mediocreTable.containsAll(list));
  }

  @Test
  void isEmpty() {
    assertTrue(badTable.isEmpty());
    badTable.add("ty");
    assertFalse(badTable.isEmpty());
    badTable.clear();
    assertTrue(badTable.isEmpty());
  }

  @Test
  void remove() {
    goodTable.add("z");
    assertTrue(goodTable.remove("z"));
    assertFalse(goodTable.remove("zzz"));
    assertEquals(goodTable.size(), 0);
  }

  @Test
  void removeAll() {
    list.add("a");
    list.add("cc");
    mediocreTable.add("b");
    mediocreTable.add("a");
    mediocreTable.add("cc");
    assertTrue(mediocreTable.removeAll(list));
    assertTrue(mediocreTable.contains("b"));
  }

  @Test
  void size() {
    assertEquals(badTable.size(), 0);
    badTable.addAll(list);
    assertTrue(badTable.isEmpty());
    badTable.add("r");
    assertEquals(badTable.size(), 1);
    badTable.add("r");
    assertEquals(badTable.size(), 1);
    badTable.add("rr");
    badTable.add("rrr");
    assertEquals(badTable.size(), 3);
  }

  @Test
  void testCollision() {
    badTable.addAll(wordList);
    mediocreTable.addAll(wordList);
    goodTable.addAll(wordList);
    System.out.println("for bad functor, # of collisions is: " + badTable.getCollisions());
    System.out.println("for mediocre functor, # of collisions is: " + mediocreTable.getCollisions());
    System.out.println("for good functor, # of collisions is: " + goodTable.getCollisions());
  }

  private ArrayList<String> readFromFile(String file) {
    ArrayList<String> words = new ArrayList<String>();
    try (Scanner fileInput = new Scanner(new File(file))) {
      fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");
      while (fileInput.hasNext()) {
        String s = fileInput.next();
        if (!s.equals("")) words.add(s.toLowerCase());
      }
    } catch (FileNotFoundException e) {
      System.err.println("File " + file + " cannot be found.");
    }
    return words;
  }
}