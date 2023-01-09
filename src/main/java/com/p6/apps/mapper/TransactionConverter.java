package com.p6.apps.mapper;
import com.p6.apps.model.entity.TransactionEntity;
import com.p6.apps.service.data.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
    public Transaction mapperTransaction(TransactionEntity transactionEntity) {
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(transactionEntity.getIdTransaction());
        transaction.setAmountTransaction(transactionEntity.getAmountTransaction());
        transaction.setTimeTransaction(transactionEntity.getTimeTransaction());
        transaction.setDescription(transactionEntity.getDescription());
        transaction.setCommission(transactionEntity.getCommission());
        return transaction;
//        return objectMapper.convertValue(transactionEntity, Transaction.class);
    }

    public List<Transaction> mapperTransaction(List<TransactionEntity> transactionEntityList){
        return transactionEntityList.stream().map(this::mapperTransaction).collect(Collectors.toList());
    }
}
