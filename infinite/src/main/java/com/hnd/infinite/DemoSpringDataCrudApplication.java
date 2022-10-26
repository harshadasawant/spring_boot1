package com.hnd.infinite;

import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.service.Customer1ServiceImpl;
import com.hnd.infinite.service.CustomerServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;

@SpringBootApplication
public class DemoSpringDataCrudApplication implements CommandLineRunner {

    public static final Log LOGGER = LogFactory.getLog(DemoSpringDataCrudApplication.class);
    @Autowired
    Customer1ServiceImpl customerService;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringDataCrudApplication.class, args);
    }

    public void run(String... args) throws Exception {
        addCustomer();
    }
    public void addCustomer() {
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerId(6);
        customer.setEmailId("harry@hnd.com");
        customer.setName("Harry");
        customer.setDateOfBirth(LocalDate.now());
        try {
            customerService.addCustomer(customer);
            LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(),
                        "Something went wrong. Please check log file for more details."));
        }
    }


}
