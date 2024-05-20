package com.example.agricolaserver.member.domain;

import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@DynamicInsert
@Builder
@AllArgsConstructor
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

    @ColumnDefault("0")
    private Integer hiddenJob;

    public int getTotalScore() {
        return Score.calculateTotalScore(this);
    }
}
