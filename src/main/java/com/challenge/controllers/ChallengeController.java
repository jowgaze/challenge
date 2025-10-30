package com.challenge.controllers;

import com.challenge.dto.IntervalDto;
import com.challenge.dto.balance.BalanceResponseDto;
import com.challenge.dto.customer.InactiveCustomerDto;
import com.challenge.dto.product.TopProductResponseDto;
import com.challenge.services.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge/api/")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService service;

    @PostMapping("/balance")
    public ResponseEntity<List<BalanceResponseDto>> getBalance(@RequestBody IntervalDto request) {
        return ResponseEntity.ok(service.getBalance(request));
    }

    @PostMapping("/top-products/{channelId}")
    public ResponseEntity<List<TopProductResponseDto>> getTopProducts(@PathVariable("channelId") Long channelId, @RequestBody IntervalDto interval){
        return ResponseEntity.ok(service.getTopProducts(channelId, interval));
    }

    @GetMapping("/inactive-customers")
    public ResponseEntity<List<InactiveCustomerDto>> getInactiveCustomers(){
        return ResponseEntity.ok(service.getInactiveCustomers());
    }
}
