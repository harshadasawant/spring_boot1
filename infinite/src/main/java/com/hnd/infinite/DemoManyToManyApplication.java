package com.hnd.infinite;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.hnd.infinite.dto.CustomersDTO;
import com.hnd.infinite.dto.ServicesDTO;
import com.hnd.infinite.service.BankService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
@SpringBootApplication
public class DemoManyToManyApplication implements CommandLineRunner {

    public static final Log LOGGER = LogFactory.getLog(DemoManyToManyApplication.class);
    @Autowired
    BankService bankService;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoManyToManyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //		addCustomerAndService();
//		addExistingServiceToExistingCustomer();
		deallocateServiceForExistingCustomer();
//		deleteCustomer();
//        getCustomer();

    }

    public void addCustomerAndService() {
        try {
            CustomersDTO customerDTO = new CustomersDTO();
            customerDTO.setDateOfBirth(LocalDate.of(1995, 2, 1));
            customerDTO.setEmailId("peter@hnd.com");
            customerDTO.setName("Peter");
            Set<ServicesDTO> servicesList = new LinkedHashSet<ServicesDTO>();
            ServicesDTO servicesDTO1 = new ServicesDTO();
            servicesDTO1.setServiceId(3005);
            servicesDTO1.setServiceName("Demat Services");
            servicesDTO1.setServiceCost(200);
            servicesList.add(servicesDTO1);
            customerDTO.setBankServices(servicesList);
            Integer customerId = bankService.addCustomerAndService(customerDTO);
            LOGGER.info(environment.getProperty("UserInterface.NEW_CUSTOMER_SUCCESS") + customerId);
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public void addExistingServiceToExistingCustomer() {
        try{
            Integer customerId=1004;
            List<Integer> serviceIds=new ArrayList<>();
            serviceIds.add(3001);
            serviceIds.add(3003);
            bankService.addExistingServiceToExistingCustomer(customerId, serviceIds);
            LOGGER.info(environment.getProperty("UserInterface.CUSTOMER_SERVICE_ALLOCATION_SUCCESS"));

        }catch(Exception e){
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public void deallocateServiceForExistingCustomer() {
        try{
            Integer customerId=1002;
            List<Integer> serviceIds=new ArrayList<>();
            serviceIds.add(3003);
            bankService.deallocateServiceForExistingCustomer(customerId, serviceIds);
            LOGGER.info(environment.getProperty("UserInterface.CUSTOMER_SERVICE_DEALLOCATION_SUCCESS"));
        }catch(Exception e){
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public void deleteCustomer() {
        try{
            Integer customerId=1001;
            bankService.deleteCustomer(customerId);
            LOGGER.info(environment.getProperty("UserInterface.CUSTOMER_DELETE_SUCCESS"));
        }catch(Exception e){
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public void getCustomer() {
        try{
            Integer customerId=1004;
            CustomersDTO customerDTO=bankService.getCustomer(customerId);
            LOGGER.info(customerDTO);
            Set<ServicesDTO> serviceList=customerDTO.getBankServices();
            if(serviceList==null){
                LOGGER.info(environment.getProperty("UserInterface.SERVICE_UNAVAILABLE"));
            }
        }catch(Exception e){
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
}


