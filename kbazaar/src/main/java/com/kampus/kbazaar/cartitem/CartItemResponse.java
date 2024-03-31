package com.kampus.kbazaar.cartitem;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



public record CartItemResponse (

    Long id,
    String username,
    String sku,
    String name,
    double price,
    int quantity,
    double discount,
    String promotionCodes

) {}
