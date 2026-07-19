package com.github.kigahy.ticketbook.performance.service;

import com.github.kigahy.ticketbook.performance.dto.PerformanceResponse;
import com.github.kigahy.ticketbook.performance.entity.Performance;
import com.github.kigahy.ticketbook.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PerformanceResponse> getAllPerformances(){

        // 전체를 조회하는 것이니 new아닌 map 사용
        return performanceRepository.findAll()
        .stream()
        .map(PerformanceResponse::from)
        .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PerformanceResponse> getBookablePerformances() {
        return null; //추후 구현
    }
}
