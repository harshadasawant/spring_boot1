package com.hnd.infinite.service;

//import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;

public interface CustomerService {
    public CustomerDTO getCustomer(Integer customerId) throws HnDBankException;
    public void addCustomer(CustomerDTO customerDTO) throws HnDBankException;
    public void updateCustomer(Integer customerId, String emailId) throws HnDBankException;

}
