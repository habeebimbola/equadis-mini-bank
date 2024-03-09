package com.equadis.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonSerialize
public class BankAccountDto {

    @NotNull(message = "Bank Account Number Cannot Be Null")
    @Positive(message = "Bank Account Cannot Be A Negative Number")
    @JsonProperty("accountNo")
    private Integer accountNo;

    @Positive(message = "Account Balance Must Be A Positive Value")
    @NotNull(message = "Account Balance Cannot Be Null")
    @JsonProperty("balance")
    private Double balance;

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BankAccountDto(Integer accountNo, Double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public BankAccountDto() {
    }

    @Override
    public String toString() {
        return "{\"accountNo\":" + accountNo +",\"balance\":" + balance + "}";
    }
}
