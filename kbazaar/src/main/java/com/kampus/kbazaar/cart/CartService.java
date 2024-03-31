package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cartitem.CartItemRepository;
import com.kampus.kbazaar.cartitem.CartItemService;
import com.kampus.kbazaar.promotion.PromotionApplyCartRequest;
import com.kampus.kbazaar.shopper.Shopper;
import com.kampus.kbazaar.shopper.ShopperRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;

    private final ShopperRepository shopperRepository;

    public CartResponse applyCartPromotion(
            String username, PromotionApplyCartRequest promotionApplyCartRequest) {
        return new CartResponse();
    }

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ShopperRepository shopperRepository,
            CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.shopperRepository = shopperRepository;
        this.cartItemService = cartItemService;
    }

    // get all carts
    public List<CartResponse> getAllCart() {

        // getb all user
        List<String> usernameList =
                shopperRepository.findAll().stream().map(Shopper::getUsername).toList();

        // for loop getcartby usernam
        List<CartResponse> carts =
                usernameList.stream().map(cartItemService::getCartByUsername).toList();

        return carts;
    }

    //    public List<CartResponse> getAllCart() {
    //        List<Cart> carts = cartRepository.findAll();
    //        List<CartResponse> cartResponseDto = new ArrayList<>();
    //        for (Cart cart : carts) {
    //            List<CartItem> cartItems = cartItemRepository.findByUsername(cart.getUsername());
    //            List<CartItemDto> cartItemDtos = new ArrayList<>();
    //            for (CartItem cartItem : cartItems) {
    //                cartItemDtos.add(
    //                        new CartItemDto(
    //                                cartItem.getId(),
    //                                cartItem.getUsername(),
    //                                cartItem.getSku(),
    //                                cartItem.getPrice(),
    //                                cartItem.getQuantity(),
    //                                cartItem.getDiscount(),
    //                                cartItem.getPromotionCodes()));
    //            }
    //
    //            CartResponseDto cartResponse =
    //                    new CartResponseDto(
    //                            cart.getUsername(),
    //                            cartItemDtos,
    //                            cart.getDiscount(),
    //                            cart.getTotalDiscount(),
    //                            cart.getSubtotal(),
    //                            cart.getGrandTotal());
    //        }
    //
    //        return cartResponseDto;
    //    }
}
