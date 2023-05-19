package org.example.service;

import javafx.util.Pair;
import org.example.model.CurrencyExchangeOperation;
import org.example.repository.ExchangeOperationRepository;
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

    private final ExchangeOperationRepository repository;

    @Autowired
    public ExchangeOperationService(ExchangeOperationRepository repository) {
        this.repository = repository;
    }

    public void addExchangeOperation(String currency, String operation, float rate, float amount) {
        CurrencyExchangeOperation exchangeOperation = new CurrencyExchangeOperation();
        exchangeOperation.setDate(LocalDateTime.now());
        exchangeOperation.setCurrency(currency);
        exchangeOperation.setRate(rate);
        exchangeOperation.setOperation(operation);
        exchangeOperation.setAmount(amount);
        repository.addExchangeOperation(exchangeOperation);
    }

    public List<CurrencyExchangeOperation> getAllOperations() {
        return repository.findAllOperation().stream().filter(CurrencyExchangeOperation::isStatus).collect(Collectors.toList());
    }

    public Map<Pair<Object, String>, Double> getAllOperationsGroupBy(String groupBy) {
        List<CurrencyExchangeOperation> operations = repository.findAllOperation();

        return operations.stream()
                .filter(CurrencyExchangeOperation::isStatus)
                .collect(Collectors.groupingBy(
                        operation -> new Pair<Object, String>(
                                getDate(groupBy, operation),
                                operation.getOperation()),
                        Collectors.summingDouble(CurrencyExchangeOperation::getAmount)
                ));
    }


    private Object getDate(String groupBy, CurrencyExchangeOperation operation) {
        switch (groupBy) {
            case "day":
                return operation.getDate().toLocalDate();
            case "month":
                return YearMonth.from(operation.getDate());
            default:
                return Year.from(operation.getDate());
        }
    }


}
