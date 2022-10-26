package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.AddressDTO;
import com.hnd.infinite.dto.CustomerAddDTO;

public interface CustomerAddService {
    public CustomerAddDTO getCustomer(Integer customerId) throws HnDBankException;
    public Integer addCustomer(CustomerAddDTO customerDTO) throws HnDBankException;
    public void updateAddress(Integer customerId, AddressDTO addressDTO) throws HnDBankException;
    public void deleteCustomer(Integer customerId) throws HnDBankException;
    public void deleteCustomerOnly(Integer customerId) throws HnDBankException;
}
