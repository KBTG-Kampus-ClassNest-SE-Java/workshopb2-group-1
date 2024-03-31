package com.kampus.kbazaar.cartitem;

public record CartItemResponse(
        Long id,
        String username,
        String sku,
        String name,
        double price,
        int quantity,
        double discount,
        String promotionCodes) {}
