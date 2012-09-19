package com.twu28.biblioteca;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public int nextInt() {
        return scanner.nextInt();
    }
}
