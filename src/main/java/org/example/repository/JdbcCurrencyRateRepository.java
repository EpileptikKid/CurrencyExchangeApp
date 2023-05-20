package org.example.repository;

import org.example.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCurrencyRateRepository {

    private final DataSource dataSource;

    @Autowired
    public JdbcCurrencyRateRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Currency> findAllCurrencies() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     PreparedQueries.SELECT_ALL_CURRENCIES)) {

            ResultSet resultSet = statement.executeQuery();
            List<Currency> currencies = new ArrayList<>();

            while (resultSet.next()) {
                currencies.add(mapResultSetToCurrency(resultSet));
            }
            return currencies;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean addCurrency(Currency currency) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     PreparedQueries.ADD_CURRENCY)) {

            setCurrencyParameters(statement, currency);
            int rowsAdded = statement.executeUpdate();
            return rowsAdded > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateCurrency(Currency currency) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     PreparedQueries.UPDATE_CURRENCY)) {

            statement.setFloat(1, currency.getBuy());
            statement.setFloat(2, currency.getSale());
            statement.setString(3, currency.getCcy());
            statement.setString(4, currency.getBaseCcy());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public Currency getCurrencyByCcy(String ccy) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     PreparedQueries.SELECT_CURRENCY_BY_CCY)) {

            statement.setString(1, ccy);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? mapResultSetToCurrency(resultSet) : null;

        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteCurrencyByCcy(String ccy) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     PreparedQueries.DELETE_CURRENCY_BY_CCY_AND_BASE_CCY)) {

            statement.setString(1, ccy);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    private Currency mapResultSetToCurrency(ResultSet resultSet) throws SQLException {
        Currency currency = new Currency();
        currency.setCcy(resultSet.getString("ccy"));
        currency.setBase_ccy(resultSet.getString("base_ccy"));
        currency.setBuy(resultSet.getFloat("buy"));
        currency.setSale(resultSet.getFloat("sale"));
        return currency;
    }

    private void setCurrencyParameters(PreparedStatement statement, Currency currency) throws SQLException {
        statement.setString(1, currency.getCcy());
        statement.setString(2, currency.getBaseCcy());
        statement.setFloat(3, currency.getBuy());
        statement.setFloat(4, currency.getSale());
    }
}
