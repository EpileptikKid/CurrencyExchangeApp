package org.example.repository;

/*
* Клас має модифікатор доступу default тому що використовується лише в межах даного пакету
*/
class PreparedQueries {

    static final String SELECT_ALL_CURRENCIES = "SELECT * FROM currency";

    static final String UPDATE_CURRENCY =
            "UPDATE currency SET buy = ?, sale = ? WHERE ccy = ? AND base_ccy = ?";

    static final String SELECT_CURRENCY_BY_CCY = "SELECT * FROM currency WHERE ccy = ?";

    static final String DELETE_CURRENCY_BY_CCY_AND_BASE_CCY =
            "DELETE FROM currency WHERE ccy = ?";

    static final String ADD_CURRENCY = "INSERT INTO currency (ccy, base_ccy, buy, sale) VALUES (?, ?, ?, ?)";

    static final String FIND_ALL_OPERATIONS = "SELECT * FROM exchange_log";

    static final String ADD_OPERATION =
            "INSERT INTO exchange_log (date, currency, rate, operation, amount) VALUES (?, ?, ?, ?, ?)";

    static final String SET_OPERATION_STATUS_FALSE_BY_ID = "UPDATE exchange_log SET status = false WHERE id = ?";

    static final String SET_OPERATION_STATUS_TRUE_BY_ID = "UPDATE exchange_log SET status = true WHERE id = ?";
}
