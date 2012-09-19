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
        booksAvailable.add(new Book(2, "Da Vinci Code"));
        booksAvailable.add(new Book(1, "Alice in Wonderland"));
        booksAvailable.add(new Book(3, "Angels and Demons"));
        booksAvailable.add(new Book(5, "Chambers of Secret"));

//        List<Movie> moviesAvailable =new ArrayList<Movie>();
//        moviesAvailable.add(new Movie("The Godfather","Fransis","9.1"));
//        moviesAvailable.add(new Movie("Snatch","Guy Ritchie","8.4"));
//

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
        main.reserveBook(1);
        verify(mockOutput).println("Book - 1:Alice in Wonderland is reserved. Enjoy the book");
        main.reserveBook(5);
        verify(mockOutput).println("Book - 5:Chambers of Secret is reserved. Enjoy the book");
    }

    @Test
    public void shouldSayBookIsNotAvailableIfBookIsAlreadyReserved() throws Exception {
        main.reserveBook(1);
        main.reserveBook(1);
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

    @Test(timeout = 100)
    public void shouldReserveBookAndNotBeAbleToReserveAgain() throws Exception {
        when(mockInput.nextInt()).thenReturn(1, 5, 2, 0);

        main.run();

        verify(mockOutput).println("5:Chambers of Secret");
        verify(mockOutput).println("Book - 5:Chambers of Secret is reserved. Enjoy the book");
        verify(mockOutput).println("Contact librarian");
    }

    @Test
    public void shouldDisplayBookIsNotAvailableWhenBookOfIsbnNotFound() throws Exception {
        main.reserveBook(4);
        verify(mockOutput).println("Sorry! requested book is not available");
    }
}
