package com.kampus.kbazaar.product;

import com.kampus.kbazaar.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(Product::toResponse).toList();
    }

    public ProductResponse getBySku(String sku) {
        Optional<Product> product = productRepository.findBySku(sku);
        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return product.get().toResponse();
    }

    public List<ProductResponse> getAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return productRepository.findAll(pageable).stream().map(Product::toResponse).toList();
    }
}
