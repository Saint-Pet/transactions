package com.smartbudget.transactions.repository;

import com.smartbudget.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Integer userId);

    List<Transaction> findByTypeId(Integer typeId);

    List<Transaction> findByCategoryId(Integer categoryId);

    List<Transaction> findByStatusId(Integer statusId);

    List<Transaction> findByTransactionTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByAmountGreaterThan(BigDecimal amount);

    List<Transaction> findByAmountLessThan(BigDecimal amount);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.userId = :userId")
    BigDecimal findTotalAmountByUserId(Integer userId);

    List<Transaction> findByUserIdAndTransactionTimeBetween(Integer userId, LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByUserIdAndTypeIdAndCategoryIdAndStatusIdAndTransactionTimeBetweenAndAmountGreaterThanEqualAndAmountLessThanEqual(
            Integer userId, Integer typeId, Integer categoryId, Integer statusId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minAmount, BigDecimal maxAmount);
}
