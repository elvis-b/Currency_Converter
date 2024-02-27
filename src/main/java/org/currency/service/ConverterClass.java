package org.currency.service;

import java.io.IOException;


public class ConverterClass {

    public double convertCurrency(double amount, double exchangeRate) {
        return amount / exchangeRate;
    }
}
