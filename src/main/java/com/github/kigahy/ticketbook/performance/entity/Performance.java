package com.github.kigahy.ticketbook.performance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "performance_table", indexes = {
        @Index(name = "idx_reservation_time", columnList = "reservation_time")
})

public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long id;

    @Column(name = "title")
    private String title;

    // 예매 시작일
    @Column(name = "reservation_time")
    private LocalDateTime reserveTime;

    // 공연 시작일
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Performance(String title, LocalDateTime reservationTime, LocalDateTime startTime, Status status) {
        this.title = title;
        this.reserveTime = reservationTime;
        this.startTime = startTime;
        this.status = status;
    }

}
