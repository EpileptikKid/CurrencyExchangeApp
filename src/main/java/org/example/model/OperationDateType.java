package org.example.model;

import java.util.Objects;

/*
    Спеціальний клас що використовується при групуванні CurrencyExchangeOperation об'єктів
    за певним терміном та операцією
 */

public class OperationDateType {
    private Object date;
    private String operation;

    public Object getDate() {
        return date;
    }

    public OperationDateType(Object date, String operation) {
        this.date = date;
        this.operation = operation;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationDateType that = (OperationDateType) o;
        return Objects.equals(date, that.date) && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, operation);
    }

    @Override
    public String toString() {
        return "OperationDateType{" +
                "date=" + date +
                ", operation='" + operation + '\'' +
                '}';
    }
}
