package com.equadis.bank.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @UuidGenerator
    @Column(name = "UUID")
    private UUID uuid;

    @Column(name = "CUSTOMER_ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @OneToOne(mappedBy = "id")
    private BankAccount bankAccount;


    public Customer() {
    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

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
