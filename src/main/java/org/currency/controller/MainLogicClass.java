package org.currency.controller;

import org.currency.model.ConverterClass;
import org.currency.model.DataRetriever;
import org.currency.view.ConsoleUserInterface;
import org.currency.view.UserInterface;
import java.io.IOException;

public class MainLogicClass {

    private final ConverterClass converter;
    private final UserInterface userInterface;

    public MainLogicClass(DataRetriever dataRetriever, ConsoleUserInterface userInterface) throws IOException {
        this.converter = new ConverterClass(dataRetriever);
        this.userInterface = userInterface;
    }

    public void startCurrencyConverter() {
        try {
            userInterface.print("Enter the amount to exchange: ");
            double amount = Double.parseDouble(userInterface.getByUser());

            userInterface.print("Enter the code of the currency to exchange to (e.g., EUR): ");
            String currencyCode = userInterface.getByUser();

            double exchangedAmount = converter.convertCurrency(amount, currencyCode);
            userInterface.print(String.format("You can buy %.2f %s for %.2f RUB\n", exchangedAmount, currencyCode, amount));
        } catch (Exception e) {
            userInterface.print("An error occurred while reading exchange rates: " + e.getMessage());
        }
    }
}
