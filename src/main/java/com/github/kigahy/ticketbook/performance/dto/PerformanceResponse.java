package com.github.kigahy.ticketbook.performance.dto;
import com.github.kigahy.ticketbook.performance.entity.Performance;
import com.github.kigahy.ticketbook.performance.entity.Status;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PerformanceResponse {

    private Long performanceId;
    private String title;
    private LocalDateTime reserveTime;
    private LocalDateTime startTime;
    private Status Status;

    // 유지보수성을 위한 from
    // 서비스로직에서 람다식 말고 간단히 사용 가능
    public static PerformanceResponse from (Performance performance) {
        return new PerformanceResponse(
            performance.getId(),
            performance.getTitle(),
            performance.getReserveTime(),
            performance.getStartTime(),
            performance.getStatus()

        );

    }
}
