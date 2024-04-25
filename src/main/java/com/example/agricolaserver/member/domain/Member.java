package com.example.agricolaserver.member.domain;

import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Room roomId;
    private Integer number;
    @Builder
    public Member(Room roomId, Integer number){
        this.roomId = roomId;
        this.number = number;
    }
}