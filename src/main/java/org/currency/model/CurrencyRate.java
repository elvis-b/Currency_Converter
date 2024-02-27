package org.currency.model;

public class CurrencyRate {
    private String name;
    private double rate;

    public CurrencyRate(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
