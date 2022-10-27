package com.hnd.infinite.repository;
import com.hnd.infinite.entity.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {
}

