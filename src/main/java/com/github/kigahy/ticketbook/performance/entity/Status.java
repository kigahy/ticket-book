package com.github.kigahy.ticketbook.performance.entity;

public enum Status {
    UPCOMING, // 티켓 오픈 전
    AVAILABLE, // 예매 시작
    SOLD_OUT, // 잔여 좌석 없음 -> 매진
    CLOSED // 시간 경과 -> 예매 종료
}
