package com.example.agricolaserver.house.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateHouseRequestDTO {
    private String type;
    private Integer xy;
    private Integer stock_type;
}



