package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.entity.Customer;
import com.hnd.infinite.repository.Customer1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service(value = "customer1Service")
@Transactional

public class Customer1ServiceImpl implements Customer1Service {
    @Autowired
    private Customer1Repository customerRespository;
    @Override
    public void addCustomer(CustomerDTO customerDto) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerDto.getCustomerId());
        if (optional.isPresent())
            throw new HnDBankException("Service.CUSTOMER_FOUND");
        Customer customer = new Customer();
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customer.setEmailId(customerDto.getEmailId());
        customer.setName(customerDto.getName());
        customer.setCustomerId(customerDto.getCustomerId());
        customerRespository.save(customer);
    }


}
