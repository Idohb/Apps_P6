package com.p6.apps.service;
import com.p6.apps.controller.dto.transaction.TransactionRequest;
import com.p6.apps.mapper.TransactionConverter;
import com.p6.apps.model.entity.TransactionEntity;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.TransactionRepository;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
    private final TransactionConverter transactionConverter;
    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    public TransactionService(TransactionConverter transactionConverter, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionConverter = transactionConverter;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<Transaction> getTransactions() {return transactionConverter.mapperTransaction(transactionRepository.findAll());}

    //
    //  N'oublie pas de faire une méthode de commission (ou de remanier la méthode addTransaction pour ça)
    //
    public Transaction addTransaction(TransactionRequest transactionRequest) {
        UserEntity userCreditor = userRepository.findById(transactionRequest.getCreditor()).orElseThrow( () -> new NoSuchElementException("") );
        UserEntity userDebtor = userRepository.findById(transactionRequest.getDebtor()).orElseThrow( () -> new NoSuchElementException("") );
        TransactionEntity transactionEntity = new TransactionEntity(0L,
                transactionRequest.getDescription(),
                transactionRequest.getAmountTransaction(),
                transactionRequest.getTimeTransaction(),
                transactionRequest.getCommission(),
                userCreditor,
                userDebtor
        );
        transactionEntity = transactionRepository.save(transactionEntity);
        return transactionConverter.mapperTransaction(transactionEntity);

    }

    public Transaction updateTransaction(final Long id, TransactionRequest transactionRequest) {

        TransactionEntity transactionEntity = transactionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(transactionEntity, transactionRequest);
        transactionEntity = transactionRepository.save(transactionEntity);
        return transactionConverter.mapperTransaction(transactionEntity);

    }

    public void deleteTransaction(final Long id) {
        transactionRepository.deleteById(id);
    }

    public void deleteTransactions() {
        transactionRepository.deleteAll();
    }

    private void updateEntity(TransactionEntity transactionEntity, TransactionRequest transactionRequest) {

        if (transactionRequest.getDescription() != null)
            transactionEntity.setDescription(transactionRequest.getDescription());

        if (transactionRequest.getTimeTransaction() != null)
            transactionEntity.setTimeTransaction(transactionRequest.getTimeTransaction());

    }
}