package com.hnd.infinite;

import com.hnd.infinite.dto.CustomerDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoRestTemplateApplication implements CommandLineRunner {
    private static final Log LOGGER = LogFactory.getLog(DemoRestTemplateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoRestTemplateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        getCustomerDetails(2);
//        		CustomerDTO customerDTO = new CustomerDTO();
//                customerDTO.setCustomerId(6);
//		customerDTO.setEmailId("peter@hnd.com");
//		customerDTO.setName("Peter");
//		addCustomer(customerDTO);

//		CustomerDTO customerDTO1 = new CustomerDTO();
//		customerDTO1.setCustomerId(6);
//		customerDTO1.setEmailId("harsha@hnd.com");
//		customerDTO1.setName("Peter");
//		updateCustomer(customerDTO1);
//
        deleteCustomer(6);
    }



    public void getCustomerDetails(Integer customerId) {
        String url = "http://localhost:8080/hndbank/customers/{customerId}";
        RestTemplate restTemplate = new RestTemplate();
        CustomerDTO customerDTO = restTemplate.getForObject(url, CustomerDTO.class, customerId);
        LOGGER.info(customerDTO);
        LOGGER.info("\n");
    }
    public void addCustomer(CustomerDTO customer) {
        String url = "http://localhost:8080/hndbank/customers";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, customer, String.class);
        LOGGER.info(response);
        LOGGER.info("\n");
    }
    public void updateCustomer(CustomerDTO customerDTO) {
        String url = "http://localhost:8080/hndbank/customers/{customerId}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, customerDTO, customerDTO.getCustomerId());
        LOGGER.info("Customer updated successfully");
        LOGGER.info("\n");
    }
    public void deleteCustomer(Integer customerId) {
        String url = "http://localhost:8080/hndbank/customers/{customerId}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, customerId);
        LOGGER.info("Customer deleted successfully");
        LOGGER.info("\n");
    }

}

