package com.challenge.dto.report;

import com.challenge.dto.TicketDto;
import com.challenge.dto.balance.BalanceResponseDto;
import com.challenge.dto.customer.InactiveCustomerDto;
import com.challenge.dto.product.TopProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDto {
    private String storeName;
    private String channelName;
    private LocalDate start;
    private LocalDate end;
    private List<BalanceResponseDto> balances;
    private List<TopProductResponseDto> topProducts;
    private List<TicketDto> tickets;
    private List<InactiveCustomerDto> inactiveCustomer;
}
