package com.github.kigahy.ticketbook.performance.service;

import com.github.kigahy.ticketbook.performance.dto.PerformanceResponse;

import java.util.List;

public interface PerformanceService {

    List<PerformanceResponse> getAllPerformances(); // 전체 공연 조회
    List<PerformanceResponse> getBookablePerformances(); // 예매중인 공연 조회
}
