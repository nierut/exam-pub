package com.example.pub.repositories;

import com.example.pub.models.Commission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Commission, Long> {
    public List<Commission> getCommissionsByProductName(String name);
}
