package com.springboot.repository;

import com.springboot.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProRepository extends JpaRepository<Order, Long> {

}
