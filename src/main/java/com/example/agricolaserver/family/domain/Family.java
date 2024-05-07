package com.example.agricolaserver.family.domain;

import com.example.agricolaserver.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@RequiredArgsConstructor
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    private Member member;
    private Boolean status; //신생아면 false
    private Integer xy;
    @Builder
    public Family(Member member,Boolean status,Integer xy){
        this.member =member;
        this.status = status;
        this.xy = xy;
    }
}