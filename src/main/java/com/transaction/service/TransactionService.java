package com.transaction.service;

import com.transaction.models.*;
import com.transaction.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionsRepository transactionRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private BalanceService balanceService;

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Transaction createTransaction(Integer user_id, BigDecimal amount, Integer type_id,
                                         Integer category_id, Integer status_id,
                                         String description, String currency_code, Integer bank_id) {
        // Валидация транзакции
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Transaction transaction = new Transaction();
        Optional<Type> type = typeRepository.findById(type_id);
        Optional<Bank> bank = bankRepository.findById(bank_id);
        Optional<Currency> currency = currencyRepository.findById(currency_code);
        Optional<Category> category = categoryRepository.findById(category_id);
        Optional<Status> status = statusRepository.findById(status_id);

        if (type.isPresent()) {
            transaction.setType(type.get());
        } else {
            throw new IllegalArgumentException("Invalid type_id");
        }
        if (bank.isPresent()) {
            transaction.setBank(bank.get());
        } else {
            throw new IllegalArgumentException("Invalid bank_id");
        }
        if (currency.isPresent()) {
            transaction.setCurrency(currency.get());
        } else {
            throw new IllegalArgumentException("Invalid currency_code");
        }
        if (category.isPresent()) {
            transaction.setCategory(category.get());
        } else {
            throw new IllegalArgumentException("Invalid category_id");
        }
        if (status.isPresent()) {
            transaction.setStatus(status.get());
        } else {
            throw new IllegalArgumentException("Invalid status_id");
        }


        transaction.setUser_id(user_id);
        transaction.setAmount(amount);
        transaction.setTransaction_time(LocalDateTime.now());
        transaction.setDescription(description);

        Transaction savedTransaction = transactionRepository.save(transaction);

        balanceService.changeBalance(user_id, type_id, amount, bank_id, currency_code, transaction.getTransaction_time());

//        kafkaTemplate.send("transaction-topic", savedTransaction.toString());

        return savedTransaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}