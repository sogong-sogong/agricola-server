package com.example.agricolaserver.member.domain;

import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Room room;
    private Integer number;
    @ColumnDefault("0")
    private Integer openAuxiliary;
    @ColumnDefault("7")
    private Integer hiddenAuxiliary;
    private Integer equipment;
    @ColumnDefault("0")
    private Integer openJob;
    @Builder
    public Member(Room roomId, Integer number){
        this.room = roomId;
        this.number = number;
    }
}