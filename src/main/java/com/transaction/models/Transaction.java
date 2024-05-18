package com.transaction.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "currency_code")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    private Integer user_id;
    private BigDecimal amount;
    private LocalDateTime transaction_time;
    private String description;

    // Getters
    public Integer getTransaction_id(){
        return transaction_id;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public LocalDateTime getTransaction_time() {
        return transaction_time;
    }
    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }
    public Bank getBank() {
        return bank;
    }
    public Currency getCurrency() {
        return currency;
    }
    public Category getCategory() {
        return category;
    }
    public Status getStatus() {
        return status;
    }

    //Setters
    public void setUser_id(Integer userId) {
        this.user_id = userId;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setTransaction_time(LocalDateTime now) {
        this.transaction_time = now;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + transaction_id +
                ", user_id=" + user_id +
                ", bank=" + bank.getName() +
                ", amount=" + amount +
                ", category='" + category.getCategoryName() + '\'' +
                ", status='" + status.getStatus_name() + '\'' +
                ", transaction_time=" + transaction_time + '\''+
                ", description='" + description + '\'' +
                ", currency_code='" + currency.getCurrencyName() + '\'' +
                '}';
    }
}
