package com.transaction.service;

import com.transaction.dto.BalanceDTO;
import com.transaction.model.Balance;
import com.transaction.model.Bank;
import com.transaction.model.Currency;
import com.transaction.repository.BalanceRepository;
import com.transaction.repository.BankRepository;
import com.transaction.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

    public List<Balance> getUserBalances(Integer id){
        return balanceRepository.findAllByUserId(id);
    }

    public Optional<Balance> getBalanceByUserIdAndBankIdAndCurrencyCode(Integer userId, Integer bankId, String currencyCode) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        Optional<Currency> currency = currencyRepository.findById(currencyCode);
        return balanceRepository.findByCurrencyAndBankAndUserId(currency.get(), bank.get(), userId);
    }

    public List<Balance> getAllBalances(){
        return balanceRepository.findAll();
    }

    public List<Balance> getBalancesByCurrencyCode(String currencyCode){
        Optional<Currency> currency = currencyRepository.findById(currencyCode);
        return balanceRepository.findAllByCurrency(currency.get());
    }
    public List<Balance> getBalancesByBankId(Integer bankId){
        Optional<Bank> bank = bankRepository.findById(bankId);
        return balanceRepository.findAllByBank(bank.get());
    }

    public void createNewBalance(Integer userId, Integer bankId, String currencyCode) {
        Bank bank = bankRepository.findById(bankId).get();
        Currency currency = currencyRepository.findById(currencyCode).get();
        Balance balance = new Balance();
        balance.setBank(bank);
        balance.setCurrency(currency);
        balance.setUserId(userId);
        balanceRepository.save(balance);
    }
    public void deleteBalance(Integer userId, Integer bankId, String currencyCode) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        Optional<Currency> currency = currencyRepository.findById(currencyCode);
        Optional<Balance> optionalBalance = balanceRepository.findByCurrencyAndBankAndUserId(currency.get(), bank.get(), userId);
        if (optionalBalance.isEmpty()) {
            throw new IllegalArgumentException("Balance not found for user_id: " + userId + ", bank_id: " + bankId + ", currency_code: " + currencyCode);
        }
        Balance balance = optionalBalance.get();
        balanceRepository.delete(balance);
    }

    public List<Balance> getBalanceById(Integer balanceId) {
        return balanceRepository.findById(balanceId);
    }
}
