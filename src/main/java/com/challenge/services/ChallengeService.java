package com.challenge.services;

import com.challenge.dto.BalanceRequestDto;
import com.challenge.dto.BalanceResponseDto;
import com.challenge.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final SaleRepository repository;

    @Transactional(readOnly = true)
    public List<BalanceResponseDto> getBalance(BalanceRequestDto request) {
        BalanceResponseDto presencial = getBalanceByType(request, "P");
        BalanceResponseDto delivery = getBalanceByType(request, "D");
        BalanceResponseDto total = getTotalBalance(presencial.amount(), delivery.amount());

        return Stream.of(presencial, delivery, total).toList();
    }

    private BalanceResponseDto getBalanceByType(BalanceRequestDto request, String type) {
        LocalDateTime start = request.start();
        LocalDateTime end = request.end();
        String typeName = getTypeName(type);
        BigDecimal amount = repository.findTotalSales(type, start, end);

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
