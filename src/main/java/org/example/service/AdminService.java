package org.example.service;

import org.example.model.Currency;
import org.example.model.CurrencyExchangeOperation;
import org.example.repository.ExchangeOperationRepository;
import org.example.repository.JdbcCurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return journalRepository.findAllOperation();
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

    public void updateCurrencyByCcy(Currency currency) {
        currency.setBase_ccy("UAH");
        currencyRepository.updateCurrency(currency);
    }

    public void addCurrency(Currency currency) {
        currency.setBase_ccy("UAH");
        currencyRepository.addCurrency(currency);
    }

    public void deleteCurrencyByCcy(String ccy) {
        currencyRepository.deleteCurrencyByCcy(ccy);
    }

}
