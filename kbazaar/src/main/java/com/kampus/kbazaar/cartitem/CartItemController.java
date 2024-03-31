package com.kampus.kbazaar.cartitem;

import com.kampus.kbazaar.cart.CartResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/carts/{username}/items")
    public ResponseEntity<CartResponse> addCartItem(
            @PathVariable String username, @RequestBody CartItem cartItem) {

        cartItem.setUsername(username);
        return new ResponseEntity<>(cartItemService.addCartItem(cartItem), HttpStatus.CREATED);
    }
}
