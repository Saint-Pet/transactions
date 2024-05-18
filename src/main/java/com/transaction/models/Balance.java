package com.transaction.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "balances")
public class Balance {

    @Id
    private Integer balance_id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "currency_code")
    private Currency currency;

    @Column(name = "user_id")
    private Integer user_id;

    private BigDecimal amount;
    private LocalDateTime last_updated;

    //Setters
    public void setLast_updated(LocalDateTime time){
        this.last_updated = time;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    //Getters
    public Integer getBalance_id() {
        return balance_id;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    public Bank getBank() {
        return bank;
    }
    public Currency getCurrency() {
        return currency;
    }

}
