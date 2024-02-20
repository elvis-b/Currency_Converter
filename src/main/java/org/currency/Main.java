package org.currency;

import org.currency.controller.MainLogicClass;
import org.currency.model.DataRetriever;
import org.currency.model.RatesGetter;
import org.currency.view.ConsoleUserInterface;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String filePath = "exchange_rates.json";

            DataRetriever dataRetriever = new RatesGetter(filePath);
            ConsoleUserInterface userInterface = new ConsoleUserInterface(scanner);
            MainLogicClass mainLogic = new MainLogicClass(dataRetriever, userInterface);

            mainLogic.startCurrencyConverter();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}