package com.example.agricolaserver.room.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@RequiredArgsConstructor
@DynamicInsert
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer round;
    private Integer starter; //선플레이어의 number
    private Integer turn;
    @ColumnDefault("0")
    private Integer number; //멤버 수
    @Builder
    public Room(Long id,Integer starter, Integer number){
        this.id = id;
        this.starter = starter;
        this.number = number;
    }
}