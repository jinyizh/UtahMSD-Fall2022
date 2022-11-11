package assignment02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryGenericTest {

    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        LibraryGeneric<String> lib = new LibraryGeneric<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));

        var booksCheckedOut1 = lib.lookup(patron1);
        assertEquals(booksCheckedOut1.size(), 2);
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(),patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(),new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }

    @Test
    public void phoneNumberTest(){
        // test a library that uses phone numbers (PhoneNumber) to id patrons
        var lib = new LibraryGeneric<PhoneNumber>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        assertEquals(booksCheckedOut2.size(), 2);
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut2.get(0).getHolder(),patron2);
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }

    @Test
    public void sortingIsbnTest() {
        var lib = new LibraryGeneric<String>();
        var libCopy = lib.getInventoryList();
        assertEquals(libCopy, new ArrayList<>()); // empty list
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        var libCopy1 = lib.getInventoryList();
        assertEquals(libCopy1.get(0).getTitle(), "Into the Wild");
    }

    @Test
    public void sortingAuthorTest() {
        var lib = new LibraryGeneric<String>();
        var libCopy = lib.getOrderedByAuthor();
        assertEquals(libCopy, new ArrayList<>()); // empty list
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        var libCopy1 = lib.getOrderedByAuthor();
        assertEquals(libCopy1.get(0).getAuthor(), "David Baldacci");
        assertEquals(libCopy1.get(2).getTitle(), "The World is Flat"); // author: Thomas L. Friedman

        lib.add(9999999999999L, "Thomas L. Friedman", "I Never Wrote This");
        var libCopy2 = lib.getOrderedByAuthor();
        assertEquals(libCopy2.get(2).getTitle(), "I Never Wrote This");
    }

    @Test
    public void overdueListTest() {
        LibraryGeneric<String> lib = new LibraryGeneric<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        lib.checkout(9780330351690L, patron1, 1, 1, 2008);
        lib.checkout(9780374292799L, patron1, 1, 1, 2009);

        var libCopy = lib.getOverdueList(1, 1, 2022);
        assertEquals(libCopy.get(0).getTitle(), "Into the Wild");
    }

    @Test
    public void emptyTest() {
        LibraryGeneric<PhoneNumber> lib = new LibraryGeneric<>();
        assertNull(lib.lookup(9780446580342L));

        PhoneNumber patron = new PhoneNumber("999.999.9999");

        assertFalse(lib.checkout(9780446580342L, patron, 1, 1, 2022)); // no such book

        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        assertNull(lib.lookup(9780446580342L)); // still null because not checkout
        assertTrue(lib.checkout(9780446580342L, patron, 1, 1, 2022)); // now checked out
    }

    @Test
    public void testLargeLibrary() {
        var lib = new LibraryGeneric<String>();
        lib.addAll("Mushroom_Publishing.txt");
        var booksCheckOut = lib.lookup("John Smith");
        assertEquals(booksCheckOut, new ArrayList<>()); // If the specified holder has no books checked out, returns an empty list.

        lib.checkout(9781843192039L, "John Smith", 12, 31, 2000);
        var booksCheckOut1 = lib.lookup("John Smith");
        assertEquals(booksCheckOut1.size(), 1);
        assertEquals(booksCheckOut1.get(0).getHolder(), "John Smith");
        assertEquals(booksCheckOut1.get(0).getTitle(), "Operation: Sergeant York");

        assertTrue(lib.checkin("John Smith"));
        assertFalse(lib.checkin(9781843192039L)); // already checked in
    }
}