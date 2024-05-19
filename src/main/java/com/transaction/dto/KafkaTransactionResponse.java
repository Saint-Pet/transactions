package com.transaction.dto;

import com.transaction.model.Transaction;
import java.util.List;

public class KafkaTransactionResponse {
    private Integer userId;
    private List<Transaction> transactions;

    public KafkaTransactionResponse(Integer userId, List<Transaction> transactions) {
        this.userId = userId;
        this.transactions = transactions;
    }

    // Getters and setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "KafkaTransactionResponse{" +
                "userId=" + userId +
                ", transactions=" + transactions +
                '}';
    }
}
