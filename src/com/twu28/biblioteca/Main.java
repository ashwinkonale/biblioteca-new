package com.twu28.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    List<Book> books = new ArrayList<Book>();

    private final PrintStream output;

    public Main(PrintStream output, List<Book> booksAvailable) {
        this.output = output;
        this.books = booksAvailable;
    }

    public void run() {
        Scanner reader = new Scanner(System.in);
        int userChoice = reader.nextInt();
        while (userChoice != 0) {
            displayWelcomeMessage();
        }
    }

    public void displayWelcomeMessage() {
        output.println("Welcome User");
    }

    public static void main(String[] args) {
        new Main(System.out, null).run();
    }

    public void printMenuOptions() {
        output.println("1. View All Books");
        output.println("2. Library Number");
        output.println("0. Exit");
    }

    public void printBooks() {
        for (Book book : books) {
            output.println(book.toString());
        }
    }

    public void reserveBook(String indexNumber) {
        int index = Integer.parseInt(indexNumber);
        Book requestedBook = books.get(--index);
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

    public void exit() {
        System.exit(0);

    }
}
