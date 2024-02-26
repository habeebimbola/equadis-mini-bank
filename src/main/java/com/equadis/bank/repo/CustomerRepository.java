package com.equadis.bank.repo;

import com.equadis.bank.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public abstract Customer findByCustID(Integer custID);
}
