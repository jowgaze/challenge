package com.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
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
}
