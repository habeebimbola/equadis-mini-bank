package com.equadis.bank.rest;

import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.domain.dto.TransactionDto;
import com.equadis.bank.service.BankAccountService;
import com.equadis.bank.validation.BankAccountError;
import com.equadis.bank.validation.BankAccountErrorBuilder;
import com.equadis.bank.validation.TransactionErrorsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AccountMgtController {
    private final BankAccountService bankAccountService;

    public AccountMgtController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createNewBankAccount(@RequestBody() BankAccountDto bankAccountDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(BankAccountErrorBuilder.fromBindingErrors(bindingResult));
        }

        BankAccountDto bankAccount = this.bankAccountService.createNewBankAccount(bankAccountDto.getBalance());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bankAcctNumber}").buildAndExpand(bankAccount.getAccountNo()).toUri();

        return ResponseEntity.created(location).body(bankAccount);

    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFromAccount(@RequestBody() TransactionDto transactionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(TransactionErrorsBuilder.fromBindingErrors(bindingResult));
        }
        boolean success = this.bankAccountService.withdrawFromAccount(transactionDto.getBankAccountDto().getAccountNo(), transactionDto.getTransactionAmount() );

        return ResponseEntity.ok().build();

    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositIntoAccount(@RequestBody TransactionDto transactionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        this.bankAccountService.depositInAccount(transactionDto.getBankAccountDto().getAccountNo(), transactionDto.getTransactionAmount());

        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BankAccountError handleException(Exception exception){
        return new BankAccountError(exception.getMessage());
    }
}
