package com.equadis.bank.repo;

import com.equadis.bank.domain.Transaction;
import com.equadis.bank.domain.TransactionType;
import com.equadis.bank.domain.dto.TransactionDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
//    public abstract List<Transaction> findBySearchField(String searchField);

    public abstract List<Transaction> findByAccountIdAndTransactionType(Integer accountId, TransactionType transactionType);

    public abstract List<Transaction> findByAccountIdAndTransactionId(Integer accountId, Integer transactionId);
}
