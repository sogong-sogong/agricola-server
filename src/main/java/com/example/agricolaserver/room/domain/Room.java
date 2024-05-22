package com.example.agricolaserver.room.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
    public Room(Integer starter){
        this.starter = starter;
    }
    public void addNumber(){
        this.number++;
    }
    public void updateRound(){
        if(this.round==null){
            this.round = 1;
        }
        else{
            this.round++;
        }
    }
}