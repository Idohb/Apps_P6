package com.p6.apps.service;
import com.p6.apps.mapper.BankConverter;
import com.p6.apps.model.repository.BankRepository;
import com.p6.apps.service.data.Bank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankConverter bankConverter;
    private final BankRepository bankRepository;

    public BankService(BankConverter bankConverter, BankRepository bankRepository) {
        this.bankConverter = bankConverter;
        this.bankRepository = bankRepository;
    }

    public List<Bank> getLogins() {return bankConverter.mapperBank(bankRepository.findAll());}
}
