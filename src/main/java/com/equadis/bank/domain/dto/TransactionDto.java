package com.equadis.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

@JsonSerialize
public class TransactionDto {

    @JsonProperty("transactionTime")
    private LocalDateTime transactionTime;

    @JsonProperty("amount")
    private Double transactionAmount;

    @JsonProperty("account")
    private BankAccountDto bankAccountDto;

    @JsonProperty("transactionId")
    private Integer transId;

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BankAccountDto getBankAccountDto() {
        return bankAccountDto;
    }

    public void setBankAccountDto(BankAccountDto bankAccountDto) {
        this.bankAccountDto = bankAccountDto;
    }

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }
}
