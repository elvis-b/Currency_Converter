package org.currency.model;

import java.io.IOException;

public interface DataRetriever {
    ExchangeRates loadExchangeRates() throws IOException;
}
