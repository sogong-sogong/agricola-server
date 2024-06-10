package com.example.agricolaserver.storage.domain;

import com.example.agricolaserver.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@DynamicInsert
public class Storage {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Member member;
    @ColumnDefault("0")
    private Integer wood;
    @ColumnDefault("0")
    private Integer clay;
    @ColumnDefault("0")
    private Integer stone;
    @ColumnDefault("0")
    private Integer weed;
    @ColumnDefault("0")
    private Integer grain;
    @ColumnDefault("0")
    private Integer vegetable;
    @ColumnDefault("0")
    private Integer food;
    @ColumnDefault("0")
    private Integer sheep;
    @ColumnDefault("0")
    private Integer pig;
    @ColumnDefault("0")
    private Integer cow;
    @ColumnDefault("2")
    private Integer family;
    @ColumnDefault("15")
    private Integer fence;
    @ColumnDefault("4")
    private Integer cowshed;
    @Builder
    public Storage(Member memberId,Integer food){
        this.member = memberId;
        this.food = food;
    }

    public void update(Integer wood, Integer clay, Integer stone, Integer weed, Integer grain, Integer vegetable, Integer food,
                              Integer sheep, Integer pig, Integer cow, Integer family, Integer fence,Integer cowshed){
        this.wood = wood;
        this.clay = clay;
        this.stone = stone;
        this.weed = weed;
        this.grain = grain;
        this.vegetable = vegetable;
        this.food = food;
        this.sheep = sheep;
        this.pig = pig;
        this.cow = cow;
        this.family = family;
        this.fence = fence;
        this.cowshed = cowshed;
    }
}
