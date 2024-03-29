package com.equadis.bank.service;

import com.equadis.bank.domain.BankAccount;
import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.TransactionType;
import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.repo.BankAccountRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    private final BankAccountRepo bankAccountRepo;
    private final TransactionService transactionService;

    public BankAccountServiceImpl(BankAccountRepo bankAccountRepo, TransactionService transactionService) {
        this.bankAccountRepo = bankAccountRepo;
        this.transactionService = transactionService;
    }

    @Override
    public BankAccountDto createNewBankAccount(Double initialAmount, Integer accountNumber, Customer customer) {

        if(initialAmount < 0){
            throw  new RuntimeException("Minimum Account Balance Cannot Be Negative");
        }

        BankAccount newBankAccount = new BankAccount(initialAmount, accountNumber, customer);
        BankAccount bankAccount = this.bankAccountRepo.save(newBankAccount);

        if(bankAccount.getUuid() != null){
            return this.createBankAccountDto(bankAccount);
        }
        return new BankAccountDto();
    }

    @Override
    public boolean withdrawFromAccount(Integer accountId, Double amount) {
        boolean isSuccessful = false;
        BankAccount bankAccount = this.findAccountByNo(accountId);

        if (bankAccount.getAccountNumber() == 0){
            return isSuccessful;
        }

        if(amount <= 0  ){
            return isSuccessful;

//            throw new RuntimeException("Withdrawal Amount Cannot Be Negative Value");
        }

        if( bankAccount.getBalance() < 1 || (bankAccount.getBalance() - amount) < 1){
            throw new RuntimeException("Insufficient Account Balance For Withdrawal");
        }

        Double newBalance = bankAccount.getBalance() - amount;
        bankAccount.setBalance( newBalance );
        this.bankAccountRepo.save(bankAccount);
        this.transactionService.createNewTransaction(amount,accountId, TransactionType.DEBIT);

        isSuccessful = true;
        return isSuccessful;
    }

    @Override
    public void depositInAccount(Integer accountId, Double amount) {

        BankAccount bankAccount = this.findAccountByNo(accountId);

        if ( amount <= 0){
            throw new RuntimeException("Invalid Deposit Amount. Negative Values");
        }

        Double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);

        this.bankAccountRepo.save(bankAccount);

        this.transactionService.createNewTransaction(amount,accountId, TransactionType.CREDIT);
    }

    @Override
    public BankAccount findAccountByNo(Integer accountNo) {

       Optional<BankAccount> accountOptional = this.bankAccountRepo.findByAccountNumber(accountNo);

        return accountOptional.orElse(new BankAccount(0D, 0, new Customer(0, "") ));

    }

    public BankAccountDto createBankAccountDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setAccountNo(bankAccount.getAccountNumber());
        bankAccountDto.setBalance(bankAccount.getBalance());
        return bankAccountDto;
    }
}
