package com.challenge.dto.balance;

import java.time.LocalDateTime;

public record BalanceRequestDto(
        LocalDateTime start,
        LocalDateTime end
) {
}
