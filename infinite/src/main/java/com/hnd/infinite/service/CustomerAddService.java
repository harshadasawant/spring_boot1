package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerAddDTO;

public interface CustomerAddService {
    public CustomerAddDTO getCustomer(Integer customerId) throws HnDBankException;
    public Integer addCustomer(CustomerAddDTO customerDTO);
}
