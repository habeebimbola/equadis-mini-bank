package com.equadis.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@JsonDeserialize
public class TransactionDto {

    @JsonProperty("transactionTime")
    private LocalDateTime transactionTime;

    @NotNull(message = "Transaction Amount not set. Please set a transaction amount")
    @Positive(message = "Transaction amount must be a positive value")
    @JsonProperty("amount")
    private Double transactionAmount;

    @NotNull(message = "Please specify transaction account number")
    @JsonProperty("accountNo")
    private Integer accountNo;

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

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }
}
