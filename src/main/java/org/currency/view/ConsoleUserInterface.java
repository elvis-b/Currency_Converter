package org.currency.view;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {

    private final Scanner scanner;

    public ConsoleUserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showToUser(String message) {
        System.out.print(message);
    }

    public String getByUser() {
        return scanner.nextLine();
    }
}
