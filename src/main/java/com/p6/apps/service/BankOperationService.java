package com.p6.apps.service;

import com.p6.apps.controller.dto.bank.BankOperationRequest;
import com.p6.apps.mapper.BankConverter;
import com.p6.apps.model.entity.BankEntity;
import com.p6.apps.model.entity.BankOperationEntity;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.BankOperationRepository;
import com.p6.apps.model.repository.BankRepository;
import com.p6.apps.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class BankOperationService {
    private final BankConverter bankConverter;
    private final BankRepository bankRepository;
    private ModelMapper modelMapper;
    private final UserRepository userRepository;

    private final BankOperationRepository bankOperationRepository;

    public BankOperationService(BankConverter bankConverter,
                                BankRepository bankRepository,
                                UserRepository userRepository,
                                ModelMapper modelMapper,
                                BankOperationRepository bankOperationRepository) {
        this.bankConverter = bankConverter;
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bankOperationRepository = bankOperationRepository;
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

        BankOperationEntity bankOperation = bankOperation = bankOperationRepository.save(bankOperationEntity);


    }
}
