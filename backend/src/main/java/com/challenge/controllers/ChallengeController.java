package com.challenge.controllers;

import com.challenge.dto.IntervalDto;
import com.challenge.dto.TicketDto;
import com.challenge.dto.balance.BalanceResponseDto;
import com.challenge.dto.channel.ChannelResponseDto;
import com.challenge.dto.customer.InactiveCustomerDto;
import com.challenge.dto.product.TopProductResponseDto;
import com.challenge.dto.store.StoreResponseDto;
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

    @PostMapping("/balance/{storeId}")
    public ResponseEntity<List<BalanceResponseDto>> getBalance(@PathVariable("storeId") Long storeId,
                                                               @RequestBody IntervalDto request) {
        return ResponseEntity.ok(service.getBalance(storeId, request));
    }

    @PostMapping("/top-products/{storeId}/{channelId}")
    public ResponseEntity<List<TopProductResponseDto>> getTopProducts(@PathVariable("storeId") Long storeId,
                                                                      @PathVariable("channelId") Long channelId,
                                                                      @RequestBody IntervalDto interval){
        return ResponseEntity.ok(service.getTopProducts(storeId, channelId, interval));
    }

    @PostMapping("/ticket/{storeId}/{channelId}")
    public ResponseEntity<List<TicketDto>> getTicket(@PathVariable("storeId") Long storeId,
                                                     @PathVariable("channelId") Long channelId,
                                                     @RequestBody IntervalDto interval){
        return ResponseEntity.ok(service.getTicket(storeId, channelId, interval));
    }

    @GetMapping("/inactive-customers/{storeId}/{channelId}")
    public ResponseEntity<List<InactiveCustomerDto>> getInactiveCustomers(@PathVariable("storeId") Long storeId,
                                                                          @PathVariable("channelId") Long channelId){
        return ResponseEntity.ok(service.getInactiveCustomers(storeId, channelId));
    }

    @GetMapping("/stores")
    public ResponseEntity<List<StoreResponseDto>> getStores(){
        return ResponseEntity.ok(service.getStores());
    }

    @GetMapping("/channels")
    public ResponseEntity<List<ChannelResponseDto>> getChannels(){
        return ResponseEntity.ok(service.getChannels());
    }
}
