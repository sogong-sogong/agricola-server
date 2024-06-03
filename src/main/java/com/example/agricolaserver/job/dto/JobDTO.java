package com.example.agricolaserver.job.dto;

public class JobDTO {
    private final Long memberId;
    private final Long roomId;
    private final Integer jobId;
    private final Integer open;

    public JobDTO(Long memberId, Long roomId, int jobId, int open) {
        this.memberId = memberId;
        this.roomId = roomId;
        this.jobId = jobId;
        this.open = open;
    }

    public JobDTO(int jobId, int open) {
        this.memberId = null; // 임시 값 설정
        this.roomId = null;   // 임시 값 설정
        this.jobId = jobId;
        this.open = open;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public Integer getOpen() {
        return open;
    }
}