package org.currency.model;

public class ExchangeRates {

    private final double rubToUsdRate;
    private final double rubToEurRate;

    public ExchangeRates(double rubToUsdRate, double rubToEurRate) {
        this.rubToUsdRate = rubToUsdRate;
        this.rubToEurRate = rubToEurRate;
    }

    public double getExchangeRate(String currencyCode) {
        return switch (currencyCode.toUpperCase()) {
            case "USD" -> rubToUsdRate;
            case "EUR" -> rubToEurRate;
            default -> throw new IllegalArgumentException("Unsupported currency code: " + currencyCode);
        };
    }
}
