package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.Promotion;
import com.kampus.kbazaar.promotion.PromotionService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;

    private final PromotionService promotionService;

    public CartController(CartService cartService, PromotionService promotionService) {
        this.cartService = cartService;
        this.promotionService = promotionService;
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartResponse>> getCart() {
        return ResponseEntity.ok(cartService.getAllCart());
    }

    @PostMapping("/carts/{username}/promotions")
    public CartResponse applyCartPromotion(
            @PathVariable String username, @RequestBody Promotion promotion) {
        return promotionService.applyPromotion(username, promotion);
    }
}
