package com.challenge.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InactiveCustomerDto {
    private Long customerId;
    private String customerName;
    private Long totalPurchases;
    private LocalDate lastPurchaseDate;
}

