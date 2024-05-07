package com.example.agricolaserver.job.domain;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Getter
public class Job {
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
    public Job(String id,Member member, Room room,Integer score,Boolean open){
        this.id = id;
        this.member = member;
        this.room = room;
        this.score = score;
        this.open = open;
    }
}