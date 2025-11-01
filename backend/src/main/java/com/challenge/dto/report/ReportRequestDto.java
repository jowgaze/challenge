package com.challenge.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportRequestDto {
    private Long storeId;
    private Long channelId;
    private LocalDateTime start;
    private LocalDateTime end;
}
