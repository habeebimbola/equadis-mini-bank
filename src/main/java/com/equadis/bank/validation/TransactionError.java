package com.equadis.bank.validation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class TransactionError {

    private final String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errorsList = new ArrayList<>();

    public TransactionError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addError(String errorMessage){
        this.errorsList.add(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getErrorsList() {
        return errorsList;
    }
}
