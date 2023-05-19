package org.example.repository;

import org.example.model.Currency;
import org.example.model.CurrencyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcCurrencyRateRepository {

    private final DataSource dataSource;

    @Autowired
    public JdbcCurrencyRateRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CurrencyList findAllCurrencies() {
        CurrencyList currencies = new CurrencyList();
        try (PreparedStatement preparedStatement =
                dataSource.getConnection().prepareStatement(
                        CurrencyQueries.SELECT_ALL_CURRENCIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                currencies.addCurrency(mapResultSetToCurrency(resultSet));
            }
            return currencies;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean addCurrency(Currency currency) {
        try (PreparedStatement statement =
                dataSource.getConnection().prepareStatement(
                        CurrencyQueries.ADD_CURRENCY)) {

            setCurrencyParameters(statement, currency);
            int rowsAdded = statement.executeUpdate();
            return rowsAdded > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateCurrency(Currency currency) {
        try (PreparedStatement statement =
                dataSource.getConnection().prepareStatement(
                        CurrencyQueries.UPDATE_CURRENCY)) {

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
        try (PreparedStatement preparedStatement =
                dataSource.getConnection().prepareStatement(
                        CurrencyQueries.SELECT_CURRENCY_BY_CCY
                )) {

            preparedStatement.setString(1, ccy);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? mapResultSetToCurrency(resultSet) : null;

        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteCurrencyByCcy(String ccy) {
        try (PreparedStatement preparedStatement =
                dataSource.getConnection().prepareStatement(
                        CurrencyQueries.DELETE_CURRENCY_BY_CCY_AND_BASE_CCY
                )) {

            preparedStatement.setString(1, ccy);
            int rowsDeleted = preparedStatement.executeUpdate();

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
