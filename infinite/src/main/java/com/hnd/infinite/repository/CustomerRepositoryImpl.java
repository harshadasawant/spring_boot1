package com.hnd.infinite.repository;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "customerRepositor")
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
    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer=new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setName(customerDTO.getName());
        customer.setCustomerType(customerDTO.getCustomerType());
        entityManager.persist(customer);
    }

    @Override
    public Integer updateCustomer(Integer customerId, String emailId) {
        Integer customerIdReturned = null;
        Customer customer = entityManager.find(Customer.class, customerId);
        customer.setEmailId(emailId);
        customerIdReturned = customer.getCustomerId();
        return customerIdReturned;
    }
    @Override
    public Integer deleteCustomer(Integer customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        entityManager.remove(customer);
        Integer customerIdReturned = customer.getCustomerId();
        return customerIdReturned;
    }
    public List<CustomerDTO> getCustomerdetails() {
        List<CustomerDTO> customerDTOs = null;
        String queryString = "select c from Customer c";
        Query query = entityManager.createQuery(queryString);
        List<Customer> customers = query.getResultList();
        customerDTOs = new ArrayList<>();
        for (Customer customerEntity : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customerEntity.getCustomerId());
            customerDTO.setDateOfBirth(customerEntity.getDateOfBirth());
            customerDTO.setEmailId(customerEntity.getEmailId());
            customerDTO.setName(customerEntity.getName());
            customerDTO.setCustomerType(customerEntity.getCustomerType());
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    public List<CustomerDTO> getCustomerdetailsParam(int custId) {
        List<CustomerDTO> customerDTOs = null;
        // Comment the below 3 lines while using named parameter
//        String queryString = "select c from Customer c where c.customerId=?1";
//        Query query = entityManager.createQuery(queryString);
//        query.setParameter(1, custId);

        String queryString ="select c from Customer c where c.customerId=:customerId"; Query
                query=entityManager.createQuery(queryString);
        query.setParameter("customerId", custId);

        List<Customer> customers = query.getResultList();
        customerDTOs = new ArrayList<>();
        for (Customer customerEntity : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customerEntity.getCustomerId());
            customerDTO.setDateOfBirth(customerEntity.getDateOfBirth());
            customerDTO.setEmailId(customerEntity.getEmailId());
            customerDTO.setName(customerEntity.getName());
            customerDTO.setCustomerType(customerEntity.getCustomerType());
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }
    public List<Object[]> getCustomerNameAndDob() {
        String queryString = "select distinct c.name,c.dateOfBirth from Customer c";
        Query query = entityManager.createQuery(queryString);
        List<Object[]> result = query.getResultList();
        return result;
    }
    public List<String> getCustomerName() {
        List<String> customerNames = null;
        String queryString = "select c.name from Customer c";
        Query query = entityManager.createQuery(queryString);
        customerNames = query.getResultList();
        return customerNames;
    }


}