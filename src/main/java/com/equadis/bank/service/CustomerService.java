package com.equadis.bank.service;

import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.dto.CustomerDto;

public interface CustomerService {

    public abstract CustomerDto createNewCustomer(String name, Integer custID);

    public abstract Customer getCustomerByID(Integer custID);
}
