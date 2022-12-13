package assignment06;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class QuadProbeHashTable implements Set<String> {

  private String[] storage;
  private HashFunctor functor;
  private int capacity;
  private int size;
  private int collisions;
  private double lambda;

  /**
   * Constructs a hash table of the given capacity that uses the hashing function
   * specified by the given functor.
   */
  public QuadProbeHashTable(int capacity, HashFunctor functor) {
    if (!isPrime(capacity)) {
      this.capacity = nextPrime(capacity);
    } else {
      this.capacity = capacity;
    }
    this.storage = new String[this.capacity];
    this.functor = functor;
    this.size = 0;
    this.collisions = 0;
    this.lambda = 0;
  }

  /**
   * Ensures that this set contains the specified item.
   *
   * @param item - the item whose presence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * the input item was actually inserted); otherwise, returns false
   */
  @Override
  public boolean add(String item) {
    // the table should be resized and rehashed when lambda exceeds 0.5
    if (this.lambda > 0.5) rehash();
    int hashIndex = functor.hash(item) % capacity;
    return insert(item, hashIndex, storage);
  }

  /**
   * Ensures that this set contains all items in the specified collection.
   *
   * @param items - the collection of items whose presence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * any item in the input collection was actually inserted); otherwise,
   * returns false
   */
  @Override
  public boolean addAll(Collection<? extends String> items) {
    Iterator<? extends String> iterator = items.iterator();
    int originalSize = size; // whenever add items, size changes. Used as indicator of whether set changed.
    while (iterator.hasNext()) add(iterator.next());
    return size != originalSize;
  }

  /**
   * Removes all items from this set. The set will be empty after this method
   * call.
   */
  @Override
  public void clear() {
    storage = new String[this.capacity];
    size = 0;
  }

  /**
   * Determines if there is an item in this set that is equal to the specified
   * item.
   *
   * @param item - the item sought in this set
   * @return true if there is an item in this set that is equal to the input item;
   * otherwise, returns false
   */
  @Override
  public boolean contains(String item) {
    int hashIndex = functor.hash(item) % capacity;
    int i = 0; // use in hash + i^2
    while (storage[hashIndex] != null) {
      if (storage[hashIndex].equals(item)) return true;
      hashIndex += i * i;
      if (hashIndex > capacity) hashIndex %= capacity;
      i++;
    }
    return false;
  }

  /**
   * Determines if for each item in the specified collection, there is an item in
   * this set that is equal to it.
   *
   * @param items - the collection of items sought in this set
   * @return true if for each item in the specified collection, there is an item
   * in this set that is equal to it; otherwise, returns false
   */
  @Override
  public boolean containsAll(Collection<? extends String> items) {
    for (String item : items) if (!contains(item)) return false;
    return true;
  }

  /**
   * Returns true if this set contains no items.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Ensures that this set does not contain the specified item.
   *
   * @param item - the item whose absence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * the input item was actually removed); otherwise, returns false
   */
  @Override
  public boolean remove(String item) {
    int hashIndex = functor.hash(item) % capacity;
    if (!contains(item)) {
      return false;
    } else if (storage[hashIndex].equals(item)) {
      storage[hashIndex] = null;
      size--;
      return true;
    } else { // find the item
      int i = 1;
      int tryIt = (int) (hashIndex + i * i) % capacity;
      while (tryIt != hashIndex) {
        if (storage[tryIt] == null) {
          return false;
        } else if (storage[tryIt].equals(item)) {
          break;
        }
        i++;
        tryIt = (int) (hashIndex + i * i) % capacity;
      }
      size--;
      return true;
    }
  }

  /**
   * Ensures that this set does not contain any of the items in the specified
   * collection.
   *
   * @param items - the collection of items whose absence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * any item in the input collection was actually removed); otherwise,
   * returns false
   */
  @Override
  public boolean removeAll(Collection<? extends String> items) {
    Iterator<? extends String> iterator = items.iterator();
    int originalSize = size;
    while (iterator.hasNext()) remove(iterator.next());
    return size != originalSize;
  }

  /**
   * Returns the number of items in this set.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Helper method used to determine if a number is a prime number
   *
   * @param n the number to check
   * @return true if n is a prime number
   */
  private static boolean isPrime(int n) {
    if (n % 2 == 0) return false;
    for (int i = 3; i <= Math.sqrt(n); i += 2) if (n % i == 0) return false;
    return true;
  }

  /**
   * Helper method used to find the next prime number after n
   *
   * @param n the number
   * @return the next prime larger than n
   */
  private static int nextPrime(int n) {
    if (n % 2 == 0) n++;
    for (int i = n; i < 2 * n + 2; i += 2) if (isPrime(i)) return i;
    return -1; // placeholder
  }

  /**
   * Helper method used to rehash the table if lambda > 0.5
   */
  private void rehash() {
    int primeCapacity = nextPrime(this.capacity * 2);
    this.size = 0;
    this.collisions = 0;
    String[] newData = new String[primeCapacity];
    for (int i = 0; i < this.capacity; i++) {
      if (this.storage[i] != null) {
        int hashIndex = this.functor.hash(this.storage[i]) % primeCapacity;
        insert(this.storage[i], hashIndex, newData);
      }
    }
    this.storage = newData;
    this.capacity = primeCapacity;
  }

  /**
   * Helper method used for adding and rehashing
   *
   * @param item      item to be added
   * @param hashIndex hash index of the item
   * @param table     the array to which the item is inserted
   * @return true if inserted
   */
  private boolean insert(String item, int hashIndex, String[] table) {
    int i = 1; // where i is used in hash + i^2
    int temp = hashIndex % table.length;
    while (true) {
      if (table[temp] == null) { // insert if empty
        table[temp] = item;
        this.size++;
        this.lambda = (double) this.size / this.capacity;
        return true;
      }
      if (table[temp].equals(item)) {
        return false;
      }
      temp = hashIndex + i * i;
      this.collisions++;
      temp %= table.length;
      i++;
    }
  }
}
