package com.challenge.services;

import com.challenge.dto.IntervalDto;
import com.challenge.dto.balance.BalanceResponseDto;
import com.challenge.dto.customer.InactiveCustomerDto;
import com.challenge.dto.product.TopProductResponseDto;
import com.challenge.repositories.CustomerRepository;
import com.challenge.repositories.ProductSaleRepository;
import com.challenge.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final SaleRepository saleRepository;
    private final ProductSaleRepository productSaleRepository;
    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<InactiveCustomerDto> getInactiveCustomers(){
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return customerRepository.findInactiveCustomers(thirtyDaysAgo)
                .stream()
                .map( obj -> new InactiveCustomerDto(
                        ((Number) obj[0]).longValue(),
                        obj[1].toString(),
                        ((Number) obj[2]).longValue(),
                        LocalDate.parse(obj[3].toString().substring(0, 10))
                        )
                ).toList();
    }

    @Transactional(readOnly = true)
    public List<BalanceResponseDto> getBalance(IntervalDto request) {
        BalanceResponseDto presencial = getBalanceByType(request, "P");
        BalanceResponseDto delivery = getBalanceByType(request, "D");
        BalanceResponseDto total = getTotalBalance(presencial.amount(), delivery.amount());

        return Stream.of(presencial, delivery, total).toList();
    }

    @Transactional(readOnly = true)
    public List<TopProductResponseDto> getTopProducts(Long channelId, IntervalDto interval){
        if (channelId == 0)
            channelId = null;

        return productSaleRepository.findTopProduct(channelId, interval.start(), interval.end())
                .stream()
                .map(obj -> new TopProductResponseDto(
                        ((Number) obj[3]).longValue(),
                        (String) obj[0],
                        ((Number) obj[1]).longValue(),
                        obj[2] != null ? ((Number) obj[2]).doubleValue() : 0.0
                ))
                .toList();
    }

    private BalanceResponseDto getBalanceByType(IntervalDto request, String type) {
        LocalDateTime start = request.start();
        LocalDateTime end = request.end();
        String typeName = getTypeName(type);
        BigDecimal amount = saleRepository.findTotalSales(type, start, end);

        return new BalanceResponseDto(
                typeName,
                Objects.requireNonNullElse(amount, BigDecimal.ZERO)
        );
    }

    private BalanceResponseDto getTotalBalance(BigDecimal presencialAmount, BigDecimal deliveryAmount) {
        BigDecimal totalAmount = presencialAmount.add(deliveryAmount);
        return new BalanceResponseDto("TOTAL", totalAmount);
    }

    private String getTypeName(String type) {
        if (type.equals("D")) return "DELIVERY";
        return "PRESENCIAL";
    }
}
