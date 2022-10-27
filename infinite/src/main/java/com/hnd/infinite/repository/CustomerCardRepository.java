package com.hnd.infinite.repository;

import com.hnd.infinite.entity.CustomerCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCardRepository extends CrudRepository<CustomerCard, Integer> {
}
