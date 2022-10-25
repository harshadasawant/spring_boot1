package com.hnd.infinite.repository;

import com.hnd.infinite.dto.CustomerLoginDTO;

public interface CustomerLoginRepository {
    public CustomerLoginDTO getCustomerLoginByLoginName(String loginName);

}
