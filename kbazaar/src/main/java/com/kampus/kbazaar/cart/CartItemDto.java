package com.kampus.kbazaar.cart;

import java.math.BigDecimal;

public record CartItemDto(
        Integer id,
        String username,
        String sku,
        BigDecimal price,
        Integer quantity,
        BigDecimal discount,
        String promotionCodes) {}
