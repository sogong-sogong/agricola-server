package com.example.agricolaserver.auxiliaryequipment.dto;

public class AuxiliaryEquipmentDTO {
    private final Long memberId;
    private final Long roomId;
    private final Integer auxId;
    private final Integer score;
    private final Integer open;

    public AuxiliaryEquipmentDTO(Long memberId, Long roomId, int auxId, Integer score, int open) {
        this.memberId = memberId;
        this.roomId = roomId;
        this.auxId = auxId;
        this.score = score;
        this.open = open;
    }

    public AuxiliaryEquipmentDTO(int auxId, int score, int open) {
        this.memberId = null; // 임시 값 설정
        this.roomId = null;   // 임시 값 설정
        this.auxId = auxId;
        this.score = score;
        this.open = open;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Integer getAuxId() {
        return auxId;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getOpen() {
        return open;
    }
}