package org.currency;

import org.currency.service.MainLogicClass;
import org.currency.service.ConverterClass;
import org.currency.model.CurrencyRate;
import org.currency.service.RatesGetter;
import org.currency.service.RatesGetterFromFile;
import org.currency.view.ConsoleUserInterface;
import org.currency.view.UserInterface;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "exchange_rates.json";

        RatesGetter ratesGetter = new RatesGetterFromFile(filePath);
        List<CurrencyRate> exchangeRates = ratesGetter.loadExchangeRates();
        UserInterface userInterface = new ConsoleUserInterface();
        ConverterClass converter = new ConverterClass();
        MainLogicClass mainLogic = new MainLogicClass(converter, userInterface, exchangeRates);

        mainLogic.startCurrencyConverter();
    }
}