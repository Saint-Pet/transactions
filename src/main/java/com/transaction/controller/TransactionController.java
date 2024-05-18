package com.transaction.controller;

import com.transaction.models.Transaction;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        try {
            Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
            return ResponseEntity.ok(updatedTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/convert")
    public ResponseEntity<String> getTransactionAmountInCurrency(@PathVariable Long id, @RequestParam String toCurrency) {
        String result = transactionService.getTransactionAmountInCurrency(id, toCurrency);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable Integer userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<Transaction>> getTransactionsByTypeId(@PathVariable Integer typeId) {
        List<Transaction> transactions = transactionService.getTransactionsByTypeId(typeId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Transaction>> getTransactionsByCategoryId(@PathVariable Integer categoryId) {
        List<Transaction> transactions = transactionService.getTransactionsByCategoryId(categoryId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<Transaction>> getTransactionsByStatusId(@PathVariable Integer statusId) {
        List<Transaction> transactions = transactionService.getTransactionsByStatusId(statusId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<Transaction> transactions = transactionService.getTransactionsByDateRange(startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/amount-greater-than")
    public ResponseEntity<List<Transaction>> getTransactionsByAmountGreaterThan(@RequestParam BigDecimal amount) {
        List<Transaction> transactions = transactionService.getTransactionsByAmountGreaterThan(amount);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/amount-less-than")
    public ResponseEntity<List<Transaction>> getTransactionsByAmountLessThan(@RequestParam BigDecimal amount) {
        List<Transaction> transactions = transactionService.getTransactionsByAmountLessThan(amount);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<Transaction>> getTransactionsByUserIdAndDateRange(@PathVariable Integer userId, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<Transaction> transactions = transactionService.getTransactionsByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Transaction>> getFilteredTransactions(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer typeId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer statusId,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount) {

        List<Transaction> transactions = transactionService.getFilteredTransactions(userId, typeId, categoryId, statusId, startDate, endDate, minAmount, maxAmount);
        return ResponseEntity.ok(transactions);
    }
}
