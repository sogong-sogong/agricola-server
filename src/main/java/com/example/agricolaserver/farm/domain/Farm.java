package com.example.agricolaserver.farm.domain;

import com.example.agricolaserver.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    @Column(length = 10,nullable = false)
    private Integer type; // 빈밭 = 0, 곡식밭 = 1, 채소밭 = 2
    private Integer xy;
    private Integer crop; // 작물 개수
    @Builder
    public Farm(Member member, Integer type,Integer xy,Integer crop){
        this.member = member;
        this.type = type;
        this.xy = xy;
        this.crop = crop;
    }
}