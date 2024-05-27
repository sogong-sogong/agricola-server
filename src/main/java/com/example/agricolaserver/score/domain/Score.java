package com.example.agricolaserver.score.domain;

import com.example.agricolaserver.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@RequiredArgsConstructor
@DynamicInsert
public class Score {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Member member;
    @ColumnDefault("-14")
    private Integer score;
    @ColumnDefault("-1")
    private Integer field;
    @ColumnDefault("-1")
    private Integer cage;
    @ColumnDefault("-1")
    private Integer grain;
    @ColumnDefault("-1")
    private Integer vegetable;
    @ColumnDefault("-1")
    private Integer sheep;
    @ColumnDefault("-1")
    private Integer pig;
    @ColumnDefault("-1")
    private Integer cow;
    @ColumnDefault("-13")
    private Integer blank;
    @ColumnDefault("0")
    private Integer fencedCowshed;
    @ColumnDefault("0")
    private Integer mudHouse;
    @ColumnDefault("0")
    private Integer stoneHouse;
    @ColumnDefault("6")
    private Integer family;
    @ColumnDefault("0")
    private Integer begging;
    @ColumnDefault("0")
    private Integer card;
    @ColumnDefault("0")
    private Integer extra;
    
    @Builder
    public Score(Member member, Integer score, Integer field, Integer cage, Integer grain, Integer vegetable, Integer sheep, Integer pig, Integer cow, Integer blank, Integer fencedCowshed, Integer mudHouse, Integer stoneHouse, Integer family, Integer begging, Integer card, Integer extra){
        this.member = member;
        this.field = field;
        this.cage = cage;
        this.grain = grain;
        this.vegetable = vegetable;
        this.sheep = sheep;
        this.pig = pig;
        this.cow = cow;
        this.blank = blank;
        this.fencedCowshed = fencedCowshed;
        this.mudHouse = mudHouse;
        this.stoneHouse = stoneHouse;
        this.family = family;
        this.begging = begging;
        this.card = card;
        this.extra = extra;
        this.score = score;
    }
}