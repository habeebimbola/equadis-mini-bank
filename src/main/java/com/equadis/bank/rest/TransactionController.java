package com.equadis.bank.rest;

import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.domain.dto.TransactionDto;
import com.equadis.bank.service.TransactionService;
import com.equadis.bank.validation.TransactionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction/{accountId}")
    public ResponseEntity<List<TransactionDto>> getAccountTransactions(@PathVariable("accountId") Integer accountId,
                                                                       @RequestBody() BankAccountDto bankAccountDto){

        return ResponseEntity.ok( this.transactionService.getTransactionsBy(bankAccountDto.toString()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TransactionError handleException(Exception exception){
        return new TransactionError(exception.getMessage());
    }
}
