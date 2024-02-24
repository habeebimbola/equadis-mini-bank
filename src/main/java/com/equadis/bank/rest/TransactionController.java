package com.equadis.bank.rest;

import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.domain.dto.TransactionDto;
import com.equadis.bank.service.TransactionService;
import com.equadis.bank.validation.TransactionError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TransactionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction/{accountNo}")
    public ResponseEntity<List<TransactionDto>> getAccountTransactions(@PathVariable("accountNo") String accountNo){

        return ResponseEntity.ok( this.transactionService.getTransactionsBy(accountNo));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TransactionError handleException(Exception exception){
        return new TransactionError(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException runtimeException){
        LOGGER.error(runtimeException.getMessage());
    }
}
