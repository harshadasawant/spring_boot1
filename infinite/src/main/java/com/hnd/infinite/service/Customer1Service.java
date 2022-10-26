package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;

public interface Customer1Service {
    public void addCustomer(CustomerDTO customer) throws HnDBankException;
}
