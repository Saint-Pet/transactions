package com.transaction.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;
    private Integer user_id;
    private BigDecimal amount;
    private Integer type_id;
    private Integer category_id;
    private Integer status_id;
    private LocalDateTime transaction_time;
    private String description;
    private String currency_code;
    private Integer bank_id;

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
    public int getType_id() {
        return type_id;
    }
    public Integer getCategory() {
        return category_id;
    }
    public int getStatus_id() {
        return status_id;
    }
    public LocalDateTime getTransaction_time() {
        return transaction_time;
    }
    public String getDescription() {
        return description;
    }
    public String getCurrency_code() {
        return currency_code;
    }
    public Integer getBank_id(){
        return bank_id;
    }

    //Setters
    public void setUser_id(Integer userId) {
        this.user_id = userId;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setType_id(Integer typeId) {
        this.type_id = typeId;
    }
    public void setCategory_id(Integer category) {
        this.category_id = category;
    }
    public void setStatus_id(Integer statusId) {
        this.status_id = statusId;
    }
    public void setTransaction_time(LocalDateTime now) {
        this.transaction_time = now;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCurrency_code(String currencyCode) {
        this.currency_code = currencyCode;
    }
    public void setBank_id(Integer bank_id){
        this.bank_id=bank_id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + transaction_id +
                ", user_id=" + user_id +
                ", amount=" + amount +
                ", category='" + category_id + '\'' +
                ", status='" + status_id + '\'' +
                ", transaction_time=" + transaction_time + '\''+
                ", description='" + description + '\'' +
                ", currency_code='" + currency_code + '\'' +
                '}';
    }
}
