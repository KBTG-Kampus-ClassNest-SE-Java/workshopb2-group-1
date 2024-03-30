package com.kampus.kbazaar.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class CartItem {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column (name = "sku")
    private String sku;
    @Column (name = "name")
    private String name;
    @Column (name = "price")
    private BigDecimal price;
    @Column (name = "quantity")
    private Integer quantity;
    @Column (name = "discount")
    private BigDecimal discount;
    @Column (name = "promotion_codes")
    private String promotionCodes;

}
