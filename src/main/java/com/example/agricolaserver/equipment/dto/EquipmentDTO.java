package com.example.agricolaserver.equipment.dto;

public class EquipmentDTO {
    private final Long memberId;
    private final Long roomId;
    private final Integer equipmentId;
    private final Integer score;

    public EquipmentDTO(Long memberId, Long roomId, int equipmentId, int score) {
        this.memberId = memberId;
        this.roomId = roomId;
        this.equipmentId = equipmentId;
        this.score = score;
    }

    public EquipmentDTO(int equipmentId, int score) {
        this.memberId = null; // 임시 값 설정
        this.roomId = null;   // 임시 값 설정
        this.equipmentId = equipmentId;
        this.score = score;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public Integer getScore() {
        return score;
    }
}