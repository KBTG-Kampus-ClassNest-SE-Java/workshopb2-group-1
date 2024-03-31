package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String username;
    private List<CartItem> items;
    private double discount;
    private double totalDiscount;
    private int subtotal;
    private int grandTotal;

}
