package com.hnd.infinite.service;

import com.hnd.infinite.Exception.HnDBankException;
import com.hnd.infinite.dto.LoanDTO;

public interface CustomerLoanService {
    public LoanDTO getLoanDetails(Integer loanId) throws HnDBankException;
}
