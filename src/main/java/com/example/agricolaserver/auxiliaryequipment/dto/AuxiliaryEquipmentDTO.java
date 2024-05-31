package com.example.agricolaserver.auxiliaryequipment.dto;

public class AuxiliaryEquipmentDTO {
    private Long memberId;
    private Long roomId;
    private Integer auxId;
    private Integer score;
    private Integer open;

    public AuxiliaryEquipmentDTO(Long memberId, Long roomId, Integer auxId, Integer score, Integer open) {
        this.memberId = memberId;
        this.roomId = roomId;
        this.auxId = auxId;
        this.score = score;
        this.open = open;
    }

    // Getter 및 Setter 메서드
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getAuxId() {
        return auxId;
    }

    public void setAuxId(Integer auxId) {
        this.auxId = auxId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }
}

