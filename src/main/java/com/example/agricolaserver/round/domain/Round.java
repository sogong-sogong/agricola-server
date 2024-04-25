package com.example.agricolaserver.round.domain;

import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderNumber;
    @ManyToOne
    private Room room;
    @Column(length = 30,nullable = false)
    private String title;

    @Builder
    public Round(Room roomId, String title){
        this.room = roomId;
        this.title = title;
    }
}
