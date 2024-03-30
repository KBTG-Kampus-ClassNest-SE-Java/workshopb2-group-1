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
