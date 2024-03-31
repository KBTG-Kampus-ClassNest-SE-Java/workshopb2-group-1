package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cartitem.CartItemResponse;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String username;
    private List<CartItemResponse> items;
    private BigDecimal discount;
    private BigDecimal totalDiscount;
    private BigDecimal subtotal;
    private BigDecimal grandTotal;
    private String promotionCodes;
    private double shippingFee;
}
