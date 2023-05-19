package org.example.repository;

/*
 * Клас має модифікатор доступу default тому що використовується лише в межах даного пакету
 */
class ExchangeOperationQueries {
    static final String FIND_ALL_OPERATIONS = "SELECT * FROM exchange_log";
    static final String ADD_OPERATION =
            "INSERT INTO exchange_log (date, currency, rate, operation, amount) VALUES (?, ?, ?, ?, ?)";
    static final String SET_OPERATION_STATUS_FALSE_BY_ID = "UPDATE exchange_log SET status = false WHERE id = ?";
    static final String SET_OPERATION_STATUS_TRUE_BY_ID = "UPDATE exchange_log SET status = true WHERE id = ?";
}
