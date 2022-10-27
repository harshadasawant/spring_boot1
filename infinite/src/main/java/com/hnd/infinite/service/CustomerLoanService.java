package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.LoanDTO;

public interface CustomerLoanService {
    public LoanDTO getLoanDetails(Integer loanId) throws HnDBankException;
    public Integer addLoanAndCustomer(LoanDTO loanDTO) throws HnDBankException;
    public Integer sanctionLoanToExistingCustomer(Integer customerId,LoanDTO loanDTO) throws HnDBankException;
    public void closeLoan(Integer loanId) throws HnDBankException;
    public void deleteLoan(Integer loanId) throws HnDBankException;
}
