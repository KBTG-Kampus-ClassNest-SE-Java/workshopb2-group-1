package com.kampus.kbazaar.promotion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.kampus.kbazaar.cart.CartRepository;
import com.kampus.kbazaar.cart.CartResponse;
import com.kampus.kbazaar.cartitem.CartItem;
import com.kampus.kbazaar.cartitem.CartItemRepository;
import com.kampus.kbazaar.cartitem.CartItemResponse;
import com.kampus.kbazaar.cartitem.CartItemService;
import com.kampus.kbazaar.exceptions.NotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PromotionServiceTest {

    @Mock private PromotionRepository promotionRepository;

    @Mock private CartItemRepository mockCartItemRepository;

    @Mock private CartRepository mockCartRepository;

    @Mock private CartItemService mockCartItemService;

    @InjectMocks private PromotionService promotionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should be able to get all promotions")
    void shouldBeAbleToGetAllPromotion() {
        // Arrange
        Promotion promotion1 = new Promotion();
        Promotion promotion2 = new Promotion();
        when(promotionRepository.findAll()).thenReturn(Arrays.asList(promotion1, promotion2));

        // Act
        List<PromotionResponse> promotionResponses = promotionService.getAll();

        // Assert
        assertEquals(2, promotionResponses.size());
    }

    @Test
    @DisplayName("should be able to get promotion by code")
    void getPromotionByCode_ExistingCode_ShouldReturnPromotionResponse() {
        // Arrange
        String code = "BUY2GET1FREE";
        Promotion promotion = new Promotion();
        promotion.setCode(code);
        when(promotionRepository.findByCode(code)).thenReturn(Optional.of(promotion));

        // Act
        PromotionResponse promotionResponse = promotionService.getPromotionByCode(code);

        // Assert
        assertNotNull(promotionResponse);
        assertEquals(code, promotionResponse.code());
    }

    @Test
    @DisplayName("should throw NotFoundException when promotion code not found")
    void getPromotionByCode_NonExistingCode_ShouldThrowNotFoundException() {
        // Arrange
        String code = "NON-EXISTING-CODE";
        when(promotionRepository.findByCode(code)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> promotionService.getPromotionByCode(code));
    }

    @Test
    @DisplayName("should Appply Promotion To The RightProduct")
    void shouldAppplyPromotionToTheRightProduct() {

        // Mock repository method returning empty optional
        PromotionService productService =
                new PromotionService(
                        promotionRepository,
                        mockCartItemRepository,
                        mockCartRepository,
                        mockCartItemService);

        Long promotionId = 1L;
        String code = "SUMMER50";
        String name = "Summer Sale";
        String description = "Get 50% off on summer collection";
        LocalDateTime startDate =
                LocalDateTime.of(2024, 6, 1, 0, 0); // Start date: 2024-06-01 00:00
        LocalDateTime endDate = LocalDateTime.of(2024, 6, 30, 23, 59); // End date: 2024-06-30 23:59
        String discountType = "Percentage";
        BigDecimal discountAmount = BigDecimal.valueOf(50); // 50% discount
        BigDecimal maxDiscountAmount = BigDecimal.valueOf(100); // Maximum discount amount
        String applicableTo = "Summer Collection";
        String productSkus = "SKU001,SKU002,SKU003";
        Integer minQuantity = 1;
        Integer freeQuantity = 0;

        Promotion promotion =
                new Promotion(
                        promotionId,
                        code,
                        name,
                        description,
                        startDate,
                        endDate,
                        discountType,
                        discountAmount,
                        maxDiscountAmount,
                        applicableTo,
                        productSkus,
                        minQuantity,
                        freeQuantity);

        Long id = 1L;
        String username = "john_doe";
        String sku = "SKU001";
        String nameCartItem = "Sample Product";
        double price = 50.99;
        Integer quantity = 1;
        double discount = 50.00;
        String promotionCodes = "SUMMER50";

        CartItemResponse cartItemResponseExpected =
                new CartItemResponse(
                        id, username, sku, nameCartItem, price, quantity, discount, promotionCodes);

        List<CartItem> cartItems =
                List.of(
                        CartItem.builder()
                                .id(id)
                                .username(username)
                                .sku(sku)
                                .name(nameCartItem)
                                .price(price)
                                .quantity(quantity)
                                .discount(0)
                                .promotionCodes("")
                                .build());

        when(mockCartItemRepository.findByUsername(username)).thenReturn(cartItems);
        when(mockCartItemService.getCartByUsername(username))
                .thenReturn(
                        CartResponse.builder()
                                .items(List.of(cartItemResponseExpected))
                                .username(username)
                                .build());

        CartResponse cartResponseActual = promotionService.applyPromotion(username, promotion);

        assertEquals(cartItemResponseExpected.username(), cartResponseActual.getUsername());
        assertEquals(1, cartResponseActual.getItems().size());
        assertEquals(promotionCodes, cartResponseActual.getItems().get(0).promotionCodes());
        assertEquals(discount, cartResponseActual.getItems().get(0).discount());
    }
}
