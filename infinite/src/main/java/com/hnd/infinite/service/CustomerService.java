package com.hnd.infinite.service;

//import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;

public interface CustomerService {
    public CustomerDTO getCustomer(Integer customerId) throws HnDBankException;

}
