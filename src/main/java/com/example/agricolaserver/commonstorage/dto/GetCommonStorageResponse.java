package com.example.agricolaserver.commonstorage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCommonStorageResponse {
    private Long id;
    private Integer wood;
    private Integer clay;
    private Integer stone;
    private Integer weed;
    private Integer grain;
    private Integer vegetable;
    private Integer food;
    private Integer sheep;
    private Integer pig;
    private Integer cow;
}

