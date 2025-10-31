package com.challenge.dto;

import java.time.LocalDateTime;

public record IntervalDto(
        LocalDateTime start,
        LocalDateTime end
) {
}
