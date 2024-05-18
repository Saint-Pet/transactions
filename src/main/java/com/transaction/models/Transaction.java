package com.transaction.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private Integer id;

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

    private Integer userId;
    private BigDecimal amount;
    private LocalDateTime transactionTime;
    private String description;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user_id=" + userId +
                ", bank=" + bank.getName() +
                ", amount=" + amount +
                ", category='" + category.getCategoryName() + '\'' +
                ", status='" + status.getStatusName() + '\'' +
                ", transaction_time=" + transactionTime + '\''+
                ", description='" + description + '\'' +
                ", currency_code='" + currency.getCurrencyName() + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer transaction_id) {
        this.id = transaction_id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
