package com.equadis.bank.service;

import com.equadis.bank.domain.TransactionType;
import com.equadis.bank.domain.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

//    public abstract void setAccountService(BankAccountService bankAccountService);
    public abstract void createNewTransaction(Double transactionAmount, Integer accountId, TransactionType transactionType);

    public abstract List<TransactionDto> getTransactionsBy(String searchField);
}
