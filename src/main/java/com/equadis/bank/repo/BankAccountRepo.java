package com.equadis.bank.repo;

import com.equadis.bank.domain.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankAccountRepo extends CrudRepository<BankAccount, Integer> {
    public abstract Optional<BankAccount> findByAccountNumber(Integer accountNumber);
}
