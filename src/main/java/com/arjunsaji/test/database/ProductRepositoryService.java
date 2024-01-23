package com.arjunsaji.test.database;

import com.arjunsaji.test.entity.Product;
import com.arjunsaji.test.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductRepositoryService {

    final ProductRepository productRepository;

    public Product findByProductName(String name) {
        return productRepository.findByProductName(name);
    }

    public void save(Product products) {
        productRepository.save(products);
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
