package org.currency.view;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {

    private final Scanner scanner;

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public String getByUser() {
        return scanner.nextLine();
    }
}
