package com.github.kigahy.ticketbook.performance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import com.github.kigahy.ticketbook.performance.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "performance", indexes = {
        @Index(name = "idx_reserve_time", columnList = "reserve_time")
})

public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long id;

    @Column(name = "title")
    private String title;

    // 예매 시작일
    @Column(name = "reserve_time")
    private LocalDateTime reserveTime;

    // 공연 시작일
    @Column(name = "start_time")
    private LocalDateTime startTime;

//    // 예매상태. 직접 DB에 넣는 게 아니니 생성자에서는 제외함
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private Status status;

    // 가능한 좌석수
    @Column(name = "total_seats")
    private int totalSeats;

    // 남은 좌석수
    @Column(name = "remain_seats")
    private int remainSeats;

    public Performance(String title, LocalDateTime reserveTime, LocalDateTime startTime, int totalSeats, int remainSeats) {
        this.title = title;
        this.reserveTime = reserveTime;
        this.startTime = startTime;
        this.totalSeats = totalSeats;
        this.remainSeats = remainSeats;
    }

    // 시간에 따라 예매상태 자동 업데이트하는 메서드
    public Status getStatus() {
        LocalDateTime now = LocalDateTime.now();

        // 예매 시작 전
        if (now.isBefore(this.reserveTime)) {
            return Status.UPCOMING;
        }

        // 공연 시작 시간을 지났거나 남은 좌석이 없음
        if (now.isAfter(this.startTime) || this.remainSeats <= 0) {
            return Status.SOLD_OUT;
        }

        // 예매 기간 중이고 좌석도 남아있다면 판매중
        return Status.AVAILABLE;
    }

}
