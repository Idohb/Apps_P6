package com.p6.apps.service;
import com.p6.apps.controller.dto.bank.BankRequest;
import com.p6.apps.model.entity.BankEntity;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.BankRepository;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.Bank;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public BankService(BankRepository bankRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<Bank> getBanks() {
        List<BankEntity> bankEntityList = bankRepository.findAll();
        return bankEntityList.stream()
                .map(entity -> modelMapper.map(entity, Bank.class))
                .collect(Collectors.toList());
    }

    public Bank addBank(BankRequest bankRequest) {
        UserEntity user = userRepository.findById(bankRequest.getUser()).orElseThrow(() -> new NoSuchElementException("Id " + bankRequest.getUser() + " not found"));
        BankEntity bankEntity = new BankEntity(0L,
                bankRequest.getIban(),
                bankRequest.getAmountBank(),
                user,
                new ArrayList<>(), // userOperation
                new HashSet<>()
        );

        bankEntity = bankRepository.save(bankEntity);
        return modelMapper.map(bankEntity, Bank.class);

    }

    public Bank updateBank(final Long id, BankRequest bankRequest) {

        BankEntity bankEntity = bankRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(bankEntity, bankRequest);
        bankEntity = bankRepository.save(bankEntity);
        return modelMapper.map(
                bankEntity,
                Bank.class
        );

    }

    public void deleteBank(final Long id) {
        bankRepository.deleteById(id);
    }

    public void deleteBanks() {
        bankRepository.deleteAll();
    }

    private void updateEntity(BankEntity bankEntity, BankRequest bankRequest) {

        if (bankRequest.getIban() != null)
            bankEntity.setIban(bankRequest.getIban());

        bankEntity.setAmountBank(bankRequest.getAmountBank());

    }

    public Bank getBank(final Long id) {
        BankEntity bankEntity = bankRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id " + id + " bank not found"));
        return modelMapper.map(bankEntity, Bank.class);
    }
}
