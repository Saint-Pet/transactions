package com.transaction.repository;

import com.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // дополнительные методы поиска при необходимости
}
