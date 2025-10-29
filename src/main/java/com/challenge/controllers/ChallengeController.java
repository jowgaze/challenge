package com.challenge.controllers;

import com.challenge.dto.BalanceRequestDto;
import com.challenge.dto.BalanceResponseDto;
import com.challenge.services.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/challenge/api/")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService service;

    @PostMapping("/balance")
    public ResponseEntity<List<BalanceResponseDto>> getBalance(@RequestBody BalanceRequestDto request) {
        return ResponseEntity.ok(service.getBalance(request));
    }
}
