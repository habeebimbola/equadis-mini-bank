package com.equadis.bank.rest;

import com.equadis.bank.domain.BankAccount;
import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.domain.dto.CustomerDto;
import com.equadis.bank.domain.dto.TransactionDto;
import com.equadis.bank.domain.dto.TransactionStatus;
import com.equadis.bank.service.BankAccountService;
import com.equadis.bank.service.CustomerService;
import com.equadis.bank.validation.BankAccountError;
import com.equadis.bank.validation.BankAccountErrorBuilder;
import com.equadis.bank.validation.TransactionErrorsBuilder;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AccountMgtController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountMgtController.class);


    private final BankAccountService bankAccountService;
    private final CustomerService customerService;

    public AccountMgtController(BankAccountService bankAccountService, CustomerService customerService) {
        this.bankAccountService = bankAccountService;
        this.customerService = customerService;
    }

    @GetMapping("/get-account/{accountNo}")
    public ResponseEntity<BankAccount> findBankAccount(@PathVariable("accountNo") Integer accountNo ){

        BankAccount bankAccount = this.bankAccountService.findAccountByNo(accountNo);

        if (bankAccount.getId() == null || bankAccount.getAccountNumber() == 0){
            return ResponseEntity.notFound().header("Account No", +accountNo+ " Provided account not found").build();
        }

        return ResponseEntity.ok(bankAccount);
    }

    @PostMapping("/create-account/{custID}")
    public ResponseEntity<?> createNewBankAccount (@PathVariable("custID") Integer customerId,  @Valid() @RequestBody() BankAccountDto bankAccountDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(BankAccountErrorBuilder.fromBindingErrors(bindingResult));
        }

        Customer customer =  this.customerService.getCustomerByID(customerId);

        if(customer.getId() == 0){
            return ResponseEntity.badRequest().build();
        }

        BankAccountDto bankAccount = this.bankAccountService.createNewBankAccount(bankAccountDto.getBalance(), bankAccountDto.getAccountNo() ,customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bankAcctNumber}").buildAndExpand(bankAccount.getAccountNo()).toUri();

        return ResponseEntity.created(location).body(bankAccount);

    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFromAccount(@Valid() @RequestBody() TransactionDto transactionDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(TransactionErrorsBuilder.fromBindingErrors(bindingResult));
        }

        boolean success = this.bankAccountService.withdrawFromAccount(transactionDto.getAccountNo(), transactionDto.getTransactionAmount());

        if (success){
            return ResponseEntity.ok(TransactionStatus.SUCCESSFUL);
        }

        return ResponseEntity.badRequest().body("Could not complete transaction");

    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositIntoAccount(@Valid() @RequestBody() TransactionDto transactionDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        this.bankAccountService.depositInAccount(transactionDto.getAccountNo(), transactionDto.getTransactionAmount());

        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BankAccountError handleException(Exception exception){
        return new BankAccountError(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleServerException(RuntimeException runtimeException){
        LOGGER.error( runtimeException.getMessage());
    }
}


