package com.example.agricolaserver.cage.domain;

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
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    @Column(length = 10,nullable = false)
    private Integer type; // 우리 = 0, 외양간 = 1, 우리+외양간 = 2
    private Integer stock; // 양 = 0, 돼지 = 1, 소 = 2
    private Integer xy;
    private Integer stock_cnt; // 가축 수
    private Integer field; // 울타리 번호
    @Builder
    public Cage(Member member, Integer type, Integer stock, Integer xy, Integer stock_cnt, Integer field){
        this.member = member;
        this.type = type;
        this.stock = stock;
        this.xy = xy;
        this.stock_cnt = stock_cnt;
        this.field = field;
    }

    public void updatecage(Integer type, Integer stock, Integer xy, Integer stock_cnt, Integer field){
        this.type = type;
        this.stock = stock;
        this.xy = xy;
        this.stock_cnt = stock_cnt;
        this.field = field;
    }
}