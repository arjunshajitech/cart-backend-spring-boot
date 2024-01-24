package com.arjunsaji.test.controller.order;

import com.arjunsaji.test.config.Constants;
import com.arjunsaji.test.entity.Order;
import com.arjunsaji.test.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(Constants.API_V1_ORDER)
public class OrderController {

    final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrdersOfUser(Authentication authentication) {
        return orderService.getAllOrders(authentication.getName());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(Authentication authentication,@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request,authentication.getName());
    }
}
