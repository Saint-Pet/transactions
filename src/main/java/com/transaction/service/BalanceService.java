package com.transaction.service;

import com.transaction.dto.BalanceDTO;
import com.transaction.model.Balance;
import com.transaction.model.Bank;
import com.transaction.model.Currency;
import com.transaction.repository.BalanceRepository;
import com.transaction.repository.BankRepository;
import com.transaction.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Transactional
    public Optional<Balance> updateBalance(BalanceDTO balanceDTO) {
        Integer userId = balanceDTO.getUserId();
        Optional<Bank> bank = bankRepository.findById(balanceDTO.getBankId());
        Optional<Currency> currency = currencyRepository.findById(balanceDTO.getCurrencyCode());

        if (currency.isPresent() && bank.isPresent()) {

            Optional<Balance> optionalBalance = balanceRepository.findByCurrencyAndBankAndUserId(currency.get(), bank.get(), userId);
            if (optionalBalance.isEmpty()) {
                throw new IllegalArgumentException("Balance not found for user_id: " + userId + ", bank_id: " + balanceDTO.getBankId() + ", currency_code: " + balanceDTO.getCurrencyCode());
            }

            Balance balance = optionalBalance.get();
            if (balanceDTO.getTransactionType() == 1) {
                balance.setAmount(balance.getAmount().add(balanceDTO.getValue()));
            } else if (balanceDTO.getTransactionType() == 2) {
                if (balance.getAmount().compareTo(balanceDTO.getValue()) < 0) {
                    throw new IllegalArgumentException("Insufficient funds");
                }
                balance.setAmount(balance.getAmount().subtract(balanceDTO.getValue()));
            }

            balance.setLast_updated(balanceDTO.getTime());
            balanceRepository.save(balance);
            return Optional.of(balance);
        } else {
            throw new IllegalArgumentException("Currency or Bank not found");
        }
    }
}
