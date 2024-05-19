package com.transaction.controller;

import com.transaction.model.Currency;
import com.transaction.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Currency> getCurrencyByCode(@PathVariable String code) {
        return currencyService.getCurrencyByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String code, @RequestBody Currency currencyDetails) {
        try {
            Currency updatedCurrency = currencyService.updateCurrency(code, currencyDetails);
            return ResponseEntity.ok(updatedCurrency);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String code) {
        try {
            currencyService.deleteCurrency(code);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exchange-rate")
    public ResponseEntity<Double> getExchangeRate(@RequestParam String fromCurrency, @RequestParam String toCurrency) {
        try {
            Double rate = currencyService.getExchangeRate(fromCurrency, toCurrency);
            return ResponseEntity.ok(rate);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/convert")
    public ResponseEntity<Double> convertCurrency(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam Double amount) {
        try {
            Double convertedAmount = currencyService.convertCurrency(fromCurrency, toCurrency, amount);
            return ResponseEntity.ok(convertedAmount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
