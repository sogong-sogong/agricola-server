package com.example.agricolaserver.cage.dto;

import com.example.agricolaserver.cage.domain.Cage;

public record CageDTO(Long cageId, Integer type, Integer stock, Integer xy, Integer stock_cnt, Integer field) {
    public static CageDTO makeCageDTO(Cage cage) {
        return new CageDTO(cage.getId(), cage.getType(), cage.getStock(), cage.getXy(),cage.getStock_cnt(), cage.getField());
    }
}