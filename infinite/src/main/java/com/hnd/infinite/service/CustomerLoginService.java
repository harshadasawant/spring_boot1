package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import dto.CustomerLoginDTO;

public interface CustomerLoginService {
    public String authenticateCustomer(CustomerLoginDTO customerLogin) throws HnDBankException;

}
