package com.equadis.bank.service;

import com.equadis.bank.domain.BankAccount;
import com.equadis.bank.domain.Transaction;
import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.domain.dto.TransactionDto;
import com.equadis.bank.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public void setAccountService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void createNewTransaction(Double transactionAmount, Integer accountId) {

        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionAmount(transactionAmount);
        newTransaction.setTransactionAccount(bankAccountService.findAccountByNo(accountId));

        this.transactionRepo.save(newTransaction);

    }

    @Override
    public List<TransactionDto> getTransactionsBy(String searchField) {

        List<Transaction> transactionList = (List<Transaction>) this.transactionRepo.findAll();
        return transactionList.stream().map((transaction -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionAmount(transaction.getTransactionAmount());
            transactionDto.setTransactionTime(transaction.getCreatedDate());
            transactionDto.setBankAccountDto(createBankAccountDto( transaction.getTransactionAccount()));
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
