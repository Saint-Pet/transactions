package com.transaction.service;

import com.transaction.dto.TransactionDTO;
import com.transaction.model.*;
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
    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @Transactional
    public Optional<Transaction> updateTransaction(Long transactionId, Transaction transaction) {
        Optional<Type> type = typeRepository.findById(transaction.getType().getId());
        Optional<Category> category = categoryRepository.findById(transaction.getCategory().getId());
        Optional<Status> status = statusRepository.findById(transaction.getStatus().getId());
        Optional<Bank> bank = bankRepository.findById(transaction.getBank().getId());
        Optional<Currency> currency = currencyRepository.findById(transaction.getCurrency().getCode());

        if (type.isEmpty() || category.isEmpty() || status.isEmpty() || bank.isEmpty() || currency.isEmpty()) {
            throw new IllegalArgumentException("One or more fields are invalid");
        }

        Optional<Transaction> updatedTransaction = transactionRepository.findById(transactionId);
        if (updatedTransaction.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found");
        }
        updatedTransaction.get().setType(type.get());
        updatedTransaction.get().setBank(bank.get());
        updatedTransaction.get().setCategory(category.get());
        updatedTransaction.get().setStatus(status.get());
        updatedTransaction.get().setCurrency(currency.get());
        updatedTransaction.get().setUserId(transaction.getUserId());
        updatedTransaction.get().setTransactionTime(LocalDateTime.now());
        updatedTransaction.get().setAmount(transaction.getAmount());
        updatedTransaction.get().setDescription(transaction.getDescription());
        return updatedTransaction;
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public BigDecimal convertAmount(Transaction transaction, String toCurrencyCode) {
        String fromCurrencyCode = transaction.getCurrency().getCode();
        Double convertedAmount = currencyService.convertCurrency(fromCurrencyCode, toCurrencyCode, transaction.getAmount().doubleValue());
        return BigDecimal.valueOf(convertedAmount);
    }

    public String getTransactionAmountInCurrency(Long transactionId, String toCurrencyCode) {
        return transactionRepository.findById(transactionId)
                .map(transaction -> {
                    BigDecimal convertedAmount = convertAmount(transaction, toCurrencyCode);
                    return String.format("Transaction amount in %s: %s", toCurrencyCode, convertedAmount);
                })
                .orElse("Transaction not found");
    }

    public List<Transaction> getTransactionsByUserId(Integer userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getTransactionsByTypeId(Integer typeId) {
        return transactionRepository.findByTypeId(typeId);
    }

    public List<Transaction> getTransactionsByCategoryId(Integer categoryId) {
        return transactionRepository.findByCategoryId(categoryId);
    }

    public List<Transaction> getTransactionsByStatusId(Integer statusId) {
        return transactionRepository.findByStatusId(statusId);
    }

    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByTransactionTimeBetween(startDate, endDate);
    }

    public List<Transaction> getTransactionsByAmountGreaterThan(BigDecimal amount) {
        return transactionRepository.findByAmountGreaterThan(amount);
    }

    public List<Transaction> getTransactionsByAmountLessThan(BigDecimal amount) {
        return transactionRepository.findByAmountLessThan(amount);
    }

    public List<Transaction> getTransactionsByUserIdAndDateRange(Integer userId, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByUserIdAndTransactionTimeBetween(userId, startDate, endDate);
    }

    public List<Transaction> getFilteredTransactions(Integer userId, Integer typeId, Integer categoryId, Integer statusId,
                                                     LocalDateTime startDate, LocalDateTime endDate, BigDecimal minAmount, BigDecimal maxAmount) {
        return transactionRepository.findByUserIdAndTypeIdAndCategoryIdAndStatusIdAndTransactionTimeBetweenAndAmountGreaterThanEqualAndAmountLessThanEqual(
                userId, typeId, categoryId, statusId, startDate, endDate, minAmount, maxAmount);
    }
}
