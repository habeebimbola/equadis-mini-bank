package com.equadis.bank.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TransactionType {
    CREDIT, DEBIT;
}
