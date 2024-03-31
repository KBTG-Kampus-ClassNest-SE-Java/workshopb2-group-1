package com.kampus.kbazaar.cartitem;

import com.kampus.kbazaar.cartitem.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> getCartByUsername(String username);
}
