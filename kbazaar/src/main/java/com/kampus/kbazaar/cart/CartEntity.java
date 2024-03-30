package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
@Data

public class CartEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column (name = "discount")
    private BigDecimal discount;
    @Column (name = "total_discount")
    private BigDecimal totalDiscount;
    @Column (name = "promotion_codes")
    private String promotionCodes;
    @Column (name = "subtotal")
    private BigDecimal subTotal;
    @Column (name = "grand_total")
    private BigDecimal grandTotal;

}
