package com.example.agricolaserver.family.domain;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Room room;
    private Boolean status; //신생아면 false
    private Integer xy;
    @Builder
    public Family(Member member,Room room,Boolean status,Integer xy){
        this.member = member;
        this.room =room;
        this.status = status;
        this.xy = xy;
    }
    public void updatePosition(Integer xy){
        this.xy = xy;
    }
}