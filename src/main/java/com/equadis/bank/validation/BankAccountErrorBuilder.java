package com.equadis.bank.validation;

import org.springframework.validation.BindingResult;

public class BankAccountErrorBuilder {

    public static BankAccountError fromBindingErrors(BindingResult bindingResult){
        BankAccountError bankAccountError = new BankAccountError("There are "+bindingResult.getErrorCount()+" total errors");

        bindingResult.getAllErrors().forEach((error)->{ bankAccountError.addError(error.getDefaultMessage());});

        return bankAccountError;
    }
}
