package com.example.agricolaserver.cage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCageRequestDTO {
    private Long id;
    private Integer type;
    private Integer stock;
    private Integer xy;
    private Integer stock_cnt;
}



