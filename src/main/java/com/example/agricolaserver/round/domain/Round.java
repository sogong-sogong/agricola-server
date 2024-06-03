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
    private Integer orderNumber;
    @ManyToOne
    private Room room;
    @Column(length = 30,nullable = false)
    private String title;
    private Boolean open;
    private Integer number; //자원 개수
    @Builder
    public Round(Room roomId, String title, Boolean open){
        this.room = roomId;
        this.title = title;
        this.open = open;
    }
    public void open(){
        this.open = true;
    }
    public void setOrderNumber(Integer number){
        this.orderNumber = number;
    }
    public void setNumber(Integer number){this.number = number;}
}