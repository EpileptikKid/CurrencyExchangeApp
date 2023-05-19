package org.example.repository;

import org.example.model.CurrencyExchangeOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ExchangeOperationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExchangeOperationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CurrencyExchangeOperation> findAllOperation() {

        return jdbcTemplate.query(
                ExchangeOperationQueries.FIND_ALL_OPERATIONS,
                new BeanPropertyRowMapper<>(CurrencyExchangeOperation.class));
    }

    public void addExchangeOperation(CurrencyExchangeOperation operation) {
        try {
            jdbcTemplate.update(ExchangeOperationQueries.ADD_OPERATION,
                    operation.getDate(), operation.getCurrency(), operation.getRate(), operation.getOperation(), operation.getAmount());
        } catch (DataIntegrityViolationException e) {
            System.out.println("Parameter exception");
        }
    }

    public void deleteExchangeOperationById(int id) {
        jdbcTemplate.update(ExchangeOperationQueries.SET_OPERATION_STATUS_FALSE_BY_ID, id);
    }

    public void restoreExchangeOperationById(int id) {
        jdbcTemplate.update(ExchangeOperationQueries.SET_OPERATION_STATUS_TRUE_BY_ID, id);
    }
}
