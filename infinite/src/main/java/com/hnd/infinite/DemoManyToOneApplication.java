package com.hnd.infinite;

import com.hnd.infinite.dto.LoanDTO;
import com.hnd.infinite.service.CustomerLoanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
@SpringBootApplication
public class DemoManyToOneApplication implements CommandLineRunner {

    public static final Log LOGGER = LogFactory.getLog(DemoManyToOneApplication.class);
    @Autowired
    CustomerLoanService customerLoanService;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoManyToOneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getLoanDetails();
    }

    public void getLoanDetails() {
        try {
            LoanDTO loanDTO = customerLoanService.getLoanDetails(2001);
            LOGGER.info(loanDTO);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }

}


