package com.equadis.bank.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "BALANCE")
    @Positive(message = "Account Balance Cannot Be A Negative Amount")
    private Double balance;

    public BankAccount() {
    }

    public BankAccount(Double balance) {
        this.balance = balance;
    }

    public Integer getUuid() {
        return id;
    }

    public void setUuid(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
