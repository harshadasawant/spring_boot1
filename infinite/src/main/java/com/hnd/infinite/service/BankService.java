package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomersDTO;

public interface BankService {
    public Integer addCustomerAndService(CustomersDTO customerDTO) throws HnDBankException;

}
