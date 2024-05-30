package com.example.agricolaserver.commonstorage.dto;

import com.example.agricolaserver.room.domain.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommonStorageRequestDTO {
    private Room roomId;
    private Integer wood;
    private Integer clay;
    private Integer stone;
    private Integer weed;
    private Integer grain;
    private Integer vegetable;
    private Integer food;
    private Integer sheep;
    private Integer pig;
    private Integer cow;
}
