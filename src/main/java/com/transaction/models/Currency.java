package com.transaction.models;

import jakarta.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    private String currency_code;
    private String currency_name;
    private String symbol;

    // Getters and Setters
    public String getCurrency_code(){
        return currency_code;
    }
    public String getCurrency_name() {
        return currency_name;
    }
    public String getSymbol(){
        return symbol;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
