package com.challenge.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class InactiveCustomerDto {
    private Long customerId;
    private String customerName;
    private Long totalPurchases;
    private LocalDate lastPurchaseDate;

    public InactiveCustomerDto(Long customerId, String customerName, Long totalPurchases, LocalDateTime lastPurchaseDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPurchases = totalPurchases;
        this.lastPurchaseDate = LocalDate.parse(lastPurchaseDate.toString().substring(0, 10));
    }
}

