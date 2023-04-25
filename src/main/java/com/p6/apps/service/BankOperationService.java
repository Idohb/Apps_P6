package com.p6.apps.service;

import com.p6.apps.controller.dto.bank.BankOperationRequest;
import com.p6.apps.model.entity.BankEntity;
import com.p6.apps.model.entity.BankOperationEntity;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.BankOperationRepository;
import com.p6.apps.model.repository.BankRepository;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.BankOperation;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class BankOperationService {
    private final BankRepository bankRepository;

    private final BankOperationRepository bankOperationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;



    public BankOperationService(BankRepository bankRepository,
                                BankOperationRepository bankOperationRepository,
                                UserRepository userRepository,
                                ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.bankOperationRepository = bankOperationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public BankOperation makeDeposit(BankOperationRequest bankOperationRequest) {
        LocalDateTime date = LocalDateTime.now();

        double amount = bankOperationRequest.getAmount();

        UserEntity userEntity = userRepository.findById(bankOperationRequest.getIdUser()).orElseThrow(() -> new NoSuchElementException("Id " + bankOperationRequest.getIdUser() + " not found"));
        BankEntity bankEntity = bankRepository.findByUserAndIdBank(userEntity, bankOperationRequest.getIdBank())
                .orElseThrow(() -> new NoSuchElementException("Bank not found"));

        BankOperationEntity bankOperationEntity = new BankOperationEntity(0L,
                userEntity,
                bankEntity,
                date,
                bankOperationRequest.getAmount()
                );

        bankEntity.setAmountBank(bankEntity.getAmountBank() + amount);
        userEntity.setBalance(userEntity.getBalance()       - amount);
        bankRepository.save(bankEntity);
        userRepository.save(userEntity);
        bankOperationRepository.save(bankOperationEntity);

        return modelMapper.map(
                bankOperationEntity,
                BankOperation.class
        );

    }
    @Transactional
    public BankOperation withdrawMoney(BankOperationRequest bankOperationRequest) {
        LocalDateTime date = LocalDateTime.now();

        double amount = bankOperationRequest.getAmount();

        UserEntity userEntity = userRepository.findById(bankOperationRequest.getIdUser())
                .orElseThrow(() -> new NoSuchElementException("Id " + bankOperationRequest.getIdUser() + " not found"));
        BankEntity bankEntity = bankRepository.findByUserAndIdBank(userEntity, bankOperationRequest.getIdBank())
                .orElseThrow(() -> new NoSuchElementException("Bank not found"));

        BankOperationEntity bankOperationEntity = new BankOperationEntity(0L,
                userEntity,
                bankEntity,
                date,
                bankOperationRequest.getAmount()
                );



        bankEntity.setAmountBank(bankEntity.getAmountBank() - amount);
        userEntity.setBalance(userEntity.getBalance()       + amount);
        bankRepository.save(bankEntity);
        userRepository.save(userEntity);
        BankOperationEntity boe = bankOperationRepository.save(bankOperationEntity);

        return modelMapper.map(
                boe,
                BankOperation.class
        );
    }
}
