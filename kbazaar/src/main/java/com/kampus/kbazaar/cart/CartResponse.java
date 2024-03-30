package com.kampus.kbazaar.cart;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    //    private Long id;

    private String username;

    private List<CartItemResponse> items;

    private BigDecimal discount;

    private BigDecimal totalDiscount;

    private BigDecimal subtotal;

    private BigDecimal grandTotal;

    private String promotionCodes;
}
