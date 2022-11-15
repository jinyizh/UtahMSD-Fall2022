package assignment03;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchSet<E> implements SortedSet, Iterable {

  private E[] data;
  private int size;
  private int capacity = 10;
  private Comparator<? super E> comparator = null;

  public BinarySearchSet() {
    this.data = (E[]) new Object[capacity];
  }

  public BinarySearchSet(Comparator<? super E> comparator) {
    this.data = (E[]) new Object[capacity];
    this.comparator = comparator;
  }

  @Override
  public Comparator comparator() {
    return this.comparator;
  }

  @Override
  public Object first() throws NoSuchElementException {
    return data[0];
  }

  @Override
  public Object last() throws NoSuchElementException {
    return data[data.length - 1];
  }

  @Override
  public boolean add(Object element) {
    if (this.contains(element) || element == null) return false;
    if (this.comparator == null) { // ordered in natural ordering
      Comparable<E> elementComparable = (Comparable<E>) element;
      if (elementComparable.compareTo((E) this.last()) >= 0) { // add after end

      }

    } else { // ordered using provided comparator

    }
    return false;
  }

  @Override
  public boolean addAll(Collection elements) {
    Iterator<? extends E> itr = elements.iterator();
    return false;
  }

  @Override
  public void clear() {
    Iterator<? extends E> itr = this.iterator();
    while (itr.hasNext()) {
      itr.remove();
    }
  }

  @Override
  public boolean contains(Object element) {
    for (E e : this.data) {
      if (e == null) return false;
      if (e.equals(element)) return true;
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection elements) {
    Iterator<E> iterator = elements.iterator();
    return false;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public Iterator iterator() {
    return null;
  }

  @Override
  public void forEach(Consumer action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator spliterator() {
    return Iterable.super.spliterator();
  }

  @Override
  public boolean remove(Object element) {
    return false;
  }

  @Override
  public boolean removeAll(Collection elements) {
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Object[] toArray() {
    E[] array = (E[]) new Object[size];
    System.arraycopy(data, 0, array, 0, size);
    return array;
  }

  public void ensureCapacity(int minCapacity) {
    int current = data.length;
    if (minCapacity > current) {
      E[] newData = (E[]) new Object[Math.max(current * 2, minCapacity)];
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    }
  }

  
}
