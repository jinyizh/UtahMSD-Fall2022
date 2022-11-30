package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

  private Node<T> root; // root node
  private int size; // size of BST

  /**
   * Inner class representing nodes in the BST
   *
   * @param <T> - type of node value
   */
  private static class Node<T extends Comparable<? super T>> {
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
    if (value == null) throw new NullPointerException();
    int originalSize = this.size;
    if (contains(value)) { // if the value is already in the BST
      return false;
    } else {
      this.root = addRecursive(root, value); // add the value, start from the root node
      return this.size > originalSize; // should be true if value was added
    }
  }

  /**
   * Recursive helper function to add a new Node
   *
   * @param node  - the Node to which the value is added
   * @param value - the value to be added into the BST
   * @return - the value added to the Node
   */
  private Node<T> addRecursive(Node<T> node, T value) {
    if (node == null) {
      node = new Node<>(value); // if the current node is null, add node here
      this.size++; // increase size
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
  public boolean contains(T item) throws NullPointerException {
    if (item == null) throw new NullPointerException();
    return containsRecursive(this.root, item);
  }

  /**
   * Recursive helper method to search a Node
   *
   * @param node  - the Node to search
   * @param value - the value of the Node
   * @return - return if the Node is in the BST
   */
  private boolean containsRecursive(Node<T> node, T value) {
    if (node == null) { // if the node is null, its left and right node will be null too as per constructor
      return false; // obviously no need to continue checking
    } else if (node.value == value) {
      return true;
    } else if (value.compareTo(node.value) < 0) { // recurs down to the left node
      return containsRecursive(node.left, value);
    } else if (value.compareTo(node.value) > 0) { // recurs down to the right node
      return containsRecursive(node.right, value);
    }
    return false; // placeholder
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
    for (T item : items) { // IntelliJ wants to use simplified if statement for iterator
      if (!contains(item)) return false;
    }
    return true;
  }

  /**
   * Returns the first (i.e., smallest) item in this set.
   *
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public T first() throws NoSuchElementException {
    if (this.root == null) throw new NoSuchElementException();
    Node<T> node = this.root;
    while (node.left != null) node = node.left;
    return node.value;
  }

  /**
   * Returns true if this set contains no items.
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the last (i.e., largest) item in this set.
   *
   * @throws NoSuchElementException if the set is empty
   */
  @Override
  public T last() throws NoSuchElementException {
    if (this.root == null) throw new NoSuchElementException();
    Node<T> node = this.root;
    while (node.right != null) node = node.right;
    return node.value;
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
   * Returns an ArrayList containing all the items in this set, in sorted order.
   *
   * @return - the array list containing the items
   */
  @Override
  public ArrayList<T> toArrayList() {
    ArrayList<T> arrayList = new ArrayList<>();
    inOrderTraversal(this.root, arrayList);
    return arrayList;
  }

  /**
   * Recursive helper function that traverse the BST in-order
   *
   * @param node - Node from which the traversal starts (usually the root)
   //* @return - Array List that contains the node values in sorted order
   */
  public void inOrderTraversal(Node<T> node, ArrayList<T> list) {
    if (node != null) {
      inOrderTraversal(node.left, list);
      list.add(node.value);
      inOrderTraversal(node.right, list);
    }
  }

  /**
   * Helper function for testing
   * @return - the root node of the BST
   */
  public Node<T> getRoot() {
    return this.root;
  }
}
