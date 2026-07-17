package com.github.kigahy.ticketbook.performance.repository;

import com.github.kigahy.ticketbook.performance.entity.Performance;
import com.github.kigahy.ticketbook.performance.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    // 예매 가능 상태인 공연만 날짜 순으로 고속 조회
    // 너무 길어서 필요 시 줄임
    List<Performance> findByStatusOrderByReservationTimeAsc(Status status);

    // 필요 시 복합 인덱스 처리 추가

}
