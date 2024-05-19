package com.smartbudget.transactions.model;

import jakarta.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @Column(name = "currency_code")
    private String code;

    @Column(name = "currency_name")
    private String currencyName;
    private String symbol;


    public String getCode() {
        return code;
    }

    public void setCode(String currencyCode) {
        this.code = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
