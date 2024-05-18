package com.example.agricolaserver.storage.dto;

import com.example.agricolaserver.storage.domain.Storage;

public record GetStorageDTO(Integer wood, Integer clay, Integer stone,
                            Integer weed, Integer grain, Integer vegetable, Integer food,
                            Integer sheep, Integer pig, Integer cow, Integer family,
                            Integer fence, Integer cowshed) {
    public static GetStorageDTO makeStorageDTO(Storage storage) {
        return new GetStorageDTO(storage.getWood(), storage.getClay(),
                storage.getStone(), storage.getWeed(), storage.getGrain(),
                storage.getVegetable(), storage.getFood(), storage.getSheep(),
                storage.getPig(), storage.getCow(), storage.getFamily(),
                storage.getFence(), storage.getCowshed());
    }
}

