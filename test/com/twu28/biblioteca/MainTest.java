package com.twu28.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class MainTest {

    private PrintStream mockOutput;
    private Main main;
    private Input mockInput;

    @Before
    public void setUp() throws Exception {
        mockInput = mock(Input.class);
        mockOutput = mock(PrintStream.class);
        ArrayList<Book> booksAvailable = new ArrayList<Book>();
        booksAvailable.add(new Book(1, "Alice in Wonderland"));
        booksAvailable.add(new Book(2, "Da Vinci Code"));
        booksAvailable.add(new Book(3, "Angels and Demons"));

        main = new Main(mockOutput, mockInput, booksAvailable);
    }

    @Test
    public void shouldWelcomeUser() throws Exception {
        main.displayWelcomeMessage();

        verify(mockOutput).println("Welcome User");
    }

    @Test
    public void shouldPrintMenuOptions() throws Exception {
        main.printMenuOptions();

        verify(mockOutput).println("1. View All Books");
        verify(mockOutput).println("2. Library Number");
        verify(mockOutput).println("0. Exit");
    }

    @Test
    public void shouldPrintBooksInLibrary() throws Exception {
        main.printBooks();
        verify(mockOutput).println("1:Alice in Wonderland");
        verify(mockOutput).println("2:Da Vinci Code");
        verify(mockOutput).println("3:Angels and Demons");
    }

    @Test
    public void shouldBeAbleToReserveUnreservedBook() throws Exception {
        main.reserveBook("1");
        verify(mockOutput).println("Book - 1:Alice in Wonderland is reserved. Enjoy the book");
    }

    @Test
    public void shouldSayBookIsNotAvailableIfBookIsAlreadyReserved() throws Exception {
        main.reserveBook("1");
        main.reserveBook("1");
        verify(mockOutput).println("Book - 1:Alice in Wonderland is not available.");
    }

    @Test
    public void shouldDisplayLibrarianMessage() throws Exception {
        main.displayLibrarianMessage();
        verify(mockOutput).println("Contact librarian");
    }

    @Test(timeout = 100)
    public void shouldExitApplicationWhenMenuOptionIsZero() {
        when(mockInput.nextInt()).thenReturn(0);

        main.run();
    }
}
