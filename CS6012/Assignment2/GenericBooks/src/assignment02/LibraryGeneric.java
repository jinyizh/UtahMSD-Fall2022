package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

public class LibraryGeneric<Type> {
  private ArrayList<LibraryBookGeneric<Type>> library;

  public LibraryGeneric() {
    library = new ArrayList<LibraryBookGeneric<Type>>();
  }

  public void add(long isbn, String author, String title) {
    library.add(new LibraryBookGeneric<>(isbn, author, title));
  }

  public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
    library.addAll(list);
  }

  public void addAll(String filename) {
    ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

    try (Scanner fileIn = new Scanner(new File(filename))) {

      int lineNum = 1;

      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();

        try (Scanner lineIn = new Scanner(line)) {
          lineIn.useDelimiter("\\t");

          if (!lineIn.hasNextLong()) {
            throw new ParseException("ISBN", lineNum);
          }
          long isbn = lineIn.nextLong();

          if (!lineIn.hasNext()) {
            throw new ParseException("Author", lineNum);
          }
          String author = lineIn.next();

          if (!lineIn.hasNext()) {
            throw new ParseException("Title", lineNum);
          }
          String title = lineIn.next();
          toBeAdded.add(new LibraryBookGeneric<>(isbn, author, title));
        }
        lineNum++;
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage() + " Nothing added to the library.");
      return;
    } catch (ParseException e) {
      System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
          + ". Nothing added to the library.");
      return;
    }

    library.addAll(toBeAdded);
  }

  public Type lookup(long isbn) {
    for (LibraryBookGeneric<Type> libraryBookGeneric : library) {
      if (libraryBookGeneric.getIsbn() == isbn) {
        return libraryBookGeneric.getHolder(); // holder will be null if book not checked out
      }
    }
    return null;
  }

  public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
    ArrayList<LibraryBookGeneric<Type>> libraryBookGenerics = new ArrayList<>(); // ArrayList of books of specified holder
    for (LibraryBookGeneric<Type> libraryBookGeneric : library) {
      if (Objects.equals(libraryBookGeneric.getHolder(), holder)) {
        libraryBookGenerics.add(libraryBookGeneric);
      }
    }
    return libraryBookGenerics;
  }

  public boolean checkout(long isbn, Type holder, int month, int day, int year) {
    // FILL IN -- do not return false unless appropriate
    for (LibraryBookGeneric<Type> libraryBookGeneric : library) {
      if (Objects.equals(libraryBookGeneric.getIsbn(), isbn)) { // find the book...
        if (libraryBookGeneric.getHolder() == null && libraryBookGeneric.getDueDate() == null) {
          // ...and the book has not checked out
          GregorianCalendar dueDate = new GregorianCalendar(year, month, day); // create due date of book
          libraryBookGeneric.checkout(holder, dueDate);
          return true;
        } else return false; // ...but the book is checked out
      }
    }
    return false; // can't find the book after running the loop
  }

  public boolean checkin(long isbn) {
    for (LibraryBookGeneric<Type> libraryBookGeneric : library) {
      if (Objects.equals(libraryBookGeneric.getIsbn(), isbn)) {
        if (libraryBookGeneric.getHolder() != null && libraryBookGeneric.getDueDate() != null) {
          libraryBookGeneric.checkin();
          return true;
        } else return false; // book already checked in
      }
    }
    return false; // default: no book with such ISBN
  }

  public boolean checkin(Type holder) {
    for (LibraryBookGeneric<Type> libraryBookGeneric : library) {
      if (Objects.equals(libraryBookGeneric.getHolder(), holder)) {
        libraryBookGeneric.checkin();
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the list of library books, sorted by ISBN (smallest ISBN first).
   */
  public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
    ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
    libraryCopy.addAll(library);

    OrderByIsbn comparator = new OrderByIsbn();

    sort(libraryCopy, comparator);

    return libraryCopy;
  }

  /**
   * Returns the list of library books, sorted by author
   */
  public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
    // FILL IN -- do not return null
    ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<>();
    libraryCopy.addAll(library);

    OrderByAuthor comparator = new OrderByAuthor();

    sort(libraryCopy, comparator);

    return libraryCopy;
  }

  /**
   * Returns the list of library books whose due date is older than the input
   * date. The list is sorted by date (oldest first).
   *
   * If no library books are overdue, returns an empty list.
   */
  public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
    // FILL IN -- do not return null
    ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<>();

    GregorianCalendar date = new GregorianCalendar(year, month, day); // pass the input

    for (LibraryBookGeneric<Type> libraryBookGeneric : library) {
      if (libraryBookGeneric.getDueDate().before(date)) libraryCopy.add(libraryBookGeneric); // add books that are overdue
    }

    OrderByDueDate comparator = new OrderByDueDate();

    sort(libraryCopy, comparator);

    return libraryCopy;
  }



  /**
   * Performs a SELECTION SORT on the input ArrayList.
   *    1. Find the smallest item in the list.
   *    2. Swap the smallest item with the first item in the list.
   *    3. Now let the list be the remaining unsorted portion
   *       (second item to Nth item) and repeat steps 1, 2, and 3.
   */
  private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
    for (int i = 0; i < list.size() - 1; i++) {
      int j, minIndex;
      for (j = i + 1, minIndex = i; j < list.size(); j++)
        if (c.compare(list.get(j), list.get(minIndex)) < 0)
          minIndex = j;
      ListType temp = list.get(i);
      list.set(i, list.get(minIndex));
      list.set(minIndex, temp);
    }
  }

  /**
   * Comparator that defines an ordering among library books using the ISBN.
   */
  protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

    /**
     * Returns a negative value if lhs is smaller than rhs. Returns a positive
     * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
     */
    public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
      return (int) (lhs.getIsbn() - rhs.getIsbn());
    }
  }

  /**
   * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
   */
  protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {
    
    // FILL IN
    public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
      if (lhs.getAuthor().compareTo(rhs.getAuthor()) != 0) {
        return lhs.getAuthor().compareTo(rhs.getAuthor()); // when authors are not the same
      } else {
        return lhs.getTitle().compareTo(rhs.getTitle()); // tiebreaker
      }
    }
  }

  /**
   * Comparator that defines an ordering among library books using the due date.
   */
  protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {

    // FILL IN
    public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
      return lhs.getDueDate().compareTo(rhs.getDueDate());
    }
  }
}
