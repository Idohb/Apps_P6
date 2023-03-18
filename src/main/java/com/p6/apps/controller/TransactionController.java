package com.p6.apps.controller;
import com.p6.apps.controller.dto.transaction.TransactionRequest;
import com.p6.apps.exception.InsufficientBalanceException;
import com.p6.apps.service.TransactionService;
import com.p6.apps.service.data.Transaction;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("transaction/{id}")
    public ResponseEntity<List<Transaction>> getLogin(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(transactionService.getTransaction(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("transaction")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.addTransaction(transactionRequest));
    }

    @PostMapping("balance")
    public ResponseEntity<Transaction> applyChangeBalance(@Valid @RequestBody TransactionRequest transactionRequest) throws InsufficientBalanceException {
        return ResponseEntity.ok(transactionService.makeTransaction(transactionRequest));
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") final Long id, @Valid @RequestBody TransactionRequest transactionRequest) {
        try {
            return ResponseEntity.ok(transactionService.updateTransaction(id, transactionRequest));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") final Long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<?> deleteTransaction() {
        transactionService.deleteTransactions();
        return ResponseEntity.noContent().build();
    }
}
