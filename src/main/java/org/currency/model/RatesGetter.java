package org.currency.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class RatesGetter implements DataRetriever {

    private final String filePath;

    public RatesGetter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ExchangeRates loadExchangeRates() throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(reader);

            double usdRate = 0.0;
            double eurRate = 0.0;

            for (Object obj : jsonArray) {
                JSONObject currencyObject = (JSONObject) obj;
                String currencyCode = (String) currencyObject.keySet().iterator().next();
                double rate = (double) currencyObject.get(currencyCode);

                switch (currencyCode.toLowerCase()) {
                    case "usd":
                        usdRate = rate;
                        break;
                    case "eur":
                        eurRate = rate;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }

            return new ExchangeRates(usdRate, eurRate);
        } catch (ParseException e) {
            throw new IOException("Error parsing exchange rates JSON: " + e.getMessage());
        }
    }
}
