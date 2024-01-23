package com.arjunsaji.test.controller.cart;

import com.arjunsaji.test.database.CartRepositoryService;
import com.arjunsaji.test.database.ProductRepositoryService;
import com.arjunsaji.test.database.UserRepositoryService;
import com.arjunsaji.test.entity.Cart;
import com.arjunsaji.test.entity.Product;
import com.arjunsaji.test.entity.User;
import com.arjunsaji.test.request.CartRequest;
import com.arjunsaji.test.response.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    final CartRepositoryService cartRepositoryService;
    final ProductRepositoryService productRepositoryService;
    final UserRepositoryService userRepositoryService;


    public ResponseEntity<?> addToCart(CartRequest cartRequest, String email) {
        User user = userRepositoryService.findByEmail(email);
        Optional<Product> product = productRepositoryService.findById(cartRequest.getProductId());
        if (product.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Product not found."));

        Cart cart = cartRepositoryService.findByProductIdAndUserId(cartRequest.getProductId(),user.getId());
        if (cart != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Already item in the cart."));

        cartRepositoryService.save(new Cart(user.getId(),product.get().getId(),product.get().getPrice()));
        return ResponseEntity.status(HttpStatus.OK).body(new Message("Add to cart success."));
    }

    public ResponseEntity<?> removeFromCart(UUID id, String email) {
        User user = userRepositoryService.findByEmail(email);
        Cart cart = cartRepositoryService.findByProductIdAndUserId(id,user.getId());
        if (cart == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Product not found."));

        cartRepositoryService.deleteById(cart.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new Message("Removed from cart success."));
    }

    public List<Cart> listAllUserCart(String email) {
        User user = userRepositoryService.findByEmail(email);
        return cartRepositoryService.findAllByUserId(user.getId());
    }
}
