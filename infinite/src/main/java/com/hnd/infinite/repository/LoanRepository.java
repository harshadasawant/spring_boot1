package com.hnd.infinite.repository;

import com.hnd.infinite.entity.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Integer> {
}
