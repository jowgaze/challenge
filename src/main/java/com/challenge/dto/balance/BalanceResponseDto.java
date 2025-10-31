package com.challenge.dto.balance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor
@Getter
@Setter
public class BalanceResponseDto {
    private String channelName;
    private BigDecimal amount;

    public BalanceResponseDto(String channelName, Double amount) {
        this.channelName = channelName;
        this.amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_EVEN);
    }
}