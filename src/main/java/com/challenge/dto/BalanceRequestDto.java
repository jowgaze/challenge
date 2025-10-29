package com.challenge.dto;

import java.time.LocalDateTime;

public record BalanceRequestDto(
        LocalDateTime start,
        LocalDateTime end
) {
}
