package org.currency;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FileHandler {
    private static final String FILE_PATH = "exchange_rates.json";

    public static JSONObject loadExchangeRatesFromFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FILE_PATH);
        return (JSONObject) parser.parse(reader);
    }

    public static void saveExchangeRatesToFile(JSONObject exchangeRates) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        writer.write(exchangeRates.toJSONString());
        writer.close();
    }

    public static double getExchangeRate(JSONObject exchangeRates, String currencyCode) {
        for (Object key : exchangeRates.keySet()) {
            String keyString = (String) key;
            if (keyString.equalsIgnoreCase(currencyCode)) {
                return  (double) exchangeRates.get(key);
            }
        }
        return -1;
    }

    public static void addCurrency(JSONObject exchangeRates, String currencyCode, double exchangeRate) throws IOException {
        exchangeRates.put(currencyCode, exchangeRate);
        saveExchangeRatesToFile(exchangeRates);
    }

    public static void modifyCurrency(JSONObject exchangeRates, String currencyCode, double newExchangeRate) throws IOException {
        if (exchangeRates.containsKey(currencyCode)) {
            exchangeRates.put(currencyCode, newExchangeRate);
            saveExchangeRatesToFile(exchangeRates);
        } else {
            throw new IllegalArgumentException("Currency code not found");
        }
    }

    public static List<String> showCurrenciesWithRates(JSONObject exchangeRates) {
        List<String> currenciesWithRates = new ArrayList<>();
        for (Object key : exchangeRates.keySet()) {
            String currencyCode = (String) key;
            double exchangeRate = (double) exchangeRates.get(currencyCode);
            currenciesWithRates.add(currencyCode + ": " + exchangeRate);
        }
        return currenciesWithRates;
    }



}
