package com.p6.apps.service;

import com.p6.apps.controller.dto.bank.BankOperationRequest;
import com.p6.apps.model.entity.BankEntity;
import com.p6.apps.model.entity.BankOperationEntity;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.BankRepository;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.Bank;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class BankOperationService {
    private final BankRepository bankRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;



    public BankOperationService(BankRepository bankRepository,
                                UserRepository userRepository,
                                ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Bank makeTransfer(BankOperationRequest bankOperationRequest) {
        LocalDateTime date = LocalDateTime.now();
        UserEntity userEntity = userRepository.findById(bankOperationRequest.getIdUser()).orElseThrow(() -> new NoSuchElementException("Id " + bankOperationRequest.getIdUser() + " not found"));
        BankEntity bankEntity = bankRepository.findById(bankOperationRequest.getIdBank()).orElseThrow(() -> new NoSuchElementException("Id " + bankOperationRequest.getIdBank() + " not found"));
        BankOperationEntity bankOperationEntity = new BankOperationEntity(0L,
                date,
                bankOperationRequest.getAmount(),
                userEntity,
                bankEntity
        );

        return modelMapper.map(
                bankOperationEntity,
                Bank.class
        );

    }
}
