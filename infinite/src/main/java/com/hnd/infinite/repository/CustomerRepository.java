package com.hnd.infinite.repository;

import com.hnd.infinite.dto.CustomerDTO;

import java.util.List;

public interface CustomerRepository {
    public CustomerDTO getCustomer(Integer customerId);
    public void addCustomer(CustomerDTO customerDTO);
    public Integer updateCustomer(Integer customerId, String emailId);
    public Integer deleteCustomer(Integer customerId);

    public List<CustomerDTO> getCustomerdetails();
    public List<CustomerDTO> getCustomerdetailsParam(int custId);
    public List<Object[]> getCustomerNameAndDob();
    public List<String> getCustomerName();
    public List<Object[]> getCustomerAggr();




}
