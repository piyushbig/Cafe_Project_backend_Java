package com.cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cafe.entity.Order;
import com.cafe.entity.OrderItem;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
	List<Order> findByUserId(int userid);
//    List<Order> findByCafeId(int cafeid);
    // Add more custom query methods if needed
	
	public void save(OrderItem orderItem);
	
	
}
