package com.arjunsaji.test.database;

import com.arjunsaji.test.entity.Cart;
import com.arjunsaji.test.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CartRepositoryService {

    final CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart findByProductIdAndUserId(UUID productId, UUID id) {
        return cartRepository.findByUserIdAndProductId(id,productId);
    }

    public void deleteById(UUID id) {
        cartRepository.deleteById(id);
    }

    public List<Cart> findAllByUserId(UUID id) {
        return cartRepository.findAllByUserId(id);
    }

    public void deleteByProductId(UUID prodId) {
        cartRepository.deleteByProductId(prodId);
    }
}
