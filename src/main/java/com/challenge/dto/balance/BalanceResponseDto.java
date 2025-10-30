package com.challenge.dto.balance;

import java.math.BigDecimal;

public record BalanceResponseDto(
        String type,
        BigDecimal amount
) {
}
