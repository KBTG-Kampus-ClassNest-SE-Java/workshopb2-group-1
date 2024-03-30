package com.kampus.kbazaar.promotion;

public record createPromotionResponse(
        String username,

        List<Item> items,

        int      discount,
        int        totalDiscount,
        double     subtotal,
        double     grandTotal,
        String      promotionCodes
) {

}
