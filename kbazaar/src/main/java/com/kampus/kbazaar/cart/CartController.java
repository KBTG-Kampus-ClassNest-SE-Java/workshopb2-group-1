package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.PromotionApplyCartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public ResponseEntity getCart() { // NOSONAR
        return ResponseEntity.ok().build();
    }

    @PostMapping("/carts/{username}/promotions")
    public CartResponse applyCartPromotion(
            @PathVariable String username,
            @RequestBody PromotionApplyCartRequest promotionApplyCartRequest) {
        return cartService.applyCartPromotion(username, promotionApplyCartRequest);
    }
}
