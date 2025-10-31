package com.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@Getter
@Setter
public class TicketDto {
    private Long channelId;
    private String channelName;
    private Long storeId;
    private String storeName;
    private Long totalSales;
    private BigDecimal revenue;
    private BigDecimal avgTicket;

    public TicketDto(Long channelId, String channelName, Long storeId, String storeName, Long totalSales, Double revenue) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.storeId = storeId;
        this.storeName = storeName;
        this.totalSales = totalSales;
        this.revenue = BigDecimal.valueOf(revenue).setScale(2, RoundingMode.HALF_EVEN);;
        this.avgTicket = BigDecimal.valueOf(revenue/totalSales).setScale(2, RoundingMode.HALF_EVEN);;
    }
}
