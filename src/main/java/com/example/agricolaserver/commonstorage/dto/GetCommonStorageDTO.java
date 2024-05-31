package com.example.agricolaserver.commonstorage.dto;

import com.example.agricolaserver.commonstorage.domain.CommonStorage;

public record GetCommonStorageDTO (Integer wood, Integer clay, Integer stone,
                                  Integer weed, Integer grain, Integer vegetable, Integer food,
                                  Integer sheep, Integer pig, Integer cow){
    public static GetCommonStorageDTO makeCommonStorageDTO(CommonStorage commonstorage) {
        return new GetCommonStorageDTO(commonstorage.getWood(), commonstorage.getClay(),
                commonstorage.getStone(), commonstorage.getWeed(), commonstorage.getGrain(),
                commonstorage.getVegetable(), commonstorage.getFood(), commonstorage.getSheep(),
                commonstorage.getPig(), commonstorage.getCow());
    }
}
