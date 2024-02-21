package com.equadis.bank.rest;

import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.dto.CustomerDto;
import com.equadis.bank.service.CustomerService;
import com.equadis.bank.validation.CustomerMgtError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api")
public class CustomerMgtController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create-customer")
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody() CustomerDto customerDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        CustomerDto customer = this.customerService.createNewCustomer(customerDto.getName(), customerDto.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(location).body(customer);

    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Integer customerId){
        Customer customer = this.customerService.getCustomerByID(customerId);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setId(customer.getId());

        return ResponseEntity.ok(  customerDto);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomerMgtError handleException(Exception exception){
        return new CustomerMgtError(exception.getMessage());
    }


}
