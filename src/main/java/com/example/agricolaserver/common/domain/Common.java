package com.example.agricolaserver.common.domain;
import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@RequiredArgsConstructor
@DynamicInsert
public class Common {
    @Id
    private Long id;
    @OneToOne
    @MapsId
    private Room room;
    @ColumnDefault("35") //30 + 5
    private Integer wood;
    @ColumnDefault("29") //24 + 5
    private Integer clay;
    @ColumnDefault("21") // 16 + 5
    private Integer stone;
    @ColumnDefault("19") //14 + 5
    private Integer weed;
    @ColumnDefault("27") //24 + 3
    private Integer grain;
    @ColumnDefault("20") //16 + 4
    private Integer vegetable;
    @ColumnDefault("65") //76-3*3-2
    private Integer food;
    @ColumnDefault("22") // 낱개 자원 18 + 자원 토큰 4
    private Integer sheep;
    @ColumnDefault("19") //15 + 4
    private Integer pig;
    @ColumnDefault("17") // 13 + 4
    private Integer cow;

    @Builder
    public Common(Room roomId){
        this.room = roomId;

    }
}