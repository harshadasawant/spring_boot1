package com.hnd.infinite.repository;

import com.hnd.infinite.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface Customer1Repository extends JpaRepository<Customer, Integer> {
    List<Customer> findByEmailId(String emailId);

    @Query("SELECT c.name FROM Customer c WHERE c.emailId = :emailId")
    List<String> findNameByEmailId(@Param("emailId") String emailId);

    @Query("UPDATE Customer c SET c.emailId = ?1 WHERE c.customerId = ?2")
    @Modifying
    @Transactional
    Integer updateCustomerEmailId(String updateCustomerByEmailId, Integer customerId);


}
