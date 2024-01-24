package com.arjunsaji.test.controller.order;

import com.arjunsaji.test.database.CartRepositoryService;
import com.arjunsaji.test.database.OrderRepositoryService;
import com.arjunsaji.test.database.ProductRepositoryService;
import com.arjunsaji.test.database.UserRepositoryService;
import com.arjunsaji.test.entity.Order;
import com.arjunsaji.test.entity.Product;
import com.arjunsaji.test.entity.User;
import com.arjunsaji.test.request.CreateOrderRequest;
import com.arjunsaji.test.response.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    final OrderRepositoryService orderRepositoryService;
    final UserRepositoryService userRepositoryService;
    final ProductRepositoryService productRepositoryService;
    final CartRepositoryService cartRepositoryService;

    public List<Order> getAllOrders(String email) {
        User user = userRepositoryService.findByEmail(email);
        return orderRepositoryService.findAllByUserId(user.getId());
    }

    public ResponseEntity<?> createOrder(CreateOrderRequest request, String email) {

        User user = userRepositoryService.findByEmail(email);
        UUID groupId = UUID.randomUUID();
        if (request.getProductId().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Products cannot be empty."));

        for (UUID prodId : request.getProductId()) {
            Optional<Product> product = productRepositoryService.findById(prodId);
            if (product.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Products not found. ID : " + prodId));
        }

        for (UUID prodId : request.getProductId()) {
            Optional<Product> product = productRepositoryService.findById(prodId);
            orderRepositoryService.save(new Order(user.getId(),prodId,groupId,product.get().getPrice()));
            cartRepositoryService.deleteByProductId(prodId);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new Message("Order successful."));
    }
}
