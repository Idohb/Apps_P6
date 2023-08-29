package com.p6.apps.controller;
import com.p6.apps.controller.dto.bank.BankRequest;
import com.p6.apps.service.BankService;
import com.p6.apps.service.data.Bank;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("banks")
    public ResponseEntity<List<Bank>> getBanks() {
        try {
            return ResponseEntity.ok(bankService.getBanks());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("bank/{id}")
    public ResponseEntity<Bank> getBank(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(bankService.getBank(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("bank")
    public ResponseEntity<Bank> createBank(@Valid @RequestBody BankRequest bankRequest) {
        return ResponseEntity.ok(bankService.addBank(bankRequest));
    }

    @PutMapping("/bank/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable("id") final Long id, @Valid @RequestBody BankRequest bankRequest) {
        try {
            return ResponseEntity.ok(bankService.updateBank(id, bankRequest));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable("id") final Long id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/banks")
    public ResponseEntity<?> deleteBanks() {
        bankService.deleteBanks();
        return ResponseEntity.noContent().build();
    }


}
