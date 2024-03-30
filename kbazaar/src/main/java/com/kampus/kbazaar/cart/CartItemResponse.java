package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
