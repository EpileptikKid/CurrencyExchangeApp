package org.example.service;

import org.example.model.Currency;
import org.example.model.CurrencyExchangeOperation;
import org.example.repository.ExchangeOperationRepository;
import org.example.repository.JdbcCurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExchangeOperationService {

    private final ExchangeOperationRepository exchangeRepository;
    private final JdbcCurrencyRateRepository currencyRepository;

    @Autowired
    public ExchangeOperationService(ExchangeOperationRepository exchangeRepository,
                                    JdbcCurrencyRateRepository currencyRepository) {
        this.exchangeRepository = exchangeRepository;
        this.currencyRepository = currencyRepository;
    }

    public void addExchangeOperation(CurrencyExchangeOperation operation) {
        operation.setDate(LocalDateTime.now());
        exchangeRepository.addExchangeOperation(operation);
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAllCurrencies();
    }

    public List<CurrencyExchangeOperation> getAllOperations() {
        return exchangeRepository.findAllOperation().stream()
                .filter(CurrencyExchangeOperation::isStatus)
                .collect(Collectors.toList());
    }

    public Map<Object, Map<String, Double>> getAllOperationsGroupBy(String groupBy) {
        List<CurrencyExchangeOperation> operations = exchangeRepository.findAllOperation();

        return operations.stream()
                .filter(CurrencyExchangeOperation::isStatus)
                .collect(Collectors.groupingBy(
                        operation -> getDate(groupBy, operation.getDate()),
                        Collectors.groupingBy(CurrencyExchangeOperation::getOperation,
                                Collectors.summingDouble(CurrencyExchangeOperation::getAmount))
                ));
    }


    private Object getDate(String groupBy, LocalDateTime date) {
        switch (groupBy) {
            case "day":
                return date.toLocalDate();
            case "month":
                return YearMonth.from(date);
            case "quartet":
                return (date.getMonth().getValue() / 4) + 1
                        + "-й квартал " + date.getYear();
            default:
                return Year.from(date);
        }
    }


}
