package com.hnd.infinite.service;
import java.util.Optional;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.CustomerDTO;
import com.hnd.infinite.dto.LoanDTO;
import com.hnd.infinite.entity.Customer;
import com.hnd.infinite.entity.Loan;
import com.hnd.infinite.repository.CustomerRepository;
import com.hnd.infinite.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService {
    @Autowired
    private CustomerRepository customerRepository;

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
}

