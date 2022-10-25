package com.hnd.infinite.repository;

import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDTO getCustomer(Integer customerId) {
        CustomerDTO customerDTO=null;
        Customer customer = entityManager.find(Customer.class, customerId);
        if(customer!=null){
            customerDTO=new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setName(customer.getName());
            customerDTO.setCustomerType(customer.getCustomerType());
        }
        return customerDTO;
    }
}
