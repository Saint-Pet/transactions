package com.smartbudget.transactions.model;

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
    private Integer userId;

    private BigDecimal amount;
    private LocalDateTime last_updated;

    //Setters
    public void setLast_updated(LocalDateTime time){
        this.last_updated = time;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setUserId(Integer userId) {  // Исправлено имя метода
        this.userId = userId;
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
    public Integer getUserId() {
        return userId;
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
