package com.example.agricolaserver.commonstorage.domain;
import com.example.agricolaserver.room.domain.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@RequiredArgsConstructor
@DynamicInsert
@Setter
public class CommonStorage {
    @Id
    private Long id;
    @OneToOne
    @MapsId
    private Room room;
    @ColumnDefault("33") //30 + 5 - 2
    private Integer wood;
    @ColumnDefault("26") //24 + 5 - 3
    private Integer clay;
    @ColumnDefault("21") // 16 + 5
    private Integer stone;
    @ColumnDefault("18") //14 + 5 - 1
    private Integer weed;
    @ColumnDefault("27") //24 + 3
    private Integer grain;
    @ColumnDefault("20") //16 + 4
    private Integer vegetable;
    @ColumnDefault("63") //76-3*3-2 - 2
    private Integer food;
    @ColumnDefault("22") // 낱개 자원 18 + 자원 토큰 4
    private Integer sheep;
    @ColumnDefault("19") //15 + 4
    private Integer pig;
    @ColumnDefault("17") // 13 + 4
    private Integer cow;

    @Builder
    public CommonStorage(Room roomId){
        this.room = roomId;
    }
}