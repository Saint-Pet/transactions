package com.transaction.repository;

import java.util.Optional;

import com.transaction.models.Balance;
import com.transaction.models.Bank;
import com.transaction.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByCurrencyAndBankAndUserId(Currency currency, Bank bank, Integer userId);
}
