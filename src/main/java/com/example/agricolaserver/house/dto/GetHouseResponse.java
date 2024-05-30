package com.example.agricolaserver.house.dto;

import com.example.agricolaserver.house.domain.House;
import lombok.Getter;

@Getter
public class GetHouseResponse {
    private Long id;
    private String type;
    private Integer xy;
    private Integer stock_type;

    public static GetHouseResponse from(House house) {
        GetHouseResponse response = new GetHouseResponse();
        response.id = house.getId();
        response.type = house.getType();
        response.xy = house.getXy();
        response.stock_type = house.getStock_type();
        return response;
    }
}



