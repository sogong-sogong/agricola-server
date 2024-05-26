package com.example.agricolaserver.family.dto;
import java.util.List;

public record GetPositionResponse(Long memberId, List<family> family) {
    public static record family(Long id,Integer xy){
    }
}