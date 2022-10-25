package com.hnd.infinite.repository;

import com.hnd.infinite.dto.CustomerDTO;

public interface CustomerRepository {
    public CustomerDTO getCustomer(Integer customerId);

}
