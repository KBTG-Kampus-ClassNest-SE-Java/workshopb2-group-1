package com.kampus.kbazaar.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartResponseDto(
        String username,
        List<CartItemDto> items,
        BigDecimal discount,
        BigDecimal totalDiscount,
        BigDecimal subTotal,
        BigDecimal grandTotal) {}
