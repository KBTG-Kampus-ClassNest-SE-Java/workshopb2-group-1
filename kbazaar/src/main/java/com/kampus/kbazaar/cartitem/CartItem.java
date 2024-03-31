package com.kampus.kbazaar.cartitem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "discount")
    private double discount;

    @Column(name = "promotion_codes")
    private String promotionCodes;

    public CartItemResponse toResponse() {
        return new CartItemResponse(
                this.id,
                this.username,
                this.sku,
                this.name,
                this.price,
                this.quantity,
                this.discount,
                this.promotionCodes);
    }
}
