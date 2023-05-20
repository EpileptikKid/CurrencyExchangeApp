package org.example.service;

import org.example.model.Currency;
import org.example.model.CurrencyExchangeOperation;
import org.example.repository.ExchangeOperationRepository;
import org.example.repository.JdbcCurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class AdminService {

    private final JdbcCurrencyRateRepository currencyRepository;
    private final ExchangeOperationRepository journalRepository;

    @Autowired
    public AdminService(JdbcCurrencyRateRepository currencyRepository, ExchangeOperationRepository journalRepository) {
        this.currencyRepository = currencyRepository;
        this.journalRepository = journalRepository;
    }

    public void deleteExchangeOperationById(int id) {
        journalRepository.deleteExchangeOperationById(id);
    }

    public List<CurrencyExchangeOperation> getAllOperations() {
        List<CurrencyExchangeOperation> operations = journalRepository.findAllOperation();
        Collections.sort(operations);
        return operations;
    }

    public void restoreExchangeOperationById(int id) {
        journalRepository.restoreExchangeOperationById(id);
    }

    public List<Currency> getAllCurrencies() {
       return currencyRepository.findAllCurrencies();
    }

    public Currency getCurrencyByCcy(String ccy) {
        return currencyRepository.getCurrencyByCcy(ccy);
    }

    public void updateCurrencyByCcy(String ccy, float buy, float sale) {
        Currency currency = new Currency();
        currency.setCcy(ccy);
        currency.setBase_ccy("UAH");
        currency.setBuy(buy);
        currency.setSale(sale);
        currencyRepository.updateCurrency(currency);
    }

    public void addCurrency(String ccy, float buy, float sale) {
        Currency currency = new Currency();
        currency.setCcy(ccy);
        currency.setBase_ccy("UAH");
        currency.setBuy(buy);
        currency.setSale(sale);
        currencyRepository.addCurrency(currency);
    }

    public void deleteCurrencyByCcy(String ccy) {
        currencyRepository.deleteCurrencyByCcy(ccy);
    }

}
