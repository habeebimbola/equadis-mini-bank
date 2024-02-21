package com.equadis.bank.repo;

import com.equadis.bank.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
//    public abstract List<Transaction> findBySearchField(String searchField);
}
