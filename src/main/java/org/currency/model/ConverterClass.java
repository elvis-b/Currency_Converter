package org.currency.model;

import java.io.IOException;

public class ConverterClass {

    private final ExchangeRates exchangeRates;

    public ConverterClass(DataRetriever dataRetriever) throws IOException {
        this.exchangeRates = dataRetriever.loadExchangeRates();
    }

    public double convertCurrency(double amount, String currencyCode) {
        double exchangeRate = exchangeRates.getExchangeRate(currencyCode);
        return amount / exchangeRate;
    }
}
