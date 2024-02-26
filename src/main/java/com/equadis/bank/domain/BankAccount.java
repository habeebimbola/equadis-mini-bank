package com.equadis.bank.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT")
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ACCOUNT_NUMBER")
    private Integer accountNumber;

    @Column(name = "BALANCE")
    @Positive(message = "Account Balance Cannot Be A Negative Amount")
    private Double balance;

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Customer accountOwner;

    public BankAccount() {
    }

    public BankAccount(Double balance, Integer accountNumber, Customer accountOwner) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public Integer getUuid() {
        return id;
    }

    public void setUuid(Integer id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Customer getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(Customer accountOwner) {
        this.accountOwner = accountOwner;
    }

    @PrePersist
    public void onCreate()
    {
        this.setCreatedDate(LocalDateTime.now());
    }
}
