package com.kampus.kbazaar.promotion;

import java.util.List;

public record PromotionApplyRequest(String code, List<String> productSkus) {}
