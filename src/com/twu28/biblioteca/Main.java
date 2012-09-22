package com.twu28.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    List<Book> books = new ArrayList<Book>();
    List<Movie> movies = new ArrayList<Movie>();

    private final PrintStream output;
    private final Input input;
    private List<User> users = new ArrayList<User>();
    private User currentUser;


    public Main(PrintStream output, Input input, List<Book> booksAvailable, List<Movie> moviesAvailable, List<User> userList) {
        this.output = output;
        this.books = booksAvailable;
        this.input = input;
        this.movies = moviesAvailable;
        this.users = userList;
    }

    public void run() {
        login();
        displayWelcomeMessage();
        printMenuOptions();
        performMenuSelection();
    }

    void performMenuSelection() {
        int userChoice = input.nextInt();
        while (userChoice != 0) {
            if (userChoice == 1) {
                printBooks();
                if (currentUser != null) {
                    reserveBook();
                } else {
                    output.println("Cannot reserve book without logging in.");
                }
            } else if (userChoice == 2)
                displayLibrarianMessage();
            else if (userChoice == 3)
                printMovies();
            printMenuOptions();
            userChoice = input.nextInt();
        }
    }

    public void displayWelcomeMessage() {
        output.println("Welcome to the Library");
    }

    public static void main(String[] args) {
        List<Book> availableBooks = new ArrayList<Book>();
        availableBooks.add(new Book(1, "Deathly Hallows"));
        availableBooks.add(new Book(2, "Chamber of Secret"));
        availableBooks.add(new Book(3, "the Kite Runner"));

        List<Movie> availableMovies = new ArrayList<Movie>();
        availableMovies.add(new Movie("Inception", "Christopher Nolan", "9.5"));
        availableMovies.add(new Movie("Expendables", "S Stallon", "7.0"));
        availableMovies.add(new Movie("Lincoln", "Steven", "N/A"));

        List<User> userList = new ArrayList<User>();
        userList.add(new User("1111111", "password1"));
        userList.add(new User("1111112", "password2"));
        userList.add(new User("1111113", "password3"));

        new Main(System.out, new Input(), availableBooks, availableMovies, userList).run();
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

    public void reserveBook() {
        output.println("Select the isbn of the book to be reserved");
        int isbn = input.nextInt();

        Book requestedBook = null;
        for (Book book : books) {
            if (book.isSameIsbn(isbn)) {
                requestedBook = book;
            }
        }

        if (requestedBook == null) {
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
        if (currentUser != null) {
            output.println("Your UserID is-->" + currentUser);
        } else {
            output.println("Contact librarian");
        }
    }

    public void printMovies() {
        String pattern = "%-20s%-20s%10s\n";
        output.printf(pattern, "Movie", "Director", "rating");
        for (Movie movie : movies) {
            output.println(movie.display());
        }
    }

    public void printLoginOptions() {
        output.println("Select your login options");
        output.println("1: User");
        output.println("2: Guest");
        output.println("0: Exit");
    }

    public boolean validateUser(String userId, String password) {
        User loggedInUser = new User(userId, password);
        for (User user : users) {
            if (loggedInUser.equals(user))
                return true;
        }
        return false;
    }

    public void login() {
        boolean continueLoop = true;
        while (continueLoop) {
            try {
                printLoginOptions();
                int loginType = input.nextInt();
                if (loginType == 1) {
                    if (userLogin()) break;
                } else if (loginType == 2) {
                    guestLogin();
                    break;
                } else if (loginType == 0) {
                    continueLoop = false;
                } else {
                    output.println("Select Valid Option");
                }
            } catch (Exception E) {
                output.println("enter valid option. !!");
                continueLoop = false;
            }
        }

    }

    private void guestLogin() {
        output.println("You are entering Library as guest");
    }

    private boolean userLogin() {
        output.println("Enter UserID:");
        String userId = input.next();
        output.println("Enter password:");
        String password = input.next();
        User loggedUser = new User(userId, password);

        if (validateUser(userId, password)) {
            output.println("You are logged with userID-->" + userId);
            this.currentUser = loggedUser;
            return true;
        }
        output.println("Incorrect UserName or password. Please try again.!");
        return false;
    }
}
