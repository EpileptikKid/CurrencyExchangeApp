package org.example.service;

import org.example.model.Currency;
import org.example.repository.JdbcCurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestCurrencyService {
    private final JdbcCurrencyRateRepository repository;

    @Autowired
    public RestCurrencyService(JdbcCurrencyRateRepository repository) {
        this.repository = repository;
    }

    public List<Currency> findAllCurrencies() {
        return repository.findAllCurrencies();
    }

    public boolean addCurrency(Currency currency) {
        return repository.addCurrency(currency);
    }
    public boolean updateCurrency(Currency currency) {
        return repository.updateCurrency(currency);
    }

    public Currency getCurrencyByCcy(String ccy) {
        return repository.getCurrencyByCcy(ccy);
    }

    public boolean deleteCurrencyByCcy(String ccy) {
        return repository.deleteCurrencyByCcy(ccy);
    }

    public boolean isCurrencyExist(String ccy) {
        return repository.getCurrencyByCcy(ccy) != null;
    }
}
