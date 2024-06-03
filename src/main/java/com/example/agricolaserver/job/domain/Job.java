package com.example.agricolaserver.job.domain;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class Job {
    @Id
    @Column(length = 30, nullable = false)
    String id;

    @ManyToOne
    Member member;

    @ManyToOne
    Room room;

    Boolean open;

    @Builder
    public Job(String id, Member member, Room room, Boolean open) {
        this.id = id;
        this.member = member;
        this.room = room;
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }
}
