package com.arjunsaji.test.repository;

import com.arjunsaji.test.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findByUserIdAndProductId(UUID id, UUID productId);

    List<Cart> findAllByUserId(UUID id);

    void deleteByProductId(UUID prodId);
}
