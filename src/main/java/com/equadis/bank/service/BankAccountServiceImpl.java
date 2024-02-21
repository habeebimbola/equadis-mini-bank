package com.equadis.bank.service;

import com.equadis.bank.domain.BankAccount;
import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.repo.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    private BankAccountRepo bankAccountRepo;

    @Autowired
    private TransactionService transactionService;
    @Override
    public BankAccountDto createNewBankAccount(Double initialAmount) {

        if(initialAmount < 0){
            throw  new RuntimeException("Minimum Account Balance Cannot Be Negative");
        }

        BankAccount newBankAccount = new BankAccount(initialAmount);
        BankAccount bankAccount = this.bankAccountRepo.save(newBankAccount);

        if(bankAccount.getUuid() != null){
            return this.createBankAccountDto(bankAccount);
        }
        return new BankAccountDto();
    }

    @Override
    public boolean withdrawFromAccount(Integer accountId, Double amount) {

        BankAccount bankAccount = this.findAccountByNo(accountId);

        if(amount < 0  ){
            throw new RuntimeException("Withdrawal Amount Cannot Be Negative Value");
        }

        if( bankAccount.getBalance() < 0){
            throw new RuntimeException("Insufficient Account Balance For Withdrawal");
        }

        Double newBalance = bankAccount.getBalance() - amount;
        bankAccount.setBalance( newBalance );
        this.bankAccountRepo.save(bankAccount);

        this.transactionService.createNewTransaction(amount,accountId);


        return true;
    }

    @Override
    public void depositInAccount(Integer accountId, Double amount) {

        BankAccount bankAccount = this.findAccountByNo(accountId);

        if ( amount < 0){
            throw new RuntimeException("Invalid Deposit Amount. Negative Values");
        }

        Double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);

        this.bankAccountRepo.save(bankAccount);

        this.transactionService.createNewTransaction(amount,accountId);
    }

    @Override
    public BankAccount findAccountByNo(Integer accountNo) {

       Optional<BankAccount> accountOptional = this.bankAccountRepo.findById(accountNo);

        return accountOptional.orElse(new BankAccount(0D));

    }

    public BankAccountDto createBankAccountDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setAccountNo(bankAccount.getUuid());
        bankAccountDto.setBalance(bankAccount.getBalance());
        return bankAccountDto;
    }
}
