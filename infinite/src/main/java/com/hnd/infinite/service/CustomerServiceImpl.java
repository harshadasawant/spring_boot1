package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service(value = "customerService")
@Transactional

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public CustomerDTO getCustomer(Integer customerId) throws HnDBankException  {
        CustomerDTO customerDTO = customerRepository.getCustomer(customerId);
//        System.out.println(customerDTO.getCustomerId());
        if (customerDTO == null) {
            throw new HnDBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        System.out.println("=="+customerDTO);
        return customerDTO;
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) throws HnDBankException {
        if (customerRepository.getCustomer(customerDTO.getCustomerId()) != null) {
            throw new HnDBankException("Service.CUSTOMER_ALREADY_EXISTS");
        }
        customerRepository.addCustomer(customerDTO);
    }
    @Override
    public void updateCustomer(Integer customerId, String emailId) throws HnDBankException {
        CustomerDTO customerDTO = customerRepository.getCustomer(customerId);
        if (customerDTO == null) {
            throw new HnDBankException("Service.CUSTOMER_UNAVAILABLE");
        }
        customerRepository.updateCustomer(customerId, emailId);
    }


}
