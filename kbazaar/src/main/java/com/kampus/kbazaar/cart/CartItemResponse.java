package com.kampus.kbazaar.cart;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    private Long id;

    private String username;

    private String sku;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal discount;

    private String promotionCodes;
}
