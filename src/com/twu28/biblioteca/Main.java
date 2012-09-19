package com.twu28.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    List<Book> books = new ArrayList<Book>();
    List<Movie> movies = new ArrayList<Movie>();

    private final PrintStream output;
    private final Input input;


    public Main(PrintStream output, Input input, List<Book> booksAvailable, List<Movie> moviesAvailable) {
        this.output = output;
        this.books = booksAvailable;
        this.input = input;
        this.movies=moviesAvailable;
    }

    public void run() {
        displayWelcomeMessage();
        printMenuOptions();

        int userChoice = input.nextInt();
        while (userChoice != 0) {
            if (userChoice == 1) {
                printBooks();
                output.println("Select the isbn of the book to be reserved");
                int bookToReserve = input.nextInt();
                reserveBook(bookToReserve);
            } else if (userChoice == 2)
                displayLibrarianMessage();
            else if(userChoice == 3)
                printMovies();
            printMenuOptions();
            userChoice = input.nextInt();
        }
    }

    public void displayWelcomeMessage() {
        output.println("Welcome User");
    }

    public static void main(String[] args) {
        List<Book> availableBooks = new ArrayList<Book>();
        List<Movie>availableMovies = new ArrayList<Movie>();
        availableBooks.add(new Book(1, "Deathly Hallows"));
        availableBooks.add(new Book(2, "Chamber of Secret"));

        availableMovies.add(new Movie("Inception","Christopher Nolan","9.5"));
        availableMovies.add(new Movie("Expendables","S Stallon","7.0"));
        availableMovies.add(new Movie("Lincoln","Steven","N/A"));

        new Main(System.out, new Input(), availableBooks, availableMovies).run();
    }

    public void printMenuOptions() {
        output.println("1. View All Books");
        output.println("2. Library Number");
        output.println("3. View all movies");
        output.println("0. Exit");
    }

    public void printBooks() {
        for (Book book : books) {
            output.println(book.toString());
        }
    }

    public void reserveBook(int isbn) {

        Book requestedBook = null;
        for (Book book : books) {
            if (book.isSameIsbn(isbn)) {
                requestedBook = book;
            }
        }

        if(requestedBook == null) {
            output.println("Sorry! requested book is not available");
            return;
        }

        if (requestedBook.isReserved()) {
            output.println("Book - " + requestedBook.toString() + " is not available.");
        } else {
            requestedBook.reserve();
            output.println("Book - " + requestedBook.toString() + " is reserved. Enjoy the book");
        }
    }

    public void displayLibrarianMessage() {
        output.println("Contact librarian");
    }

    public void printMovies() {
        String pattern="%-20s%-20s%10s\n";
        output.printf(pattern,"Movie","Director","rating");
        for(Movie movie:movies){
            output.println(movie.display());
        }
    }
}
