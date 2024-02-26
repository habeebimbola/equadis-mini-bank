package com.equadis.bank.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANS_ID")
    private Integer transactionId;

    @Column(name = "AMOUNT")
    private Double transactionAmount;

    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @Column(name = "TRANS_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Enumerated( value = EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    public TransactionType transactionType;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreationDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    public void onCreate(){
        this.setCreationDate(LocalDateTime.now());
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
