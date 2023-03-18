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
        UserEntity userEntity = userRepository.findById(bankOperationRequest.getIdUser()).orElseThrow(() -> new NoSuchElementException("Id " + bankOperationRequest.getIdUser() + " not found"));
        BankEntity bankEntity = bankRepository.findByUserAndIdBank(userEntity, bankOperationRequest.getIdBank())
                .orElseThrow(() -> new NoSuchElementException("Bank not found"));

        BankOperationEntity bankOperationEntity = new BankOperationEntity(0L,
                date,
                bankOperationRequest.getAmount(),
                userEntity,
                bankEntity
        );

        bankEntity.setAmountBank(bankEntity.getAmountBank() + bankOperationRequest.getAmount());
        bankRepository.save(bankEntity);
        bankOperationRepository.save(bankOperationEntity);

        return modelMapper.map(
                bankOperationEntity,
                BankOperation.class
        );

    }
    @Transactional
    public BankOperation withdrawMoney(BankOperationRequest bankOperationRequest) {
        LocalDateTime date = LocalDateTime.now();
        UserEntity userEntity = userRepository.findById(bankOperationRequest.getIdUser())
                .orElseThrow(() -> new NoSuchElementException("Id " + bankOperationRequest.getIdUser() + " not found"));
        BankEntity bankEntity = bankRepository.findByUserAndIdBank(userEntity, bankOperationRequest.getIdBank())
                .orElseThrow(() -> new NoSuchElementException("Bank not found"));

        BankOperationEntity bankOperationEntity = new BankOperationEntity(0L,
                date,
                bankOperationRequest.getAmount(),
                userEntity,
                bankEntity
        );



        bankEntity.setAmountBank(bankEntity.getAmountBank() - bankOperationRequest.getAmount());
        bankRepository.save(bankEntity);
        bankOperationRepository.save(bankOperationEntity);

        return modelMapper.map(
                bankOperationEntity,
                BankOperation.class
        );
    }
}
