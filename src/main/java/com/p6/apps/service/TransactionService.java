package com.p6.apps.service;
import com.p6.apps.controller.dto.transaction.TransactionRequest;
import com.p6.apps.mapper.TransactionConverter;
import com.p6.apps.model.entity.TransactionEntity;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.TransactionRepository;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionConverter transactionConverter;
    private final TransactionRepository transactionRepository;
    private ModelMapper modelMapper;
    private final UserRepository userRepository;

    public TransactionService(TransactionConverter transactionConverter, TransactionRepository transactionRepository, UserRepository userRepository,ModelMapper modelMapper) {
        this.transactionConverter = transactionConverter;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<Transaction> getTransactions() {
        List<TransactionEntity> transactionEntityList = transactionRepository.findAll();
        return transactionEntityList.stream()
                .map(entity -> modelMapper.map(entity,Transaction.class))
                .collect(Collectors.toList());
    }

    public Transaction getTransaction(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return modelMapper.map(transactionEntity, Transaction.class);
    }

    //
    //  N'oublie pas de faire une méthode de commission (ou de remanier la méthode addTransaction pour ça)
    //
    public Transaction addTransaction(TransactionRequest transactionRequest) {
        UserEntity userCreditor = userRepository.findById(transactionRequest.getCreditor()).orElseThrow( () -> new NoSuchElementException("") );
        UserEntity userDebtor = userRepository.findById(transactionRequest.getDebtor()).orElseThrow( () -> new NoSuchElementException("") );

        TransactionEntity transactionEntity = this.createTransaction(transactionRequest, userCreditor, userDebtor);
        transactionEntity = transactionRepository.save(transactionEntity);
        return modelMapper.map(transactionEntity, Transaction.class);
//        return transactionConverter.mapperTransaction(transactionEntity);

    }

    private TransactionEntity createTransaction(TransactionRequest transactionRequest, UserEntity userCreditor, UserEntity userDebtor) {
        LocalDateTime date = LocalDateTime.now();
        return new TransactionEntity(0L,
                transactionRequest.getDescription(),
                transactionRequest.getAmountTransaction(),
                date,
                transactionRequest.getCommission(),
                userCreditor,
                userDebtor
        );
    }

    public Transaction updateTransaction(final Long id, TransactionRequest transactionRequest) {

        TransactionEntity transactionEntity = transactionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(transactionEntity, transactionRequest);
        transactionEntity = transactionRepository.save(transactionEntity);
        return modelMapper.map(
                transactionEntity,
                Transaction.class
        );
//        return transactionConverter.mapperTransaction(transactionEntity);

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

    public Transaction makeTransaction(TransactionRequest transactionRequest) {
        TransactionEntity transactionEntity = calculateBalance(transactionRequest);
        TransactionEntity transactionEntity1 = transactionRepository.save(transactionEntity);
        return transactionConverter.mapperTransaction(transactionEntity1);
    }
    private TransactionEntity calculateBalance(TransactionRequest transactionRequest) {

        UserEntity userCreditor = changeUserBalance(
                userRepository.findById(transactionRequest.getCreditor()).orElseThrow(() -> new NoSuchElementException("Id " + transactionRequest.getCreditor() + " not found")),
                - transactionRequest.getAmountTransaction()
        );
        UserEntity userDebtor = changeUserBalance(
                userRepository.findById(transactionRequest.getDebtor()).orElseThrow(() -> new NoSuchElementException("Id " + transactionRequest.getDebtor() + " not found")),
                transactionRequest.getAmountTransaction()
        );

        return this.createTransaction(transactionRequest, userCreditor,userDebtor);

    }

    private UserEntity changeUserBalance(UserEntity userEntity, double amount) {
        double balance = userEntity.getBalance();
        userEntity.setBalance(balance + amount);
        return userRepository.save(userEntity);
    }

}