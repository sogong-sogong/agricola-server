package com.example.agricolaserver.boardresources.domain;

import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class BoardResources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private Room room;

    private Integer xy;

    private Integer number;

    @Builder
    public BoardResources(Long id,Room room,Integer xy,Integer number){
        this.id = id;
        this.room = room;
        this.xy = xy;
        this.number = number;
    }
}