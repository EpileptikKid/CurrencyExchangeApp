package org.example.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/*
    Спеціальний клас-обгортка для кращого представлення об'єктів Currency в форматі xml
 */

@JacksonXmlRootElement(localName = "exchange")
public class CurrencyList {
    @JacksonXmlProperty(localName = "currency")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Currency> currencies = new ArrayList<>();

    public CurrencyList(List<Currency> currencyList) {
        currencies.addAll(currencyList);
    }

    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
