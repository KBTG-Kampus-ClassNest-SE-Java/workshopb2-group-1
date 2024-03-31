package com.kampus.kbazaar.cartitem;

import com.kampus.kbazaar.cart.Cart;
import com.kampus.kbazaar.cart.CartRepository;
import com.kampus.kbazaar.cart.CartResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public CartResponse getCartByUsername(String username) {
        Optional<Cart> optionalCartResponse = cartRepository.getCartByUsername(username);
        CartResponse cartResponse = new CartResponse();
        if (optionalCartResponse.isPresent()) {
            // get cart
            cartResponse = optionalCartResponse.get().toResponse();

            // get items
            List<CartItemResponse> cartItemResponseList = getAllItemsByUsername(username);

            // set items
            cartResponse.setItems(cartItemResponseList);

            // set response
            cartResponse.setTotalDiscount(
                    cartItemResponseList.stream()
                            .map(CartItemResponse::discount)
                            .map(BigDecimal::new)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .add(cartResponse.getDiscount()));

            cartResponse.setSubtotal(
                    cartItemResponseList.stream()
                            .map(CartItemResponse::price)
                            .map(BigDecimal::new)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

            cartResponse.setGrandTotal(cartResponse.getSubtotal().subtract(cartResponse.getTotalDiscount()));

        } else {
            Cart cart = new Cart();
            cart.setUsername(username);
            cart.setDiscount(BigDecimal.ZERO);
            cart.setTotalDiscount(BigDecimal.ZERO);
            cart.setSubtotal(BigDecimal.ZERO);
            cart.setGrandTotal(BigDecimal.ZERO);
            cart.setPromotionCodes("");
            cartResponse = cartRepository.save(cart).toResponse();
        }

        return cartResponse;
    }

    public List<CartItemResponse> getAllItemsByUsername(String username) {
        return cartItemRepository.findByUsername(username).stream()
                .map(CartItem::toResponse)
                .toList();
    }

    public CartResponse addCartItem(CartItem cartItem) {

        String username = cartItem.getUsername();

        // save new item
        cartItemRepository.save(cartItem);

        // get cart detail
        CartResponse cartResponse = getCartByUsername(username);

        // get items in cart
        List<CartItemResponse> cartItemResponseList = getAllItemsByUsername(username);

        // create response
        cartResponse.setItems(cartItemResponseList);

        // create response
        cartResponse = getCartByUsername(username);

        return cartResponse;
    }
}
