package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomersDTO;

import java.util.List;

public interface BankService {
    public Integer addCustomerAndService(CustomersDTO customerDTO) throws HnDBankException;
    public void addExistingServiceToExistingCustomer(Integer customerId, List<Integer> serviceIds) throws HnDBankException;
    public void deallocateServiceForExistingCustomer(Integer customerId,List<Integer> serviceIds) throws HnDBankException;
    public void deleteCustomer(Integer customerId) throws HnDBankException;
    public CustomersDTO getCustomer(Integer customerId) throws HnDBankException;


}
