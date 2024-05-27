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
        this.field = (field != null) ? field : -1;
        this.cage = (cage != null) ? cage : -1;
        this.grain = (grain != null) ? grain : -1;
        this.vegetable = (vegetable != null) ? vegetable : -1;
        this.sheep = (sheep != null) ? sheep : -1;
        this.pig = (pig != null) ? pig : -1;
        this.cow = (cow != null) ? cow : -1;
        this.blank = (blank != null) ? blank : -13;
        this.fencedCowshed = (fencedCowshed != null) ? fencedCowshed : 0;
        this.mudHouse = (mudHouse != null) ? mudHouse : 0;
        this.stoneHouse = (stoneHouse != null) ? stoneHouse : 0;
        this.family = (family != null) ? family : 6;
        this.begging = (begging != null) ? begging : 0;
        this.card = (card != null) ? card : 0;
        this.extra = (extra != null) ? extra : 0;
        this.score = (score != null) ? score : calculateTotalScore();
    }
    
    // 모든 점수의 총합을 계산하는 메서드
    private Integer calculateTotalScore() {
        return this.field + this.cage + this.grain + this.vegetable + this.sheep + this.pig + this.cow + this.blank + this.fencedCowshed + this.mudHouse + this.stoneHouse + this.family + this.begging + this.card + this.extra;
    }
    
}
