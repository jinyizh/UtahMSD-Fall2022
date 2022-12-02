package assignment06;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {

  private LinkedList<String>[] storage; // appended to buckets
  private HashFunctor functor; // hash function
  private int capacity; // capacity of array
  private int size; // size of Hash Table (num of k-v pairs)
  private int collisions; // total num of collisions

  @SuppressWarnings("unchecked")
  public ChainingHashTable(int capacity, HashFunctor functor) {
    this.storage = (LinkedList<String>[]) new LinkedList[capacity];
    this.functor = functor;
    this.capacity = capacity;
    this.size = 0;
    this.collisions = 0;
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
    int hashIndex = functor.hash(item) % capacity;
    if (storage[hashIndex] == null) { // LL hasn't been created at this location
      LinkedList<String> list = new LinkedList<>();
      list.add(item);
      storage[hashIndex] = list; // put list into the bucket
      size++;
      return true;
    } else if (storage[hashIndex].contains(item)) { // LL has been created && contains the item
      return false;
    } else { // add item to the already-existed LL
      storage[hashIndex].add(item);
      size++;
      collisions++; // LL exists, which means collision exists
      return true;
    }
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
    storage = (LinkedList<String>[]) new LinkedList[capacity]; // re-construct
    size = 0; // re-construct, no k-v pairs anymore
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
    if (storage[hashIndex] == null || !storage[hashIndex].contains(item)) { // LL not exist, or item not in LL
      return false;
    } else { // item is in that LL
      return true;
    }
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
    if (storage[hashIndex] == null || !storage[hashIndex].contains(item)) { // LL not exist, or item not in LL
      return false;
    } else { // remove item from the already-existed LL
      storage[hashIndex].remove(item);
      size--;
      if (storage[hashIndex].isEmpty()) storage[hashIndex] = null; // nullify array[hashIndex] if nothing in LL
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
}
