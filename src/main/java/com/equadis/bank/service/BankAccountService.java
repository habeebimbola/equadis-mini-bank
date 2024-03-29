package com.equadis.bank.service;

import com.equadis.bank.domain.BankAccount;
import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.dto.BankAccountDto;

public interface BankAccountService {

    BankAccountDto createNewBankAccount(Double initialAmount, Integer accountNumber, Customer customer);
    boolean withdrawFromAccount(Integer accountId,Double amount);

    void depositInAccount(Integer accountId, Double amount);

    BankAccount findAccountByNo(Integer accountNo);

}
