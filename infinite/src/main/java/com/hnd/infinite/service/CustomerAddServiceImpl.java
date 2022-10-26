package com.hnd.infinite.service;
import java.util.Optional;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.AddressDTO;
import com.hnd.infinite.dto.CustomerAddDTO;
import com.hnd.infinite.entity.Address;
import com.hnd.infinite.entity.CustomerAdd;
import com.hnd.infinite.repository.CustomerAddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "customerAddService")
@Transactional
public class CustomerAddServiceImpl implements CustomerAddService {
    @Autowired
    private CustomerAddRepository customerRepository;
    @Override
    public CustomerAddDTO getCustomer(Integer customerId) throws HnDBankException {
        Optional<CustomerAdd> optional = customerRepository.findById(customerId);
        CustomerAdd customer = optional.orElseThrow(() -> new HnDBankException("Service.INVALID_CUSTOMERID"));
        CustomerAddDTO customerAddDTO = new CustomerAddDTO();
        customerAddDTO.setCustomerId(customer.getCustomerId());
        customerAddDTO.setName(customer.getName());
        customerAddDTO.setEmailId(customer.getEmailId());
        customerAddDTO.setDateOfBirth(customer.getDateOfBirth());
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(customer.getAddress().getAddressId());
        addressDTO.setCity(customer.getAddress().getCity());
        addressDTO.setStreet(customer.getAddress().getStreet());
        customerAddDTO.setAddress(addressDTO);
        return customerAddDTO;
    }
    @Override
    public Integer addCustomer(CustomerAddDTO customerDTO) {
        CustomerAdd customerAdd = new CustomerAdd();
        customerAdd.setCustomerId(customerDTO.getCustomerId());
        customerAdd.setDateOfBirth(customerDTO.getDateOfBirth());
        customerAdd.setEmailId(customerDTO.getEmailId());
        customerAdd.setName(customerDTO.getName());

        Address address = new Address();
        address.setAddressId(customerDTO.getAddress().getAddressId());
        address.setCity(customerDTO.getAddress().getCity());
        address.setStreet(customerDTO.getAddress().getStreet());

        customerAdd.setAddress(address);
        customerRepository.save(customerAdd);
        return customerAdd.getCustomerId();
    }
    @Override
    public void updateAddress(Integer customerId, AddressDTO addressDTO) throws HnDBankException {
        Optional<CustomerAdd> optional = customerRepository.findById(customerId);
        CustomerAdd customer = optional.orElseThrow(() -> new HnDBankException("Service.INVALID_CUSTOMERID"));
        Address address = customer.getAddress();
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
    }

    @Override
    public void deleteCustomer(Integer customerId) throws HnDBankException {
        Optional<CustomerAdd> optional = customerRepository.findById(customerId);
        CustomerAdd customer = optional.orElseThrow(() -> new HnDBankException("Service.INVALID_CUSTOMERID"));
        customerRepository.delete(customer);
    }
    @Override
    public void deleteCustomerOnly(Integer customerId) throws HnDBankException {
        Optional<CustomerAdd> optional = customerRepository.findById(customerId);
        CustomerAdd customer = optional.orElseThrow(() -> new HnDBankException("Service.INVALID_CUSTOMERID"));
        customer.setAddress(null);
        customerRepository.delete(customer);
    }




}
