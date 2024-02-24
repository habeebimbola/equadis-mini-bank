package com.equadis.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TransactionStatus {
    SUCCESSFUL("Transaction Successful"), UNSUCCESSFUL("Transaction Was Not Successful");

    TransactionStatus(String s) {
        this.tranState = s;
    }

    @JsonProperty("transState")
    public TransactionStatus ggtTransactionStatus()
    {
        return this;
    }

    private final String tranState;
}
