package com.hnd.infinite.repository;

import com.hnd.infinite.entity.Card;
import org.springframework.data.repository.CrudRepository;
public interface CardRepository extends CrudRepository<Card, Integer> {
}
