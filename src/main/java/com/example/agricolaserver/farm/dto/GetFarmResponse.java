package com.example.agricolaserver.farm.dto;

import com.example.agricolaserver.farm.domain.Farm;
import lombok.Getter;

@Getter
public class GetFarmResponse {
    private Long id;
    private Integer type;
    private Integer xy;
    private Integer crop;

    public static GetFarmResponse from(Farm farm) {
        GetFarmResponse response = new GetFarmResponse();
        response.id = farm.getId();
        response.type = farm.getType();
        response.xy = farm.getXy();
        response.crop = farm.getCrop();
        return response;
    }
}