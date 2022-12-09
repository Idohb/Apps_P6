package com.p6.apps.controller;
import com.p6.apps.service.TransactionService;
import com.p6.apps.service.data.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        try {
            return ResponseEntity.ok(transactionService.getTransactions());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
