package com.hnd.infinite.service;
import java.util.Optional;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.dto.LoanDTO;
import com.hnd.infinite.entity.Customer;
import com.hnd.infinite.entity.Loan;
import com.hnd.infinite.repository.Customer1Repository;
import com.hnd.infinite.repository.CustomerRepository;
import com.hnd.infinite.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService {
    @Autowired
    private Customer1Repository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanDTO getLoanDetails(Integer loanId) throws HnDBankException {
        Optional<Loan> optional = loanRepository.findById(loanId);
        Loan loan = optional.orElseThrow(() -> new HnDBankException("Service.LOAN_UNAVAILABLE"));
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setAmount(loan.getAmount());
        loanDTO.setLoanId(loan.getLoanId());
        loanDTO.setLoanIssueDate(loan.getIssueDate());
        loanDTO.setStatus(loan.getStatus());
        Customer customer = loan.getCustomer();
        if (customer != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setName(customer.getName());
            loanDTO.setCustomer(customerDTO);
        }
        return loanDTO;
    }
    @Override
    public Integer addLoanAndCustomer(LoanDTO loanDTO) throws HnDBankException {
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setIssueDate(loanDTO.getLoanIssueDate());
        loan.setStatus("open");
        CustomerDTO customerDTO = loanDTO.getCustomer();
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setName(customerDTO.getName());
        loan.setCustomer(customer);
        loanRepository.save(loan);
        return loan.getLoanId();
    }

    @Override
    public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws HnDBankException {
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setIssueDate(loanDTO.getLoanIssueDate());
        loan.setStatus(loanDTO.getStatus());
        Optional<Customer> optional = customerRepository.findById(customerId);
        Customer customer = optional.orElseThrow(()->new HnDBankException("Service.CUSTOMER_UNAVAILABLE"));
        loan.setCustomer(customer);
        loanRepository.save(loan);
        return loan.getLoanId();
    }

    @Override
    public void closeLoan(Integer loanId) throws HnDBankException {
        Optional<Loan> optional = loanRepository.findById(loanId);
        Loan loan = optional.orElseThrow(()->new HnDBankException("Service.LOAN_UNAVAILABLE"));
        loan.setStatus("Closed");
    }

    public void deleteLoan(Integer loanId) throws HnDBankException{
        Optional<Loan> optional = loanRepository.findById(loanId);
        Loan loan = optional.orElseThrow(()->new HnDBankException("Service.LOAN_UNAVAILABLE"));
        loan.setCustomer(null);
        loanRepository.delete(loan);
    }


    }

