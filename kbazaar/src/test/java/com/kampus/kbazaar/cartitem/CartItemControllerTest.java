package com.kampus.kbazaar.cartitem;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kampus.kbazaar.cart.CartResponse;
import com.kampus.kbazaar.security.JwtAuthFilter;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(
        controllers = CartItemController.class,
        excludeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = JwtAuthFilter.class))
class CartItemControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private CartItemService cartItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCartItem_ReturnsOk() throws Exception {
        String requestBody =
                "{\n"
                        + "  \"code\": \"FIXEDAMOUNT2\",\n"
                        + "  \"name\": \"Fixed Amount $2 Off Specific Products\",\n"
                        + "  \"description\": \"Get $2 off on specific products.\",\n"
                        + "  \"startDate\": \"2024-03-25T10:30:00.000Z\",\n"
                        + "  \"endDate\": \"2025-05-25T10:30:00.000Z\",\n"
                        + "  \"discountType\": \"FIXED_AMOUNT\",\n"
                        + "  \"discountAmount\": 2.00,\n"
                        + "  \"applicableTo\": \"SPECIFIC_PRODUCTS\",\n"
                        + "  \"productSkus\": \"STATIONERY-STAPLER-SWINGLINE,STATIONERY-PENCIL-FABER-CASTELL\"\n"
                        + "}";

        CartResponse cartResponse =
                CartResponse.builder()
                        .username("username")
                        .items(null)
                        .discount(BigDecimal.ZERO)
                        .totalDiscount(BigDecimal.ZERO)
                        .subtotal(BigDecimal.ZERO)
                        .grandTotal(BigDecimal.ZERO)
                        .promotionCodes("")
                        .build();

        when(cartItemService.addCartItem(any())).thenReturn(cartResponse);
        mockMvc.perform(
                        post("/api/v1/carts/username/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isCreated());

        verify(cartItemService, times(1)).addCartItem(any());
    }
}
