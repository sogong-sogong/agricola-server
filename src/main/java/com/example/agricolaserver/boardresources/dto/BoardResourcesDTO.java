package com.example.agricolaserver.boardresources.dto;

import com.example.agricolaserver.boardresources.domain.BoardResources;

public record BoardResourcesDTO(Integer xy, Integer number) {
    public static BoardResourcesDTO makeBoardResourcesDTO(BoardResources boardResources){
        return new BoardResourcesDTO(boardResources.getXy(),boardResources.getNumber());
    }
}