package com.springboot.repository;

import com.springboot.domain.Order;

public interface OrderRepository {
//    주문목록 저장
    Long saveOrder(Order order);
}
