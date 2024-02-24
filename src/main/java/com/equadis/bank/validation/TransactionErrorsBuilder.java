package com.equadis.bank.validation;

import org.springframework.validation.BindingResult;

public class TransactionErrorsBuilder {
    public static TransactionError fromBindingErrors(BindingResult bindingResult){
        TransactionError transactionError = new TransactionError("There are "+ bindingResult.getErrorCount()+"");
        bindingResult.getAllErrors().forEach((error)-> transactionError.addError( error.getDefaultMessage()));

        return transactionError;
    }
}
