package com.project.api_store_java.controller;

import com.project.api_store_java.dto.CustomerDTO;
import com.project.api_store_java.payload.response.Response;
import com.project.api_store_java.service.implementations.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private static final Logger LOGGER   = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCustomerById(@Min(1) @PathVariable("id") Long id){
        LOGGER.info("Getting employee by id {}.", id);
        CustomerDTO customer = customerService.getCustomer(id);
        Response response = Response.builder()
                .messageType("success")
                .message("Customer found:")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCustomers(){
        LOGGER.info("Getting all customers.");
        Response response = Response.builder()
                .messageType("success")
                .message("Customer found:")
                .data(customerService.getAllCustomers())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        LOGGER.info("Saving customer.");
        customerService.saveCustomer(customerDTO);
        Response response = Response.builder()
                .messageType("success")
                .message("Customer saved successfully:")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCustomer(@Min(1) @PathVariable("id") Long id, @RequestBody @Valid CustomerDTO customerDTO){
        LOGGER.info("Updating customer.");
        CustomerDTO customerUpdated = customerService.updateCustomer(id, customerDTO);
        Response response = Response.builder()
                .messageType("success")
                .message("Customer updated successfully:")
                .data(customerUpdated)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<Response> deleteCustomer(@Min(1) @PathVariable("id") Long id){
        LOGGER.info("Deleting customer.");
        customerService.deleteCustomer(id);
        Response response = Response.builder()
                .messageType("success")
                .message("Customer deleted successfully:")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }







}
