package com.transaction.repository;

import java.util.List;
import java.util.Optional;

import com.transaction.model.Balance;
import com.transaction.model.Bank;
import com.transaction.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByCurrencyAndBankAndUserId(Currency currency, Bank bank, Integer userId);
    List<Balance> findAllByCurrency(Currency currency);
    List<Balance> findAllByUserId(Integer userId);
    List<Balance> findAllByBank(Bank bank);
    List<Balance> findById(Integer id);
}
