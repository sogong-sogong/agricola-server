package com.example.agricolaserver.auxiliaryequipment.domain;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class AuxiliaryEquipment {
    @Id
    @Column(length = 30,nullable = false)
    String id;
    @ManyToOne
    Member member;
    @ManyToOne
    Room room;
    Integer score;
    Boolean open;

    @Builder
    public AuxiliaryEquipment(String id,Member member, Room room){
        this.id = id;
        this.member = member;
        this.room = room;
    }
}