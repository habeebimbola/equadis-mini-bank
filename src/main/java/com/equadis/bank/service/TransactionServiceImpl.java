package com.equadis.bank.service;

import com.equadis.bank.domain.BankAccount;
import com.equadis.bank.domain.Transaction;
import com.equadis.bank.domain.TransactionType;
import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.domain.dto.TransactionDto;
import com.equadis.bank.repo.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;


    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }


    @Override
    public void createNewTransaction(Double transactionAmount, Integer accountId, TransactionType transactionType) {

        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionAmount(transactionAmount);
        newTransaction.setAccountId(accountId);
        newTransaction.setTransactionType(transactionType);

        this.transactionRepo.save(newTransaction);

    }

    @Override
    public List<TransactionDto> getTransactionsBy(String searchField) {

        List<Transaction> transactionList = (List<Transaction>) this.transactionRepo.findAll();
        return transactionList.stream().map((transaction -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionAmount(transaction.getTransactionAmount());
            transactionDto.setTransactionTime(transaction.getCreatedDate());
            transactionDto.setAccountNo(transaction.getAccountId());
//            transactionDto.setBankAccountDto(createBankAccountDto( transaction.getTransactionAccount()));
            return transactionDto;
        })).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getTransactionsByType(Integer accountId, TransactionType transactionType) {

        return this.transactionRepo.findByAccountIdAndTransactionType(accountId, transactionType).stream().map((transaction -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setAccountNo(transaction.getAccountId());
            transactionDto.setTransactionAmount(transaction.getTransactionAmount());
            transactionDto.setTransId(transaction.getTransactionId());
            transactionDto.setTransactionTime(transaction.getCreatedDate());
            return transactionDto;
        })).collect(Collectors.toList());

    }

    @Override
    public List<TransactionDto> getTransactionsByTransactionId(Integer accountId, Integer transactionId) {

        return this.transactionRepo.findByAccountIdAndTransactionId(accountId, transactionId).stream().map((transaction -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setAccountNo(transaction.getAccountId());
            transactionDto.setTransactionAmount(transaction.getTransactionAmount());
            transactionDto.setTransId(transaction.getTransactionId());
            transactionDto.setTransactionTime(transaction.getCreatedDate());
            return transactionDto;
        })).collect(Collectors.toList());
    }

    private BankAccountDto createBankAccountDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setAccountNo(bankAccount.getUuid());
        bankAccountDto.setBalance(bankAccount.getBalance());
        return bankAccountDto;
    }
}
