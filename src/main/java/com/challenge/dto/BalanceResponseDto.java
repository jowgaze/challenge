package com.challenge.dto;

import java.math.BigDecimal;

public record BalanceResponseDto(
        String type,
        BigDecimal amount
) {
}
