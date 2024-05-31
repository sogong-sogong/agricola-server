package com.example.agricolaserver.farm.dto;

import com.example.agricolaserver.farm.domain.Farm;


public record FarmDTO(Integer type, Integer xy, Integer crop) {
    public static FarmDTO makeFarmDTO(Farm farm) {
        return new FarmDTO(farm.getType(), farm.getXy(), farm.getCrop());
    }
}
