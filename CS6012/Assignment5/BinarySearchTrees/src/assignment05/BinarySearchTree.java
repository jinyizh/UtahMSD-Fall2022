package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

  private Node<T> root; // root node
  int size;

  /**
   * Inner class representing nodes in the BST
   *
   * @param <T> - type of node value
   */
  private class Node<T extends Comparable<? super T>> {
    T value; // value of the tree node
    Node<T> left, right; // nodes to the left and right of the current node

    // Constructor for Node. When a Node is created, its left and right nodes are set to null
    public Node(T value) {
      this.value = value;
      this.left = this.right = null;
    }
  }

  /**
   * No-parameter constructor that creates an empty BST
   */
  BinarySearchTree() {
    this.root = null;
    this.size = 0;
  }

  /**
   * Ensures that this set contains the specified item.
   *
   * @param value - the item whose presence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if
   * the input item was actually inserted); otherwise, returns false
   * @throws NullPointerException if the item is null
   */
  @Override
  public boolean add(T value) throws NullPointerException {
    int originalSize = this.size;
    if (value == null) throw new NullPointerException();
    if (contains(value)) { // if the value is already in the BST
      return false;
    } else {
      this.root = addRecursive(root, value); // add the value, start from the root node
      return this.size > originalSize; // should be true if value was added
    }
  }

  /**
   * Recursive function to add a new Node
   * @param node - the node to which the value is added
   * @param value - the value to be added into the BST
   * @return - the value added to the node
   */
  private Node addRecursive(Node<T> node, T value) {
    if (node == null) {
      node = new Node<>(value); // if the current node is null, add node here
      this.size++; // size increases
    } else if (value.compareTo(node.value) < 0) { // recurs down to the left node
      node.left = addRecursive(node.left, value);
    } else if (value.compareTo(node.value) > 0) { // recurs down to the right node
      node.right = addRecursive(node.right, value);
    }
    return node;
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
    int originalSize = this.size;
    Iterator<? extends T> iterator = items.iterator();
    if (iterator.hasNext()) add(iterator.next());
    return this.size > originalSize;
  }

  /**
   * Removes all items from this set. The set will be empty after this method
   * call.
   */
  @Override
  public void clear() {
    this.root = null;
    this.size = 0;
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
    return this.size;
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
