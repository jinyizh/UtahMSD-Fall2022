package assignment03;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchSet<E> implements SortedSet, Iterable {

  private E[] data;
  private int size; // everytime an element is added, size++
  private int capacity = 1; // initial capacity, grows when reached
  private Comparator<? super E> comparator = null;

  public BinarySearchSet() {
    this.data = (E[]) new Object[capacity];
    this.size = 0;
  }

  public BinarySearchSet(Comparator<? super E> comparator) {
    this.data = (E[]) new Object[capacity];
    this.size = 0;
    this.comparator = comparator;
  }

  @Override
  public Comparator comparator() {
    return this.comparator;
  }

  @Override
  public Object first() throws NoSuchElementException {
    if (isEmpty()) throw new NoSuchElementException();
    return data[0];
  }

  @Override
  public Object last() throws NoSuchElementException {
    if (isEmpty()) throw new NoSuchElementException();
    return data[data.length - 1];
  }

  @Override
  public boolean add(Object element) {
    if (this.contains(element) || element == null) return false; // prevent adding null
    if (size == capacity) grow(); // just grow 1 capacity
    data[size++] = (E) element;
    bubSort(); // sort the data array
    return true;
  }

  @Override
  public boolean addAll(Collection elements) {
    Iterator<? extends E> iterator = elements.iterator();
    int originalSize = this.size;
    while (iterator.hasNext()) add(iterator.next());
    return this.size != originalSize; // if collection is subset then return false
  }

  @Override
  public void clear() {
    capacity = 0; // re-initialize
    data = (E[]) new Object[capacity];
    size = 0; // shouldn't be null as required
  }

  @Override
  public boolean contains(Object element) {
    for (int i = 0; i < size; i++) {
      if (data[i].equals(element)) return true;
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection elements) {
    for (Object object : elements) {
      if (!contains(object)) return false;
    }
    return true;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public Iterator iterator() {
    SetIterator setIterator = new SetIterator();
    return setIterator;
  }

  @Override
  public boolean remove(Object element) {
    for (int i = 0; i < data.length; i++) {
      if (data[i].equals(element)) { // then remove data[i]
        capacity--;
        E[] newData = (E[]) new Object[capacity];
        // add elements before and after removed element to new array respectively
        for (int j = 0; j < i; j++) {
          newData[j] = data[j];
        }
        for (int k = i + 1; k < data.length; k++) {
          newData[k - 1] = data[k]; // # of elements in newData is 1 fewer after data[i]
        }
        data = newData;
        size--; // have to do this manually since size can only be changed in constructors, .add() and .addAll()
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean removeAll(Collection elements) {
    Iterator<E> iterator = elements.iterator();
    int originalSize = size;
    while (iterator.hasNext()) remove(iterator.next());
    return size != originalSize;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Object[] toArray() {
    return data;
  }

  private void bubSort() { // bubble sort method, sort data
    Comparator<? super E> comparator = this.comparator;
    if (data == null || isEmpty()) return; // can't sort null or empty array
    if (comparator == null) { // natural ordering
      for (int i = 0; i < data.length - 1; i++) {
        for (int j = 0; j < data.length - i - 1; j++) {
          Comparable<E> jElement = (Comparable<E>) data[j];
          Comparable<E> j1Element = (Comparable<E>) data[j + 1];
          if (jElement.compareTo((E) j1Element) > 0) {
            Comparable<E> temp = (Comparable<E>) data[j];
            data[j] = (E) j1Element;
            data[j + 1] = (E) temp;
          }
        }
      }
    } else { // ordering using comparator
      for (int i = 0; i < data.length - 1; i++) {
        for (int j = 0; j < data.length - i - 1; j++) {
          if (comparator.compare(data[j], data[j + 1]) > 0) {
            E temp = data[j];
            data[j] = data[j + 1];
            data[j + 1] = temp;
          }
        }
      }
    }
  }

  private void grow() { // grow data capacity by 1)
    capacity++;
    E[] newData = (E[]) new Object[capacity];
    for (int j = 0; j < data.length; j++) {
      newData[j] = data[j];
    }
    data = newData;
  }

  public E getValue(int i) { // for testing purpose
    return data[i];
  }

  public class SetIterator implements Iterator<E> {

    int pos; // position

    public SetIterator() {
      pos = 0;
    }

    @Override
    public boolean hasNext() {
      return this.pos < data.length;
    }

    @Override
    public E next() throws NoSuchElementException {
      if (this.hasNext()) {
        return data[this.pos++];
      } else {
        return null;
      }
    }

    @Override
    public void remove() throws UnsupportedOperationException, IllegalStateException {
      Iterator.super.remove();
    }
  }

}