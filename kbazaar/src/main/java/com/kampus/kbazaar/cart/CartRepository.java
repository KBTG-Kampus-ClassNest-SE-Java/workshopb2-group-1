package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> getCartByUsername(String username);
}
