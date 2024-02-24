package com.equadis.bank.rest;

import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.dto.CustomerDto;
import com.equadis.bank.service.CustomerService;
import com.equadis.bank.validation.CustomerMgtError;
import com.equadis.bank.validation.CustomerMgtErrorBuilder;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CustomerMgtController {

    private final CustomerService customerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerMgtController.class);

    public CustomerMgtController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create-customer")
    public ResponseEntity<?> createNewCustomer(@Valid() @RequestBody() CustomerDto customerDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(CustomerMgtErrorBuilder.fromBindingError(bindingResult));
        }

        CustomerDto customer = this.customerService.createNewCustomer(customerDto.getName(), customerDto.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/customer/{id}").buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(location).body(customer);

    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Integer customerId){
        Customer customer = this.customerService.getCustomerByID(customerId);

        if(customer.getId() == null){
            return ResponseEntity.notFound().build();
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setId(customer.getId());

        return ResponseEntity.ok(  customerDto);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomerMgtError handleException(Exception exception){
        return new CustomerMgtError(exception.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public void handleException(RuntimeException runtimeException){
        LOGGER.error(runtimeException.getMessage());
    }


}
