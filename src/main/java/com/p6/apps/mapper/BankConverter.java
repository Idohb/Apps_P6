package com.p6.apps.mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.apps.model.entity.BankEntity;
import com.p6.apps.service.data.Bank;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankConverter {

    private final ObjectMapper objectMapper;

    public BankConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Bank mapperBank(BankEntity BankEntity) {
        return objectMapper.convertValue(BankEntity, Bank.class);
    }

    public List<Bank> mapperBank(List<BankEntity> BankEntityList) {
        return BankEntityList.stream().map(this::mapperBank).collect(Collectors.toList());
    }

}
