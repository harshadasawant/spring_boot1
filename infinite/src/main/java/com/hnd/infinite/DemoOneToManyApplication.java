package com.hnd.infinite;


import com.hnd.infinite.dto.CardDTO;
import com.hnd.infinite.dto.CustomerCardDTO;
import com.hnd.infinite.service.CustomerCardService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class DemoOneToManyApplication implements CommandLineRunner {

    public static final Log LOGGER = LogFactory.getLog(DemoOneToManyApplication.class);
    @Autowired
    CustomerCardService cardCustomerService;
    @Autowired
    Environment environment;
    public static void main(String[] args) {
        SpringApplication.run(DemoOneToManyApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        //		 getCustomerWithCardDetails();
//		 addCustomer();
//		 deleteCardOfExistingCustomer();
		 deleteCustomer();

    }

    public void getCustomerWithCardDetails() {
        try {
            Integer customerId = 1001;
            CustomerCardDTO customerDTO = cardCustomerService.getCustomerDetails(customerId);
            LOGGER.info(customerDTO);
            if (customerDTO.getCards().isEmpty()) {
                LOGGER.info(environment.getProperty("UserInterface.NO_CARDS"));
            }
        }
        catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public void addCustomer() {
        try {
            CustomerCardDTO customerDTO = new CustomerCardDTO();
            customerDTO.setName("Tom Rosley");
            customerDTO.setEmailId("Tom@hnd.com");
            customerDTO.setDateOfBirth(LocalDate.of(1992, 1, 10));
            CardDTO cardDTO = new CardDTO();
            cardDTO.setCardId(12352);
            cardDTO.setCardNumber("6642160005012199");
            cardDTO.setExpiryDate(LocalDate.of(2024, 02, 27));
            CardDTO cardDTO2 = new CardDTO();
            cardDTO2.setCardId(12353);
            cardDTO2.setCardNumber("6642160005012200");
            cardDTO2.setExpiryDate(LocalDate.of(2022, 10, 15));
            List<CardDTO> cardDTOs = new LinkedList<>();
            cardDTOs.add(cardDTO);
            cardDTOs.add(cardDTO2);
            customerDTO.setCards(cardDTOs);
            cardCustomerService.addCustomer(customerDTO);
            LOGGER.info("\n" + environment.getProperty("UserInterface.CARD_AND_CUSTOMER_ADDED"));
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }



    public void deleteCardOfExistingCustomer() {
        try {
            Integer customerId = 1001;
            List<Integer> cardIdsToDelete = new ArrayList<>();
            cardIdsToDelete.add(12346);
            cardIdsToDelete.add(12347);
            cardCustomerService.deleteCardOfExistingCustomer(customerId, cardIdsToDelete);
            LOGGER.info("\n" + environment.getProperty("UserInterface.CARD_DEACTIVATED"));
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }
    public void deleteCustomer() {
        try {
            Integer customerId = 1001;
            cardCustomerService.deleteCustomer(customerId);
            LOGGER.info("\n" + environment.getProperty("UserInterface.CUSTOMER_DELETED"));
        } catch (Exception e) {
            String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
            LOGGER.info(message);
        }
    }


}
