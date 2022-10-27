package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomersDTO;
import com.hnd.infinite.dto.ServicesDTO;
import com.hnd.infinite.entity.Customers;
import com.hnd.infinite.entity.Services;
import com.hnd.infinite.repository.CustomerRepository;
import com.hnd.infinite.repository.CustomersRepository;
import com.hnd.infinite.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;


@Service(value = "bankService")
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    private CustomersRepository customerRepository;

    @Autowired
    private ServicesRepository servicesRepository;
    @Override
    public Integer addCustomerAndService(CustomersDTO customerDTO) throws HnDBankException {
        Integer customerId = null;
        Set<ServicesDTO> bankServicesDTO = customerDTO.getBankServices();
        Customers customer = new Customers();
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setName(customerDTO.getName());
        Set<Services> bankServices = null;
        if (bankServicesDTO != null && !bankServicesDTO.isEmpty()) {
            bankServices = new LinkedHashSet<>();
            for (ServicesDTO servicesDTO : bankServicesDTO) {
                Services service = new Services();
                service.setServiceId(servicesDTO.getServiceId());
                service.setServiceName(servicesDTO.getServiceName());
                service.setServiceCost(servicesDTO.getServiceCost());
                bankServices.add(service);
            }
            customer.setBankServices(bankServices);
        }

        customerRepository.save(customer);
        customerId = customer.getCustomerId();
        return customerId;
    }
}