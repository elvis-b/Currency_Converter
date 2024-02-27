package org.currency.service;

import org.currency.model.CurrencyRate;

import java.io.IOException;
import java.util.List;

public interface RatesGetter {
    List<CurrencyRate> loadExchangeRates();
}
