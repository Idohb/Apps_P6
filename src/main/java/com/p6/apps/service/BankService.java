package com.p6.apps.service;
import com.p6.apps.controller.dto.bank.BankRequest;
import com.p6.apps.mapper.BankConverter;
import com.p6.apps.model.entity.BankEntity;
import com.p6.apps.model.repository.BankRepository;
import com.p6.apps.service.data.Bank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankService {

    private final BankConverter bankConverter;
    private final BankRepository bankRepository;

    public BankService(BankConverter bankConverter, BankRepository bankRepository) {
        this.bankConverter = bankConverter;
        this.bankRepository = bankRepository;
    }

    public List<Bank> getBanks() {return bankConverter.mapperBank(bankRepository.findAll());}

    public Bank addBank(BankRequest bankRequest) {
        BankEntity bankEntity = new BankEntity(0L,
                bankRequest.getIban(),
                bankRequest.getAmountBank());
        bankEntity = bankRepository.save(bankEntity);
        return bankConverter.mapperBank(bankEntity);

    }

    public Bank updateBank(final Long id, BankRequest bankRequest) {

        BankEntity bankEntity = bankRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(bankEntity, bankRequest);
        bankEntity = bankRepository.save(bankEntity);
        return bankConverter.mapperBank(bankEntity);

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

    }
}
