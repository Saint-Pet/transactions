package com.transaction.service;

import com.transaction.models.Transaction;
import com.transaction.repository.TransactionRepository;
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

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        transaction.setTransactionTime(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction updateTransaction(Long transactionId, Transaction updatedTransaction) {
        return transactionRepository.findById(transactionId)
                .map(transaction -> {
                    transaction.setUserId(updatedTransaction.getUserId());
                    transaction.setAmount(updatedTransaction.getAmount());
                    transaction.setType(updatedTransaction.getType());
                    transaction.setCategory(updatedTransaction.getCategory());
                    transaction.setStatus(updatedTransaction.getStatus());
                    transaction.setDescription(updatedTransaction.getDescription());
                    transaction.setCurrency(updatedTransaction.getCurrency());
                    transaction.setBank(updatedTransaction.getBank());
                    transaction.setTransactionTime(LocalDateTime.now());
                    return transactionRepository.save(transaction);
                })
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
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
