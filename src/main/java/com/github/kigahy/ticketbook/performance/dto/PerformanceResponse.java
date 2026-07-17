package com.github.kigahy.ticketbook.performance.dto;
import com.github.kigahy.ticketbook.performance.entity.Status;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PerformanceResponse {

    private Long performanceId;
    private LocalDateTime reservationTime;
    private LocalDateTime startTime;
    private Status status;
}
