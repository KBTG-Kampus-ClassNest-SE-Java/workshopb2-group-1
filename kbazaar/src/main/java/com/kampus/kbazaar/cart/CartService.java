package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.promotion.PromotionApplyCartRequest;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    public CartResponse applyCartPromotion(
            String username, PromotionApplyCartRequest promotionApplyCartRequest) {
        return new CartResponse();
    }
}
