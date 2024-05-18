package com.transaction.controller;

import com.transaction.model.Transaction;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public Transaction createTransaction(@RequestParam Integer user_id,
                                         @RequestParam BigDecimal amount,
                                         @RequestParam Integer type_id,
                                         @RequestParam Integer category_id,
                                         @RequestParam Integer status_id,
                                         @RequestParam String description,
                                         @RequestParam String currency_code) {
        return transactionService.createTransaction(user_id, amount, type_id, category_id,
                status_id,description,currency_code);
    }

    @GetMapping("/getall")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
