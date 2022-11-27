package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

  private Node<T> root; // root node

  /**
   * Inner class representing nodes in the BST
   *
   * @param <T> - type of node value
   */
  private class Node<T extends Comparable<? super T>> {
    T value;
    Node<T> left, right;

    public Node(T value) { // constructor for node
      this.value = value;
      this.left = this.right = null;
    }
  }

  /**
   * No-parameter constructor that creates an empty BST
   */
  BinarySearchTree() {
    this.root = null;
  }

  /**
   * Ensures that this set contains the specified item.
   *
   * @param item - the item whose presence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * the input item was actually inserted); otherwise, returns false
   * @throws NullPointerException if the item is null
   */
  @Override
  public boolean add(T item) {
    return false;
  }

  /**
   * Ensures that this set contains all items in the specified collection.
   *
   * @param items - the collection of items whose presence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * any item in the input collection was actually inserted); otherwise,
   * returns false
   * @throws NullPointerException if any of the items is null
   */
  @Override
  public boolean addAll(Collection<? extends T> items) {
    return false;
  }

  /**
   * Removes all items from this set. The set will be empty after this method
   * call.
   */
  @Override
  public void clear() {

  }

  /**
   * Determines if there is an item in this set that is equal to the specified
   * item.
   *
   * @param item - the item sought in this set
   * @return true if there is an item in this set that is equal to the input item;
   * otherwise, returns false
   * @throws NullPointerException if the item is null
   */
  @Override
  public boolean contains(T item) {
    return false;
  }

  /**
   * Determines if for each item in the specified collection, there is an item in
   * this set that is equal to it.
   *
   * @param items - the collection of items sought in this set
   * @return true if for each item in the specified collection, there is an item
   * in this set that is equal to it; otherwise, returns false
   * @throws NullPointerException if any of the items is null
   */
  @Override
  public boolean containsAll(Collection<? extends T> items) {
    return false;
  }

  /**
   * Returns the first (i.e., smallest) item in this set.
   *
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public T first() throws NoSuchElementException {
    return null;
  }

  /**
   * Returns true if this set contains no items.
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * Returns the last (i.e., largest) item in this set.
   *
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public T last() throws NoSuchElementException {
    return null;
  }

  /**
   * Ensures that this set does not contain the specified item.
   *
   * @param item - the item whose absence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * the input item was actually removed); otherwise, returns false
   * @throws NullPointerException if the item is null
   */
  @Override
  public boolean remove(T item) {
    return false;
  }

  /**
   * Ensures that this set does not contain any of the items in the specified
   * collection.
   *
   * @param items - the collection of items whose absence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * any item in the input collection was actually removed); otherwise,
   * returns false
   * @throws NullPointerException if any of the items is null
   */
  @Override
  public boolean removeAll(Collection<? extends T> items) {
    return false;
  }

  /**
   * Returns the number of items in this set.
   */
  @Override
  public int size() {
    return 0;
  }

  /**
   * Returns an ArrayList containing all of the items in this set, in sorted
   * order.
   */
  @Override
  public ArrayList<T> toArrayList() {
    return null;
  }
}
