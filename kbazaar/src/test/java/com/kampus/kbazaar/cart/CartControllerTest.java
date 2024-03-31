package com.kampus.kbazaar.cart;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kampus.kbazaar.promotion.PromotionService;
import com.kampus.kbazaar.security.JwtAuthFilter;
import java.math.BigDecimal;
import java.util.List;
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
        controllers = CartController.class,
        excludeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = JwtAuthFilter.class))
public class CartControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private CartService cartService;

    @MockBean private PromotionService promotionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCart_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/v1/carts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCards_ReturnsCorrectResponse() throws Exception {
        when(cartService.getAllCart())
                .thenReturn(
                        List.of(
                                CartResponse.builder()
                                        .username("TechNinja")
                                        .discount(BigDecimal.valueOf(0))
                                        .totalDiscount(BigDecimal.valueOf(0))
                                        .subtotal(BigDecimal.valueOf(1))
                                        .grandTotal(BigDecimal.valueOf(1))
                                        .items(List.of())
                                        .build()));

        mockMvc.perform(get("/api/v1/carts").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].username").value("TechNinja"))
                .andExpect(jsonPath("$[0].items").isArray())
                .andExpect(jsonPath("$[0].discount").value(0))
                .andExpect(jsonPath("$[0].totalDiscount").value(0))
                .andExpect(jsonPath("$[0].subtotal").value(1))
                .andExpect(jsonPath("$[0].grandTotal").value(1))
                .andExpect(status().isOk());
    }
}
