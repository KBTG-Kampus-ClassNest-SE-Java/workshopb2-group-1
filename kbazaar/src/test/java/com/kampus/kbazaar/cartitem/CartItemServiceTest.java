package com.kampus.kbazaar.cartitem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kampus.kbazaar.cart.Cart;
import com.kampus.kbazaar.cart.CartRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartItemServiceTest {

    @InjectMocks private CartItemService cartItemService;

    @Mock private CartItemRepository cartItemRepository;

    @Mock private CartRepository cartRepository;

    @Test
    void shouldAddCartItem() {
        CartItem cartItem =
                CartItem.builder()
                        .id(1L)
                        .username("username")
                        .sku("sku")
                        .name("name")
                        .price(100.0)
                        .quantity(1)
                        .discount(0)
                        .promotionCodes("promo-code")
                        .build();

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUsername("mockusername");
        cart.setDiscount(BigDecimal.ZERO);
        cart.setTotalDiscount(BigDecimal.ZERO);
        cart.setPromotionCodes("promo-code");
        cart.setSubtotal(BigDecimal.ZERO);
        cart.setGrandTotal(BigDecimal.ZERO);

        List<CartItemResponse> cartItemResponseList = new ArrayList<>();

        when(cartRepository.getCartByUsername(anyString())).thenReturn(Optional.of(cart));
        when(cartItemService.getAllItemsByUsername(anyString())).thenReturn(cartItemResponseList);
        cartItemService.addCartItem(cartItem);

        verify(cartItemRepository).save(any());
    }
}
