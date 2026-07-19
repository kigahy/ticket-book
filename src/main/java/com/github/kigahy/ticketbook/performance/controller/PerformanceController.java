package com.github.kigahy.ticketbook.performance.controller;


import com.github.kigahy.ticketbook.performance.dto.PerformanceResponse;
import com.github.kigahy.ticketbook.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceService performanceService;
//    private final PerformanceResponse performanceResponse;

    @GetMapping("/shows")
    public ResponseEntity<List<PerformanceResponse>> getAllPerformances() {
        return ResponseEntity.ok(performanceService.getAllPerformances());
    }

}
