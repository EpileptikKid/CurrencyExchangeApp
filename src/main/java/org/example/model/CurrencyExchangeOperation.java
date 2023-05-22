package org.example.model;

import java.time.LocalDateTime;
import java.util.Objects;

/*
    Клас для представлення сутності запису в журналі операцій обміну валют
 */

public class CurrencyExchangeOperation {

    private long id;
    private LocalDateTime date;
    private String currency;
    private float rate;
    private String operation;
    private double amount;
    private boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyExchangeOperation that = (CurrencyExchangeOperation) o;
        return Float.compare(that.rate, rate) == 0 && Double.compare(that.amount, amount) == 0 && status == that.status && Objects.equals(date, that.date) && Objects.equals(currency, that.currency) && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, currency, rate, operation, amount, status);
    }

    @Override
    public String toString() {
        return "CurrencyExchangeOperation{" +
                "id=" + id +
                ", date=" + date +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                ", operation='" + operation + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
