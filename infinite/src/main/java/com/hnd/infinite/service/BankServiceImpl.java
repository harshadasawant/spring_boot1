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
import java.util.List;
import java.util.Optional;
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

    @Override
    public void addExistingServiceToExistingCustomer(Integer customerId,
                                                     List<Integer> serviceIds) throws HnDBankException {

        Optional<Customers> optional = customerRepository.findById(customerId);
        Customers customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_UNAVAILABLE"));

        for(Integer serviceId : serviceIds) {
            Optional<Services> optional1 = servicesRepository.findById(serviceId);
            Services service = optional1.orElseThrow(() -> new HnDBankException("Service.SERVICE_UNAVAILABLE"));
            if(!customer.getBankServices().contains(service)) {
                customer.getBankServices().add(service);
            }
        }
    }

    @Override
    public void deallocateServiceForExistingCustomer(Integer customerId,
                                                     List<Integer> serviceIds) throws HnDBankException {

        Optional<Customers> optional = customerRepository.findById(customerId);
        Customers customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_UNAVAILABLE"));
        Set<Services> bankServices = customer.getBankServices();
        for(Integer serviceId:serviceIds) {
            Optional<Services> optional1 = servicesRepository.findById(serviceId);
            if(optional1.isPresent()) {
                Services service = optional1.get();
                bankServices.remove(service);
            }
        }
    }
    @Override
    public void deleteCustomer(Integer customerId) throws HnDBankException {

        Optional<Customers> optional = customerRepository.findById(customerId);
        Customers customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_CANNOT_DELETE"));
        customer.setBankServices(null);
        customerRepository.delete(customer);

    }


    @Override
    public CustomersDTO getCustomer(Integer customerId) throws HnDBankException {

        Optional<Customers> optional = customerRepository.findById(customerId);
        Customers customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_UNAVAILABLE"));
        CustomersDTO customerDTO = new CustomersDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setName(customer.getName());
        Set<Services> bankServicesSet = customer.getBankServices();
        Set<ServicesDTO> bankServicesDTO = new LinkedHashSet<>();
        if (bankServicesSet != null && !bankServicesSet.isEmpty()) {
            for (Services bankServices : bankServicesSet) {
                ServicesDTO servicesDTO = new ServicesDTO();
                servicesDTO.setServiceId(bankServices.getServiceId());
                servicesDTO.setServiceName(bankServices.getServiceName());
                servicesDTO.setServiceCost(bankServices.getServiceCost());
                bankServicesDTO.add(servicesDTO);
            }
            customerDTO.setBankServices(bankServicesDTO);
        }
        return customerDTO;
    }

}