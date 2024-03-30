package com.kampus.kbazaar.cart;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;

    }

    public List<CartResponseDto> getAllCart() {
        List<CartEntity> carts = cartRepository.findAll();
        List <CartResponseDto> cartResponseDto = new ArrayList<>();
        for (CartEntity cart : carts) {
            List<CartItem> cartItems = cartItemRepository.findByUsername(cart.getUsername());
            List<CartItemDto> cartItemDtos = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                cartItemDtos.add(new CartItemDto(cartItem.getId(), cartItem.getUsername(), cartItem.getSku(), cartItem.getPrice(), cartItem.getQuantity(), cartItem.getDiscount(),cartItem.getPromotionCodes()));
            }

            CartResponseDto cartResponse = new CartResponseDto(cart.getUsername(), cartItemDtos, cart.getDiscount(), cart.getTotalDiscount(), cart.getSubTotal(), cart.getGrandTotal());

        }

        return cartResponseDto;
    }

}
