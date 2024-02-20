package org.currency;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class CurrencyConverter {

    public static void currencyConverter(double amount, String currencyCode) {
        try {
            JSONObject exchangeRates = FileHandler.loadExchangeRatesFromFile();
            if (exchangeRates == null) {
                System.out.println("Failed to load exchange rates.");
                return;
            }

            double exchangeRate = FileHandler.getExchangeRate(exchangeRates, currencyCode);
            if (exchangeRate == -1) {
                System.out.println("Currency code not found.");
                return;
            }

            double exchangedAmount = amount / exchangeRate;
            System.out.printf("You can buy %.2f %s for %.2f RUB\n", exchangedAmount, currencyCode, amount);

        } catch (IOException | ParseException | IllegalArgumentException e) {
            System.out.println("An error ocurred:" + e.getMessage());
        }
    }

}
