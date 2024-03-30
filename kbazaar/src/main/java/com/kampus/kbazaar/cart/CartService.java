package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.PromotionApplyCartRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartResponse applyCartPromotion(
            String username, PromotionApplyCartRequest promotionApplyCartRequest) {
        return new CartResponse();
    }

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartResponseDto> getAllCart() {
        List<Cart> carts = cartRepository.findAll();
        List<CartResponseDto> cartResponseDto = new ArrayList<>();
        for (Cart cart : carts) {
            List<CartItem> cartItems = cartItemRepository.findByUsername(cart.getUsername());
            List<CartItemDto> cartItemDtos = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                cartItemDtos.add(
                        new CartItemDto(
                                cartItem.getId(),
                                cartItem.getUsername(),
                                cartItem.getSku(),
                                cartItem.getPrice(),
                                cartItem.getQuantity(),
                                cartItem.getDiscount(),
                                cartItem.getPromotionCodes()));
            }

            CartResponseDto cartResponse =
                    new CartResponseDto(
                            cart.getUsername(),
                            cartItemDtos,
                            cart.getDiscount(),
                            cart.getTotalDiscount(),
                            cart.getSubtotal(),
                            cart.getGrandTotal());
        }

        return cartResponseDto;
    }
}
