package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartResponse> getAllCarts() {
        return null;
    }


    public CartResponse getCartByUsername(String username) {
        List<CartItem> cartItems = cartItemRepository.findByUsername(username);
        return CartResponse.builder()
                .username(username)
                .items(cartItems)
                .discount(0)
                .totalDiscount(0)
                .subtotal(0)
                .grandTotal(0)
                .build();
    }





    public CartResponse addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return getCartByUsername(cartItem.getUsername());
    }

}
