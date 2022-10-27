package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CardDTO;
import com.hnd.infinite.dto.CustomerCardDTO;
import com.hnd.infinite.entity.Card;
import com.hnd.infinite.entity.CustomerCard;
import com.hnd.infinite.repository.CardRepository;
import com.hnd.infinite.repository.CustomerCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerCardServiceImpl implements CustomerCardService {
    @Autowired
    private CustomerCardRepository customerRepository;

    @Autowired
    private CardRepository cardRepository;
    @Override
    public CustomerCardDTO getCustomerDetails(Integer customerId) throws HnDBankException {
        CustomerCardDTO customerDTO = new CustomerCardDTO();
        try {
            Optional<CustomerCard> optional = customerRepository.findById(customerId);
            System.out.println("===========" + optional);
            CustomerCard customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
            System.out.println("=================================" + customer);

            customerDTO.setEmailId(customer.getEmailId());
            System.out.println("===========" + customer.getEmailId());
            customerDTO.setName(customer.getName());
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            List<Card> cards = customer.getCards();
            List<CardDTO> cardDTOs = new LinkedList<>();

            if (!cards.isEmpty()) {
                cardDTOs = cards.stream()
                        .map(c -> new CardDTO(c.getCardId(), c.getCardNumber(), c.getExpiryDate()))
                        .collect(Collectors.toList());
            }
            customerDTO.setCards(cardDTOs);
            System.out.println("=====" + customerDTO);
        }catch(Exception e){
            e.printStackTrace();
        }
        return customerDTO;
    }

    @Override
    public Integer addCustomer(CustomerCardDTO customerDTO) throws HnDBankException {
        CustomerCard customer = new CustomerCard();
        customer.setName(customerDTO.getName());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        List<CardDTO> cardDTOs = customerDTO.getCards();
        List<Card> cards;

        cards = cardDTOs.stream()
                .map(c->new Card(c.getCardId(),c.getCardNumber(),c.getExpiryDate()))
                .collect(Collectors.toList());
        customer.setCards(cards);
        customerRepository.save(customer);
        return customer.getCustomerId();

    }


    @Override
    public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws HnDBankException {
        Optional<CustomerCard> optional = customerRepository.findById(customerId);
        CustomerCard customer = optional.orElseThrow(()->new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
        for (Integer cardId : cardIdsToDelete) {
            Optional<Card> optionalCard = cardRepository.findById(cardId);

        if(optionalCard.isPresent()) {
            customer.getCards().remove(optionalCard.orElse(null));
            cardRepository.deleteById(cardId);
        }
    }
}
    @Override
    public void deleteCustomer(Integer customerId) throws HnDBankException {
        Optional<CustomerCard> optional = customerRepository.findById(customerId);
        CustomerCard customer = optional.orElseThrow(()->new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
        customerRepository.delete(customer);
    }
}
