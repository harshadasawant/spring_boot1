package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CardDTO;
import com.hnd.infinite.dto.CustomerCardDTO;

import java.util.List;

public interface CustomerCardService {
    public CustomerCardDTO getCustomerDetails(Integer customerId) throws HnDBankException;
    public Integer addCustomer(CustomerCardDTO customerDTO) throws HnDBankException;
    public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws HnDBankException;
    public void deleteCustomer(Integer customerId) throws HnDBankException;


}
