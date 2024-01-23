package com.arjunsaji.test.controller.cart;

import com.arjunsaji.test.config.Constants;
import com.arjunsaji.test.entity.Cart;
import com.arjunsaji.test.request.CartRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(Constants.API_V1_CART)
public class CardController {

    final CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(Authentication authentication, @RequestBody CartRequest cartRequest) {
        return cartService.addToCart(cartRequest, authentication.getName());
    }

    @PostMapping("/remove-from-cart/{productId}")
    public ResponseEntity<?> removeFromCart(Authentication authentication, @PathVariable("productId") UUID id) {
        return cartService.removeFromCart(id, authentication.getName());
    }

    @GetMapping
    public List<Cart> listAllUserCart(Authentication authentication) {
        return cartService.listAllUserCart(authentication.getName());
    }
}
