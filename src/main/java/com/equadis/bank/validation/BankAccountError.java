package com.equadis.bank.validation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class BankAccountError {
    private final String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> eerrorsList = new ArrayList<>();

    public BankAccountError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void  addError(String errorMessage){
        this.eerrorsList.add(errorMessage);
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getEerrorsList() {
        return eerrorsList;
    }
}
