package com.equadis.bank.repo;

import com.equadis.bank.domain.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepo extends CrudRepository<BankAccount, Integer> {
}
