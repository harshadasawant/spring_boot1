package com.hnd.infinite.controller;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hndbank")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private Environment environment;
    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws HnDBankException {
        List<CustomerDTO> customerList = customerService.getCustomerdetails();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }



}
