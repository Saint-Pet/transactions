package com.transaction.service;

import com.transaction.model.Transaction;
import com.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Transaction createTransaction(Integer user_id, BigDecimal amount, Integer type_id,
                                         Integer category_id, Integer status_id,
                                         String description, String currency_code) {
        // Валидация транзакции
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Transaction transaction = new Transaction();
        transaction.setUser_id(user_id);
        transaction.setAmount(amount);
        transaction.setType_id(type_id);
        transaction.setCategory_id(category_id);
        transaction.setStatus_id(status_id);
        transaction.setDescription(description);
        transaction.setCurrency_code(currency_code);

        Transaction savedTransaction = transactionRepository.save(transaction);

        kafkaTemplate.send("transaction-topic", savedTransaction.toString());

        return savedTransaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}