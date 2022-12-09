package com.p6.apps.service;
import com.p6.apps.mapper.TransactionConverter;
import com.p6.apps.model.repository.TransactionRepository;
import com.p6.apps.service.data.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionConverter transactionConverter;
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionConverter transactionConverter, TransactionRepository transactionRepository) {
        this.transactionConverter = transactionConverter;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions() {return transactionConverter.mapperTransaction(transactionRepository.findAll());}
}
