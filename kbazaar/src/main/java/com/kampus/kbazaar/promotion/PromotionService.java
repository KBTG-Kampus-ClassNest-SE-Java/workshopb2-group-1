package com.kampus.kbazaar.promotion;

import com.kampus.kbazaar.cart.CartRepository;
import com.kampus.kbazaar.cart.CartResponse;
import com.kampus.kbazaar.cartitem.CartItem;
import com.kampus.kbazaar.cartitem.CartItemRepository;
import com.kampus.kbazaar.cartitem.CartItemService;
import com.kampus.kbazaar.exceptions.NotFoundException;
import io.jsonwebtoken.lang.Strings;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    private final CartItemRepository cartItemRepository;

    private final CartItemService cartItemService;

    public PromotionService(
            PromotionRepository promotionRepository,
            CartItemRepository cartItemRepository,
            CartRepository cartRepository,
            CartItemService cartItemService) {
        this.promotionRepository = promotionRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartItemService = cartItemService;
    }

    public List<PromotionResponse> getAll() {
        return promotionRepository.findAll().stream().map(Promotion::toResponse).toList();
    }

    public PromotionResponse getPromotionByCode(String code) {
        return promotionRepository
                .findByCode(code)
                .map(Promotion::toResponse)
                .orElseThrow(() -> new NotFoundException("Promotion not found"));
    }

    public CartResponse applyPromotion(String username, Promotion promotion) {
        List<CartItem> cartItems = cartItemRepository.findByUsername(username);

        String[] productSkus = Strings.split(promotion.getProductSkus(), ",");
        Set<String> productSkuSet = Set.of(productSkus);

        for (CartItem item : cartItems) {
            if (productSkuSet.contains(item.getSku())) {
                item.setDiscount(promotion.getDiscountAmount().doubleValue());
                item.setPromotionCodes(promotion.getCode());
                cartItemRepository.save(item);
            }
        }

        return cartItemService.getCartByUsername(username);
    }
}
