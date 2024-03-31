package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.PromotionApplyCartRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //    @GetMapping("/carts")
    //    public ResponseEntity<List<CartResponse>> getCart() {
    //        List<CartResponse> carts = cartService.getAllCart();
    //        return ResponseEntity.ok(carts);
    //    }

    @PostMapping("/carts/{username}/promotions")
    public CartResponse applyCartPromotion(
            @PathVariable String username,
            @RequestBody PromotionApplyCartRequest promotionApplyCartRequest) {
        return cartService.applyCartPromotion(username, promotionApplyCartRequest);
    }
}
