package com.example.agricolaserver.score.domain;

import com.example.agricolaserver.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

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
}
