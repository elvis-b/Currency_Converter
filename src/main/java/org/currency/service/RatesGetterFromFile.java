package org.currency.service;

import com.google.gson.*;
import org.currency.model.CurrencyRate;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RatesGetterFromFile implements RatesGetter {

    private final String filePath;

    public RatesGetterFromFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<CurrencyRate> loadExchangeRates() {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);

            List<CurrencyRate> exchangeRates = new ArrayList<>();

            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                            String currency = entry.getKey();
                            double rate = entry.getValue().getAsDouble();
                            exchangeRates.add(new CurrencyRate(currency, rate));
                        }
                    }
                }
            }

            return exchangeRates;
        } catch (IOException e) {
            System.out.println("Error loading exchange rates from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
