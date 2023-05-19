package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ccy", "base_ccy", "buy", "sale"})
public class Currency {
    private String ccy;
    @JsonProperty("base_ccy")
    private String baseCcy;
    private float buy;
    private float sale;


    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBase_ccy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + baseCcy + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
