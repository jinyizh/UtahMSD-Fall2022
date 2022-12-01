package assignment05;

import java.util.*;
import java.util.function.BinaryOperator;

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
  public boolean addAll(Collection<? extends T> items) throws NullPointerException {
    Object[] itemList = items.toArray();
    boolean itemAdded = false;
    // Check for null values first
    for(int y = 0; y < itemList.length; y++){
      if(itemList[y] == null){
        System.err.println("Cannot add null to the Binary Search Tree");
        throw new NullPointerException();
      }
    }
    // Add items
    for(int x = 0; x < itemList.length; x++){
      if(add((T) itemList[x])){
        itemAdded = true;
      }
    }
    return itemAdded;
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
  public boolean remove(T item) throws NullPointerException {
    if (this.size == 1) { // special case: BST only has one node
      this.root = null;
      this.size = 0;
    }
    if (item == null) throw new NullPointerException();
    if (!contains(item) || this.size == 0) return false;
    // item is in the BST, find the node to remove and its parent:
    Node<T> parent, removed;
    if (this.root.value.equals(item)) {
      parent = this.root; // parent node of the node to remove
      removed = this.root; // node to remove
    } else {
      parent = findParent(this.root, item);
      if (parent.left.value.equals(item)) {
        removed = parent.left;
      } else { // node that has value is the right child of the parent
        removed = parent.right;
      }
    }
    // now need to consider 3 cases for a node to remove:
    // 1, node is at a leaf
    // 2, node has two children / subtrees
    // 3, node has one child / subtree and another child / subtree is null
    if (removed.left == null && removed.right == null) { // 1
      return removeLeaf(parent, item);
    } else if (removed.left != null && removed.right != null) { // 3
      return removeDoubleChildren(parent, item);
    } else { //2
      return removedSingleChild(parent, item);
    }
  }

  /**
   * Helper method to remove a leaf node
   * @param parent - parent node of the leaf
   * @param item - value of the node to remove
   * @return - if the BST size changes (always)
   */
  private boolean removeLeaf(Node<T> parent, T item) {
    if (parent.left.value.equals(item)) {
      parent.left = null;
    } else {
      parent.right = null;
    }
    this.size--;
    return true;
  }

  /**
   * Helper method to remove node that has only one branch that contains the value to remove (another null)
   * @param parent - parent node of the node to remove
   * @param item - value to remove
   * @return - is the BST size changes (always)
   */
  private boolean removedSingleChild(Node<T> parent, T item) {
    if (parent.left.value.equals(item)) { // node to remove is the left child / root of subtree
      if (parent.left.left == null) { // node to remove doesn't have left branch
        parent.left = parent.left.right;
      } else {
        parent.left = parent.left.left;
      }
    } else { // node to remove is the right child / root of subtree
      if (parent.right.left == null) { // node to remove doesn't have left branch
        parent.right = parent.right.right;
      } else {
        parent.right = parent.right.left;
      }
    }
    this.size--;
    return true;
  }

  /**
   * Helper method to remove node that has two branches
   * @param parent - parent node of the node to remove
   * @param item - value to be removed
   * @return - calls one of other two remove methods aforementioned
   */
  private boolean removeDoubleChildren(Node<T> parent, T item) {
    if (this.root.value.equals(item)) {
      parent = this.root;
    } else if (parent.left.value.equals(item)) {
      parent = parent.left;
    } else {
      parent = parent.right;
    }
    // need to find the successor node
    Node<T> successor = parent.right;
    Node<T> successorsParent = parent;
    int count = 0;
    while (successor.left != null) {
      if (count == 0) {
        successorsParent = successorsParent.right;
      } else {
        successorsParent = successorsParent.left;
      }
      successor = successorsParent.left;
      count++;
    }
    parent.value = successor.value;
    if (successor.right == null) { // 1
      return removeLeaf(successorsParent, successor.value);
    } else { // 2
      return removedSingleChild(successorsParent, successor.value);
    }
  }

  /**
   * Helper function to find the parent node of the node to be removed
   * @param node - the current Node
   * @param item - item (value) of the Node to be removed
   * @return - parent Node of the Node that contains the value item
   */
  private Node<T> findParent(Node<T> node, T item) {
    if (node.left.value.equals(item) || node.right.value.equals(item)) {
      return node;
    } else if (item.compareTo(node.value) < 0) { // recurs down to the left
      node = node.left;
      return findParent(node, item);
    } else { // item > node.value, recurs down to the right
      node = node.right;
      return findParent(node, item);
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
   * @throws NullPointerException if any of the items is null
   */
  @Override
  public boolean removeAll(Collection<? extends T> items) throws NullPointerException {
    Object[] itemArray = items.toArray();
    boolean itemRemoved = false;
    for (Object item : itemArray) {
      if (item == null) throw new NullPointerException();
    }
    for (Object item : itemArray) {
      if (remove((T) item)) {
        itemRemoved = true;
      }
    }
    return itemRemoved;
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
   * @param node - Node from which the traversal starts (usually the root)
   * @param list - the Array List containing node values in sorted order
   */
  public void inOrderTraversal(Node<T> node, ArrayList<T> list) {
    if (node != null) {
      inOrderTraversal(node.left, list);
      list.add(node.value);
      inOrderTraversal(node.right, list);
    }
  }

  /**
   * Helper method for testing
   * @return - the root node of the BST
   */
  public Node<T> getRoot() {
    return this.root;
  }

  /**
   * Helper method for testing
   * @param node - the node we want to know its left child
   * @return - left child of the node
   */
  public Node<T> getLeft(Node<T> node) {
    return node.left;
  }

  /**
   * Helper method for testing
   * @param node - the node we want to know its right child
   * @return - right child of the node
   */
  public Node<T> getRight(Node<T> node) {
    return node.right;
  }

  /**
   * Helper method for testing
   * @param node - node we want to get its value
   * @return value of the node
   */
  public T getValue(Node<T> node) {
    return node.value;
  }
}
