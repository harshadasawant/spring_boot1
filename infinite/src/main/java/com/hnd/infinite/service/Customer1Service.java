package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;

import java.util.List;

public interface Customer1Service {
    public void addCustomer(CustomerDTO customer) throws HnDBankException;
    public CustomerDTO getCustomer(Integer customerId) throws HnDBankException;
    public List<CustomerDTO> findAll() throws HnDBankException;
    public void updateCustomer(Integer customerId, String emailId) throws HnDBankException;
    public void deleteCustomer(Integer customerId)throws HnDBankException;


    void findBy(String emailId, int custId) throws HnDBankException;
}
