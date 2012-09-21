package com.twu28.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
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

        List<Movie> moviesAvailable = new ArrayList<Movie>();
        moviesAvailable.add(new Movie("The Godfather", "Fransis", "9.1"));
        moviesAvailable.add(new Movie("Snatch", "Guy Ritchie", "8.4"));

        List<User> userList = new ArrayList<User>();
        userList.add(new User("1111111", "password1"));
        userList.add(new User("1111112", "password2"));
        userList.add(new User("1111113", "password3"));


        main = new Main(mockOutput, mockInput, booksAvailable, moviesAvailable, userList);
    }

    @Test
    public void shouldWelcomeUser() throws Exception {
        main.displayWelcomeMessage();

        verify(mockOutput).println("Welcome to the Library");
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
        when(mockInput.nextInt()).thenReturn(1, 5);
        main.reserveBook();
        verify(mockOutput).println("Select the isbn of the book to be reserved");
        verify(mockOutput).println("Book - 1:Alice in Wonderland is reserved. Enjoy the book");
        main.reserveBook();
        verify(mockOutput).println("Book - 5:Chambers of Secret is reserved. Enjoy the book");
    }

    @Test
    public void shouldSayBookIsNotAvailableIfBookIsAlreadyReserved() throws Exception {
        when(mockInput.nextInt()).thenReturn(1, 1);
        main.reserveBook();
        main.reserveBook();
        verify(mockOutput).println("Book - 1:Alice in Wonderland is not available.");
    }

    @Test(timeout = 100)
    public void shouldExitApplicationWhenMenuOptionIsZero() {
        when(mockInput.nextInt()).thenReturn(0);

        main.run();
    }

    @Test(timeout = 100)
    public void shouldReserveBookAndNotBeAbleToReserveAgain() throws Exception {
        when(mockInput.nextInt()).thenReturn(1, 1, 5, 2, 0);
        when(mockInput.next()).thenReturn("1111111", "password1");
        main.run();

        verify(mockOutput).println("5:Chambers of Secret");
        verify(mockOutput).println("Book - 5:Chambers of Secret is reserved. Enjoy the book");
        verify(mockOutput).println("Your UserID is-->1111111");
    }

    @Test
    public void shouldDisplayBookIsNotAvailableWhenBookOfIsbnNotFound() throws Exception {
        main.reserveBook();
        verify(mockOutput).println("Sorry! requested book is not available");
    }

    @Test
    public void shouldBeAbleToViewMovieListInMenuOption() {
        main.printMenuOptions();
        verify(mockOutput).println("3. View all movies");
        verify(mockOutput).println("2. Library Number");
        verify(mockOutput).println("3. View all movies");
        verify(mockOutput).println("0. Exit");
    }

    @Test
    public void shouldBeAbleToViewAllMovies() {
        main.printMovies();
        verify(mockOutput).println("Snatch              Guy Ritchie                8.4");
        verify(mockOutput).println("The Godfather       Fransis                    9.1");
    }

    @Test(timeout = 100)
    public void shouldDisplayMoviesWhenUserChoiceIs3() {
        when(mockInput.nextInt()).thenReturn(2, 3, 0);
        main.run();
        verify(mockOutput).println("Snatch              Guy Ritchie                8.4");
    }

    @Test
    public void shouldDisplayLoginOptions() {
        main.run();
        verify(mockOutput).println("Select your login options");
        verify(mockOutput).println("1: User");
        verify(mockOutput).println("2: Guest");
    }

    @Test
    public void testForValidateUser() {
        assertTrue(main.validateUser("1111111", "password1"));
    }

    @Test(timeout = 100)
    public void shouldDisplayLoggedInAsUserForAnUser() {
        when(mockInput.nextInt()).thenReturn(1, 0);
        when(mockInput.next()).thenReturn("1111111", "password1");
        main.run();
        verify(mockOutput).println("Select your login options");
        verify(mockOutput).println("1: User");
        verify(mockOutput).println("2: Guest");
        verify(mockOutput).println("Enter UserID:");
        verify(mockOutput).println("Enter password:");
        verify(mockOutput).println("You are logged with userID-->1111111");
    }

    @Test(timeout = 100)
    public void shouldDisplayLoggedInAsGuestForGuest() {
        when(mockInput.nextInt()).thenReturn(2, 0);
        main.run();
        verify(mockOutput).println("Select your login options");
        verify(mockOutput).println("1: User");
        verify(mockOutput).println("2: Guest");
        verify(mockOutput).println("You are entering Library as guest");
    }

    @Test(timeout = 100)
    public void shouldDisplayErrorMessageIfValidationIsUnsuccessful() {
        when(mockInput.nextInt()).thenReturn(1, 1, 0);
        when(mockInput.next()).thenReturn("1111112", "password1");
        main.run();
        verify(mockOutput, times(2)).println("Incorrect UserName or password. Please try again.!");
        verify(mockOutput, times(3)).println("Select your login options");
    }

    @Test(timeout = 100)
    public void testDisplayLibrarianMessageForUser() {
        when(mockInput.nextInt()).thenReturn(1, 2, 0);
        when(mockInput.next()).thenReturn("1111111", "password1");
        main.run();
        verify(mockOutput).println("Your UserID is-->1111111");

    }

    @Test(timeout = 100)
    public void testDisplayLibrarianMessageForGuest() {
        when(mockInput.nextInt()).thenReturn(2, 2, 0);
        main.run();
        verify(mockOutput).println("Contact librarian");
    }


}
