package com.equadis.bank.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CUSTOMER_ID")
    private Integer custID;

    @Column(name = "NAME")
    private String name;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime modifiedDate;


    public Customer() {
    }

    public Customer(Integer custID, String name) {
        this.custID = custID;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustID() {
        return custID;
    }

    public void setCustID(Integer custID) {
        this.custID = custID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

//    public BankAccount getBankAccount() {
//        return bankAccount;
//    }
//
//    public void setBankAccount(BankAccount bankAccount) {
//        this.bankAccount = bankAccount;
//    }

    @PrePersist
    public void onCreate(){
        this.setCreationDate(LocalDateTime.now());
        this.setModifiedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(){
        this.setModifiedDate(LocalDateTime.now());
    }
}
