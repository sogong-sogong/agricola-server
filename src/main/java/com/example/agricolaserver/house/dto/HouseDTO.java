package com.example.agricolaserver.house.dto;

import com.example.agricolaserver.house.domain.House;


public record HouseDTO(Long id, String type, Integer xy, Integer stock_type) {
    public static HouseDTO makeHouseDTO(House house) {
        return new HouseDTO(house.getId(), house.getType(), house.getXy(), house.getStock_type());
    }
}
