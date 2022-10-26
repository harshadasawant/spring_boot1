package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.entity.Customer;
import com.hnd.infinite.repository.Customer1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    @Override
    public CustomerDTO getCustomer(Integer customerId) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setEmailId(customer.getEmailId());
        customerDto.setName(customer.getName());
        return customerDto;
    }
    @Override
    public List<CustomerDTO> findAll() throws HnDBankException {
        Iterable<Customer> customers = customerRespository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerDTO customerDto = new CustomerDTO();
            customerDto.setCustomerId(customer.getCustomerId());
            customerDto.setDateOfBirth(customer.getDateOfBirth());
            customerDto.setEmailId(customer.getEmailId());

            customerDto.setName(customer.getName());
            customerDTOs.add(customerDto);
        });
		if (customerDTOs.isEmpty())
                throw new HnDBankException("Service.CUSTOMERS_NOT_FOUND");
		return customerDTOs;
}

    @Override
    public void updateCustomer(Integer customerId, String emailId) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
        customer.setEmailId(emailId);
    }

    @Override
    public void deleteCustomer(Integer customerId) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
        customerRespository.deleteById(customerId);
    }




}
