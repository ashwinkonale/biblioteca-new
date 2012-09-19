package com.twu28.biblioteca;

public class Book {
    private String bookName;
    private int index;
    private boolean isReserved;

    public Book(int index, String bookName) {
        this.index = index;
        this.bookName = bookName;
    }

    public void reserve() {
        this.isReserved = true;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public String toString() {
        return index + ":" + bookName;
    }
}
