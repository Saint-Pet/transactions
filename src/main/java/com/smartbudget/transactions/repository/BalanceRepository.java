package com.smartbudget.transactions.repository;

import java.util.List;
import java.util.Optional;

import com.smartbudget.transactions.model.Balance;
import com.smartbudget.transactions.model.Bank;
import com.smartbudget.transactions.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByCurrencyAndBankAndUserId(Currency currency, Bank bank, Integer userId);
    List<Balance> findAllByCurrency(Currency currency);
    List<Balance> findAllByUserId(Integer userId);
    List<Balance> findAllByBank(Bank bank);
    List<Balance> findByUserId(Integer id);
}
