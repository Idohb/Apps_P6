package com.p6.apps.mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.apps.model.entity.TransactionEntity;
import com.p6.apps.service.data.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
    private final ObjectMapper objectMapper;

    public TransactionConverter(ObjectMapper objectMapper) {this.objectMapper = objectMapper;}

    public Transaction mapperTransaction(TransactionEntity transactionEntity) {return objectMapper.convertValue(transactionEntity, Transaction.class);}

    public List<Transaction> mapperTransaction(List<TransactionEntity> transactionEntityList){
        return transactionEntityList.stream().map(this::mapperTransaction).collect(Collectors.toList());
    }
}
