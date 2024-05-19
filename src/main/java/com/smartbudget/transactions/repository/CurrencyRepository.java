package com.smartbudget.transactions.repository;

import com.smartbudget.transactions.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {}
