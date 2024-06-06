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
    @ColumnDefault("0")
    private Integer score;
    @ColumnDefault("0")
    private Integer farm;
    @ColumnDefault("0")
    private Integer cage;
    @ColumnDefault("0")
    private Integer grain;
    @ColumnDefault("0")
    private Integer vegetable;
    @ColumnDefault("0")
    private Integer sheep;
    @ColumnDefault("0")
    private Integer pig;
    @ColumnDefault("0")
    private Integer cow;
    @ColumnDefault("0")
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
    public Score(Member member, Integer score, Integer farm, Integer cage, Integer grain, Integer vegetable, Integer sheep, Integer pig, Integer cow, Integer blank, Integer fencedCowshed, Integer mudHouse, Integer stoneHouse, Integer family, Integer begging, Integer card, Integer extra){
        this.member = member;
        this.farm = farm;
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

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    public void setCage(Integer cage) {
        this.cage = cage;
    }

    public void setGrain(Integer grain) {
        this.grain = grain;
    }

    public void setVegetable(Integer vegetable) {
        this.vegetable = vegetable;
    }

    public void setSheep(Integer sheep) {
        this.sheep = sheep;
    }

    public void setPig(Integer pig) {
        this.pig = pig;
    }

    public void setCow(Integer cow) {
        this.cow = cow;
    }

    public void setBlank(Integer blank) {
        this.blank = blank;
    }

    public void setFencedCowshed(Integer fencedCowshed) {
        this.fencedCowshed = fencedCowshed;
    }

    public void setMudHouse(Integer mudHouse) {
        this.mudHouse = mudHouse;
    }

    public void setStoneHouse(Integer stoneHouse) {
        this.stoneHouse = stoneHouse;
    }

    public void setFamily(Integer family) {
        this.family = family;
    }

    public void setBegging(Integer begging) {
        this.begging = begging;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }
}