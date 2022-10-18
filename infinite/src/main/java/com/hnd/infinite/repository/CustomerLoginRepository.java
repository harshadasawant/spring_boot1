package com.hnd.infinite.repository;

import dto.CustomerLoginDTO;

public interface CustomerLoginRepository {
    public CustomerLoginDTO getCustomerLoginByLoginName(String loginName);

}
