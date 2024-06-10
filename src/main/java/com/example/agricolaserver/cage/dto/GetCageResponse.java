package com.example.agricolaserver.cage.dto;

import com.example.agricolaserver.cage.domain.Cage;
import lombok.Getter;

@Getter
public class GetCageResponse {
    private Long id;
    private Integer type;
    private Integer stock;
    private Integer xy;
    private Integer stock_cnt;

    public static GetCageResponse from(Cage cage) {
        GetCageResponse response = new GetCageResponse();
        response.id = cage.getId();
        response.type = cage.getType();
        response.stock = cage.getStock();
        response.xy = cage.getXy();
        response.stock_cnt = cage.getStock_cnt();
        return response;
    }
}