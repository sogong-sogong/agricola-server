package com.example.agricolaserver.house.domain;

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
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    @Column(length = 10,nullable = false)
    private String type; //wood mud stone 중 하나
    private Integer xy;
    private Integer stock_type; //양=0, 돼지=1, 소=2
    @Builder
    public House(Member member, String type,Integer xy,Integer stock_type){
        this.member = member;
        this.type = type;
        this.xy = xy;
        this.stock_type = stock_type;
    }
}