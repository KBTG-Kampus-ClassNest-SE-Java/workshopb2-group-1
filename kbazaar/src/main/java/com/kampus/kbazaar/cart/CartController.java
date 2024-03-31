package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.PromotionApplyCartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

        @GetMapping("/carts")
        public ResponseEntity<List<CartResponse>> getCart() {
//            List<CartResponse> carts = cartService.getAllCart();
            return ResponseEntity.ok(Collections.emptyList());
        }

    @PostMapping("/carts/{username}/promotions")
    public CartResponse applyCartPromotion(
            @PathVariable String username,
            @RequestBody PromotionApplyCartRequest promotionApplyCartRequest) {
        return cartService.applyCartPromotion(username, promotionApplyCartRequest);
    }
}
