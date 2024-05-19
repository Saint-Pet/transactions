package com.smartbudget.transactions.repository;

import com.smartbudget.transactions.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {}
