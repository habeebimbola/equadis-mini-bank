package com.equadis.bank.validation;

import org.springframework.validation.BindingResult;

public class CustomerMgtErrorBuilder {
    public static CustomerMgtError fromBindingError(BindingResult bindingResult){
        CustomerMgtError customerMgtError = new CustomerMgtError("There are "+bindingResult.getErrorCount()+" total errors.");
        bindingResult.getAllErrors().forEach((error -> { customerMgtError.addError( error.getDefaultMessage());}));
        return customerMgtError;
    }
}
