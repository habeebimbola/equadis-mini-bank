package com.equadis.bank.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANS_ID")
    private Long transactionId;

    @Column(name = "AMOUNT")
    private Double transactionAmount;

    @OneToOne(mappedBy = "id")
    private BankAccount transactionAccount;

    @Column(name = "TRANS_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BankAccount getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(BankAccount transactionAccount) {
        this.transactionAccount = transactionAccount;
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
}
