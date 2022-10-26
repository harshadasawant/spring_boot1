package com.hnd.infinite;


import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.dto.CustomerType;
import com.hnd.infinite.service.CustomerServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.List;

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
//        getCustomer();
//        addCustomer();
//        updateCustomer();
//        deleteCustomer();
        getCustomerdetailsParam(1);
        getCustomerdetails();
        getCustomerNameAndDob();
        getCustomerNames();
    }
    public void addCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(2);
        customerDTO.setEmailId("harry@hnd.com");
        customerDTO.setName("Harry");
        customerDTO.setDateOfBirth(LocalDate.of(1980, 4, 22));
        customerDTO.setCustomerType(CustomerType.GOLD);
        try {
            customerService.addCustomer(customerDTO);
            LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(),
                        "Something went wrong. Please check log file for more details."));
        }
    }

    public void getCustomer() throws HnDBankException  {
        try {
            CustomerDTO customerDTO = customerService.getCustomer(2);
            System.out.println(customerDTO);
            LOGGER.info("===" + customerDTO);
        }catch(HnDBankException exception){
            LOGGER.error(environment.getProperty(exception.getMessage()));
        }
    }
    public void updateCustomer() {
        try {
            customerService.updateCustomer(1, "martin01@hnd.com");
            LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(),
                        "Something went wrong. Please check log file for more details."));
        }
    }
    public void deleteCustomer() {
        try {
            customerService.deleteCustomer(1);
            LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
        } catch (Exception e) {
            if (e.getMessage() != null)
                LOGGER.info(environment.getProperty(e.getMessage(),
                        "Something went wrong. Please check log file for more details."));
        }
    }

    public  void getCustomerdetails(){
        try {
            List<CustomerDTO> customerDTOs = customerService.getCustomerdetails();

            for (CustomerDTO customerDTO : customerDTOs) {
                LOGGER.info(customerDTO);
            }
            LOGGER.info("\n");
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public  void getCustomerdetailsParam(int custId){
        try {
            List<CustomerDTO> customerDTOs = customerService.getCustomerdetailsParam(custId);

            for (CustomerDTO customerDTO : customerDTOs) {
                LOGGER.info(customerDTO);
            }
            LOGGER.info("\n");
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public  void getCustomerNameAndDob() {
        try {
            List<Object[]> objects = customerService.getCustomerNameAndDob();

            for (Object[] object : objects) {
                LOGGER.info(object[0]+"\t\t"+object[1]);
            }
            LOGGER.info("\n");
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info( message);
        }
    }
    public  void getCustomerNames() {
        try {
            List<String> customerNames = customerService.getCustomerName();

            for (String name  : customerNames) {
                LOGGER.info(name);
            }
            LOGGER.info("\n");
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info( message);
        }
    }



}