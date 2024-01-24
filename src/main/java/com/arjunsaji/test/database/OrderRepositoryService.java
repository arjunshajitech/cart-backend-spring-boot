package com.arjunsaji.test.database;

import com.arjunsaji.test.entity.Order;
import com.arjunsaji.test.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderRepositoryService {

    final OrderRepository orderRepository;

    public List<Order> findAllByUserId(UUID id) {
        return orderRepository.findAllByUserId(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }
}
