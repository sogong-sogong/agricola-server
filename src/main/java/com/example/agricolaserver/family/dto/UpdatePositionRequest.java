package com.example.agricolaserver.family.dto;

import java.util.List;

public record UpdatePositionRequest(Long id,Integer xy) {
}
/*
[
    {"id": 2, "xy": 1},
    {"id": 5, "xy": 2}
]
*/