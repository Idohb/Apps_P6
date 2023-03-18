package com.p6.apps.controller;

import com.p6.apps.controller.dto.bank.BankOperationRequest;
import com.p6.apps.service.BankOperationService;
import com.p6.apps.service.data.BankOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class BankOperationController {

    private final BankOperationService bankOperationService;

    @Autowired
    public BankOperationController(BankOperationService bankOperationService) {
        this.bankOperationService = bankOperationService;
    }

    @PostMapping("deposit")
    public ResponseEntity<BankOperation> makeDeposit(@Valid @RequestBody BankOperationRequest bankOperationRequest) {
        return ResponseEntity.ok(bankOperationService.makeDeposit(bankOperationRequest));
    }

    @PostMapping("withdraw")
    public ResponseEntity<BankOperation> withdrawMoney(@Valid @RequestBody BankOperationRequest bankOperationRequest) {
        return ResponseEntity.ok(bankOperationService.withdrawMoney(bankOperationRequest));
    }
}
