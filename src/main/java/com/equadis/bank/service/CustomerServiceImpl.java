package com.equadis.bank.service;

import com.equadis.bank.domain.BankAccount;
import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.dto.CustomerDto;
import com.equadis.bank.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createNewCustomer(String name, Integer custID) {

        Customer customer = this.getCustomerByID(custID);

        if (customer.getCustID() != 0){
            throw new RuntimeException("Customer With Identifier Already Exists"+ custID);
        }

        Customer newCustomer = new Customer( custID, name);
//        newCustomer.setBankAccount(this.createBlankAccount());
        Customer savedCustomer = this.customerRepository.save(newCustomer);

        return this.createCustomerDto(savedCustomer);
    }


    @Override
    public Customer getCustomerByID(Integer custID) {
        Customer customer = this.customerRepository.findByCustID(custID);

        if (customer == null){
            return new Customer(0, "");
        }
        return customer;
    }

    private CustomerDto createCustomerDto(Customer savedCustomer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(savedCustomer.getCustID());
        customerDto.setName(savedCustomer.getName());
        return customerDto;

    }

    private BankAccount createBlankAccount(){
        return new BankAccount(0D,0, new Customer());
    }
}
