package com.twu28.biblioteca;

public class Book {
    private String bookName;
    private int isbn;
    private boolean isReserved;

    public Book(int isbn, String bookName) {
        this.isbn = isbn;
        this.bookName = bookName;
    }

    public void reserve() {
        this.isReserved = true;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public String toString() {
        return isbn + ":" + bookName;
    }

    boolean isSameIsbn(int isbn) {
        return this.isbn == isbn;
    }
}
