package com.transaction.controller;

import com.transaction.model.Bank;
import com.transaction.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Integer id) {
        return bankService.getBankById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable Integer id, @RequestBody Bank bankDetails) {
        try {
            Bank updatedBank = bankService.updateBank(id, bankDetails);
            return ResponseEntity.ok(updatedBank);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Integer id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
