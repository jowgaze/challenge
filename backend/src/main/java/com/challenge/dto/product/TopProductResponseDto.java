package com.challenge.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor
@Getter
@Setter
public class TopProductResponseDto {
    private Long position;
    private String name;
    private BigDecimal totalSold;
    private BigDecimal totalRevenue;

    public TopProductResponseDto(String name, Double totalSold, Double totalRevenue) {
        this.name = name;
        this.totalSold = BigDecimal.valueOf(totalSold).setScale(2, RoundingMode.HALF_EVEN);;
        this.totalRevenue = BigDecimal.valueOf(totalRevenue).setScale(2, RoundingMode.HALF_EVEN);;
    }
}
