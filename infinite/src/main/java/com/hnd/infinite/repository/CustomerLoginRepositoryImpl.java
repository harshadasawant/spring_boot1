package com.hnd.infinite.repository;

import com.hnd.infinite.dto.CustomerLoginDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository(value="customerLoginRepository")
public class CustomerLoginRepositoryImpl implements CustomerLoginRepository {
    public CustomerLoginDTO getCustomerLoginByLoginName(String loginName) {
        Map<String, String> customerCredentials = new HashMap<String, String>();
        customerCredentials.put("robin", "robin123");
        customerCredentials.put("harry", "harry1234");
        customerCredentials.put("garry", "garry123");
        customerCredentials.put("monica", "monica123");
        CustomerLoginDTO customerLogin = new CustomerLoginDTO();
        customerLogin.setLoginName(loginName);
        customerLogin.setPassword(customerCredentials.get(loginName));
        return customerLogin;
    }

}
