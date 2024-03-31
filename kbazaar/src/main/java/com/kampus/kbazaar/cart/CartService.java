package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.cartitem.CartItemService;
import com.kampus.kbazaar.shopper.Shopper;
import com.kampus.kbazaar.shopper.ShopperRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartItemService cartItemService;

    private final ShopperRepository shopperRepository;

    public CartService(ShopperRepository shopperRepository, CartItemService cartItemService) {
        this.shopperRepository = shopperRepository;
        this.cartItemService = cartItemService;
    }

    // get all carts
    public List<CartResponse> getAllCart() {

        // get all user
        List<String> usernameList =
                shopperRepository.findAll().stream().map(Shopper::getUsername).toList();

        // for loop get cart by username

        return usernameList.stream().map(cartItemService::getCartByUsername).toList();
    }
}
