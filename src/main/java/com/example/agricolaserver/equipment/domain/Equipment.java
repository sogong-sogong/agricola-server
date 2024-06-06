package com.example.agricolaserver.equipment.domain;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class Equipment {
    @Id
    @Column(length = 30, nullable = false)
    String id;

    @ManyToOne
    Member member;

    @ManyToOne
    Room room;

    Integer score;

    @Builder
    public Equipment(String id, Member member, Room room, Integer score) {
        this.id = id;
        this.member = member;
        this.room = room;
        this.score = score;
    }
}