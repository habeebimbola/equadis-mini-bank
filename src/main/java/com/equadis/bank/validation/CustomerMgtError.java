package com.equadis.bank.validation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class CustomerMgtError {
    private final String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    public CustomerMgtError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public  void  addError(String errorMessage){
        this.errors.add(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getErrors() {
        return errors;
    }
}
