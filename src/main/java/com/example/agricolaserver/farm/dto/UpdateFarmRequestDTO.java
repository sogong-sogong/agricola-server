package com.example.agricolaserver.farm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFarmRequestDTO {
    private Integer type;
    private Integer xy;
    private Integer crop;
}



