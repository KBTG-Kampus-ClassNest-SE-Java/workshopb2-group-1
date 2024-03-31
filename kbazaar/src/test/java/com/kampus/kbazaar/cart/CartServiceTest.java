package com.kampus.kbazaar.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.kampus.kbazaar.cartitem.CartItemService;
import com.kampus.kbazaar.shopper.Shopper;
import com.kampus.kbazaar.shopper.ShopperRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock private CartItemService mockCartItemService;

    @Mock private ShopperRepository mockShopperRepository;

    @InjectMocks private CartService cartService;

    @Test
    void shouldGetAllCart() {
        when(mockShopperRepository.findAll())
                .thenReturn(
                        List.of(
                                Shopper.builder()
                                        .id(1L)
                                        .username("username")
                                        .email("email@e.com")
                                        .build()));

        when(mockCartItemService.getCartByUsername(anyString()))
                .thenReturn(
                        CartResponse.builder()
                                .username("username")
                                .discount(BigDecimal.ZERO)
                                .totalDiscount(BigDecimal.ZERO)
                                .promotionCodes("promo-code")
                                .subtotal(BigDecimal.valueOf(1))
                                .grandTotal(BigDecimal.valueOf(1))
                                .build());

        List<CartResponse> response = cartService.getAllCart();

        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getUsername(), "username");
        assertEquals(response.get(0).getDiscount(), BigDecimal.ZERO);
        assertEquals(response.get(0).getTotalDiscount(), BigDecimal.ZERO);
        assertEquals(response.get(0).getPromotionCodes(), "promo-code");
        assertEquals(response.get(0).getSubtotal(), BigDecimal.valueOf(1));
        assertEquals(response.get(0).getGrandTotal(), BigDecimal.valueOf(1));
    }
}
