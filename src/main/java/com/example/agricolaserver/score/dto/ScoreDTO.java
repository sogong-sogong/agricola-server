package com.example.agricolaserver.score.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ScoreDTO {
    private Long memberId;
    private Integer score;
    private Integer field;
    private Integer cage;
    private Integer grain;
    private Integer vegetable;
    private Integer sheep;
    private Integer pig;
    private Integer cow;
    private Integer blank;
    private Integer fencedCowshed;
    private Integer mudHouse;
    private Integer stoneHouse;
    private Integer family;
    private Integer begging;
    private Integer card;
    private Integer extra;

    public ScoreDTO(Long memberId, Integer score, Integer field, Integer cage, Integer grain, Integer vegetable, Integer sheep, Integer pig, Integer cow, Integer blank, Integer fencedCowshed, Integer mudHouse, Integer stoneHouse, Integer family, Integer begging, Integer card, Integer extra) {
        this.memberId = memberId;
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