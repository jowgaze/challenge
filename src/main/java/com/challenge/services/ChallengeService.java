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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final SaleRepository repository;

    @Transactional(readOnly = true)
    public List<BalanceResponseDto> getBalance(BalanceRequestDto request) {
        LocalDateTime start = request.start();
        LocalDateTime end = request.end();

        BigDecimal presencialAmount = repository.findTotalSales("P", start, end);
        BigDecimal deliveryAmount = repository.findTotalSales("D", start, end);
        BigDecimal totalAmount = deliveryAmount.add(presencialAmount);

        return Stream.of(
          new BalanceResponseDto("PRESENCIAL", presencialAmount),
          new BalanceResponseDto("DELIVERY", deliveryAmount),
          new BalanceResponseDto("TOTAL", totalAmount)
        ).toList();
    }
}
