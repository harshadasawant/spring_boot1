package com.hnd.infinite;


import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.service.CustomerServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoSpringOrmReadApplication implements CommandLineRunner {
    public static final Log LOGGER = LogFactory.getLog(DemoSpringOrmReadApplication.class);

    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    Environment environment;
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringOrmReadApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        getCustomer();
    }
    public void getCustomer() throws HnDBankException  {
        CustomerDTO customerDTO = customerService.getCustomer(2);
        System.out.println(customerDTO);
        LOGGER.info("==="+customerDTO);
    }
}