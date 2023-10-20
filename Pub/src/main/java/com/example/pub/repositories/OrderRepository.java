package com.example.pub.repositories;

import com.example.pub.models.Commission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Commission, Long> {
}
